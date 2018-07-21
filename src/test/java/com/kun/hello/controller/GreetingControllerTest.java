package com.kun.hello.controller;

import com.kun.hello.SpringBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GreetingControllerTest extends SpringBaseTest {
    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(new GreetingController()).build();
    }

    @Test
    public void testGreeting() throws Exception {
        mockMvc.perform(get("/greeting")).andExpect(status().isOk());
    }

}
