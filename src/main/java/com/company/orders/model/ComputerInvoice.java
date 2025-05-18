package com.company.orders.model;

import java.time.LocalDate;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "computerInvoices")
@Data
public class ComputerInvoice {

    private String date;
    private String word;

    // @JdbcTypeCode(SqlTypes.INTEGER)
    // CurrencyExchangeRateService currencyEchangeRate = new CurrencyExchangeRateService(date);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nazwa")
    private String computerName;
    @Column(name = "data_ksiegowania")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate postingDate;
    @Column(name = "koszt_USD")
    private Integer computerCost_in_USD;
    @Column(name = "koszt_PLN")
    private Integer computerCost_in_PLN;

    public Integer getComputerCost_in_PLN() {
        return computerCost_in_PLN;
    }

    //TODO
    // public void setComputerCost_in_PLN(Integer computerCost_in_PLN) {
    //     this.computerCost_in_PLN = calucaltion(computerCost_in_USD);
    // }
    //   
    // public Integer calucaltion(Integer computerCost_in_USD) {
    //     return computerCost_in_USD *
    //             Integer.valueOf(currencyEchangeRate.getCurrency(String.valueOf(postingDate)));
    // }

}
