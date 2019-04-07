package com.npaw.techtest.plugindataservice.plugindata.bizz;

import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_ACCOUNT_CODE1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_NAME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PING_TIME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper;
import com.npaw.techtest.plugindataservice.common.domain.PluginConfigData;
import com.npaw.techtest.plugindataservice.pluginconfig.bizz.PluginConfigService;
import com.npaw.techtest.plugindataservice.plugindata.domain.PluginData;


@ExtendWith(MockitoExtension.class)
class PluginDataServiceTest
{
    @Mock
    private PluginConfigService pluginConfigService;

    private PluginDataService pluginDataService;

    @BeforeEach
    void setUp()
    {
        this.pluginDataService = new PluginDataServiceImpl(pluginConfigService);
    }

    // getPluginData - ok (happy path)
    @Test
    void givenMatchingPluginConfig_whenGetPluginData_thenReturnExpectedData()
    {
        // given
        final GetPluginDataParameter mockParameter =
            PluginDataServiceTestHelper.generateGetPluginDataParameter(MOCK_ACCOUNT_CODE1, MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1);
        final PluginConfigData
            pluginConfigData = PluginDataServiceTestHelper.generatePluginConfigData(MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1, MOCK_PING_TIME1,
            Collections.singletonList(PluginDataServiceTestHelper.generateHostConfigData(MOCK_NAME1, 100)));
        when(pluginConfigService.findPluginConfig(anyString(), anyString(), anyString())).thenReturn(Optional.of(pluginConfigData));

        // when
        final PluginData result = pluginDataService.getPluginData(mockParameter);

        // then
        assertNotNull(result);
        assertThat(result.getHost(), is(pluginConfigData.getHostsConfig().get(0).getName()));
        assertThat(result.getPingTime(), is(pluginConfigData.getPingTime()));
        assertThat(result.getViewId(), instanceOf(String.class));
        verify(pluginConfigService, times(1)).findPluginConfig(
            mockParameter.getAccountCode(),
            mockParameter.getTargetDevice(),
            mockParameter.getPluginVersion()
        );
        verifyNoMoreInteractions(pluginConfigService);
    }

    // getPluginData - no config found
    @Test
    void givenNotMatchingPluginConfig_whenGetPluginData_thenReturnNullValue()
    {
        // given
        final GetPluginDataParameter mockParameter =
            PluginDataServiceTestHelper.generateGetPluginDataParameter(MOCK_ACCOUNT_CODE1, MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1);
        when(pluginConfigService.findPluginConfig(anyString(), anyString(), anyString())).thenReturn(Optional.empty());

        // when
        final PluginData result = pluginDataService.getPluginData(mockParameter);

        // then
        assertNull(result);
        verify(pluginConfigService, times(1)).findPluginConfig(
            mockParameter.getAccountCode(),
            mockParameter.getTargetDevice(),
            mockParameter.getPluginVersion()
        );
        verifyNoMoreInteractions(pluginConfigService);
    }
}
