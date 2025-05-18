package com.company.orders.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.company.orders.model.ComputerInvoice;

@Repository
public interface ComputerInvoiceRepository extends JpaRepository<ComputerInvoice, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM users WHERE (name LIKE CONCAT('%', ?1, '%')) AND points > ?2 AND removed = false"
    )
    Collection<ComputerInvoice> findAllByWordContaining(String word);

}
