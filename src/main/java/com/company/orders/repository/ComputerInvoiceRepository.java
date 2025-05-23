package com.company.orders.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.orders.model.ComputerInvoice;

@Repository
public interface ComputerInvoiceRepository extends JpaRepository<ComputerInvoice, Long> {

    @Query("SELECT c FROM ComputerInvoice c WHERE LOWER(c.computerName) LIKE LOWER(CONCAT('%',  :name, '%')) AND c.postingDate = :date ORDER BY c.computerName ASC, c.postingDate ASC")
    List<ComputerInvoice> searchByComputerNameAndPostingDate(@Param("name") String name, @Param("date") LocalDate date);
}
