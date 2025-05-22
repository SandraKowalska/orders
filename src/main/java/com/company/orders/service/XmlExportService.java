package com.company.orders.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.orders.documents.ComputerInvoiceWrapper;
import com.company.orders.model.ComputerInvoice;
import com.company.orders.repository.ComputerInvoiceRepository;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

@Service
public class XmlExportService {

    @Autowired
    private ComputerInvoiceRepository computerInvoiceRepository;

    public void exportComputerInvoicesToXml(String filePath) throws JAXBException, IOException {
        List<ComputerInvoice> computerInvoices = computerInvoiceRepository.findAll();

        ComputerInvoiceWrapper wrapper = new ComputerInvoiceWrapper();
        wrapper.setComputerInvoices(computerInvoices);

        JAXBContext context = JAXBContext.newInstance(ComputerInvoiceWrapper.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            marshaller.marshal(wrapper, fileOutputStream);
        }
    }

}
