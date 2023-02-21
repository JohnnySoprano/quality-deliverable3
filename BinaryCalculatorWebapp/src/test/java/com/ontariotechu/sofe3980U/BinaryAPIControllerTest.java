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

    // To verify add endpoint and result of addition operation where operands are the same length
    @Test
    public void getAdd() throws Exception {
        this.mvc.perform(get("/add").param("operand1","101010").param("operand2","100010"))
            .andExpect(status().isOk())
            .andExpect(content().string("1001100"));
    }

    // To verify add endpoint and result of addition operation where operands are different length
    @Test
    public void getAdd2() throws Exception {
        this.mvc.perform(get("/add").param("operand1","101").param("operand2","111000"))
            .andExpect(status().isOk())
            .andExpect(content().string("111101"));
    }

    // To verify add endpoint and result of addition operation where there is a carry
    @Test
    public void getAdd3() throws Exception {
        this.mvc.perform(get("/add").param("operand1","101011").param("operand2","111111"))
            .andExpect(status().isOk())
            .andExpect(content().string("1101010"));
    }

    // To verify add endpoint and result of addition operation where first operand is null
    @Test
    public void getAdd4() throws Exception {
        this.mvc.perform(get("/add").param("operand1","").param("operand2","101010"))
            .andExpect(status().isOk())
            .andExpect(content().string("101010"));
    }

    // To verify add endpoint and result of addition operation where both operands are null
    @Test
    public void getAdd5() throws Exception {
        this.mvc.perform(get("/add").param("operand1","").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    // To verify add endpoint and result of addition operation where second operand is 0
    @Test
    public void getAdd6() throws Exception {
        this.mvc.perform(get("/add").param("operand1","101010").param("operand2","0"))
            .andExpect(status().isOk())
            .andExpect(content().string("101010"));
    }

    // To verify or endpoint and result of bitwise OR operation where operands are the same length
    @Test
    public void getOr() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "1001").param("operand2", "0101"))
            .andExpect(status().isOk())
            .andExpect(content().string("1101"));
    }

    // To verify or endpoint and result of bitwise OR operation where operands are different lengths
    @Test
    public void getOr2() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "1101").param("operand2", "11"))
            .andExpect(status().isOk())
            .andExpect(content().string("1111"));
    }

    // To verify or endpoint and result of bitwise OR operation where first operand is null
    @Test
    public void getOr3() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "").param("operand2", "1101"))
            .andExpect(status().isOk())
            .andExpect(content().string("1101"));
    }

    // To verify or endpoint and result of bitwise OR operation where both operands are null
    @Test
    public void getOr4() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    // To verify or endpoint and result of bitwise OR operation where second operand is zero
    @Test
    public void getOr5() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "110011").param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(content().string("110011"));
    }

    // To verify and endpoint and result of bitwise AND operation with the same length operands
    @Test
    public void getAnd() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "100101").param("operand2", "101010"))
            .andExpect(status().isOk())
            .andExpect(content().string("100000"));
    }

    // To verify and endpoint and result of bitwise AND operation with different length operands
    @Test
    public void getAnd2() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "1011").param("operand2", "110011"))
            .andExpect(status().isOk())
            .andExpect(content().string("11"));
    }

    // To verify and endpoint and result of bitwise AND operation where the first operand is null
    @Test
    public void getAnd3() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "").param("operand2", "110011"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    // To verify and endpoint and result of bitwise AND operation where both operands are null
    @Test
    public void getAnd4() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    // To verify and endpoint and result of bitwise AND operation where second operand is zero
    @Test
    public void getAnd5() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "111010").param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    // To verify multiply endpoint and result of multiplication operation with the same length operands
    @Test
    public void getMultiply() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "100010").param("operand2", "100001"))
            .andExpect(status().isOk())
            .andExpect(content().string("10001100010"));
    }

    // To verify multiply endpoint and result of multiplication operation with different length operands
    @Test
    public void getMultiply2() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "110").param("operand2", "100010"))
            .andExpect(status().isOk())
            .andExpect(content().string("11001100"));
    }

    // To verify multiply endpoint and result of multiplication operation where the result is twice the length of operands
    @Test
    public void getMultiply3() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "101010").param("operand2", "110111"))
            .andExpect(status().isOk())
            .andExpect(content().string("100100000110"));
    }

    // To verify multiply endpoint and result of multiplication operation where first operand is null
    @Test
    public void getMultiply4() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "").param("operand2", "110111"))
            .andExpect(status().isOk())
            .andExpect(content().string(""));
    }

    // To verify multiply endpoint and result of multiplication operation where both operands are null
    @Test
    public void getMultiply5() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(content().string(""));
    }

    // To verify add_json endpoint returns JSON response containing the correct output for addition operation where operands are same length
    @Test    
    public void addJSON() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "101010").param("operand2", "100010"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("101010"))
            .andExpect(jsonPath("$.operand2").value("100010"))
            .andExpect(jsonPath("$.result").value("1001100"))
            .andExpect(jsonPath("$.operator").value("add"));
    }

    // To verify add_json endpoint returns JSON response containing the correct output for addition operation where operands are different length
    @Test    
    public void addJSON2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "101").param("operand2", "111000"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("101"))
            .andExpect(jsonPath("$.operand2").value("111000"))
            .andExpect(jsonPath("$.result").value("111101"))
            .andExpect(jsonPath("$.operator").value("add"));
    }

    // To verify add_json endpoint returns JSON response containing the correct output for addition operation where result has carry
    @Test    
    public void addJSON3() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "101011").param("operand2", "111111"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("101011"))
            .andExpect(jsonPath("$.operand2").value("111111"))
            .andExpect(jsonPath("$.result").value("1101010"))
            .andExpect(jsonPath("$.operator").value("add"));
    }

    // To verify add_json endpoint returns JSON response containing the correct output for addition operation where first operand is null
    @Test    
    public void addJSON4() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "").param("operand2", "101010"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value(""))
            .andExpect(jsonPath("$.operand2").value("101010"))
            .andExpect(jsonPath("$.result").value("101010"))
            .andExpect(jsonPath("$.operator").value("add"));
    }

    // To verify add_json endpoint returns JSON response containing the correct output for addition operation where both operands are null
    @Test    
    public void addJSON5() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value(""))
            .andExpect(jsonPath("$.operand2").value(""))
            .andExpect(jsonPath("$.result").value("0"))
            .andExpect(jsonPath("$.operator").value("add"));
    }

    // To verify add_json endpoint returns JSON response containing the correct output for addition operation where second operand is zero
    @Test    
    public void addJSON6() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "101010").param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("101010"))
            .andExpect(jsonPath("$.result").value("101010"))
            .andExpect(jsonPath("$.operator").value("add"));
    }

    // To verify or_json endpoint returns JSON response containing the correct output for bitwise OR operation where both operands are same length
    @Test
    public void orJSON() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1", "110011").param("operand2", "101010"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("110011"))
            .andExpect(jsonPath("$.operand2").value("101010"))
            .andExpect(jsonPath("$.result").value("111011"))
            .andExpect(jsonPath("$.operator").value("or"));
    }
    
    // To verify or_json endpoint returns JSON response containing the correct output for bitwise OR operation where both operands are different length
    @Test
    public void orJSON2() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1", "101").param("operand2", "100100"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("101"))
            .andExpect(jsonPath("$.operand2").value("100100"))
            .andExpect(jsonPath("$.result").value("100101"))
            .andExpect(jsonPath("$.operator").value("or"));
    }

    // To verify or_json endpoint returns JSON response containing the correct output for bitwise OR operation where first operand is null
    @Test
    public void orJSON3() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1", "").param("operand2", "100100"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value(""))
            .andExpect(jsonPath("$.operand2").value("100100"))
            .andExpect(jsonPath("$.result").value("100100"))
            .andExpect(jsonPath("$.operator").value("or"));
    }

    // To verify or_json endpoint returns JSON response containing the correct output for bitwise OR operation where both operands are null
    @Test
    public void orJSON4() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1", "").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value(""))
            .andExpect(jsonPath("$.operand2").value(""))
            .andExpect(jsonPath("$.result").value("0"))
            .andExpect(jsonPath("$.operator").value("or"));
    }
    
    // To verify or_json endpoint returns JSON response containing the correct output for bitwise OR operation where second operand is zero
    @Test
    public void orJSON5() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1", "110011").param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("110011"))
            .andExpect(jsonPath("$.result").value("110011"))
            .andExpect(jsonPath("$.operator").value("or"));
    }

    // To verify and_json endpoint returns JSON response containing the correct output for bitwise AND operation where operands are same length
    @Test
    public void andJSON() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1", "100101").param("operand2", "101010"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("100101"))
            .andExpect(jsonPath("$.operand2").value("101010"))
            .andExpect(jsonPath("$.result").value("100000"))
            .andExpect(jsonPath("$.operator").value("and"));
    }

    // To verify and_json endpoint returns JSON response containing the correct output for bitwise AND operation where operands are different length
    @Test
    public void andJSON2() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1", "1011").param("operand2", "110011"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("1011"))
            .andExpect(jsonPath("$.operand2").value("110011"))
            .andExpect(jsonPath("$.result").value("11"))
            .andExpect(jsonPath("$.operator").value("and"));
    }

    // To verify and_json endpoint returns JSON response containing the correct output for bitwise AND operation where first operand is null
    @Test
    public void andJSON3() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1", "").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value(""))
            .andExpect(jsonPath("$.operand2").value("1010"))
            .andExpect(jsonPath("$.result").value("0"))
            .andExpect(jsonPath("$.operator").value("and"));
    }

    // To verify and_json endpoint returns JSON response containing the correct output for bitwise AND operation where both operands are null
    @Test
    public void andJSON4() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1", "").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value(""))
            .andExpect(jsonPath("$.operand2").value(""))
            .andExpect(jsonPath("$.result").value("0"))
            .andExpect(jsonPath("$.operator").value("and"));
    }

    // To verify and_json endpoint returns JSON response containing the correct output for bitwise AND operation where second operand is zero
    @Test
    public void andJSON5() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1", "111010").param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("111010"))
            .andExpect(jsonPath("$.result").value("0"))
            .andExpect(jsonPath("$.operator").value("and"));
    }

    // To verify multiply_json endpoint returns JSON response containing the correct output for multiplication operation where operands are same length
    @Test
    public void multiplyJSON() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1", "100010").param("operand2", "100001"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("100010000000"))
            .andExpect(jsonPath("$.operand2").value("100001"))
            .andExpect(jsonPath("$.result").value("10001100010"))
            .andExpect(jsonPath("$.operator").value("multiply"));
    }

    // To verify multiply_json endpoint returns JSON response containing the correct output for multiplication operation where operands are different length
    @Test
    public void multiplyJSON2() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1", "110").param("operand2", "100010"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("110000000"))
            .andExpect(jsonPath("$.operand2").value("100010"))
            .andExpect(jsonPath("$.result").value("11001100"))
            .andExpect(jsonPath("$.operator").value("multiply"));
    }

    // To verify multiply_json endpoint returns JSON response containing the correct output for multiplication operation where result is twice the length of operands
    @Test
    public void multiplyJSON3() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1", "101010").param("operand2", "110111"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("101010000000"))
            .andExpect(jsonPath("$.operand2").value("110111"))
            .andExpect(jsonPath("$.result").value("100100000110"))
            .andExpect(jsonPath("$.operator").value("multiply"));
    }

    // To verify multiply_json endpoint returns JSON response containing the correct output for multiplication operation where first operand is null
    @Test
    public void multiplyJSON4() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1", "").param("operand2", "110111"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("000000"))
            .andExpect(jsonPath("$.operand2").value("110111"))
            .andExpect(jsonPath("$.result").value(""))
            .andExpect(jsonPath("$.operator").value("multiply"));
    }

    // To verify multiply_json endpoint returns JSON response containing the correct output for multiplication operation where both operands are null
    @Test
    public void multiplyJSON5() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1", "").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value(""))
            .andExpect(jsonPath("$.operand2").value(""))
            .andExpect(jsonPath("$.result").value(""))
            .andExpect(jsonPath("$.operator").value("multiply"));
    }
}