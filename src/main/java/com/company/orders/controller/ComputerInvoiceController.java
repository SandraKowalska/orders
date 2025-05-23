package com.company.orders.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.orders.dto.ComputerInvoiceDto;
import com.company.orders.model.ComputerInvoice;
import com.company.orders.service.ComputerInvoiceService;

@RestController
public class ComputerInvoiceController {

    private final ComputerInvoiceService computerInvoiceService;

    public ComputerInvoiceController(ComputerInvoiceService computerInvoiceService) {
        this.computerInvoiceService = computerInvoiceService;
    }

    @GetMapping("/computerInvoices")
    public Collection<ComputerInvoiceDto> getComputerInvoicesDto() {
        return computerInvoiceService.getComputerInvoices();
    }

    @PostMapping("/addComputerInvoice")
    public String addComputerInvoice(@RequestBody ComputerInvoiceDto computerInvoiceDto) {
        this.computerInvoiceService.addComputerInvoice(computerInvoiceDto);
        return "Added Computer Invoice";
    }

    @GetMapping("/computerInvoices/search")
    public List<ComputerInvoice> searchComputerInvoice(@RequestParam String name,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return computerInvoiceService.searchComputerInvoice(name, date);
    }
}
