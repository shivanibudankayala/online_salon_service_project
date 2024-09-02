package com.wipro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wipro.entity.UserCredential;
import com.wipro.repository.UserCredentialRepository;

public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserCredentialRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        // Given
        UserCredential user = new UserCredential();
        user.setName("testuser");
        user.setPassword("plainpassword");

        // When
        when(passwordEncoder.encode("plainpassword")).thenReturn("encodedPassword");  // Fix: Use the plain password here
        when(repository.save(any(UserCredential.class))).thenReturn(user);

        // Then
        String result = authService.saveUser(user);
        
        // Verify that passwordEncoder was called with the plain password
        verify(passwordEncoder).encode("plainpassword");
        
        // Verify that the repository's save method was called with the user object
        verify(repository).save(any(UserCredential.class));
        
        assertEquals("User added to the system", result);
    }

    @Test
    public void testGenerateToken() {
        // Given
        String username = "testuser";
        String token = "generatedToken";

        // When
        when(jwtService.generateToken(username)).thenReturn(token);

        // Then
        String result = authService.generateToken(username);
        verify(jwtService).generateToken(eq(username));
        assertEquals(token, result);
    }

    @Test
    public void testValidateToken() {
        // Given
        String token = "validToken";

        // When
        doNothing().when(jwtService).validateToken(token);

        // Then
        authService.validateToken(token);
        verify(jwtService).validateToken(eq(token));
    }
}
