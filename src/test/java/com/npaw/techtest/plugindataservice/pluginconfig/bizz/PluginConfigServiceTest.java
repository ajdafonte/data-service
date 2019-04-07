package com.npaw.techtest.plugindataservice.pluginconfig.bizz;

import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_ACCOUNT_CODE1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_NAME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_NAME2;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PING_TIME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PING_TIME2;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper;
import com.npaw.techtest.plugindataservice.common.config.PluginDataServiceProperties;
import com.npaw.techtest.plugindataservice.common.domain.HostConfig;
import com.npaw.techtest.plugindataservice.common.domain.PluginConfig;


@ExtendWith(MockitoExtension.class)
class PluginConfigServiceTest
{
    @Mock
    private PluginDataServiceProperties pluginDataServiceProperties;

    private PluginConfigService pluginConfigService;
    private List<HostConfig> mockHosts;
    private PluginConfig mockPluginConfig;
    private List<PluginConfig> mockCurrentClientConfig;

    @BeforeEach
    void setUp()
    {
        this.pluginConfigService = new PluginConfigServiceImpl(pluginDataServiceProperties);

        // define default client configuration for mockAccountCode1
        this.mockHosts = Arrays.asList(
            PluginDataServiceTestHelper.generateHostConfig(MOCK_NAME1, 70),
            PluginDataServiceTestHelper.generateHostConfig(MOCK_NAME2, 30)
        );
        this.mockPluginConfig = PluginDataServiceTestHelper.generatePluginConfig(
            MOCK_TARGET_DEVICE1,
            MOCK_PLUGIN_VERSION1,
            MOCK_PING_TIME1,
            mockHosts);
        this.mockCurrentClientConfig = Collections.singletonList(mockPluginConfig);
    }

    // getPluginConfigByClient - ok
    @Test
    void givenExistentAccountCode_whenGetPluginConfigByClient_thenReturnPluginConfigOfClient()
    {
        // given
        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
        doReturn(mockCurrentClientConfig).when(pluginDataServiceProperties).getClientConfig(MOCK_ACCOUNT_CODE1);

        // when
        final Optional<List<PluginConfig>> result = pluginConfigService.getPluginConfigByClient(mockAccountCode);

        // then
        assertThat(result, is(Optional.of(mockCurrentClientConfig)));
        assertThat(result.map(List::size).get(), is(1));
        verify(pluginDataServiceProperties, times(1)).getClientConfig(mockAccountCode);
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }

    // getPluginConfigByClient - nok
    @Test
    void givenUnknownAccountCode_whenGetPluginConfigByClient_thenReturnEmptyValue()
    {
        // given
        final String mockAccountCode = "mockUnknownAccountCode";
        doReturn(null).when(pluginDataServiceProperties).getClientConfig(mockAccountCode);

        // when
        final Optional<List<PluginConfig>> result = pluginConfigService.getPluginConfigByClient(mockAccountCode);

        // then
        assertThat(result, is(Optional.empty()));
        verify(pluginDataServiceProperties, times(1)).getClientConfig(mockAccountCode);
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }

    // findPluginConfig - ok
    @Test
    void givenValidInput_whenFindPluginConfig_thenReturnExpectedPluginConfig()
    {
        // given
        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
        final String mockPluginVersion = MOCK_PLUGIN_VERSION1;
        doReturn(mockCurrentClientConfig).when(pluginDataServiceProperties).getClientConfig(MOCK_ACCOUNT_CODE1);

        // when
        final Optional<PluginConfig> result = pluginConfigService.findPluginConfig(mockAccountCode, mockTargetDevice, mockPluginVersion);

        // then
        assertThat(result, is(Optional.of(mockPluginConfig)));
        verify(pluginDataServiceProperties, times(1)).getClientConfig(mockAccountCode);
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }

    // findPluginConfig - nok (client does not exist)
    @Test
    void givenUnknownAccountCode_whenFindPluginConfig_thenReturnEmptyValue()
    {
        // given
        final String mockAccountCode = "mockUnknownAccountCode";
        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
        final String mockPluginVersion = MOCK_PLUGIN_VERSION1;
        doReturn(null).when(pluginDataServiceProperties).getClientConfig(mockAccountCode);

        // when
        final Optional<PluginConfig> result = pluginConfigService.findPluginConfig(mockAccountCode, mockTargetDevice, mockPluginVersion);

        // then
        assertThat(result, is(Optional.empty()));
        verify(pluginDataServiceProperties, times(1)).getClientConfig(mockAccountCode);
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }

