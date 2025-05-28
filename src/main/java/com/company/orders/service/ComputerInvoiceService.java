package com.company.orders.service;

import java.time.LocalDate;
import java.util.Comparator;
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

    public List<ComputerInvoiceDto> searchComputerInvoice(String computerName, LocalDate postingDate, String sort) {
        final List<ComputerInvoice> computerInvoices = (List<ComputerInvoice>) computerInvoiceRepository.findAll();
        return computerInvoices.stream().map(computerInvoice -> computerInvoiceMapper.toDto(computerInvoice))
                .filter(c -> computerName == null
                        || c.getComputerName().toLowerCase().contains(computerName.toLowerCase()))
                .filter(c -> postingDate == null || c.getPostingDate().equals(postingDate))
                .sorted(getComparator(sort))
                .collect(Collectors.toList());
    }

    private Comparator<ComputerInvoiceDto> getComparator(String sort) {
        if (sort == null)
            return Comparator.comparing(ComputerInvoiceDto::getComputerName);
        boolean desc = sort.startsWith("-");
        String field = desc ? sort.substring(1) : sort;
        Comparator<ComputerInvoiceDto> comparator = switch (field) {
            case "computerName" -> Comparator.comparing(ComputerInvoiceDto::getComputerName);
            case "postingDate" -> Comparator.comparing(ComputerInvoiceDto::getPostingDate);
            default -> Comparator.comparing(ComputerInvoiceDto::getComputerName);
        };
        return desc ? comparator.reversed() : comparator;
    }

    public String deleteComputerInvoice(Long id) {
        computerInvoiceRepository.deleteById(id);
        return String.format("Computer invoice has been deleted", id);
    }
}
