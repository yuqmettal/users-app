package com.nisum.users.application.services;

import com.nisum.users.aplication.services.JWTService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JWTServiceTests {
    private JWTService jwtService;

    private static final String JWT_SECRET = "6c363a15-44dc-4bda-b247-46f93af5014b";

    @BeforeEach
    public void setup() {
        jwtService = new JWTService();
        ReflectionTestUtils.setField(jwtService, "JWT_SECRET", JWT_SECRET);
    }

    @Test
    public void shouldCreateTokenSuccessfully() {
        String email = "test@example.com";
        String token = jwtService.createToken(email);

        assertNotNull(token);

        String subject = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        assertEquals(email, subject);
    }
}
