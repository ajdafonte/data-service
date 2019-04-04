package com.npaw.techtest.plugindataservice.common.infra;

import java.util.Optional;


/**
 * MappingTool class.
 */
public class MappingTool
{
    public static <T> Optional<T> optionalOrNull(final T source)
    {
        final Optional<T> result;

        if (source == null)
        {
            result = Optional.empty();
        }
        else
        {
            result = Optional.of(source);
        }

        return result;
    }
}
