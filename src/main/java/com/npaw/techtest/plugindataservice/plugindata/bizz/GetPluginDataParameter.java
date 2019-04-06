package com.npaw.techtest.plugindataservice.plugindata.bizz;

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
public class GetPluginDataParameter
{
    private final String accountCode;
    private final String targetDevice;
    private final String pluginVersion;
}
