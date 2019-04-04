package com.npaw.techtest.plugindataservice.bizz;

import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;


@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
@ToString
@EqualsAndHashCode
public class PluginDataParameter
{
    private final Optional<String> accountCode;
    private final Optional<String> targetDevice;
    private final Optional<String> pluginVersion;
}
