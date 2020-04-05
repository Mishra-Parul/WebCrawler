package com.project.webCrawler.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author parul.mishra
 * @created 05 / 04 /2020
 */


@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDto {

    private String token;
    private String message;

}
