package com.testTask;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTesting {

    @Autowired
    private  MockMvc mockMvc;

    @Test
    public void getUser () throws Exception {
        mockMvc.perform(get("/user/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.password", is("123")))
                .andExpect(jsonPath("$.mail", is("test@gmail")))
                .andExpect(jsonPath("$.balance", is(10.0)));
    }

    @Test
    public void getUserList () throws Exception {
        mockMvc.perform(get("/user/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(10)));

    }
    @Test
    public void getBalance () throws Exception {
        mockMvc.perform(get("/balance/user/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.balance", is(10.0)));
    }

    @Test
    public void getDeposit() throws Exception {
        String body = "{\"value\": 22.6}";
        mockMvc.perform(post("/deposit/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andDo(print())
				.andExpect(status().isOk());

        mockMvc.perform(get("/balance/user/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.balance", is(22.6)));
    }

    @Test
    public void getWithdraw() throws Exception {
        String body = "{\"value\": 12.6}";
        mockMvc.perform(post("/withdraw/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get("/balance/user/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.balance", is(10.0)));
    }

    @Test
    public void getHistory() throws Exception {
        String body = "{\"value\": 20}";
        mockMvc.perform(post("/deposit/user/7")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());

        body = "{\"value\": 10}";
        mockMvc.perform(post("/withdraw/user/7")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());

        mockMvc.perform(get("/history/user/7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].amountOfMoney", is(20.0)))
                .andExpect(jsonPath("$[0].transactionType", is("Deposit")))
                .andExpect(jsonPath("$[1].amountOfMoney", is(10.0)))
                .andExpect(jsonPath("$[1].transactionType", is("Withdraw")));
    }

}
