package com.npaw.techtest.plugindataservice.plugindata.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper;


class PluginDataRestTest
{
    // equals ok
    @Test
    void givenTwoEqualPluginDataRest_whenCheckIfEquals_thenBothPluginDataRestMustBeEquals()
    {
        // given
        final PluginDataRest mockPluginDataRest1 =
            PluginDataServiceTestHelper.generatePluginDataRest(PluginDataServiceTestHelper.MOCK_NAME1,
                PluginDataServiceTestHelper.MOCK_PING_TIME1,
                PluginDataServiceTestHelper.MOCK_VIEW_ID1);
        final PluginDataRest mockPluginDataRest2 =
            PluginDataServiceTestHelper.generatePluginDataRest(PluginDataServiceTestHelper.MOCK_NAME1,
                PluginDataServiceTestHelper.MOCK_PING_TIME1,
                PluginDataServiceTestHelper.MOCK_VIEW_ID1);

        // when + then
        assertEquals(mockPluginDataRest1.hashCode(), mockPluginDataRest2.hashCode());
        assertEquals(mockPluginDataRest1, mockPluginDataRest2);
    }

    // equals nok
    @Test
    void givenTwoDifferentPluginDataRest_whenCheckIfEquals_thenBothPluginDataRestMustNotBeEquals()
    {
        // given
        final PluginDataRest mockPluginDataRest1 =
            PluginDataServiceTestHelper.generatePluginDataRest(PluginDataServiceTestHelper.MOCK_NAME1,
                PluginDataServiceTestHelper.MOCK_PING_TIME1,
                PluginDataServiceTestHelper.MOCK_VIEW_ID1);
        final PluginDataRest mockPluginDataRest2 =
            PluginDataServiceTestHelper.generatePluginDataRest(PluginDataServiceTestHelper.MOCK_NAME2,
                PluginDataServiceTestHelper.MOCK_PING_TIME2,
                PluginDataServiceTestHelper.MOCK_VIEW_ID2);

        // when + then
        assertNotEquals(mockPluginDataRest1.hashCode(), mockPluginDataRest2.hashCode());
        assertNotEquals(mockPluginDataRest1, mockPluginDataRest2);
    }

    // toString
    @Test
    void givenPluginDataRest_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final PluginDataRest mockPluginDataRest =
            PluginDataServiceTestHelper.generatePluginDataRest(PluginDataServiceTestHelper.MOCK_NAME1,
                PluginDataServiceTestHelper.MOCK_PING_TIME1,
                PluginDataServiceTestHelper.MOCK_VIEW_ID1);
        final String expected = "PluginDataRest{" +
            "host='" + mockPluginDataRest.getHost() + '\'' +
            ", pingTime=" + mockPluginDataRest.getPingTime() +
            ", viewId='" + mockPluginDataRest.getViewId() + '\'' +
            '}';

        // when
        final String result = mockPluginDataRest.toString();

        // then
        assertEquals(expected, result);

    }
}
