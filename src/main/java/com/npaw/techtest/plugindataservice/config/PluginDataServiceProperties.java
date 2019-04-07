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
    private ConcurrentHashMap<String, List<PluginConfigData>> clientsConfigs;

    public BizzThreadPoolConfigData getBizzThreadPoolConfig()
    {
        return bizzThreadPoolConfig;
    }

    public void setBizzThreadPoolConfig(final BizzThreadPoolConfigData bizzThreadPoolConfig)
    {
        this.bizzThreadPoolConfig = bizzThreadPoolConfig;
    }

    public ConcurrentHashMap<String, List<PluginConfigData>> getClientsConfigs()
    {
        return clientsConfigs;
    }

    public void setClientsConfigs(final ConcurrentHashMap<String, List<PluginConfigData>> clientsConfigs)
    {
        this.clientsConfigs = clientsConfigs;
    }

    public List<PluginConfigData> getClientConfig(final String accountCode)
    {
        return clientsConfigs.get(accountCode);
    }

}
