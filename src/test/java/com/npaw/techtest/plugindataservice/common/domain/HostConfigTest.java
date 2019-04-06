package com.npaw.techtest.plugindataservice.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper;


class HostConfigTest
{
    // equals ok
    @Test
    void givenTwoEqualHostConfigs_whenCheckIfEquals_thenBothHostConfigsMustBeEquals()
    {
        // given
        final HostConfig mockHostConfig1 =
            PluginDataServiceTestHelper.generateHostConfig(PluginDataServiceTestHelper.MOCK_NAME1, PluginDataServiceTestHelper.MOCK_LOAD1);
        final HostConfig mockHostConfig2 =
            PluginDataServiceTestHelper.generateHostConfig(PluginDataServiceTestHelper.MOCK_NAME1, PluginDataServiceTestHelper.MOCK_LOAD1);

        // when + then
        assertEquals(mockHostConfig1.hashCode(), mockHostConfig2.hashCode());
        assertEquals(mockHostConfig1, mockHostConfig2);
    }

    // equals nok
    @Test
    void givenTwoDifferentHostConfigs_whenCheckIfEquals_thenBothHostConfigsMustNotBeEquals()
    {
        // given
        final HostConfig mockHostConfig1 =
            PluginDataServiceTestHelper.generateHostConfig(PluginDataServiceTestHelper.MOCK_NAME1, PluginDataServiceTestHelper.MOCK_LOAD1);
        final HostConfig mockHostConfig2 =
            PluginDataServiceTestHelper.generateHostConfig(PluginDataServiceTestHelper.MOCK_NAME2, PluginDataServiceTestHelper.MOCK_LOAD2);

        // when + then
        assertNotEquals(mockHostConfig1.hashCode(), mockHostConfig2.hashCode());
        assertNotEquals(mockHostConfig1, mockHostConfig2);
    }

    // toString
    @Test
    void givenHostConfig_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final HostConfig mockHostConfig =
            PluginDataServiceTestHelper.generateHostConfig(PluginDataServiceTestHelper.MOCK_NAME1, PluginDataServiceTestHelper.MOCK_LOAD1);
        final String expected = "HostConfig(" +
            "name=" + mockHostConfig.getName() +
            ", load=" + mockHostConfig.getLoad() +
            ')';

        // when
        final String result = mockHostConfig.toString();

        // then
        assertEquals(expected, result);

    }
}
