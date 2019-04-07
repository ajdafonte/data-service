package com.npaw.techtest.plugindataservice.common.domain;

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
            PluginDataServiceTestHelper.generatePluginConfigData(PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1,
                PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1,
                PluginDataServiceTestHelper.MOCK_PING_TIME1,
                PluginDataServiceTestHelper.MOCK_HOSTS1);
        final PluginConfigData mockPluginConfigData2 =
            PluginDataServiceTestHelper.generatePluginConfigData(PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1,
                PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1,
                PluginDataServiceTestHelper.MOCK_PING_TIME1,
                PluginDataServiceTestHelper.MOCK_HOSTS1);

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
            PluginDataServiceTestHelper.generatePluginConfigData(PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1,
                PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1,
                PluginDataServiceTestHelper.MOCK_PING_TIME1,
                PluginDataServiceTestHelper.MOCK_HOSTS1);
        final PluginConfigData mockPluginConfigData2 =
            PluginDataServiceTestHelper.generatePluginConfigData(PluginDataServiceTestHelper.MOCK_TARGET_DEVICE2,
                PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION2,
                PluginDataServiceTestHelper.MOCK_PING_TIME2,
                PluginDataServiceTestHelper.MOCK_HOSTS1);

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
            PluginDataServiceTestHelper.generatePluginConfigData(PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1,
                PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1,
                PluginDataServiceTestHelper.MOCK_PING_TIME1,
                PluginDataServiceTestHelper.MOCK_HOSTS1);
        final String expected = "PluginConfigData(" +
            "targetDevice=" + mockPluginConfigData.getTargetDevice() +
            ", pluginVersion=" + mockPluginConfigData.getPluginVersion() +
            ", pingTime=" + mockPluginConfigData.getPingTime() +
            ", hosts=" + mockPluginConfigData.getHosts() +
            ')';

        // when
        final String result = mockPluginConfigData.toString();

        // then
        assertEquals(expected, result);

    }
}
