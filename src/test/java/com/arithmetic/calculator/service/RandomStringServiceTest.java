package com.arithmetic.calculator.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.arithmetic.calculator.repository.RandomStringRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

public class RandomStringServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RandomStringRepository repository;

    @InjectMocks
    private RandomStringService randomStringService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRandomString_ReturnsRandomString() {
        when(repository.getRandomString()).thenReturn("string-teste");

        String result = randomStringService.getRandomString();
        assertNotNull(result);
    }
}
