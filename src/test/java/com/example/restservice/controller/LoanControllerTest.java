package com.example.restservice.controller;

import com.example.restservice.service.ILoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
class LoanControllerTest {

    @Autowired
    private MockMvc controller;

    @MockBean
    private ILoanService loanService;

    @Test
    void getLoan() throws Exception {
        // TODO: fix
        assertTrue(true);
    }

}