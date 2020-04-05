package com.project.webCrawler.repository;

import com.project.webCrawler.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author parul.mishra
 * @created 04 / 04 /2020
 */

public interface CrawlerStatusRepository extends JpaRepository<Product, Integer> {

}
