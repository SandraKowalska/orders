package com.company.orders.mapper;

import org.springframework.stereotype.Component;

import com.company.orders.dto.ComputerInvoiceDto;
import com.company.orders.model.ComputerInvoice;

@Component
public class ComputerInvoiceMapper {

    public ComputerInvoiceDto toDto(ComputerInvoice computerInvoice) {
        ComputerInvoiceDto dto = new ComputerInvoiceDto();
        dto.setId(computerInvoice.getId());
        dto.setComputerName(computerInvoice.getComputerName());
        dto.setPostingDate(computerInvoice.getPostingDate());
        dto.setComputerCost_in_USD(computerInvoice.getComputerCost_in_USD());
        dto.setComputerCost_in_PLN(computerInvoice.getComputerCost_in_PLN());
        return dto;
    }

    public ComputerInvoice fromDto(ComputerInvoiceDto dto) {
        ComputerInvoice computerInvoice = new ComputerInvoice();
        computerInvoice.setId(dto.getId());
        computerInvoice.setComputerName(dto.getComputerName());
        computerInvoice.setPostingDate(dto.getPostingDate());
        computerInvoice.setComputerCost_in_USD(dto.getComputerCost_in_USD());
        computerInvoice.setComputerCost_in_PLN(dto.getComputerCost_in_PLN());
        return computerInvoice;
    }
}
