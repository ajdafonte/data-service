package com.npaw.techtest.plugindataservice.plugindata.bizz;

import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_ACCOUNT_CODE1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_NAME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PING_TIME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.generateGetPluginDataParameter;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.generateHostConfig;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.generatePluginConfig;
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.npaw.techtest.plugindataservice.common.config.PluginDataServiceProperties;
import com.npaw.techtest.plugindataservice.common.domain.PluginConfig;
import com.npaw.techtest.plugindataservice.plugindata.domain.PluginData;


@ExtendWith(MockitoExtension.class)
class PluginDataServiceTest
{
    @Mock
    private PluginDataServiceProperties pluginDataServiceProperties;

    private PluginDataService pluginDataService;

    @BeforeEach
    void setUp()
    {
        this.pluginDataService = new PluginDataServiceImpl(pluginDataServiceProperties);
    }

    // getPluginData - ok (happy path)
    @Test
    void givenMatchingPluginConfig_whenGetPluginData_thenReturnExpectedData()
    {
        // given
        final GetPluginDataParameter mockParameter = generateGetPluginDataParameter(MOCK_ACCOUNT_CODE1, MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1);
        final PluginConfig pluginConfig = generatePluginConfig(MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1, MOCK_PING_TIME1,
            Collections.singletonList(generateHostConfig(MOCK_NAME1, 100)));
        when(pluginDataServiceProperties.findPluginConfig(anyString(), anyString(), anyString())).thenReturn(pluginConfig);

        // when
        final PluginData result = pluginDataService.getPluginData(mockParameter);

        // then
        assertNotNull(result);
        assertThat(result.host(), is(pluginConfig.getHosts().get(0).getName()));
        assertThat(result.pingTime(), is(pluginConfig.getPingTime()));
        assertThat(result.viewId(), instanceOf(String.class));
        verify(pluginDataServiceProperties, times(1)).findPluginConfig(
            mockParameter.accountCode(),
            mockParameter.targetDevice(),
            mockParameter.pluginVersion()
        );
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }

    // getPluginData - no config found
    @Test
    void givenNotMatchingPluginConfig_whenGetPluginData_thenReturnNullValue()
    {
        // given
        final GetPluginDataParameter mockParameter = generateGetPluginDataParameter(MOCK_ACCOUNT_CODE1, MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1);
        when(pluginDataServiceProperties.findPluginConfig(anyString(), anyString(), anyString())).thenReturn(null);

        // when
        final PluginData result = pluginDataService.getPluginData(mockParameter);

        // then
        assertNull(result);
        verify(pluginDataServiceProperties, times(1)).findPluginConfig(
            mockParameter.accountCode(),
            mockParameter.targetDevice(),
            mockParameter.pluginVersion()
        );
        verifyNoMoreInteractions(pluginDataServiceProperties);
    }
}
