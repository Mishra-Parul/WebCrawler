package com.project.webCrawler.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author parul.mishra
 * @created 05 / 04 /2020
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class UrlDetailsResponse {

    private String url;
    private String title;
    private String status;
    private String uuid;

}
