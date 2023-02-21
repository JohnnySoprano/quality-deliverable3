package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    // To verify add endpoint and result of addition operation
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }

    // To verify multiply endpoint and result of multiplication operation
    @Test
    public void multiply() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "101").param("operand2", "1100"))
            .andExpect(status().isOk())
            .andExpect(content().string("111100"));
    }

    // To verify or endpoint and result of bitwise OR operation
    @Test
    public void or() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "100").param("operand2", "1001"))
            .andExpect(status().isOk())
            .andExpect(content().string("1101"));
    }

    // To verify and endpoint and result of bitwise AND operation
    @Test
    public void and() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "111").param("operand2", "1101"))
            .andExpect(status().isOk())
            .andExpect(content().string("101"));
    }

    // To verify add_json endpoint returns JSON response containing the correct output for addition operation
    @Test
    public void addJSON() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "1010").param("operand2", "1001"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value(1010))
            .andExpect(jsonPath("$.operand2").value(1001))
            .andExpect(jsonPath("$.result").value(10011))
            .andExpect(jsonPath("$.operator").value("add"));
    }

    // To verify multiply_json endpoint returns JSON response containing the correct output for multiplication operation
    @Test
    public void multiplyJSON() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1", "01").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("10000"))
            .andExpect(jsonPath("$.operand2").value("1010"))
            .andExpect(jsonPath("$.result").value("1010"))
            .andExpect(jsonPath("$.operator").value("multiply"));
    }

    // To verify and_json endpoint returns JSON response containing the correct output for bitwise AND operation
    @Test
    public void andJSON() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1", "1011").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value(1011))
            .andExpect(jsonPath("$.operand2").value(1010))
            .andExpect(jsonPath("$.result").value(1010))
            .andExpect(jsonPath("$.operator").value("and"));
    }

    // To verify or_json endpoint returns JSON response containing the correct output for bitwise OR operation
    @Test
    public void orJSON() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1", "111").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value(111))
            .andExpect(jsonPath("$.operand2").value(1010))
            .andExpect(jsonPath("$.result").value(1111))
            .andExpect(jsonPath("$.operator").value("or"));
    }
}