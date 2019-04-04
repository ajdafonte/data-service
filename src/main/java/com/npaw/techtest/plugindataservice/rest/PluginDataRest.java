package com.npaw.techtest.plugindataservice.rest;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 */
@XmlRootElement(name = "q")
public class PluginDataRest
{
    private String host;
    private int pingTime;
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
