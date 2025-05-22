package com.company.orders.model;

import java.time.LocalDate;

import com.company.orders.documents.LocalDateAdapter;
import com.company.orders.service.UpdateComputerCost;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

@Entity
@EntityListeners(UpdateComputerCost.class)
@Table(name = "faktura")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ComputerInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;
    @Column(name = "nazwa")
    @XmlElement(name = "nazwa")
    private String computerName;
    @Column(name = "data_ksiegowania")
    @XmlElement(name = "data_ksiegowania")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate postingDate;
    @Column(name = "koszt_USD")
    @XmlElement(name = "koszt_USD")
    private Integer computerCost_in_USD;
    @Column(name = "koszt_PLN")
    @XmlElement(name = "koszt_PLN")
    private Integer computerCost_in_PLN;
}
