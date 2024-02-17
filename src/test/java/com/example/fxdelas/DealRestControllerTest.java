package com.example.fxdelas;

import com.example.fxdelas.entity.Deal;
import com.example.fxdelas.service.DealService;
import com.example.fxdelas.service.DealServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.Random;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DealRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DealService dealService;
    @Test
    public void test_Deal_Addition() throws Exception {
        Random random = new Random();
        Deal deal = new Deal();
        deal.setDealUniqueId((long)random.nextInt(100000));
        deal.setFromCurrencyISOCode("EUR");
        deal.setToCurrencyISOCode("USD");
        deal.setDealTimeStamp(LocalDateTime.now());
        deal.setDealAmount(3882.7);


        ResultActions resultActions = mockMvc.perform(
                post("/api/deals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deal))
        );

        resultActions.andExpect(status().isOk())
                .andExpect(content().string(equalTo("deal with id " + deal.getDealUniqueId() + " saved successfully")));
    }
    @Test
    public void test_Non_Duplicate_Request() throws Exception{

        // first i will use findById method tho check for already existed deal
        // then i will try to make a request to add the deal that already stored in db

        Deal existedDeal = dealService.getDealByUniqueId(1145588L);

        ResultActions resultActions = mockMvc.perform(
                post("/api/deals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(existedDeal))
        );

        resultActions.andExpect(status().isOk())
                .andExpect(content().string(equalTo("deal with id" +" "+existedDeal.getDealUniqueId()+" "+"is already existed")));
    }

    @Test
    public void test_Missing_Fields_Validation() throws Exception{
        Deal deal = new Deal();
        deal.setDealUniqueId(11234342L);
        deal.setFromCurrencyISOCode(""); // missing
        deal.setToCurrencyISOCode("USD");
        deal.setDealTimeStamp(LocalDateTime.now());
        deal.setDealAmount(3443.45);

        ResultActions resultActions = mockMvc.perform(
                post("/api/deals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deal))
        );
        resultActions.andExpect(status().isOk())
                .andExpect(content().string(equalTo("from currency ISOCode and to currency ISOCode both are required")));
    }


}
