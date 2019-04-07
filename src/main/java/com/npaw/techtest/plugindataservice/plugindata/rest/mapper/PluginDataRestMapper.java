package com.npaw.techtest.plugindataservice.plugindata.rest.mapper;

import com.npaw.techtest.plugindataservice.plugindata.domain.PluginData;
import com.npaw.techtest.plugindataservice.plugindata.rest.PluginDataRest;


public class PluginDataRestMapper
{
    public static PluginDataRest map(final PluginData pluginData)
    {
        if (pluginData != null)
        {
            final PluginDataRest pluginDataRest = new PluginDataRest();
            pluginDataRest.setHost(pluginData.getHost());
            pluginDataRest.setPingTime(pluginData.getPingTime());
            pluginDataRest.setViewId(pluginData.getViewId());
            return pluginDataRest;
        }
        return null;
    }
}
