package com.npaw.techtest.plugindataservice.plugindata.bizz;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class PluginDataServiceTest
{
//    @Mock
//    private PluginDataServiceProperties pluginDataServiceProperties;
//
//    private PluginDataService pluginDataService;
//
//    @BeforeEach
//    void setUp()
//    {
//        this.pluginDataService = new PluginDataServiceImpl(pluginConfigService);
//    }
//
//    // getPluginData - ok (happy path)
//    @Test
//    void givenMatchingPluginConfig_whenGetPluginData_thenReturnExpectedData()
//    {
//        // given
//        final GetPluginDataParameter mockParameter = generateGetPluginDataParameter(MOCK_ACCOUNT_CODE1, MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1);
//        final PluginConfig pluginConfig = generatePluginConfig(MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1, MOCK_PING_TIME1,
//            Collections.singletonList(generateHostConfig(MOCK_NAME1, 100)));
//        when(pluginDataServiceProperties.findPluginConfig(anyString(), anyString(), anyString())).thenReturn(pluginConfig);
//
//        // when
//        final PluginData result = pluginDataService.getPluginData(mockParameter);
//
//        // then
//        assertNotNull(result);
//        assertThat(result.host(), is(pluginConfig.getHosts().get(0).getName()));
//        assertThat(result.pingTime(), is(pluginConfig.getPingTime()));
//        assertThat(result.viewId(), instanceOf(String.class));
//        verify(pluginDataServiceProperties, times(1)).findPluginConfig(
//            mockParameter.accountCode(),
//            mockParameter.targetDevice(),
//            mockParameter.pluginVersion()
//        );
//        verifyNoMoreInteractions(pluginDataServiceProperties);
//    }
//
//    // getPluginData - no config found
//    @Test
//    void givenNotMatchingPluginConfig_whenGetPluginData_thenReturnNullValue()
//    {
//        // given
//        final GetPluginDataParameter mockParameter = generateGetPluginDataParameter(MOCK_ACCOUNT_CODE1, MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1);
//        when(pluginDataServiceProperties.findPluginConfig(anyString(), anyString(), anyString())).thenReturn(null);
//
//        // when
//        final PluginData result = pluginDataService.getPluginData(mockParameter);
//
//        // then
//        assertNull(result);
//        verify(pluginDataServiceProperties, times(1)).findPluginConfig(
//            mockParameter.accountCode(),
//            mockParameter.targetDevice(),
//            mockParameter.pluginVersion()
//        );
//        verifyNoMoreInteractions(pluginDataServiceProperties);
//    }
}
