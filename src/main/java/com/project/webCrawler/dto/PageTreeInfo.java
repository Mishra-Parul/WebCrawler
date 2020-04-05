package com.project.webCrawler.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author parul.mishra
 * @created 04 / 04 /2020
 */

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageTreeInfo {

    public String url;

    public String title;

    public Integer total_links;

    public Integer total_images;

    public List<PageTreeInfo> nodes;

    public PageTreeInfo addNodesItem(final PageTreeInfo nodesItem) {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }
        if (nodesItem != null) {
            nodes.add(nodesItem);
        }
        return this;
    }


}
