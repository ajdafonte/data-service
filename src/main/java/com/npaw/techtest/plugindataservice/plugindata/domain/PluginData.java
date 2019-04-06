package com.npaw.techtest.plugindataservice.plugindata.domain;

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
public final class PluginData
{
    private final String host;
    private final int pingTime;
    private final String viewId;
}
