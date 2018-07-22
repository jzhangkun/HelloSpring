package com.kun.hello.controller;

import com.kun.hello.SpringBaseTest;
import com.kun.hello.domain.User;
import com.kun.hello.service.UserManagement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends SpringBaseTest {
    @Autowired
    @Mock
    private UserManagement userManagement;

    @Autowired
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        given(this.userManagement.getUser(anyLong()))
                .willReturn(new User());
    }

    @Test
    public void testUser() throws Exception {
        mockMvc.perform(get("/user/7").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
