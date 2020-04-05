package com.project.webCrawler.service;

import com.project.webCrawler.dto.CrawlerUrlReponse;
import com.project.webCrawler.dto.PageTreeInfo;
import com.project.webCrawler.dto.ResponseDto;
import com.project.webCrawler.dto.UrlDetailsResponse;

import java.util.List;

/**
 * @author parul.mishra
 * @created 04 / 04 /2020
 */

public interface CrawlerService {

    ResponseDto submit(String url, Integer depth);

    PageTreeInfo deepCrawl(final String url, final int depth);

    UrlDetailsResponse getStatus(String url);

    CrawlerUrlReponse getDetails(String url);
}
