package com.company.orders.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ComputerInvoiceDto {
    private Long id;
    private String computerName;
    private LocalDate postingDate;
    private Integer computerCost_in_USD;
    private Integer computerCost_in_PLN;
}
