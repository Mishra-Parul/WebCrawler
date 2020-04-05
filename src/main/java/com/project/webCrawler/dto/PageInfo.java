/**
 *
 */
package com.project.webCrawler.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jsoup.select.Elements;

import java.io.Serializable;

/**
 * @author buddy
 *
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
@AllArgsConstructor
public class PageInfo implements Serializable {

    private static final long serialVersionUID = 1993875051659981029L;

    private String title;

    private String url;

    private Elements links;

    private Integer imageCount;
}
