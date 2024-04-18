package com.acme.statusmgr;

import com.acme.statusmgr.beans.systemInfo.Mock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the most basic Status Controller request - "/server/status"
 */
@SpringBootTest
@AutoConfigureMockMvc
public class StatusControllerBasicTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Since this is a test, specify that all tests should use dummy system data.
     */
    @BeforeAll
    public static void beforeAll() {
        StatusController.setSystemInfoFacade(new Mock());
    }

    @Test
    public void noNameParamShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/server/status")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.requestCost").value(1))
                .andExpect(jsonPath("$.statusDesc").value("Server is up"));
    }

    @Test
    public void nameParamShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(get("/server/status").param("name", "RebYid"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by RebYid"));
    }

}
