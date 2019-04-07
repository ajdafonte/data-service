package com.npaw.techtest.plugindataservice.plugindata.bizz;

import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_ACCOUNT_CODE1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_ACCOUNT_CODE2;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION2;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_TARGET_DEVICE2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper;


class GetPluginDataParameterTest
{
    // equals ok
    @Test
    void givenTwoEqualGetPluginDataParameters_whenCheckIfEquals_thenBothGetPluginDataParametersMustBeEquals()
    {
        // given
        final GetPluginDataParameter mockGetPluginDataParameter1 =
            PluginDataServiceTestHelper.generateGetPluginDataParameter(MOCK_ACCOUNT_CODE1, MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1);
        final GetPluginDataParameter mockGetPluginDataParameter2 =
            PluginDataServiceTestHelper.generateGetPluginDataParameter(MOCK_ACCOUNT_CODE1, MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1);

        // when + then
        assertEquals(mockGetPluginDataParameter1.hashCode(), mockGetPluginDataParameter2.hashCode());
        assertEquals(mockGetPluginDataParameter1, mockGetPluginDataParameter2);
    }

    // equals nok
    @Test
    void givenTwoDifferentGetPluginDataParameters_whenCheckIfEquals_thenBothGetPluginDataParametersMustNotBeEquals()
    {
        // given
        final GetPluginDataParameter mockGetPluginDataParameter1 =
            PluginDataServiceTestHelper.generateGetPluginDataParameter(MOCK_ACCOUNT_CODE1, MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1);
        final GetPluginDataParameter mockGetPluginDataParameter2 =
            PluginDataServiceTestHelper.generateGetPluginDataParameter(MOCK_ACCOUNT_CODE2, MOCK_TARGET_DEVICE2, MOCK_PLUGIN_VERSION2);

        // when + then
        assertNotEquals(mockGetPluginDataParameter1.hashCode(), mockGetPluginDataParameter2.hashCode());
        assertNotEquals(mockGetPluginDataParameter1, mockGetPluginDataParameter2);
    }

    // toString
    @Test
    void givenGetPluginDataParameter_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final GetPluginDataParameter mockGetPluginDataParameter =
            PluginDataServiceTestHelper.generateGetPluginDataParameter(MOCK_ACCOUNT_CODE1, MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1);
        final String expected = "GetPluginDataParameter(" +
            "accountCode=" + mockGetPluginDataParameter.getAccountCode() +
            ", targetDevice=" + mockGetPluginDataParameter.getTargetDevice() +
            ", pluginVersion=" + mockGetPluginDataParameter.getPluginVersion() +
            ')';

        // when
        final String result = mockGetPluginDataParameter.toString();

        // then
        assertEquals(expected, result);

    }
}
