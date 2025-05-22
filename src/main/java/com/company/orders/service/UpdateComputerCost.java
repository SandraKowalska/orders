package com.company.orders.service;

import com.company.orders.model.ComputerInvoice;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class UpdateComputerCost {

    @PreUpdate
    @PrePersist
    public void setComputerCost_in_PLN(ComputerInvoice computerInvoice) {
        if (computerInvoice.getComputerCost_in_USD() != null) {
            CurrencyExchangeRateService currencyExchangeRateService = SpringContext
                    .getBean(CurrencyExchangeRateService.class);
            Double currencyRateString = currencyExchangeRateService
                    .getCurrency(String.valueOf(computerInvoice.getPostingDate()));
            Double CostOfPLN = currencyRateString * computerInvoice.getComputerCost_in_USD();
            computerInvoice.setComputerCost_in_PLN(CostOfPLN.intValue());
        }
    }
}
