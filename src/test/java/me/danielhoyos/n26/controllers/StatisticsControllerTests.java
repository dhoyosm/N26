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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author Daniel Hoyos
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = N26Application.class)
@SpringBootTest
public class StatisticsControllerTests {
    private final static String STATISTICS_URL = "/statistics";
    private final static String SUM = "$.sum";
    private final static String AVG = "$.avg";
    private final static String MIN = "$.min";
    private final static String MAX = "$.max";
    private final static String COUNT = "$.count";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void verifyReadStatistics() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(STATISTICS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(SUM).exists())
                .andExpect(jsonPath(SUM).value(0.0))
                .andExpect(jsonPath(AVG).exists())
                .andExpect(jsonPath(AVG).value(0.0))
                .andExpect(jsonPath(MIN).exists())
                .andExpect(jsonPath(MIN).value(0.0))
                .andExpect(jsonPath(MAX).exists())
                .andExpect(jsonPath(MAX).value(0.0))
                .andExpect(jsonPath(COUNT).exists())
                .andExpect(jsonPath(COUNT).value(0.0))
                .andDo(print());
    }

}
