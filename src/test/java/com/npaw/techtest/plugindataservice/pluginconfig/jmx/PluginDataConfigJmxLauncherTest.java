package com.npaw.techtest.plugindataservice.pluginconfig.jmx;

import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_ACCOUNT_CODE1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_HOSTS1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_LOAD1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_NAME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PING_TIME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.generatePluginConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper;
import com.npaw.techtest.plugindataservice.common.domain.PluginConfig;
import com.npaw.techtest.plugindataservice.pluginconfig.bizz.PluginConfigService;


@ExtendWith(MockitoExtension.class)
class PluginDataConfigJmxLauncherTest
{
    @Mock
    private PluginConfigService pluginConfigService;

    private PluginDataConfigJmxLauncher pluginDataConfigJmxLauncher;

    @BeforeEach
    void setUp()
    {
        this.pluginDataConfigJmxLauncher = new PluginDataConfigJmxLauncher(pluginConfigService);
    }

    // getConfigurationForClient - ok
    @Test
    void givenExistentAccountCode_whenGetConfigForClient_thenReturnExpectedClientConfig()
    {
        // given
        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
        final List<PluginConfig> expectedClientConfig = Collections.singletonList(
            PluginDataServiceTestHelper.generatePluginConfig(MOCK_TARGET_DEVICE1,
                MOCK_PLUGIN_VERSION1,
                MOCK_PING_TIME1,
                MOCK_HOSTS1));
        when(pluginConfigService.getPluginConfigByClient(mockAccountCode)).thenReturn(Optional.of(expectedClientConfig));

        // when
        final String result = pluginDataConfigJmxLauncher.getConfigurationForClient(mockAccountCode);

        // then
        assertNotNull(result);
        assertThat(result, is(expectedClientConfig.toString()));
        verify(pluginConfigService, times(1)).getPluginConfigByClient(mockAccountCode);
        verifyNoMoreInteractions(pluginConfigService);
    }

    // getConfigurationForClient - nok
    @Test
    void givenUnknownAccountCode_whenGetConfigForClient_thenReturnWarningMessage()
    {
        // given
        final String mockAccountCode = "mockUnknownAccountCode";
        final String expectedResult = "Warning: Client configuration not found.";
        when(pluginConfigService.getPluginConfigByClient(mockAccountCode)).thenReturn(Optional.empty());

        // when
        final String result = pluginDataConfigJmxLauncher.getConfigurationForClient(mockAccountCode);

        // then
        assertNotNull(result);
        assertThat(result, is(expectedResult));
        verify(pluginConfigService, times(1)).getPluginConfigByClient(mockAccountCode);
        verifyNoMoreInteractions(pluginConfigService);
    }

    // updateClientPluginConfiguration - ok
    @Test
    void givenValidInput_whenUpdateClientPluginConfig_thenReturnUpdatedClientPluginConfig()
    {
        // given
        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
        final String mockNewPluginVersion = "mockNewPluginVersion";
        final int mockNewPingTime = 100;
        final PluginConfig expectedPluginConfig =
            generatePluginConfig(MOCK_TARGET_DEVICE1,
                mockNewPluginVersion,
                mockNewPingTime,
                MOCK_HOSTS1);
        when(pluginConfigService
            .updatePluginConfig(mockAccountCode, mockTargetDevice, mockNewPluginVersion, mockNewPingTime))
            .thenReturn(Optional.of(expectedPluginConfig));

        // when
        final String result =
            pluginDataConfigJmxLauncher.updateClientPluginConfiguration(mockAccountCode, mockTargetDevice, mockNewPluginVersion, mockNewPingTime);

        // then
        assertNotNull(result);
        assertThat(result, is(expectedPluginConfig.toString()));
    }

    // updateClientPluginConfiguration - nok (unknownClient or unknownTargetDevice)
    @Test
    void givenUnknownAccountCode_whenUpdateClientPluginConfig_thenReturnWarningMessage()
    {
        // given
        final String mockAccountCode = "mockUnknownAccountCode";
        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
        final String mockNewPluginVersion = "mockNewPluginVersion";
        final int mockNewPingTime = 100;
        final String expectedResult = "Warning: Plugin configuration was not found.";
        when(pluginConfigService
            .updatePluginConfig(mockAccountCode, mockTargetDevice, mockNewPluginVersion, mockNewPingTime))
            .thenReturn(Optional.empty());

        // when
        final String result =
            pluginDataConfigJmxLauncher.updateClientPluginConfiguration(mockAccountCode, mockTargetDevice, mockNewPluginVersion, mockNewPingTime);

        // then
        assertNotNull(result);
        assertThat(result, is(expectedResult));
        verify(pluginConfigService, times(1))
            .updatePluginConfig(mockAccountCode, mockTargetDevice, mockNewPluginVersion, mockNewPingTime);
        verifyNoMoreInteractions(pluginConfigService);
    }

    // updateClientPluginHostConfiguration - ok
    @Test
    void givenValidInput_whenUpdateClientPluginHostConfig_thenReturnUpdatedClientPluginHostConfig()
    {
        // given
        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
        final String mockHostName = MOCK_NAME1;
        final int mockNewHostLoad = 75;
        final PluginConfig expectedPluginConfig =
            generatePluginConfig(mockTargetDevice,
                MOCK_PLUGIN_VERSION1,
                MOCK_PING_TIME1,
                Collections.singletonList(PluginDataServiceTestHelper.generateHostConfig(mockHostName, MOCK_LOAD1)));
        when(pluginConfigService
            .updatePluginHostConfig(mockAccountCode, mockTargetDevice, mockHostName, mockNewHostLoad))
            .thenReturn(Optional.of(expectedPluginConfig));

        // when
        final String result =
            pluginDataConfigJmxLauncher.updateClientPluginHostConfiguration(mockAccountCode, mockTargetDevice, mockHostName, mockNewHostLoad);

        // then
        assertNotNull(result);
        assertThat(result, is(expectedPluginConfig.toString()));
    }

    // updateClientPluginHostConfiguration - nok (unknownClient or unknownTargetDevice or unknownHostName)
    @Test
    void givenUnknownAccountCode_whenUpdateClientPluginHostConfig_thenReturnWarningMessage()
    {
        // given
        final String mockAccountCode = "mockUnknownAccountCode";
        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
        final String mockHostName = MOCK_NAME1;
        final int mockNewHostLoad = 75;
        final String expectedResult = "Warning: Plugin configuration was not found.";
        when(pluginConfigService
            .updatePluginHostConfig(mockAccountCode, mockTargetDevice, mockHostName, mockNewHostLoad))
            .thenReturn(Optional.empty());

        // when
        final String result =
            pluginDataConfigJmxLauncher.updateClientPluginHostConfiguration(mockAccountCode, mockTargetDevice, mockHostName, mockNewHostLoad);

        // then
        assertNotNull(result);
        assertThat(result, is(expectedResult));
        verify(pluginConfigService, times(1))
            .updatePluginHostConfig(mockAccountCode, mockTargetDevice, mockHostName, mockNewHostLoad);
        verifyNoMoreInteractions(pluginConfigService);
    }
}
