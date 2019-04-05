package com.npaw.techtest.plugindataservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.npaw.techtest.plugindataservice.bizz.GetPluginDataParameter;
import com.npaw.techtest.plugindataservice.bizz.PluginDataService;
import com.npaw.techtest.plugindataservice.common.infra.MappingTool;
import com.npaw.techtest.plugindataservice.rest.mapper.PluginDataRestMapper;


@RestController
@RequestMapping(value = "/pluginData")
public class PluginDataController
{
    private final PluginDataService pluginDataService;

    @Autowired
    public PluginDataController(final PluginDataService pluginDataService)
    {
        this.pluginDataService = pluginDataService;
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public PluginDataRest getPluginData(@RequestParam(required = false) final String accountCode,
                                        @RequestParam(required = false) final String targetDevice,
                                        @RequestParam(required = false) final String pluginVersion)
    {
        final GetPluginDataParameter parameter = new GetPluginDataParameter(
            MappingTool.optionalOrNull(accountCode),
            MappingTool.optionalOrNull(targetDevice),
            MappingTool.optionalOrNull(pluginVersion));
        return PluginDataRestMapper.map(pluginDataService.getPluginData(parameter));
    }
}
