package com.npaw.techtest.plugindataservice.plugindata.rest;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(
    value = "PluginData",
    description = "A PluginData information"
)
@XmlRootElement(name = "q")
public class PluginDataRest
{
    @ApiModelProperty(value = "The host name from which the plugin should send the information.")
    private String host;

    @ApiModelProperty(value = "The ping time used by the device.")
    private int pingTime;

    @ApiModelProperty(value = "Code that identifies a view.")
    private String viewId;

    public PluginDataRest()
    {
    }

    public String getHost()
    {
        return host;
    }

    @XmlElement(name = "h")
    public void setHost(final String host)
    {
        this.host = host;
    }

    public int getPingTime()
    {
        return pingTime;
    }

    @XmlElement(name = "pt")
    public void setPingTime(final int pingTime)
    {
        this.pingTime = pingTime;
    }

    public String getViewId()
    {
        return viewId;
    }

    @XmlElement(name = "c")
    public void setViewId(final String viewId)
    {
        this.viewId = viewId;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        final PluginDataRest that = (PluginDataRest) o;
        return pingTime == that.pingTime &&
            Objects.equals(host, that.host) &&
            Objects.equals(viewId, that.viewId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(host, pingTime, viewId);
    }

    @Override
    public String toString()
    {
        return "PluginDataRest{" +
            "host='" + host + '\'' +
            ", pingTime=" + pingTime +
            ", viewId='" + viewId + '\'' +
            '}';
    }
}
