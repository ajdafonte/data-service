package com.npaw.techtest.plugindataservice.bizz;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.npaw.techtest.plugindataservice.domain.PluginData;


@Service
public class PluginDataServiceImpl implements PluginDataService
{
    @Override
    public PluginData getPluginData(final PluginDataParameter pluginDataParameter)
    {
        return new PluginData("clusterA", 10, UUID.randomUUID().toString());
    }
}
