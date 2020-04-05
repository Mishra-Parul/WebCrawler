package com.project.webCrawler.repository;

import com.project.webCrawler.model.CrawlUrlInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author parul.mishra
 * @created 05 / 04 /2020
 */

public interface CrawlUrlInfoRepository  extends CrudRepository<CrawlUrlInfo, Integer> {
    CrawlUrlInfo findByUrl(String url);
}
