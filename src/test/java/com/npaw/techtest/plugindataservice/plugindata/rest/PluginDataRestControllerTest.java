package com.npaw.techtest.plugindataservice.plugindata.rest;

import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_ACCOUNT_CODE1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_NAME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PING_TIME1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_PLUGIN_VERSION1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_TARGET_DEVICE1;
import static com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper.MOCK_VIEW_ID1;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.npaw.techtest.plugindataservice.PluginDataServiceTestHelper;
import com.npaw.techtest.plugindataservice.plugindata.bizz.GetPluginDataParameter;
import com.npaw.techtest.plugindataservice.plugindata.bizz.PluginDataService;
import com.npaw.techtest.plugindataservice.plugindata.domain.PluginData;


@ExtendWith(SpringExtension.class)
@WebMvcTest(PluginDataRestController.class)
class PluginDataRestControllerTest
{
    private static final String PLUGIN_DATA_URI = "/pluginData";

    private static final String INVALID_REQUEST = "Invalid request";
    private static final String INVALID_REQUEST_PARAMETER = "Invalid request parameter";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PluginDataService pluginDataService;

    // happy path
    @Test
    void givenInputParametersAndMatchingPluginConfig_whenGetPluginData_thenReturnExpectedPluginData() throws Exception
    {
        // given
        final GetPluginDataParameter mockParameter =
            PluginDataServiceTestHelper.generateGetPluginDataParameter(MOCK_ACCOUNT_CODE1, MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1);
        final PluginData expectedResult = PluginDataServiceTestHelper.generatePluginData(MOCK_NAME1, MOCK_PING_TIME1, MOCK_VIEW_ID1);
        doReturn(expectedResult).when(pluginDataService).getPluginData(mockParameter);

        final PluginDataRest mockPluginDataRest = PluginDataServiceTestHelper.generatePluginDataRest(MOCK_NAME1, MOCK_PING_TIME1, MOCK_VIEW_ID1);
        final String expectedContent = generateSuccessBody(mockPluginDataRest);

        // when
        final ResultActions result =
            mockMvc.perform(get(PLUGIN_DATA_URI)
                .param("accountCode", mockParameter.accountCode())
                .param("targetDevice", mockParameter.targetDevice())
                .param("pluginVersion", mockParameter.pluginVersion())
                .contentType(MediaType.APPLICATION_XML_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(pluginDataService, times(1)).getPluginData(mockParameter);
        verifyNoMoreInteractions(pluginDataService);
    }

    // blank value of parameter
    @Test
    void givenSomeEmptyParameters_whenGetPluginData_thenThrowsBadRequestException() throws Exception
    {
        // given
        final GetPluginDataParameter mockParameter = PluginDataServiceTestHelper.generateGetPluginDataParameter(MOCK_ACCOUNT_CODE1, "", "");

        // when
        final ResultActions result =
            mockMvc.perform(get(PLUGIN_DATA_URI)
                .param("accountCode", mockParameter.accountCode())
                .param("targetDevice", mockParameter.targetDevice())
                .param("pluginVersion", mockParameter.pluginVersion())
                .contentType(MediaType.APPLICATION_XML_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST_PARAMETER)));
        verifyZeroInteractions(pluginDataService);
    }

    // missing parameter
    @Test
    void givenIncompleteParameters_whenGetPluginData_thenThrowsBadRequestException() throws Exception
    {
        // given
        final GetPluginDataParameter mockParameter =
            PluginDataServiceTestHelper.generateGetPluginDataParameter(null, MOCK_TARGET_DEVICE1, MOCK_PLUGIN_VERSION1);

        // when
        final ResultActions result =
            mockMvc.perform(get(PLUGIN_DATA_URI)
                .param("targetDevice", mockParameter.targetDevice())
                .param("pluginVersion", mockParameter.pluginVersion())
                .contentType(MediaType.APPLICATION_XML_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(pluginDataService);
    }

    private String generateSuccessBody(final PluginDataRest pluginDataRest) throws JAXBException
    {
        final StringWriter writer = new StringWriter();
        final JAXBContext jaxbContext = JAXBContext.newInstance(PluginDataRest.class);
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        marshaller.marshal(pluginDataRest, writer);
        return writer.toString();
    }
}
