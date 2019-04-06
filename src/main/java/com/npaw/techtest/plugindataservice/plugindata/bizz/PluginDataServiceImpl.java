package com.npaw.techtest.plugindataservice.plugindata.bizz;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npaw.techtest.plugindataservice.common.config.PluginDataServiceProperties;
import com.npaw.techtest.plugindataservice.common.domain.HostConfig;
import com.npaw.techtest.plugindataservice.common.domain.PluginConfig;
import com.npaw.techtest.plugindataservice.plugindata.domain.PluginData;


@Service
public class PluginDataServiceImpl implements PluginDataService
{
    private final PluginDataServiceProperties pluginDataServiceProperties;

    @Autowired
    public PluginDataServiceImpl(final PluginDataServiceProperties pluginDataServiceProperties)
    {
        this.pluginDataServiceProperties = pluginDataServiceProperties;
    }

    @Override
    public PluginData getPluginData(final GetPluginDataParameter parameter)
    {

        final PluginConfig clientDevice =
            pluginDataServiceProperties.findPluginConfig(parameter.accountCode(), parameter.targetDevice(), parameter.pluginVersion());

        if (clientDevice == null)
        {
            return null;
        }
        else
        {
            return new PluginData(getHost(clientDevice.getHosts()).getName(),
                clientDevice.getPingTime(), UUID.randomUUID().toString());
        }
    }

    private HostConfig getHost(final List<HostConfig> hosts)
    {
        final int rand = ThreadLocalRandom.current().nextInt(100);
        int prob = 0;
        for (final HostConfig host : hosts)
        {
            prob += host.getLoad();
            if (rand <= prob)
            {
                return host;
            }
        }
        return hosts.get(0);
    }

}
