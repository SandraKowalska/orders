package com.company.orders.service;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.orders.model.ComputerInvoice;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class UpdateComputerCost {

    @Autowired
    BankHolidayService bankHolidayService;

    @PreUpdate
    @PrePersist
    public void setComputerCost_in_PLN(ComputerInvoice computerInvoice) {
        if (computerInvoice.getComputerCost_in_USD() != null) {
            LocalDate dayOfExchangeRate = getDayOfExchangeRate(computerInvoice);
            CurrencyExchangeRateService currencyExchangeRateService = SpringContext
                    .getBean(CurrencyExchangeRateService.class);
            Double currencyRateString = currencyExchangeRateService
                    .getCurrency(String.valueOf(dayOfExchangeRate));
            if (currencyRateString == null) {

            }
            Double CostOfPLN = currencyRateString * computerInvoice.getComputerCost_in_USD();
            computerInvoice.setComputerCost_in_PLN(CostOfPLN.intValue());
        }
    }

    private LocalDate getDayOfExchangeRate(ComputerInvoice computerInvoice) {
        LocalDate dayOfExchangeRate = computerInvoice.getPostingDate();
        boolean isBankHoliday = bankHolidayService.checkIsBankHoliday(dayOfExchangeRate);
        DayOfWeek dayOfWeek = dayOfExchangeRate.getDayOfWeek();
        while (isBankHoliday) {
            dayOfExchangeRate = dayOfExchangeRate.minusDays(1);
            isBankHoliday = bankHolidayService.checkIsBankHoliday(dayOfExchangeRate);
        }
        if (dayOfWeek == DayOfWeek.of(6)) {
            dayOfExchangeRate = dayOfExchangeRate.minusDays(1);
        }
        if (dayOfWeek == DayOfWeek.of(7)) {
            dayOfExchangeRate = dayOfExchangeRate.minusDays(2);
        }
        return dayOfExchangeRate;
    }
}
