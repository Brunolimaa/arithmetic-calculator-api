package com.arithmetic.calculator.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.arithmetic.calculator.repository.RandomStringRepository;
import com.arithmetic.calculator.service.impl.RandomStringServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

public class RandomStringServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RandomStringRepository repository;

    @InjectMocks
    private RandomStringServiceImpl randomStringServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRandomString_ReturnsRandomString() {
        when(repository.getRandomString()).thenReturn("string-teste");

        String result = randomStringServiceImpl.getRandomString();
        assertNotNull(result);
    }
}
