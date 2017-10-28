package me.danielhoyos.n26.controllers;

import me.danielhoyos.n26.N26Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Daniel Hoyos
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = N26Application.class)
@SpringBootTest
public class TransactionControllerTests {
    private final static String URL = "/transaction";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void verifyTransactionCreated() throws Exception {
        String payload = "{\"amount\": \"12.3\", \"timestamp\": \"" + System.currentTimeMillis() + "\"}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(URL )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void verifyTransactionNoContent() throws Exception {
        long timestamp = System.currentTimeMillis() - 61000L;
        String payload = "{\"amount\": \"12.3\", \"timestamp\": \"" + timestamp + "\"}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(URL )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
