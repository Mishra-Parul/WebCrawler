package com.project.webCrawler.enums;

/**
 * @author parul.mishra
 * @created 05 / 04 /2020
 */
public enum Status {

    SUBMITTED("Submitted"),
    INPROCESS("InProcess"),
    PROCESSED("Processed"),
    FAILED("Failed");

    String name;

    private Status(String name){
        this.name = name;
    }

}
