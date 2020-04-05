package com.project.webCrawler.adapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.webCrawler.dto.CrawlerUrlReponse;
import com.project.webCrawler.dto.NestedUrlResponse;
import com.project.webCrawler.dto.UrlDetailsResponse;
import com.project.webCrawler.model.CrawlUrlInfo;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author parul.mishra
 * @created 05 / 04 /2020
 */

@UtilityClass
@Log4j2
public class CrawlUrlDetailAdapter {

    public UrlDetailsResponse toResponseDto(CrawlUrlInfo crawlUrlInfo){
        return UrlDetailsResponse.builder().status(crawlUrlInfo.getStatus().toString()).url(crawlUrlInfo.getUrl()).build();
    }


    public static CrawlerUrlReponse toUrlDetailReponse(CrawlUrlInfo crawlUrlInfo) {
        CrawlerUrlReponse crawlerUrlReponse = CrawlerUrlReponse.builder().build();
        String details = crawlUrlInfo.getDetails();

        try {
            Gson gson = new Gson();
            Type type = new TypeToken<List<NestedUrlResponse>>() {
            }.getType();
            List<NestedUrlResponse> nestedUrlResponses = gson.fromJson(details, type);

            long totalImages = nestedUrlResponses.stream().mapToLong(dto -> dto.getTotal_images()).sum();
            crawlerUrlReponse.setTotal_images(totalImages);

            crawlerUrlReponse.setDetails(nestedUrlResponses);
            crawlerUrlReponse.setTotal_links(nestedUrlResponses.size());
        }catch (Exception e){
            log.info(e);
        }
        return crawlerUrlReponse;
    }
}
