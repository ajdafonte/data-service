package com.npaw.techtest.plugindataservice.common.rest;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.npaw.techtest.plugindataservice.common.error.PluginDataServiceApiError;


/**
 * Handles all exceptions for all REST controllers and translates them to a proper error response. Because we don't limit it to RestController.class,
 * it also deals with both @{@link org.springframework.web.HttpMediaTypeException}.
 */
@ControllerAdvice
public class RestControllerErrorHandler
{
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    PluginDataServiceErrorRest handleException(final HttpServletRequest request, final Exception e)
    {
        return new PluginDataServiceErrorRest(request, PluginDataServiceApiError.INTERNAL_SERVER_ERROR);
    }

    /**
     * Thrown when an argument validation fails.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    PluginDataServiceErrorRest handleConstraintViolation(final HttpServletRequest request, final ConstraintViolationException exception)
    {
        final String message = exception.getConstraintViolations().stream()
            .map(ConstraintViolation::getPropertyPath)
            .findFirst()    // Path
            .map(this::omitMethodPrefix)
            .orElse("null");
        return new PluginDataServiceErrorRest(request, PluginDataServiceApiError.INVALID_REQUEST_PARAMETER, message);
    }

    private String omitMethodPrefix(final Path path)
    {
        final String pathString = path.toString();
        final String[] segments = pathString.split("\\.");
        return segments.length > 1 ? String.join(".", Arrays.copyOfRange(segments, 1, segments.length)) : pathString;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    PluginDataServiceErrorRest handleMissingServletRequestParameter(final HttpServletRequest request,
                                                                    final MissingServletRequestParameterException exception)
    {
        return new PluginDataServiceErrorRest(request, PluginDataServiceApiError.INVALID_REQUEST, exception.getMessage());
    }
}
