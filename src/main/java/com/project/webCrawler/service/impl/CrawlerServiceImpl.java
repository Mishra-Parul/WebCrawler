package com.project.webCrawler.service.impl;

import com.google.gson.Gson;
import com.project.webCrawler.adapter.CrawlUrlDetailAdapter;
import com.project.webCrawler.dto.*;
import com.project.webCrawler.enums.Status;
import com.project.webCrawler.exception.NoRecordException;
import com.project.webCrawler.model.CrawlUrlInfo;
import com.project.webCrawler.repository.CrawlUrlInfoRepository;
import com.project.webCrawler.repository.CrawlerStatusRepository;
import com.project.webCrawler.service.CrawlerService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author parul.mishra
 * @created 04 / 04 /2020
 */
@Service("crawlerServiceImpl")
@Log4j2
public class CrawlerServiceImpl  implements CrawlerService {

    @Autowired
    private CrawlUrlInfoRepository crawlUrlInfoRepository;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    private List<String> processedUrls = new ArrayList<>();

    @Override
    public ResponseDto submit(String url, Integer depth) {

        UrlValidator urlValidator = new UrlValidator();
        boolean isValid = urlValidator.isValid(url);

        if(isValid) {

            String token = UUID.randomUUID().toString();
            CrawlUrlInfo crawlUrlInfo = getEntity(url, token);
            crawlUrlInfo = crawlUrlInfoRepository.save(crawlUrlInfo);
            log.info("Saved the request " + crawlUrlInfo);

            try {
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        startProcessing(url , depth);
                    }
                });
            } catch (Exception ex) {
                crawlUrlInfo.setStatus(Status.FAILED);
                log.error(ex);
            }
            return ResponseDto.builder().token(token).message("Request Submitted Successfully").build();
        }else{
            return ResponseDto.builder().token(null).message("Request could not be submitted, url is invalid ").build();
        }
    }

    @Async
    private void startProcessing(String url, Integer depth) {
        CrawlUrlInfo crawlUrlInfo = crawlUrlInfoRepository.findByUrl(url);
        crawlUrlInfo.setStatus(Status.INPROCESS);
        crawlUrlInfoRepository.save(crawlUrlInfo);
        PageTreeInfo pageTreeInfo = deepCrawl(url, depth);
        crawlUrlInfo.setStatus(Status.PROCESSED);
        crawlUrlInfo.setTitle(pageTreeInfo.getTitle());
        crawlUrlInfo.setDetails(converttoJSON(pageTreeInfo.getNodes()));
        crawlUrlInfoRepository.save(crawlUrlInfo);
    }

    private String converttoJSON(List<PageTreeInfo> nodes) {
        Gson gson = new Gson();
        return gson.toJson(nodes);
    }

    private CrawlUrlInfo getEntity(String url, String token) {
        return CrawlUrlInfo.builder().url(url).uuid(token).status(Status.SUBMITTED)
                .createdAt(new Date()).createdBy("user")
                .build();

    }

    @Async
    public PageTreeInfo deepCrawl(final String url, final int depth) {
        log.debug("Starting crawler for url {} for depth {}", url, depth);
        if (depth > 0) {
            final List<String> updatedProcessedUrls = Optional.ofNullable(processedUrls).orElse(new ArrayList<>());
            if (!updatedProcessedUrls.contains(url)) {
                updatedProcessedUrls.add(url);
                final PageTreeInfo pageTreeInfo = PageTreeInfo.builder().url(url).build();
                    crawl(url).ifPresent(pageInfo -> {
                        pageTreeInfo.setTitle(pageInfo.getTitle());
                        pageTreeInfo.setTotal_images(pageInfo.getImageCount());
                        pageTreeInfo.setTotal_links(pageInfo.getLinks().size());
                        log.info("Found {} links on the web page: {}", pageInfo.getLinks().size(), url);
                        pageInfo.getLinks().forEach(link -> {
                            pageTreeInfo.addNodesItem(deepCrawl(link.attr("abs:href"), depth - 1));
                        });
                    });
                return pageTreeInfo;
            }
        }
        return null;
    }

    @Override
    public UrlDetailsResponse getStatus(String url) {
        CrawlUrlInfo crawlUrlInfo = crawlUrlInfoRepository.findByUrl(url);
        if(Objects.isNull(crawlUrlInfo)){
            throw new NoRecordException("No Request for url exists ");
        }
        return CrawlUrlDetailAdapter.toResponseDto(crawlUrlInfo);
    }

    @Override
    public CrawlerUrlReponse getDetails(String url) {
        CrawlUrlInfo crawlUrlInfo = crawlUrlInfoRepository.findByUrl(url);
        if(Objects.isNull(crawlUrlInfo)){
            throw new NoRecordException("No Request for url exists ");
        }
        return CrawlUrlDetailAdapter.toUrlDetailReponse(crawlUrlInfo);
    }

    public Optional<PageInfo> crawl(final String url) {

        log.info("Fetching contents for url: {}", url);
        try {
            final Document doc = Jsoup.connect(url).timeout(20000).followRedirects(false).get();

            final Elements links = doc.select("a[href]");
            final Elements images = doc.getElementsByTag("img");
            final String title = doc.title();
            log.debug("Fetched title: {}, links[{}] for url: {}", title, links.nextAll(), url);
            return Optional.of(new PageInfo(title, url, links, images.size()));
        } catch (final IOException | IllegalArgumentException e) {
            log.error(String.format("Error getting contents of url %s", url), e);
            return Optional.empty();
        }

    }


}
