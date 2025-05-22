package com.company.orders.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CurrencyExchangeRateService {

    private static final String API = "https://api.nbp.pl/api/exchangerates/rates/c/usd/";
    private Double usdExchangeRateResponse;

    RestTemplate restTemplate = new RestTemplate();

    public Double getCurrency(String date) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        ResponseEntity<String> response = restTemplate.getForEntity(API + date + "/?format=json", String.class);
        try {
            root = mapper.readTree(response.getBody());
            JsonNode name = root.findValue("ask");
            usdExchangeRateResponse = Double.valueOf(String.valueOf(name));
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return usdExchangeRateResponse;
    }
}
