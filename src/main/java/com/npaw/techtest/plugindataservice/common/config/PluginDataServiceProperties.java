package com.npaw.techtest.plugindataservice.common.config;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.npaw.techtest.plugindataservice.common.domain.PluginConfig;


@Configuration
@ConfigurationProperties(prefix = "plugindataservice")
public class PluginDataServiceProperties
{
    private ConcurrentHashMap<String, List<PluginConfig>> clientConfigs;

    public ConcurrentHashMap<String, List<PluginConfig>> getClientConfigs()
    {
        return clientConfigs;
    }

    public void setClientConfigs(final ConcurrentHashMap<String, List<PluginConfig>> clientConfigs)
    {
        this.clientConfigs = clientConfigs;
    }

    public List<PluginConfig> getClientConfig(final String accountCode)
    {
        return clientConfigs.get(accountCode);
    }

}
