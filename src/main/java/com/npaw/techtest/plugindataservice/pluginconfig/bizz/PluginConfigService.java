package com.npaw.techtest.plugindataservice.pluginconfig.bizz;

import java.util.List;
import java.util.Optional;

import com.npaw.techtest.plugindataservice.common.domain.PluginConfig;


public interface PluginConfigService
{
    /**
     * @param accountCode
     * @param targetDevice
     * @param pluginVersion
     * @return
     */
    Optional<PluginConfig> findPluginConfig(final String accountCode, final String targetDevice, final String pluginVersion);

    /**
     * @param accountCode
     * @return
     */
    Optional<List<PluginConfig>> getPluginConfigByClient(final String accountCode);

    /**
     * @param accountCode
     * @param targetDevice
     * @param newPluginVersion
     * @param newPingTime
     * @return
     */
    Optional<PluginConfig> updatePluginConfig(final String accountCode,
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
    Optional<PluginConfig> updatePluginHostConfig(final String accountCode,
                                                  final String targetDevice,
                                                  final String targetHost,
                                                  final int newHostLoad);
}
