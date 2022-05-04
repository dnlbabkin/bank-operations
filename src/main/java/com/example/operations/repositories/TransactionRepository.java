package com.example.operations.repositories;

import com.example.operations.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findByAccountNumberEquals(String accountNumber);

    Transaction findTransactionById(Long id);

}
