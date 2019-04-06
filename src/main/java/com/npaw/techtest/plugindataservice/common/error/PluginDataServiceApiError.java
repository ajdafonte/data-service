package com.npaw.techtest.plugindataservice.common.error;

import java.util.StringJoiner;

import org.springframework.http.HttpStatus;


/**
 * Holds all specific errors returned by Plugin Data Service.
 */
public enum PluginDataServiceApiError
{
    // Default error code
    INTERNAL_SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),

    // Common error codes for request handling
    INVALID_REQUEST("Invalid request: %s", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_PARAMETER("Invalid request parameter '%s'", HttpStatus.BAD_REQUEST);

    private final String errorText;
    private final HttpStatus httpStatus;

    PluginDataServiceApiError(final String errorText, final HttpStatus httpStatus)
    {
        this.errorText = errorText;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode()
    {
        return name();
    }

    public String getErrorDescription(final String... parameters)
    {
        return String.format(errorText, (Object[]) parameters);
    }

    public HttpStatus getHttpStatus()
    {
        return httpStatus;
    }

    @Override
    public String toString()
    {
        return new StringJoiner(", ", PluginDataServiceApiError.class.getSimpleName() + "[", "]")
            .add("errorText='" + errorText + "'")
            .add("httpStatus=" + httpStatus)
            .toString();
    }

    //    @Override
//    public String toString()
//    {
//        return MoreObjects.toStringHelper(this)
//            .add("errorText", errorText)
//            .add("httpStatus", httpStatus)
//            .toString();
//    }
}
