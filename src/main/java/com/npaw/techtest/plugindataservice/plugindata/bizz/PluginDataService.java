package com.npaw.techtest.plugindataservice.plugindata.bizz;

import com.npaw.techtest.plugindataservice.plugindata.domain.PluginData;


public interface PluginDataService
{
    /**
     * Retrieve a plugin data information considering some parameters.
     *
     * @param parameter the {@link GetPluginDataParameter} object that contains some query parameters to find the desired plugin config.
     * @return the {@link PluginData} object.
     */
    PluginData getPluginData(GetPluginDataParameter parameter);
}