    // findPluginConfig - nok (client ok but targetdevice does not exist)
    @Test
    void givenExistentAccountCodeAndUnknownTargetDevice_whenFindPluginConfig_thenReturnEmptyValue()
    {
        // given
        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
        final String mockTargetDevice = "mockUnknownTargetDevice";
        final String mockPluginVersion = MOCK_PLUGIN_VERSION1;
        doReturn(mockCurrentClientConfig).when(pluginDataServiceProperties).getClientConfig(MOCK_ACCOUNT_CODE1);

        // when
        final Optional<PluginConfig> result = pluginConfigService.findPluginConfig(mockAccountCode, mockTargetDevice, mockPluginVersion);

        // then
        assertThat(result, is(Optional.empty()));
        verify(pluginDataServiceProperties, times(1)).getClientConfig(mockAccountCode);
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }

    // findPluginConfig - nok (client ok but pluginVersion does not exist)
    @Test
    void givenExistentAccountCodeAndUnknownPluginVersion_whenFindPluginConfig_thenReturnEmptyValue()
    {
        // given
        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
        final String mockPluginVersion = "mockUnknownPluginVersion";
        final PluginConfig expectedPluginConfig = PluginDataServiceTestHelper.generatePluginConfig(
            MOCK_TARGET_DEVICE1,
            MOCK_PLUGIN_VERSION1,
            MOCK_PING_TIME2,
            Collections.singletonList(PluginDataServiceTestHelper.generateHostConfig(MOCK_NAME2, 100)));
        final List<PluginConfig> expectedClientConfig = Collections.singletonList(expectedPluginConfig);
        when(pluginDataServiceProperties.getClientConfig(mockAccountCode)).thenReturn(expectedClientConfig);

        // when
        final Optional<PluginConfig> result = pluginConfigService.findPluginConfig(mockAccountCode, mockTargetDevice, mockPluginVersion);

        // then
        assertThat(result, is(Optional.empty()));
        verify(pluginDataServiceProperties, times(1)).getClientConfig(mockAccountCode);
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }

    // updatePluginConfig - ok
    @Test
    void givenValidInput_whenUpdatePluginConfig_thenReturnUpdatedPluginConfig()
    {
        // given
        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
        final String mockNewPluginVersion = "mockNewPluginVersion";
        final int mockNewPingTime = 100;
        final PluginConfig expectedPluginConfig =
            PluginDataServiceTestHelper.generatePluginConfig(MOCK_TARGET_DEVICE1, mockNewPluginVersion, mockNewPingTime, mockHosts);
        doReturn(mockCurrentClientConfig).when(pluginDataServiceProperties).getClientConfig(MOCK_ACCOUNT_CODE1);

        // when
        final Optional<PluginConfig> result =
            pluginConfigService.updatePluginConfig(mockAccountCode, mockTargetDevice, mockNewPluginVersion, mockNewPingTime);

        // then
        assertThat(result, is(Optional.of(expectedPluginConfig)));
        verify(pluginDataServiceProperties, times(1)).getClientConfig(mockAccountCode);
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }

    // updatePluginConfig - nok (client nok)
    @Test
    void givenUnknownAccountCode_whenUpdatePluginConfig_thenReturnEmptyValue()
    {
        // given
        final String mockAccountCode = "mockUnknownAccountCode";
        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
        final String mockNewPluginVersion = "mockNewPluginVersion";
        final int mockNewPingTime = 100;
        doReturn(null).when(pluginDataServiceProperties).getClientConfig(mockAccountCode);

        // when
        final Optional<PluginConfig> result =
            pluginConfigService.updatePluginConfig(mockAccountCode, mockTargetDevice, mockNewPluginVersion, mockNewPingTime);

        // then
        assertThat(result, is(Optional.empty()));
        verify(pluginDataServiceProperties, times(1)).getClientConfig(mockAccountCode);
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }

