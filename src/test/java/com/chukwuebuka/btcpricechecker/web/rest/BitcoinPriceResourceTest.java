package com.chukwuebuka.btcpricechecker.web.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.chukwuebuka.btcpricechecker.BtcPriceCheckerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@SpringBootTest(classes = {BtcPriceCheckerApplication.class})
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class BitcoinPriceResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenValidCurrencyCode_canGetCurrentPrice() throws Exception {
        mockMvc.perform(get("/price/current-price/USD"))
               .andExpect(status().isOk())
               .andExpect((content().contentType(MediaType.APPLICATION_JSON_VALUE)))
               .andExpect(jsonPath("$.code").exists())
               .andExpect(jsonPath("$.price").exists());
    }

    @Test
    void givenInvalidCurrencyCode_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/price/current-price/***"))
               .andExpect(status().isBadRequest())
               .andExpect((content().contentType(MediaType.APPLICATION_JSON_VALUE)))
               .andExpect(jsonPath("$.success").exists())
               .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void givenNonExistingCurrencyCode_shouldNotFound() throws Exception {
        mockMvc.perform(get("/price/current-price/NGN"))
               .andExpect(status().isNotFound())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(jsonPath("$.success").exists())
               .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void givenValidCurrencyCodeAndDates_canGetPriceRange() throws Exception {
        mockMvc.perform(get("/price/range/USD")
                                .queryParam("startDate", "2020-01-01")
                                .queryParam("endDate", "2020-01-15"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(jsonPath("$.prices").exists());
    }

    @Test
    void givenValidCurrencyCodeAndWrongDateFormat_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/price/range/USD")
                                .queryParam("startDate", "2020/01/01")
                                .queryParam("endDate", "2020/01/15"))
               .andExpect(status().isBadRequest())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void givenInvalidCurrencyCodeRightDates_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/price/range/***")
                                .queryParam("startDate", "2020-01-01")
                                .queryParam("endDate", "2020-01-15"))
               .andExpect(status().isBadRequest())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }
}