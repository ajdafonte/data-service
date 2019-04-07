package com.npaw.techtest.plugindataservice.pluginconfig.bizz;

import java.util.List;
import java.util.Optional;

import com.npaw.techtest.plugindataservice.common.domain.PluginConfigData;


public interface PluginConfigService
{
    /**
     * @param accountCode
     * @param targetDevice
     * @param pluginVersion
     * @return
     */
    Optional<PluginConfigData> findPluginConfig(final String accountCode, final String targetDevice, final String pluginVersion);

    /**
     * @param accountCode
     * @return
     */
    Optional<List<PluginConfigData>> getPluginConfigByClient(final String accountCode);

    /**
     * @param accountCode
     * @param targetDevice
     * @param newPluginVersion
     * @param newPingTime
     * @return
     */
    Optional<PluginConfigData> updatePluginConfig(final String accountCode,
                                                  final String targetDevice,
                                                  final String newPluginVersion,
                                                  final int newPingTime);

    /**
     * @param accountCode
     * @param targetDevice
     * @param targetHost
     * @param newHostLoad
     * @return
     */
    Optional<PluginConfigData> updatePluginHostConfig(final String accountCode,
                                                      final String targetDevice,
                                                      final String targetHost,
                                                      final int newHostLoad);
}
