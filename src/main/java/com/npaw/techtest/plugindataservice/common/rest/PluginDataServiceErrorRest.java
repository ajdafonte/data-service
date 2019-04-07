package com.npaw.techtest.plugindataservice.common.rest;

import javax.servlet.http.HttpServletRequest;

import com.npaw.techtest.plugindataservice.common.error.PluginDataServiceError;


/**
 * General error rest response object for the Plugin Data Service.
 */
public class PluginDataServiceErrorRest
{
    private final String description;
    private final String url;

    PluginDataServiceErrorRest(final HttpServletRequest request, final PluginDataServiceError error, final String... errorParameters)
    {
        this.description = error.getErrorDescription(errorParameters);
        this.url = request.getRequestURL().toString();
    }

    public String getDescription()
    {
        return description;
    }

    public String getUrl()
    {
        return url;
    }
}
