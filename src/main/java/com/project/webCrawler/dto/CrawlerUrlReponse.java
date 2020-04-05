package com.project.webCrawler.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author parul.mishra
 * @created 05 / 04 /2020
 */

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CrawlerUrlReponse {

    private long total_links;
    private long total_images;
    private List<NestedUrlResponse> details;

}
