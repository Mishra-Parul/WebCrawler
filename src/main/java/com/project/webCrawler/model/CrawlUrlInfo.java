package com.project.webCrawler.model;

import com.project.webCrawler.enums.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

/**
 * @author parul.mishra
 * @created 05 / 04 /2020
 */

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "crawl_url_info")
public class CrawlUrlInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid", columnDefinition = "char(40)", unique = true, nullable = false)
    private String uuid;

    @Column(name = "url", columnDefinition = "varchar(255) NOT NULL", unique = true)
    private String url;

    @Column(name = "imageCount", columnDefinition = "int(11) DEFAULT NULL")
    private Integer imageCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar(50) NOT NULL")
    private Status status;

    @Column(name = "title", columnDefinition = "varchar(255) DEFAULT NULL")
    private String title;

    @Column(name = "details", columnDefinition = "text DEFAULT NULL")
    private String details;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    @Column(name = "created_by", columnDefinition = "char(40)")
    protected String createdBy;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;

    @Column(name = "updated_by", columnDefinition = "char(40)")
    protected String updatedBy;

}
