package com.company.orders.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.orders.dto.ComputerInvoiceDto;
import com.company.orders.mapper.ComputerInvoiceMapper;
import com.company.orders.model.ComputerInvoice;
import com.company.orders.repository.ComputerInvoiceRepository;

@Service
public class ComputerInvoiceService {

    private final ComputerInvoiceRepository computerInvoiceRepository;
    private final ComputerInvoiceMapper computerInvoiceMapper;

    public ComputerInvoiceService(@Autowired ComputerInvoiceRepository computerInvoiceRepository,
            @Autowired ComputerInvoiceMapper computerInvoiceMapper) {
        this.computerInvoiceRepository = computerInvoiceRepository;
        this.computerInvoiceMapper = computerInvoiceMapper;
    }

    public List<ComputerInvoiceDto> getComputerInvoices() {
        final List<ComputerInvoice> computerInvoices = (List<ComputerInvoice>) computerInvoiceRepository.findAll();
        return computerInvoices.stream().map(computerInvoice -> computerInvoiceMapper.toDto(computerInvoice))
                .collect(Collectors.toList());
    }

    public void addComputerInvoice(ComputerInvoiceDto computerInvoiceDto) {
        final ComputerInvoice computerInvoice = computerInvoiceMapper.fromDto(computerInvoiceDto);
        computerInvoiceRepository.save(computerInvoice);
    }

    public List<ComputerInvoice> searchComputerInvoice(String namePart, LocalDate date) {
        return computerInvoiceRepository.searchByComputerNameAndPostingDate(namePart, date);
    }
}
