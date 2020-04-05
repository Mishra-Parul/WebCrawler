package com.project.webCrawler.controller;

import com.project.webCrawler.dto.PageTreeInfo;
import com.project.webCrawler.repository.CrawlerStatusRepository;
import com.project.webCrawler.service.CrawlerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author parul.mishra
 * @created 04 / 04 /2020
 */

@Log4j2
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    CrawlerStatusRepository crawlerStatusRepository;

    @Autowired
    CrawlerService crawlerService;

    @GetMapping("/test")
    public ResponseEntity<String> testAPI(){
        log.info("Getting request to test apis ");
        long i = crawlerStatusRepository.count();
        return new ResponseEntity<>("successful", HttpStatus.OK);
    }

}
