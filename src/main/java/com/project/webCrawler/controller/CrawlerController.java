package com.project.webCrawler.controller;

import com.project.webCrawler.dto.CrawlerUrlReponse;
import com.project.webCrawler.dto.ResponseDto;
import com.project.webCrawler.dto.UrlDetailsResponse;
import com.project.webCrawler.service.CrawlerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author parul.mishra
 * @created 04 / 04 /2020
 */

@Log4j2
@RestController
@RequestMapping("/crawler")
public class CrawlerController {

    @Autowired
    private CrawlerService crawlerService;

    @PostMapping(value = "/submit", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseDto> submitRequest(
            @NotNull @RequestParam(value = "url") final String url,
            @RequestParam(value = "depth") final Integer depth) {

        log.info("Request for crawling received for url: {}, depth: {}", url, depth);
        ResponseDto responseDto  = crawlerService.submit(url, depth);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/getStatus", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UrlDetailsResponse> getstatusforUrl(
            @NotNull @RequestParam(value = "url") final String url) {

        log.info("Request for getting status for crawling request for url:", url);
        UrlDetailsResponse responseDto  = crawlerService.getStatus(url);
        return new ResponseEntity<UrlDetailsResponse>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/getDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CrawlerUrlReponse> getDetails(
            @NotNull @RequestParam(value = "url") final String url) {

        log.info("Request for getting details of crawling request for url:", url);
        CrawlerUrlReponse responseDto  = crawlerService.getDetails(url);
        return new ResponseEntity<CrawlerUrlReponse>(responseDto, HttpStatus.OK);
    }


}
