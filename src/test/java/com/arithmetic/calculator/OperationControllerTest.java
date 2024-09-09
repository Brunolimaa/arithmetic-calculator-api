package com.arithmetic.calculator;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.arithmetic.calculator.controller.OperationController;
import com.arithmetic.calculator.dto.response.OperationDTO;
import com.arithmetic.calculator.dto.response.RecordDTO;
import com.arithmetic.calculator.dto.response.UserDTO;
import com.arithmetic.calculator.service.CalculationService;
import config.TestSecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;

@WebMvcTest(OperationController.class)
@Import(TestSecurityConfig.class)
public class OperationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculationService calculationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPerformOperation() throws Exception {
        String type = "add";
        double n1 = 1.0;
        double n2 = 2.0;
        String expectedResult = "3.0";

        given(calculationService.performOperation(1L, type, n1, n2)).willReturn(expectedResult);

        mockMvc.perform(post("/api/v1/operations/{type}", type)
                        .param("number1", String.valueOf(n1))
                        .param("number2", String.valueOf(n2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResult));
    }

    @Test
    public void testGetAllOperations() throws Exception {
        Pageable pageable = PageRequest.of(0, 3);
        Page<RecordDTO> mockPage = new PageImpl<>(Collections.singletonList(new RecordDTO(1l, new OperationDTO(), new UserDTO(), Double.valueOf(1), Double.valueOf(2), "teste", LocalDateTime.now())), pageable, 1);

        given(calculationService.getAllOperations(pageable)).willReturn(mockPage);

        mockMvc.perform(get("/api/v1/operations")
                        .param("page", "0")
                        .param("size", "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value("1"));
    }
}