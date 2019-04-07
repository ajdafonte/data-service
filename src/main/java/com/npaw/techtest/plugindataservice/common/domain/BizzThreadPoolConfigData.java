package com.npaw.techtest.plugindataservice.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BizzThreadPoolConfigData
{
    private int corePoolSize;
    private int maxPoolSize;
    private int keepAliveTime;
}
