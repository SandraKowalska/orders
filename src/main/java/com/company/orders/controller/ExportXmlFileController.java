package com.company.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.orders.service.XmlExportService;

@RestController
@RequestMapping("/export")
public class ExportXmlFileController {

    @Autowired
    private XmlExportService exportService;

    @GetMapping("/computerInvoices")
    public ResponseEntity<String> exportComputerInvoices() {
        try {
            exportService.exportComputerInvoicesToXml("faktury.xml");
            return ResponseEntity.ok("Export successful");
        } catch (Exception exception) {
            return ResponseEntity.status(500).body("Export failed: " + exception.getMessage());
        }
    }
}
