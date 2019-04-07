package com.npaw.techtest.plugindataservice.common.domain;

import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_HOSTS1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PING_TIME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PING_TIME2;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION2;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_TARGET_DEVICE2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper;


class PluginConfigDataTest
{
    // equals ok
    @Test
    void givenTwoEqualPluginConfigData_whenCheckIfEquals_thenBothPluginConfigDataMustBeEquals()
    {
        // given
        final PluginConfigData mockPluginConfigData1 =
            PluginDataServiceTestHelper.generatePluginConfigData(MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1, MOCK_PING_TIME1, MOCK_HOSTS1);
        final PluginConfigData mockPluginConfigData2 =
            PluginDataServiceTestHelper.generatePluginConfigData(MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1, MOCK_PING_TIME1, MOCK_HOSTS1);

        // when + then
        assertEquals(mockPluginConfigData1.hashCode(), mockPluginConfigData2.hashCode());
        assertEquals(mockPluginConfigData1, mockPluginConfigData2);
    }

    // equals nok
    @Test
    void givenTwoDifferentPluginConfigData_whenCheckIfEquals_thenBothPluginConfigDataMustNotBeEquals()
    {
        // given
        final PluginConfigData mockPluginConfigData1 =
            PluginDataServiceTestHelper.generatePluginConfigData(MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1, MOCK_PING_TIME1, MOCK_HOSTS1);
        final PluginConfigData mockPluginConfigData2 =
            PluginDataServiceTestHelper.generatePluginConfigData(MOCK_TARGET_DEVICE2, MOCK_PLUGIN_VERSION2, MOCK_PING_TIME2, MOCK_HOSTS1);

        // when + then
        assertNotEquals(mockPluginConfigData1.hashCode(), mockPluginConfigData2.hashCode());
        assertNotEquals(mockPluginConfigData1, mockPluginConfigData2);
    }

    // toString
    @Test
    void givenPluginConfig_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final PluginConfigData mockPluginConfigData =
            PluginDataServiceTestHelper.generatePluginConfigData(MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1, MOCK_PING_TIME1, MOCK_HOSTS1);
        final String expected = "PluginConfigData(" +
            "targetDevice=" + mockPluginConfigData.getTargetDevice() +
            ", pluginVersion=" + mockPluginConfigData.getPluginVersion() +
            ", pingTime=" + mockPluginConfigData.getPingTime() +
            ", hostsConfig=" + mockPluginConfigData.getHostsConfig() +
            ')';

        // when
        final String result = mockPluginConfigData.toString();

        // then
        assertEquals(expected, result);

    }
}
