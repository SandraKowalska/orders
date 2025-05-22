package com.company.orders.documents;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public LocalDate unmarshal(String formatOfDate) {
        return (formatOfDate == null || formatOfDate.isEmpty()) ? null : LocalDate.parse(formatOfDate, FORMATTER);
    }

    @Override
    public String marshal(LocalDate formatOfDate) {
        return (formatOfDate == null ? null : formatOfDate.format(FORMATTER));
    }

}
