package com.npaw.techtest.plugindataservice.plugindata.rest;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.npaw.techtest.plugindataservice.plugindata.bizz.GetPluginDataParameter;
import com.npaw.techtest.plugindataservice.plugindata.bizz.PluginDataService;
import com.npaw.techtest.plugindataservice.plugindata.rest.mapper.PluginDataRestMapper;


@RestController
@RequestMapping(value = "/pluginData")
@Validated
public class PluginDataRestController
{
    private final PluginDataService pluginDataService;

    @Autowired
    public PluginDataRestController(final PluginDataService pluginDataService)
    {
        this.pluginDataService = pluginDataService;
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public PluginDataRest getPluginData(@RequestParam @NotBlank final String accountCode,
                                        @RequestParam @NotBlank final String targetDevice,
                                        @RequestParam @NotBlank final String pluginVersion)
    {
        final GetPluginDataParameter parameter = new GetPluginDataParameter(accountCode, targetDevice, pluginVersion);
        return PluginDataRestMapper.map(pluginDataService.getPluginData(parameter));
    }
}
