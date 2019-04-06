package com.npaw.techtest.plugindataservice.common.config;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

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

    public PluginConfig findPluginConfig(final String accountCode, final String targetDevice, final String pluginVersion)
    {
        //
        final List<PluginConfig> clientDevices = clientConfigs.get(accountCode);
        if (clientDevices == null)
        {
            return null;
        }

        //
        final Predicate<PluginConfig> filterPredicate =
            pluginConfig -> pluginConfig.getTargetDevice().equals(targetDevice) && pluginConfig.getPluginVersion().equals(pluginVersion);

        return clientDevices.stream()
            .filter(filterPredicate)
            .findFirst().orElse(null);
    }

}
