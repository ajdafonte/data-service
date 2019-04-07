package com.npaw.techtest.plugindataservice;

import java.util.Collections;
import java.util.List;

import com.npaw.techtest.plugindataservice.common.domain.BizzThreadPoolConfigData;
import com.npaw.techtest.plugindataservice.common.domain.HostConfigData;
import com.npaw.techtest.plugindataservice.common.domain.PluginConfigData;
import com.npaw.techtest.plugindataservice.plugindata.bizz.GetPluginDataParameter;
import com.npaw.techtest.plugindataservice.plugindata.domain.PluginData;
import com.npaw.techtest.plugindataservice.plugindata.rest.PluginDataRest;


public class PluginDataServiceTestHelper
{
    // host
    public static final String MOCK_NAME1 = "mockCluster1";
    public static final String MOCK_NAME2 = "mockCluster2";
    public static final int MOCK_LOAD1 = 60;
    public static final int MOCK_LOAD2 = 40;

    // plugin config
    public static final String MOCK_TARGET_DEVICE1 = "mockDevice1";
    public static final String MOCK_TARGET_DEVICE2 = "mockDevice2";
    public static final String MOCK_PLUGIN_VERSION1 = "mockPluginVersion1";
    public static final String MOCK_PLUGIN_VERSION2 = "mockPluginVersion2";
    public static final int MOCK_PING_TIME1 = 1;
    public static final int MOCK_PING_TIME2 = 10;
    public static final List<HostConfigData> MOCK_HOSTS1 = Collections.singletonList(generateHostConfigData(MOCK_NAME1, MOCK_LOAD1));

    public static final String MOCK_ACCOUNT_CODE1 = "mockAccountCode1";
    public static final String MOCK_ACCOUNT_CODE2 = "mockAccountCode2";

    public static final String MOCK_VIEW_ID1 = "mockViewId1";
    public static final String MOCK_VIEW_ID2 = "mockViewId2";

    public static HostConfigData generateHostConfigData(final String name, final int load)
    {
        final HostConfigData hostConfigData = new HostConfigData();
        hostConfigData.setName(name);
        hostConfigData.setLoad(load);
        return hostConfigData;
    }

    public static PluginConfigData generatePluginConfigData(final String targetDevice,
                                                            final String pluginVersion,
                                                            final int pingTime,
                                                            final List<HostConfigData> hosts)
    {
        final PluginConfigData pluginConfigData = new PluginConfigData();
        pluginConfigData.setTargetDevice(targetDevice);
        pluginConfigData.setPluginVersion(pluginVersion);
        pluginConfigData.setPingTime(pingTime);
        pluginConfigData.setHostsConfig(hosts);
        return pluginConfigData;
    }

    public static BizzThreadPoolConfigData generateBizzThreadPoolConfigData(final int corePoolSize, final int maxPoolSize, final int keepAliveTime)
    {
        final BizzThreadPoolConfigData threadPoolConfigData = new BizzThreadPoolConfigData();
        threadPoolConfigData.setCorePoolSize(corePoolSize);
        threadPoolConfigData.setMaxPoolSize(maxPoolSize);
        threadPoolConfigData.setKeepAliveTime(keepAliveTime);
        return threadPoolConfigData;
    }

    public static GetPluginDataParameter generateGetPluginDataParameter(final String accountCode,
                                                                        final String targetDevice,
                                                                        final String pluginVersion)
    {
        return new GetPluginDataParameter(accountCode, targetDevice, pluginVersion);
    }

    public static PluginData generatePluginData(final String hostName, final int pingTime, final String viewId)
    {
        return new PluginData(hostName, pingTime, viewId);
    }

    public static PluginDataRest generatePluginDataRest(final String hostName, final int pingTime, final String viewId)
    {
        final PluginDataRest pluginDataRest = new PluginDataRest();
        pluginDataRest.setHost(hostName);
        pluginDataRest.setPingTime(pingTime);
        pluginDataRest.setViewId(viewId);
        return pluginDataRest;
    }
}
