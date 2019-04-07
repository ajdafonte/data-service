package com.npaw.techtest.plugindataservice.pluginconfig.bizz;

import java.util.List;
import java.util.Optional;

import com.npaw.techtest.plugindataservice.common.domain.PluginConfigData;


public interface PluginConfigService
{
    /**
     * Find a plugin configuration data considering some parameters.
     *
     * @param accountCode The account code of the client.
     * @param targetDevice The target device.
     * @param pluginVersion The plugin version.
     * @return the {@link PluginConfigData} object or an empty Optional if no plugin configuration was found.
     */
    Optional<PluginConfigData> findPluginConfig(final String accountCode, final String targetDevice, final String pluginVersion);

    /**
     * Retrieve the configuration of a certain client.
     *
     * @param accountCode The account code of the client.
     * @return list of {@link PluginConfigData} that belongs to a certain client or an empty Optional if no configuration was found.
     */
    Optional<List<PluginConfigData>> getPluginConfigByClient(final String accountCode);

    /**
     * Updates the plugin version and the ping time of a plugin configuration that belongs to a certain client and matches with the specified target
     * device.
     *
     * @param accountCode The account code of the client.
     * @param targetDevice The target device.
     * @param newPluginVersion The new plugin version.
     * @param newPingTime The new ping time.
     * @return the updated {@link PluginConfigData} object or an empty Optional if no matching plugin configuration was found.
     */
    Optional<PluginConfigData> updatePluginConfig(final String accountCode,
                                                  final String targetDevice,
                                                  final String newPluginVersion,
                                                  final int newPingTime);

    /**
     * Updates the host load value of a plugin configuration that belongs to a certain client and matches with the specified target and host.
     *
     * @param accountCode The account code of the client.
     * @param targetDevice The target device.
     * @param targetHost The host name.
     * @param newHostLoad The new host load value.
     * @return the updated {@link PluginConfigData} object or an empty Optional if no matching plugin configuration was found.
     */
    Optional<PluginConfigData> updatePluginHostConfig(final String accountCode,
                                                      final String targetDevice,
                                                      final String targetHost,
                                                      final int newHostLoad);
}
