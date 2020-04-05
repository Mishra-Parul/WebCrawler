package com.project.webCrawler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author parul.mishra
 * @created 04 / 04 /2020
 */


@Configuration
@EnableAsync
public class AppConfig {

	@Value("${thread.pool.size:5}")
	private int corePoolSize;

	@Value("${thread.max.pool.size:10}")
	private int maxPoolSize;

	@Value("${thread.keepAliveSeconds:30}")
	private int keepAliveSeconds;

	@Value("${thread.queue.capacity:100000}")
	private int queueCapacity;

	@Bean(name = "executor")
	public ThreadPoolTaskExecutor executor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setKeepAliveSeconds(keepAliveSeconds);

		return executor;
	}

}