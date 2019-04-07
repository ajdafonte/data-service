package com.npaw.techtest.plugindataservice.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper;


class BizzThreadPoolConfigDataTest
{
    private static final int MOCK_CORE_POOL_SIZE1 = 8;
    private static final int MOCK_CORE_POOL_SIZE2 = 1;
    private static final int MOCK_MAX_POOL_SIZE1 = 16;
    private static final int MOCK_MAX_POOL_SIZE2 = 1000;
    private static final int MOCK_KEEP_ALIVE1 = 60;
    private static final int MOCK_KEEP_ALIVE2 = 120;

    // equals ok
    @Test
    void givenTwoEqualBizzThreadPoolConfigData_whenCheckIfEquals_thenBothBizzThreadPoolConfigDatasMustBeEquals()
    {
        // given
        final BizzThreadPoolConfigData mockBizzThreadPoolConfigData1 =
            PluginDataServiceTestHelper.generateBizzThreadPoolConfigData(MOCK_CORE_POOL_SIZE1, MOCK_MAX_POOL_SIZE1, MOCK_KEEP_ALIVE1);
        final BizzThreadPoolConfigData mockBizzThreadPoolConfigData2 =
            PluginDataServiceTestHelper.generateBizzThreadPoolConfigData(MOCK_CORE_POOL_SIZE1, MOCK_MAX_POOL_SIZE1, MOCK_KEEP_ALIVE1);

        // when + then
        assertEquals(mockBizzThreadPoolConfigData1.hashCode(), mockBizzThreadPoolConfigData2.hashCode());
        assertEquals(mockBizzThreadPoolConfigData1, mockBizzThreadPoolConfigData2);
    }

    // equals nok
    @Test
    void givenTwoDifferentBizzThreadPoolConfigDatas_whenCheckIfEquals_thenBothBizzThreadPoolConfigDatasMustNotBeEquals()
    {
        // given
        final BizzThreadPoolConfigData mockBizzThreadPoolConfigData1 =
            PluginDataServiceTestHelper.generateBizzThreadPoolConfigData(MOCK_CORE_POOL_SIZE1, MOCK_MAX_POOL_SIZE1, MOCK_KEEP_ALIVE1);
        final BizzThreadPoolConfigData mockBizzThreadPoolConfigData2 =
            PluginDataServiceTestHelper.generateBizzThreadPoolConfigData(MOCK_CORE_POOL_SIZE2, MOCK_MAX_POOL_SIZE2, MOCK_KEEP_ALIVE2);

        // when + then
        assertNotEquals(mockBizzThreadPoolConfigData1.hashCode(), mockBizzThreadPoolConfigData2.hashCode());
        assertNotEquals(mockBizzThreadPoolConfigData1, mockBizzThreadPoolConfigData2);
    }

    // toString
    @Test
    void givenBizzThreadPoolConfigData_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final BizzThreadPoolConfigData mockBizzThreadPoolConfigData =
            PluginDataServiceTestHelper.generateBizzThreadPoolConfigData(MOCK_CORE_POOL_SIZE1, MOCK_MAX_POOL_SIZE1, MOCK_KEEP_ALIVE1);
        final String expected = "BizzThreadPoolConfigData(" +
            "corePoolSize=" + mockBizzThreadPoolConfigData.getCorePoolSize() +
            ", maxPoolSize=" + mockBizzThreadPoolConfigData.getMaxPoolSize() +
            ", keepAliveTime=" + mockBizzThreadPoolConfigData.getKeepAliveTime() +
            ')';

        // when
        final String result = mockBizzThreadPoolConfigData.toString();

        // then
        assertEquals(expected, result);

    }
}
