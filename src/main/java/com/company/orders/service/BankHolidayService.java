package com.company.orders.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BankHolidayService {

    private static final String API = "https://date.nager.at/api/v3/publicholidays/";
    private boolean isBankHoliday = false;
    private Map<String, ResponseEntity<String>> yearToResponseMap = new HashMap<>();

    RestTemplate restTemplate = new RestTemplate();

    public boolean checkIsBankHoliday(LocalDate date) {
        String year = String.valueOf(date.getYear());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        ResponseEntity<String> response;
        if (yearToResponseMap.getOrDefault(year, null) == null) {
            response = restTemplate.getForEntity(API + year + "/PL", String.class);
            yearToResponseMap.put(year, response);
        } else {
            response = yearToResponseMap.get(year);
        }
        try {
            root = mapper.readTree(response.getBody());
            List<String> name = root.findValuesAsText("date");
            if (name.contains(String.valueOf(date))) {
                isBankHoliday = true;
            } else {
                isBankHoliday = false;
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return isBankHoliday;
    }
}