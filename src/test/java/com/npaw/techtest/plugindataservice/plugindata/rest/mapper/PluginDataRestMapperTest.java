package com.npaw.techtest.plugindataservice.plugindata.rest.mapper;

import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_NAME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PING_TIME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_VIEW_ID1;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper;
import com.npaw.techtest.plugindataservice.plugindata.domain.PluginData;
import com.npaw.techtest.plugindataservice.plugindata.rest.PluginDataRest;


class PluginDataRestMapperTest
{
    // map - ok
    @Test
    void givenValidPluginData_whenMapToRest_thenReturnPluginDataRestObject()
    {
        // given
        final PluginData mockPluginData = PluginDataServiceTestHelper.generatePluginData(MOCK_NAME1, MOCK_PING_TIME1, MOCK_VIEW_ID1);

        // when
        final PluginDataRest result = PluginDataRestMapper.map(mockPluginData);

        // then
        assertNotNull(result);
        assertThat(result.getHost(), is(mockPluginData.getHost()));
        assertThat(result.getPingTime(), is(mockPluginData.getPingTime()));
        assertThat(result.getViewId(), is(mockPluginData.getViewId()));
    }

    // map - null
    @Test
    void givenNullPluginData_whenMapToRest_thenReturnNullValue()
    {
        // given + when + then
        assertNull(PluginDataRestMapper.map(null));
    }
}
