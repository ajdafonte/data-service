package com.npaw.techtest.plugindataservice.pluginconfig.jmx;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import com.npaw.techtest.plugindataservice.common.domain.PluginConfigData;
import com.npaw.techtest.plugindataservice.pluginconfig.bizz.PluginConfigService;


@Component
@ManagedResource(
    objectName = "com.npaw.techtest.plugindataservice:name=PluginDataConfig",
    description = "Plugin Data configuration related operations on demand for administration/testing purposes."
)
public class PluginDataConfigJmxLauncher
{
    private static final Logger LOG = LoggerFactory.getLogger(PluginDataConfigJmxLauncher.class);

    private final PluginConfigService pluginConfigService;

    @Autowired
    public PluginDataConfigJmxLauncher(final PluginConfigService pluginConfigService)
    {
        this.pluginConfigService = pluginConfigService;
    }

    @ManagedOperation(
        description = "Returns configuration for a certain client.")
    @ManagedOperationParameters({@ManagedOperationParameter(name = "accountCode", description = "The account code")})
    public String getConfigurationForClient(final String accountCode)
    {
        LOG.info(">> Receiving request from JMX to check if exists configuration for accountCode: {}", accountCode);
        final Optional<List<PluginConfigData>> clientConfig = pluginConfigService.getPluginConfigByClient(accountCode);
        return clientConfig.map(Object::toString).orElse("Warning: Client configuration not found.");
    }

    @ManagedOperation(
        description = "For a certain client and respective target device, updates the plugin version and/or ping time.")
    @ManagedOperationParameters({
        @ManagedOperationParameter(name = "accountCode", description = "The account code"),
        @ManagedOperationParameter(name = "targetDevice", description = "The target device"),
        @ManagedOperationParameter(name = "newPluginVersion", description = "The new plugin version"),
        @ManagedOperationParameter(name = "newPingTime", description = "The new ping time"),
    })
    public String updateClientPluginConfiguration(final String accountCode,
                                                  final String targetDevice,
                                                  final String newPluginVersion,
                                                  final int newPingTime)
    {
        LOG.info(">> Receiving request from JMX to update main client config, for accountCode {} and targetDevice {}.",
            accountCode,
            targetDevice);
        LOG.info(">> New value for the plugin version = {}", newPluginVersion);
        LOG.info(">> New value for the ping time = {}", newPingTime);
        final Optional<PluginConfigData> updatedPluginConfig =
            pluginConfigService.updatePluginConfig(accountCode, targetDevice, newPluginVersion, newPingTime);
        return updatedPluginConfig.map(PluginConfigData::toString).orElse("Warning: Plugin configuration was not found.");
    }

    @ManagedOperation(
        description = "For a certain client, target device and host name, updates the host load percentage.")
    @ManagedOperationParameters({
        @ManagedOperationParameter(name = "accountCode", description = "The account code"),
        @ManagedOperationParameter(name = "targetDevice", description = "The target device"),
        @ManagedOperationParameter(name = "hostName", description = "The host name"),
        @ManagedOperationParameter(name = "newHostLoad", description = "The new host load percentage"),
    })
    public String updateClientPluginHostConfiguration(final String accountCode,
                                                      final String targetDevice,
                                                      final String hostName,
                                                      final int newHostLoad)
    {
        LOG.info(
            ">> Receiving request from JMX to update host load percentage to {}, for accountCode {}, targetDevice {} and hostName {}.",
            newHostLoad,
            accountCode,
            targetDevice,
            hostName);
        final Optional<PluginConfigData> updatedPluginConfig =
            pluginConfigService.updatePluginHostConfig(accountCode, targetDevice, hostName, newHostLoad);
        return updatedPluginConfig.map(PluginConfigData::toString).orElse("Warning: Plugin configuration was not found.");
    }

}
