package com.example.operations.repositories;

import com.example.operations.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    Transfer findByAccountNumber(String fromAccountNumber);

}
