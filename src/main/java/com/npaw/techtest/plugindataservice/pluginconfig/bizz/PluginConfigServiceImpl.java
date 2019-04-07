package com.npaw.techtest.plugindataservice.pluginconfig.bizz;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npaw.techtest.plugindataservice.common.domain.HostConfigData;
import com.npaw.techtest.plugindataservice.common.domain.PluginConfigData;
import com.npaw.techtest.plugindataservice.config.PluginDataServiceProperties;


@Service
public class PluginConfigServiceImpl implements PluginConfigService
{
    private final PluginDataServiceProperties pluginDataServiceProperties;

    @Autowired
    public PluginConfigServiceImpl(final PluginDataServiceProperties pluginDataServiceProperties)
    {
        this.pluginDataServiceProperties = pluginDataServiceProperties;
    }

    @Override
    public Optional<List<PluginConfigData>> getPluginConfigByClient(final String accountCode)
    {
        return Optional.ofNullable(pluginDataServiceProperties.getClientConfig(accountCode));
    }

    @Override
    public Optional<PluginConfigData> findPluginConfig(final String accountCode, final String targetDevice, final String pluginVersion)
    {
        // retrieve client configuration
        final Optional<List<PluginConfigData>> clientPluginConfigs = getPluginConfigByClient(accountCode);

        // check if targetDevice and pluginVersion belongs to specified client
        final Predicate<PluginConfigData> filterPredicate =
            pluginConfig -> pluginConfig.getTargetDevice().equals(targetDevice) && pluginConfig.getPluginVersion().equals(pluginVersion);

        return clientPluginConfigs.flatMap(pluginConfigs -> pluginConfigs.stream().filter(filterPredicate).findFirst());
    }

    @Override
    public Optional<PluginConfigData> updatePluginConfig(final String accountCode,
                                                         final String targetDevice,
                                                         final String newPluginVersion,
                                                         final int newPingTime)
    {
        // retrieve client configuration
        final Optional<List<PluginConfigData>> clientPluginConfigs = getPluginConfigByClient(accountCode);

        // check if targetDevice belongs to specified client
        final Optional<PluginConfigData> targetPluginConfig = clientPluginConfigs.flatMap(
            pluginConfigs -> pluginConfigs.stream()
                .filter(pluginConfig -> pluginConfig.getTargetDevice().equals(targetDevice))
                .findFirst());

        // update values
        targetPluginConfig.ifPresent(pluginConfig -> {
            pluginConfig.setPluginVersion(newPluginVersion);
            pluginConfig.setPingTime(newPingTime);
        });

        return targetPluginConfig;
    }

    @Override
    public Optional<PluginConfigData> updatePluginHostConfig(final String accountCode,
                                                             final String targetDevice,
                                                             final String targetHost,
                                                             final int newHostLoad)
    {
        // retrieve client configuration
        final Optional<List<PluginConfigData>> clientPluginConfigs = getPluginConfigByClient(accountCode);

        // check if targetDevice belongs to specified client
        final Optional<PluginConfigData> targetPluginConfig = clientPluginConfigs.flatMap(
            pluginConfigs -> pluginConfigs.stream()
                .filter(pluginConfig -> pluginConfig.getTargetDevice().equals(targetDevice))
                .findFirst());

        // try to update values
        targetPluginConfig.ifPresent(pluginConfig -> {
            final List<HostConfigData> hosts = pluginConfig.getHosts();
            // find target host and if valid update host load
            hosts.stream()
                .filter(hostConfig -> hostConfig.getName().equals(targetHost))
                .findFirst()
                .ifPresent(hostConfig -> hostConfig.setLoad(newHostLoad));
        });

        return targetPluginConfig;
    }
}
