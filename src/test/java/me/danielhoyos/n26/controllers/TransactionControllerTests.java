package me.danielhoyos.n26.controllers;

import com.google.gson.Gson;
import me.danielhoyos.n26.N26Application;
import me.danielhoyos.n26.models.Transaction;
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
    private Gson gson;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        this.gson = new Gson();
    }

    @Test
    public void verifyTransactionCreated() throws Exception {
        Transaction payload = new Transaction(12.3, System.currentTimeMillis());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(URL )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(payload))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void verifyTransactionNoContent() throws Exception {
        long timestamp = System.currentTimeMillis() - 61000L;
        Transaction payload = new Transaction(12.3, timestamp);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(URL )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(payload))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
