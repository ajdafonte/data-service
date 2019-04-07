package com.npaw.techtest.plugindataservice.config;

import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.npaw.techtest.plugindataservice.common.domain.BizzThreadPoolConfigData;


@Configuration
public class AppConfig
{
    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

    private final PluginDataServiceProperties pluginDataServiceProperties;

    @Autowired
    public AppConfig(final PluginDataServiceProperties pluginDataServiceProperties)
    {
        this.pluginDataServiceProperties = pluginDataServiceProperties;
    }

    @Bean
    public ThreadPoolTaskExecutor executor()
    {
        final BizzThreadPoolConfigData threadPoolConfigData = pluginDataServiceProperties.getBizzThreadPoolConfig();
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolConfigData.getCorePoolSize());
        executor.setMaxPoolSize(threadPoolConfigData.getMaxPoolSize());
        executor.setKeepAliveSeconds(threadPoolConfigData.getKeepAliveTime());
        executor.setThreadNamePrefix("bizz-worker-executor-");
        LOG.info("Created bizz thread pool with {} core, {} max threads and {} keep alive seconds ...",
            executor.getCorePoolSize(),
            executor.getMaxPoolSize(),
            executor.getKeepAliveSeconds());
        return executor;
    }

    @Bean
    public ExecutorService executorService(final ThreadPoolTaskExecutor executor)
    {
        return executor.getThreadPoolExecutor();
    }
}
