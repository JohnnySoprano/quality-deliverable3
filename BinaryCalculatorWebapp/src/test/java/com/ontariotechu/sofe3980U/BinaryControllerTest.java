package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

    // Checks if the default page loads with expected attributes
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
    // Checks if the model is correctly updated when the calculator page is loaded with operand1
	@Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }

    // Checks if the application can handle a POST request and return correct result
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"))
            .andExpect(model().attribute("operand2", "111"))
            .andExpect(model().attribute("operator", "+"));
    }

    // Checks if error view is returned when an invalid operator is inputed
    @Test
    public void postInvalidOperator() throws Exception {
        this.mvc.perform(post("/").param("operand1", "111").param("operator", "%").param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(view().name("Error"));
    }

    // Addition operation where operands are the same length
    @Test    
    public void postAdd() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101010").param("operator", "+").param("operand2", "100010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1001100"))
            .andExpect(model().attribute("operand1", "101010"))
            .andExpect(model().attribute("operand2", "100010"))
            .andExpect(model().attribute("operator", "+"));
    }

    // Addition operation where operands are different length
    @Test    
    public void postAdd2() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "+").param("operand2", "111000"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "111101"))
            .andExpect(model().attribute("operand1", "101"))
            .andExpect(model().attribute("operand2", "111000"))
            .andExpect(model().attribute("operator", "+"));
    }

    // Addition operation where there is a carry
    @Test    
    public void postAdd3() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101011").param("operator", "+").param("operand2", "111111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1101010"))
            .andExpect(model().attribute("operand1", "101011"))
            .andExpect(model().attribute("operand2", "111111"))
            .andExpect(model().attribute("operator", "+"));
    }

    // Addition operation where first operand is null
    @Test    
    public void postAdd4() throws Exception {
        this.mvc.perform(post("/").param("operand1", "").param("operator", "+").param("operand2", "101010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "101010"))
            .andExpect(model().attribute("operand1", ""))
            .andExpect(model().attribute("operand2", "101010"))
            .andExpect(model().attribute("operator", "+"));
    }

    // Addition operation where both operands are null
    @Test    
    public void postAdd5() throws Exception {
        this.mvc.perform(post("/").param("operand1", "").param("operator", "+").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", ""))
            .andExpect(model().attribute("operand2", ""))
            .andExpect(model().attribute("operator", "+"));
    }

    // Addition operation where second operand is zero
    @Test    
    public void postAdd6() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101010").param("operator", "+").param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "101010"))
            .andExpect(model().attribute("operand1", "101010"))
            .andExpect(model().attribute("operand2", "0"))
            .andExpect(model().attribute("operator", "+"));
    }

    // Bitwise OR operation where operands are the same length
    @Test
    public void postOr() throws Exception {
        this.mvc.perform(post("/").param("operand1", "110011").param("operator", "|").param("operand2", "101010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "111011"))
            .andExpect(model().attribute("operand1", "110011"))
            .andExpect(model().attribute("operand2", "101010"))
            .andExpect(model().attribute("operator", "|"));
    }

    // Bitwise OR operation where operands are the different length
    @Test
    public void postOr2() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "|").param("operand2", "100100"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "100101"))
            .andExpect(model().attribute("operand1", "101"))
            .andExpect(model().attribute("operand2", "100100"))
            .andExpect(model().attribute("operator", "|"));
    }

    // Bitwise OR operation where first operand is null
    @Test
    public void postOr3() throws Exception {
        this.mvc.perform(post("/").param("operand1", "").param("operator", "|").param("operand2", "100100"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "100100"))
            .andExpect(model().attribute("operand1", ""))
            .andExpect(model().attribute("operand2", "100100"))
            .andExpect(model().attribute("operator", "|"));
    }

    // Bitwise OR operation where both operands are null
    @Test
    public void postOr4() throws Exception {
        this.mvc.perform(post("/").param("operand1", "").param("operator", "|").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", ""))
            .andExpect(model().attribute("operand2", ""))
            .andExpect(model().attribute("operator", "|"));
    }

    // Bitwise OR operation where second operand is zero
    @Test
    public void postOr5() throws Exception {
        this.mvc.perform(post("/").param("operand1", "110011").param("operator", "|").param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "110011"))
            .andExpect(model().attribute("operand1", "110011"))
            .andExpect(model().attribute("operand2", "0"))
            .andExpect(model().attribute("operator", "|"));
    }

    // Bitwise AND operation where operands are the same length
    @Test
    public void postAnd() throws Exception {
        this.mvc.perform(post("/").param("operand1", "100101").param("operator", "&").param("operand2", "101010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "100000"))
            .andExpect(model().attribute("operand1", "100101"))
            .andExpect(model().attribute("operand2", "101010"))
            .andExpect(model().attribute("operator", "&"));
    }

    // Bitwise AND operation where operands are the different length
    @Test
    public void postAnd2() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1011").param("operator", "&").param("operand2", "110011"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "11"))
            .andExpect(model().attribute("operand1", "1011"))
            .andExpect(model().attribute("operand2", "110011"))
            .andExpect(model().attribute("operator", "&"));
    }

    // Bitwise AND operation where first operand is null
    @Test
    public void postAnd3() throws Exception {
        this.mvc.perform(post("/").param("operand1", "").param("operator", "&").param("operand2", "110011"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", ""))
            .andExpect(model().attribute("operand2", "110011"))
            .andExpect(model().attribute("operator", "&"));
    }

    // Bitwise AND operation where both operands are null
    @Test
    public void postAnd4() throws Exception {
        this.mvc.perform(post("/").param("operand1", "").param("operator", "&").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", ""))
            .andExpect(model().attribute("operand2", ""))
            .andExpect(model().attribute("operator", "&"));
    }

    // Bitwise AND operation where second operand is zero
    @Test
    public void postAnd5() throws Exception {
        this.mvc.perform(post("/").param("operand1", "111010").param("operator", "&").param("operand2", "0"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", "111010"))
            .andExpect(model().attribute("operand2", "0"))
            .andExpect(model().attribute("operator", "&"));
    }

    // Multiplication operation where operands are the same length
    @Test
    public void postMultiply() throws Exception {
        this.mvc.perform(post("/").param("operand1", "100010").param("operator", "*").param("operand2", "100001"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "10001100010"))
            .andExpect(model().attribute("operand1", "100010"))
            .andExpect(model().attribute("operand2", "100001"))
            .andExpect(model().attribute("operator", "*"));
    }

    // Multiplication operation where operands are the different length
    @Test
    public void postMultiply2() throws Exception {
        this.mvc.perform(post("/").param("operand1", "110").param("operator", "*").param("operand2", "100010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "11001100"))
            .andExpect(model().attribute("operand1", "110"))
            .andExpect(model().attribute("operand2", "100010"))
            .andExpect(model().attribute("operator", "*"));
    }

    // Multiplication operation where the result is twice the length of both operands
    @Test
    public void postMultiply3() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101010").param("operator", "*").param("operand2", "110111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "100100000110"))
            .andExpect(model().attribute("operand1", "101010"))
            .andExpect(model().attribute("operand2", "110111"))
            .andExpect(model().attribute("operator", "*"));
    }

    // Multiplication operation where first operand is null
    @Test
    public void postMultiply4() throws Exception {
        this.mvc.perform(post("/").param("operand1", "").param("operator", "*").param("operand2", "110111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", ""))
            .andExpect(model().attribute("operand1", ""))
            .andExpect(model().attribute("operand2", "110111"))
            .andExpect(model().attribute("operator", "*"));
    }

    // Multiplication operation where both operands are null
    @Test
    public void postMultiply5() throws Exception {
        this.mvc.perform(post("/").param("operand1", "").param("operator", "*").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", ""))
            .andExpect(model().attribute("operand1", ""))
            .andExpect(model().attribute("operand2", ""))
            .andExpect(model().attribute("operator", "*"));
    }
}