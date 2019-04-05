package com.npaw.techtest.plugindataservice.common.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.npaw.techtest.plugindataservice.common.domain.ClientConfig;


@Configuration
@ConfigurationProperties(prefix = "plugindataservice")
public class PluginDataServiceProperties
{
    private List<ClientConfig> clientConfigs;

    public List<ClientConfig> getClientConfigs()
    {
        return clientConfigs;
    }

    public void setClientConfigs(final List<ClientConfig> clientConfigs)
    {
        this.clientConfigs = clientConfigs;
    }
}
