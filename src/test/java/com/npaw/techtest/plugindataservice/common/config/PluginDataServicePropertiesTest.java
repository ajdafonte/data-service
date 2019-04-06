package com.npaw.techtest.plugindataservice.common.config;

import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_ACCOUNT_CODE1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_ACCOUNT_CODE2;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_LOAD1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_LOAD2;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_NAME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_NAME2;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PING_TIME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PING_TIME2;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION2;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_TARGET_DEVICE2;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper;
import com.npaw.techtest.plugindataservice.common.domain.PluginConfig;


@ExtendWith(SpringExtension.class)
@SpringBootTest()
class PluginDataServicePropertiesTest
{
    @Autowired
    private PluginDataServiceProperties properties;

    // Remarks:
    // - This test uses the application.yaml defined in test/resources
    // - Consider also the values defined in the helper class

    private static final PluginConfig MOCK_PLUGIN_CONFIG1;
    private static final PluginConfig MOCK_PLUGIN_CONFIG2;
    private static final PluginConfig MOCK_PLUGIN_CONFIG3;
    private static final Map<String, List<PluginConfig>> MOCK_CONFIG;
    private static final List<PluginConfig> MOCK_PLUGIN_CONFIGS1;
    private static final List<PluginConfig> MOCK_PLUGIN_CONFIGS2;

    static
    {
        MOCK_PLUGIN_CONFIG1 = PluginDataServiceTestHelper.generatePluginConfig(
            MOCK_TARGET_DEVICE1,
            MOCK_PLUGIN_VERSION1,
            MOCK_PING_TIME1,
            Arrays.asList(
                PluginDataServiceTestHelper.generateHostConfig(MOCK_NAME1, MOCK_LOAD1),
                PluginDataServiceTestHelper.generateHostConfig(MOCK_NAME2, MOCK_LOAD2)));
        MOCK_PLUGIN_CONFIG2 = PluginDataServiceTestHelper.generatePluginConfig(
            MOCK_TARGET_DEVICE2,
            MOCK_PLUGIN_VERSION2,
            MOCK_PING_TIME2,
            Collections.singletonList(PluginDataServiceTestHelper.generateHostConfig(MOCK_NAME2, 100)));
        MOCK_PLUGIN_CONFIG3 = PluginDataServiceTestHelper.generatePluginConfig(
            MOCK_TARGET_DEVICE1,
            MOCK_PLUGIN_VERSION1,
            MOCK_PING_TIME1,
            Collections.singletonList(PluginDataServiceTestHelper.generateHostConfig(MOCK_NAME1, 100)));

        MOCK_PLUGIN_CONFIGS1 = Arrays.asList(MOCK_PLUGIN_CONFIG1, MOCK_PLUGIN_CONFIG2);
        MOCK_PLUGIN_CONFIGS2 = Collections.singletonList(MOCK_PLUGIN_CONFIG3);

        MOCK_CONFIG = new ConcurrentHashMap<>();
        MOCK_CONFIG.put(MOCK_ACCOUNT_CODE1, MOCK_PLUGIN_CONFIGS1);
        MOCK_CONFIG.put(MOCK_ACCOUNT_CODE2, MOCK_PLUGIN_CONFIGS2);
    }

    // validate load properties
    @Test
    void givenValidAppProperties_whenStartingApp_thenShouldLoadPropertiesCorrectly()
    {
        // given

        // when
        final Map<String, List<PluginConfig>> result = properties.getClientConfigs();

        // then
        assertThat(result, is(MOCK_CONFIG));
        assertThat(result.size(), is(2));
        assertThat(result, IsMapContaining.hasEntry(MOCK_ACCOUNT_CODE1, MOCK_PLUGIN_CONFIGS1));
        assertThat(result, IsMapContaining.hasEntry(MOCK_ACCOUNT_CODE2, MOCK_PLUGIN_CONFIGS2));
    }

//    // validate search function - ok
//    @Test
//    void givenValidSearchParameters_whenFindPluginConfig_thenReturnExpectedPluginConfig()
//    {
//        // given
//        final PluginConfig expected = MOCK_PLUGIN_CONFIG1;
//        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
//        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
//        final String mockPluginVersion = MOCK_PLUGIN_VERSION1;
//
//        // when
//        final PluginConfig result = properties.findPluginConfig(mockAccountCode, mockTargetDevice, mockPluginVersion);
//
//        // then
//        assertNotNull(result);
//        assertThat(result, is(expected));
//    }
//
//    // validate search function - client does not exist
//    @Test
//    void givenUnknownAccountCode_whenFindPluginConfig_thenReturnNullValue()
//    {
//        // given
//        final String mockAccountCode = "mockUnkonwnAccountCode";
//        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
//        final String mockPluginVersion = MOCK_PLUGIN_VERSION1;
//
//        // when
//        final PluginConfig result = properties.findPluginConfig(mockAccountCode, mockTargetDevice, mockPluginVersion);
//
//        // then
//        assertNull(result);
//    }
//
//    // validate search function - client ok but targetdevice does not exist
//    @Test
//    void givenUnknownTargetDevice_whenFindPluginConfig_thenReturnNullValue()
//    {
//        // given
//        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
//        final String mockTargetDevice = "mockUnknownTargetDevice";
//        final String mockPluginVersion = MOCK_PLUGIN_VERSION1;
//
//        // when
//        final PluginConfig result = properties.findPluginConfig(mockAccountCode, mockTargetDevice, mockPluginVersion);
//
//        // then
//        assertNull(result);
//    }
//
//    // validate search function - client ok, targetdevice ok but pluginversion does not exist
//    @Test
//    void givenUnknownPluginVersion_whenFindPluginConfig_thenReturnNullValue()
//    {
//        // given
//        final String mockAccountCode = MOCK_ACCOUNT_CODE1;
//        final String mockTargetDevice = MOCK_TARGET_DEVICE1;
//        final String mockPluginVersion = "mockUnknownPluginVersion";
//
//        // when
//        final PluginConfig result = properties.findPluginConfig(mockAccountCode, mockTargetDevice, mockPluginVersion);
//
//        // then
//        assertNull(result);
//    }
}
