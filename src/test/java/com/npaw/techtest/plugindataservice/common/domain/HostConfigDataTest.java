package com.npaw.techtest.plugindataservice.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper;


class HostConfigDataTest
{
    // equals ok
    @Test
    void givenTwoEqualHostConfigData_whenCheckIfEquals_thenBothHostConfigDataMustBeEquals()
    {
        // given
        final HostConfigData mockHostConfigData1 =
            PluginDataServiceTestHelper.generateHostConfigData(PluginDataServiceTestHelper.MOCK_NAME1, PluginDataServiceTestHelper.MOCK_LOAD1);
        final HostConfigData mockHostConfigData2 =
            PluginDataServiceTestHelper.generateHostConfigData(PluginDataServiceTestHelper.MOCK_NAME1, PluginDataServiceTestHelper.MOCK_LOAD1);

        // when + then
        assertEquals(mockHostConfigData1.hashCode(), mockHostConfigData2.hashCode());
        assertEquals(mockHostConfigData1, mockHostConfigData2);
    }

    // equals nok
    @Test
    void givenTwoDifferentHostConfigData_whenCheckIfEquals_thenBothHostConfigDataMustNotBeEquals()
    {
        // given
        final HostConfigData mockHostConfigData1 =
            PluginDataServiceTestHelper.generateHostConfigData(PluginDataServiceTestHelper.MOCK_NAME1, PluginDataServiceTestHelper.MOCK_LOAD1);
        final HostConfigData mockHostConfigData2 =
            PluginDataServiceTestHelper.generateHostConfigData(PluginDataServiceTestHelper.MOCK_NAME2, PluginDataServiceTestHelper.MOCK_LOAD2);

        // when + then
        assertNotEquals(mockHostConfigData1.hashCode(), mockHostConfigData2.hashCode());
        assertNotEquals(mockHostConfigData1, mockHostConfigData2);
    }

    // toString
    @Test
    void givenHostConfigData_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final HostConfigData mockHostConfigData =
            PluginDataServiceTestHelper.generateHostConfigData(PluginDataServiceTestHelper.MOCK_NAME1, PluginDataServiceTestHelper.MOCK_LOAD1);
        final String expected = "HostConfigData(" +
            "name=" + mockHostConfigData.getName() +
            ", load=" + mockHostConfigData.getLoad() +
            ')';

        // when
        final String result = mockHostConfigData.toString();

        // then
        assertEquals(expected, result);

    }
}
