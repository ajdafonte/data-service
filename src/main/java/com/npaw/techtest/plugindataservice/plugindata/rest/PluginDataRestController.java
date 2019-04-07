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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value = "/pluginData")
@Validated
@Api(
    tags = "PluginData",
    value = "Resources for accessing plugin data information."
)
public class PluginDataRestController
{
    private final PluginDataService pluginDataService;

    @Autowired
    public PluginDataRestController(final PluginDataService pluginDataService)
    {
        this.pluginDataService = pluginDataService;
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    @ApiOperation(value = "Retrieve a plugin data information considering some query parameters.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful request."),
        @ApiResponse(code = 400, message = "Bad Request.")
    })
    public PluginDataRest getPluginData(
        @RequestParam @ApiParam(value = "The account code of the client.", required = true) @NotBlank final String accountCode,
        @RequestParam @ApiParam(value = "The target device.", required = true) @NotBlank final String targetDevice,
        @RequestParam @ApiParam(value = "The target plugin version.", required = true) @NotBlank final String pluginVersion)
    {
        final GetPluginDataParameter parameter = new GetPluginDataParameter(accountCode, targetDevice, pluginVersion);
        return PluginDataRestMapper.map(pluginDataService.getPluginData(parameter));
    }
}
