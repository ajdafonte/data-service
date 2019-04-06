package com.npaw.techtest.plugindataservice.plugindata.rest.mapper;

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
        final PluginData mockPluginData = PluginDataServiceTestHelper.generatePluginData(PluginDataServiceTestHelper.MOCK_NAME1,
            PluginDataServiceTestHelper.MOCK_PING_TIME1,
            PluginDataServiceTestHelper.MOCK_VIEW_ID1);

        // when
        final PluginDataRest result = PluginDataRestMapper.map(mockPluginData);

        // then
        assertNotNull(result);
        assertThat(result.getHost(), is(mockPluginData.host()));
        assertThat(result.getPingTime(), is(mockPluginData.pingTime()));
        assertThat(result.getViewId(), is(mockPluginData.viewId()));
    }

    // map - null
    @Test
    void givenNullPluginData_whenMapToRest_thenReturnNullValue()
    {
        // given + when + then
        assertNull(PluginDataRestMapper.map(null));
    }
}
