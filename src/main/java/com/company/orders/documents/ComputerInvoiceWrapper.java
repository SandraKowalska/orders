package com.company.orders.documents;

import java.util.ArrayList;
import java.util.List;

import com.company.orders.model.ComputerInvoice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "faktura")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ComputerInvoiceWrapper {

    @XmlElement(name = "komputer")
    private List<ComputerInvoice> computerInvoices = new ArrayList<>();
}
