package com.npaw.techtest.plugindataservice.plugindata.domain;

import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_NAME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_NAME2;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PING_TIME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PING_TIME2;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_VIEW_ID1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_VIEW_ID2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper;


class PluginDataTest
{
    // equals ok
    @Test
    void givenTwoEqualPluginData_whenCheckIfEquals_thenBothPluginDataMustBeEquals()
    {
        // given
        final PluginData mockPluginData1 =
            PluginDataServiceTestHelper.generatePluginData(MOCK_NAME1, MOCK_PING_TIME1, MOCK_VIEW_ID1);
        final PluginData mockPluginData2 =
            PluginDataServiceTestHelper.generatePluginData(MOCK_NAME1, MOCK_PING_TIME1, MOCK_VIEW_ID1);

        // when + then
        assertEquals(mockPluginData1.hashCode(), mockPluginData2.hashCode());
        assertEquals(mockPluginData1, mockPluginData2);
    }

    // equals nok
    @Test
    void givenTwoDifferentPluginData_whenCheckIfEquals_thenBothPluginDataMustNotBeEquals()
    {
        // given
        final PluginData mockPluginData1 =
            PluginDataServiceTestHelper.generatePluginData(MOCK_NAME1, MOCK_PING_TIME1, MOCK_VIEW_ID1);
        final PluginData mockPluginData2 =
            PluginDataServiceTestHelper.generatePluginData(MOCK_NAME2, MOCK_PING_TIME2, MOCK_VIEW_ID2);

        // when + then
        assertNotEquals(mockPluginData1.hashCode(), mockPluginData2.hashCode());
        assertNotEquals(mockPluginData1, mockPluginData2);
    }

    // toString
    @Test
    void givenPluginData_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final PluginData mockPluginData =
            PluginDataServiceTestHelper.generatePluginData(MOCK_NAME1, MOCK_PING_TIME1, MOCK_VIEW_ID1);
        final String expected = "PluginData(" +
            "host=" + mockPluginData.getHost() +
            ", pingTime=" + mockPluginData.getPingTime() +
            ", viewId=" + mockPluginData.getViewId() +
            ')';

        // when
        final String result = mockPluginData.toString();

        // then
        assertEquals(expected, result);

    }
}
