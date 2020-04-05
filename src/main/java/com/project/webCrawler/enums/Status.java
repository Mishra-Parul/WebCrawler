package com.project.webCrawler.enums;

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
