package com.wipro.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import com.wipro.entity.UserCredential;
import com.wipro.service.AuthService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // Disable security filters for testing
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    void testGetToken() throws Exception {
        when(authService.generateToken("testUser")).thenReturn("token");

        // Mock AuthenticationManager.authenticate to return an authenticated Authentication object
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenReturn(new UsernamePasswordAuthenticationToken("testUser", "password", new ArrayList<>()));

        mockMvc.perform(post("/auth/token")
                .contentType("application/json")
                .content("{\"username\":\"testUser\", \"password\":\"password\"}"))
                .andExpect(status().isOk()) // Expect 200 OK
                .andExpect(content().string("token"));
    }

    @Test
    void testRegisterUser() throws Exception {
        when(authService.saveUser(any(UserCredential.class))).thenReturn("User added to the system");

        mockMvc.perform(post("/auth/register")
                .contentType("application/json")
                .content("{\"name\":\"testUser\", \"password\":\"password\"}"))
                .andExpect(status().isOk()) // Expect 200 OK
                .andExpect(content().string("User added to the system"));
    }

    @Test
    void testValidateToken() throws Exception {
        mockMvc.perform(get("/auth/validate")
                .param("token", "validToken"))
                .andExpect(status().isOk()) // Expect 200 OK
                .andExpect(content().string("Token is valid"));
    }
}