package com.acme.statusmgr;

import com.acme.statusmgr.beans.facade.Mock;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Tests for extra credit
 */
@SpringBootTest
@AutoConfigureMockMvc
public class StatusControllerExtraCreditTest {

    @Autowired
    private MockMvc mockMvc;


    /**
     * Since this is a test, specify that all tests should use dummy system data.
     */
    @BeforeAll
    public static void beforeAll() {
        StatusController.setSystemInfoFacade(new Mock());
    }


    /**
     * Test to ensure that the getFacade() method is not causing facade field to be serialized as a detail.     *
     * @throws Exception if something goes wrong while using the mock server
     */
    @Test
    public void testFacadeNotSerialized() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/server/status/detailed?details=availableProcessors&name=Yankel"))
                .andReturn();

        // Extract the json response from the result
        String jsonResponse = result.getResponse().getContentAsString();

        // Parse the json response into a Map
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(jsonResponse, new TypeReference<>() {});

        // Assert that the json response does not contain a "facade" field
        Assertions.assertFalse(jsonMap.containsKey("facade"));
    }

    /**
     * Tests that the keys in the JSON response are in the correct order: id, contentHeader, requestCost, statusDesc
     * @throws Exception if something goes wrong while using the mock server
     */
    @Test
    public void testJsonDetailsCorrectOrder() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/server/status/detailed?details=availableProcessors&name=Yankel"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // Extract json response as a string
        String jsonResponse = result.getResponse().getContentAsString();

        // Deserialize json response into a map
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(jsonResponse, new TypeReference<>() {});

        // Define the expected order of keys in the json response
        List<String> expectedOrder = Arrays.asList("id", "contentHeader", "requestCost", "statusDesc");

        // Extract the actual order of keys from the json map
        List<String> actualOrder = new ArrayList<>(jsonMap.keySet());

        // Assert that the actual order of keys matches the expected order
        Assertions.assertEquals(expectedOrder, actualOrder);
    }
}
