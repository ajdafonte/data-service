package com.npaw.techtest.plugindataservice.config;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.npaw.techtest.plugindataservice.common.domain.BizzThreadPoolConfigData;
import com.npaw.techtest.plugindataservice.common.domain.PluginConfigData;


@Configuration
@ConfigurationProperties(prefix = "plugindataservice")
public class PluginDataServiceProperties
{
    private BizzThreadPoolConfigData bizzThreadPoolConfig;

    public BizzThreadPoolConfigData getBizzThreadPoolConfig()
    {
        return bizzThreadPoolConfig;
    }

    public void setBizzThreadPoolConfig(final BizzThreadPoolConfigData bizzThreadPoolConfig)
    {
        this.bizzThreadPoolConfig = bizzThreadPoolConfig;
    }

    private ConcurrentHashMap<String, List<PluginConfigData>> clientConfigs;

    public ConcurrentHashMap<String, List<PluginConfigData>> getClientConfigs()
    {
        return clientConfigs;
    }

    public void setClientConfigs(final ConcurrentHashMap<String, List<PluginConfigData>> clientConfigs)
    {
        this.clientConfigs = clientConfigs;
    }

    public List<PluginConfigData> getClientConfig(final String accountCode)
    {
        return clientConfigs.get(accountCode);
    }

}
