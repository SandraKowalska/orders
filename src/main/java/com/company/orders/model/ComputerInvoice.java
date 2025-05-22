package com.company.orders.model;

import java.time.LocalDate;

import com.company.orders.service.UpdateComputerCost;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@EntityListeners(UpdateComputerCost.class)
@Table(name = "faktura")
@Data
public class ComputerInvoice {

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
}