    // updatePluginConfig - nok (client ok but target device not ok)
    @Test
    void givenExistentAccountCodeAndUnknownTargetDevice_whenUpdatePluginConfig_thenReturnEmptyValue()
    {
        // given
        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
        final String mockTargetDevice = "mockUnknownTargetDevice";
        final String mockNewPluginVersion = "mockNewPluginVersion";
        final int mockNewPingTime = 100;
        doReturn(mockCurrentClientConfig).when(pluginDataServiceProperties).getClientConfig(MOCK_ACCOUNT_CODE1);

        // when
        final Optional<PluginConfig> result =
            pluginConfigService.updatePluginConfig(mockAccountCode, mockTargetDevice, mockNewPluginVersion, mockNewPingTime);

        // then
        assertThat(result, is(Optional.empty()));
        verify(pluginDataServiceProperties, times(1)).getClientConfig(mockAccountCode);
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }

    // updatePluginHostConfig - ok
    @Test
    void givenValidInput_whenUpdatePluginHostConfig_thenReturnUpdatedPluginHostConfig()
    {
        // given
        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
        final String mockHostName = MOCK_NAME1;
        final int mockNewHostLoad = 75;
        final List<HostConfig> mockExpectedHosts =
            Arrays.asList(
                PluginDataServiceTestHelper.generateHostConfig(MOCK_NAME1, mockNewHostLoad),
                PluginDataServiceTestHelper.generateHostConfig(MOCK_NAME2, 30)
            );
        final PluginConfig expectedPluginConfig =
            PluginDataServiceTestHelper.generatePluginConfig(MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1, MOCK_PING_TIME1, mockExpectedHosts);
        doReturn(mockCurrentClientConfig).when(pluginDataServiceProperties).getClientConfig(MOCK_ACCOUNT_CODE1);

        // when
        final Optional<PluginConfig> result =
            pluginConfigService.updatePluginHostConfig(mockAccountCode, mockTargetDevice, mockHostName, mockNewHostLoad);

        // then
        assertThat(result, is(Optional.of(expectedPluginConfig)));
        verify(pluginDataServiceProperties, times(1)).getClientConfig(mockAccountCode);
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }

    // updatePluginHostConfig - nok (client nok)
    @Test
    void givenUnknownAccountCode_whenUpdatePluginHostConfig_thenReturnEmptyValue()
    {
        // given
        final String mockAccountCode = "mockUnknownAccountCode";
        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
        final String mockHostName = MOCK_NAME1;
        final int mockNewHostLoad = 75;
        doReturn(null).when(pluginDataServiceProperties).getClientConfig(mockAccountCode);

        // when
        final Optional<PluginConfig> result =
            pluginConfigService.updatePluginHostConfig(mockAccountCode, mockTargetDevice, mockHostName, mockNewHostLoad);

        // then
        assertThat(result, is(Optional.empty()));
        verify(pluginDataServiceProperties, times(1)).getClientConfig(mockAccountCode);
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }

    // updatePluginHostConfig - nok (client ok but target device not ok)
    @Test
    void givenExistentAccountCodeAndUnknownTargetDevice_whenUpdatePluginHostConfig_thenReturnEmptyValue()
    {
        // given
        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
        final String mockTargetDevice = "mockUnknownTargetDevice";
        final String mockHostName = MOCK_NAME1;
        final int mockNewHostLoad = 75;
        doReturn(mockCurrentClientConfig).when(pluginDataServiceProperties).getClientConfig(MOCK_ACCOUNT_CODE1);

        // when
        final Optional<PluginConfig> result =
            pluginConfigService.updatePluginHostConfig(mockAccountCode, mockTargetDevice, mockHostName, mockNewHostLoad);

        // then
        assertThat(result, is(Optional.empty()));
        verify(pluginDataServiceProperties, times(1)).getClientConfig(mockAccountCode);
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }

    // updatePluginHostConfig - nok (client and target device ok but targetHost not ok)
    @Test
    void givenExistentAccountCodeAndTargetDeviceAndUnknownHostName_whenUpdatePluginHostConfig_thenReturnEmptyValue()
    {
        // given
        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
        final String mockHostName = "mockUnknownHostName";
        final int mockNewHostLoad = 75;
        doReturn(mockCurrentClientConfig).when(pluginDataServiceProperties).getClientConfig(MOCK_ACCOUNT_CODE1);

        // when
        final Optional<PluginConfig> result =
            pluginConfigService.updatePluginHostConfig(mockAccountCode, mockTargetDevice, mockHostName, mockNewHostLoad);

        // then
        assertThat(result, is(Optional.of(mockPluginConfig)));
        verify(pluginDataServiceProperties, times(1)).getClientConfig(mockAccountCode);
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }

}
