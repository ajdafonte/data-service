package com.npaw.techtest.plugindataservice.common.error;

import org.springframework.http.HttpStatus;

import com.google.common.base.MoreObjects;


/**
 * Holds all specific errors returned by Plugin Data Service.
 */
public enum PluginDataServiceError
{
    // Default error code
    INTERNAL_SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),

    // Common error codes for request handling
    INVALID_REQUEST("Invalid request: %s", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_PARAMETER("Invalid request parameter '%s'", HttpStatus.BAD_REQUEST);

    private final String errorText;
    private final HttpStatus httpStatus;

    PluginDataServiceError(final String errorText, final HttpStatus httpStatus)
    {
        this.errorText = errorText;
        this.httpStatus = httpStatus;
    }

    public String getErrorDescription(final String... parameters)
    {
        return String.format(errorText, (Object[]) parameters);
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
            .add("errorText", errorText)
            .add("httpStatus", httpStatus)
            .toString();
    }
}
