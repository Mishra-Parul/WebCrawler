/**
 * 
 */
package com.project.webCrawler.exception;

public class CrawlerException extends RuntimeException {

	private static final long serialVersionUID = -3368655266237942363L;

	public CrawlerException(String message) {
		super(message);
	}

	public CrawlerException(Throwable cause) {
		super(cause);
	}

	public CrawlerException(String message, Throwable cause) {
		super(message, cause);
	}

}