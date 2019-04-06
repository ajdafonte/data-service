package com.npaw.techtest.plugindataservice.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper;


class PluginConfigTest
{
    // equals ok
    @Test
    void givenTwoEqualPluginConfigs_whenCheckIfEquals_thenBothPluginConfigsMustBeEquals()
    {
        // given
        final PluginConfig mockPluginConfig1 = PluginDataServiceTestHelper.generatePluginConfig(PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1,
            PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1,
            PluginDataServiceTestHelper.MOCK_PING_TIME1,
            PluginDataServiceTestHelper.MOCK_HOSTS1);
        final PluginConfig mockPluginConfig2 = PluginDataServiceTestHelper.generatePluginConfig(PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1,
            PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1,
            PluginDataServiceTestHelper.MOCK_PING_TIME1,
            PluginDataServiceTestHelper.MOCK_HOSTS1);

        // when + then
        assertEquals(mockPluginConfig1.hashCode(), mockPluginConfig2.hashCode());
        assertEquals(mockPluginConfig1, mockPluginConfig2);
    }

    // equals nok
    @Test
    void givenTwoDifferentPluginConfigs_whenCheckIfEquals_thenBothPluginConfigsMustNotBeEquals()
    {
        // given
        final PluginConfig mockPluginConfig1 = PluginDataServiceTestHelper.generatePluginConfig(PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1,
            PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1,
            PluginDataServiceTestHelper.MOCK_PING_TIME1,
            PluginDataServiceTestHelper.MOCK_HOSTS1);
        final PluginConfig mockPluginConfig2 = PluginDataServiceTestHelper.generatePluginConfig(PluginDataServiceTestHelper.MOCK_TARGET_DEVICE2,
            PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION2,
            PluginDataServiceTestHelper.MOCK_PING_TIME2,
            PluginDataServiceTestHelper.MOCK_HOSTS1);

        // when + then
        assertNotEquals(mockPluginConfig1.hashCode(), mockPluginConfig2.hashCode());
        assertNotEquals(mockPluginConfig1, mockPluginConfig2);
    }

    // toString
    @Test
    void givenPluginConfig_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final PluginConfig mockPluginConfig = PluginDataServiceTestHelper.generatePluginConfig(PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1,
            PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1,
            PluginDataServiceTestHelper.MOCK_PING_TIME1,
            PluginDataServiceTestHelper.MOCK_HOSTS1);
        final String expected = "PluginConfig(" +
            "targetDevice=" + mockPluginConfig.getTargetDevice() +
            ", pluginVersion=" + mockPluginConfig.getPluginVersion() +
            ", pingTime=" + mockPluginConfig.getPingTime() +
            ", hosts=" + mockPluginConfig.getHosts() +
            ')';

        // when
        final String result = mockPluginConfig.toString();

        // then
        assertEquals(expected, result);

    }
}
