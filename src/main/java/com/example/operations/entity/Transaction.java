package com.example.operations.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Entity
@Table(name = "account_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "accountnumber")
    private String accountNumber;

    @Column(name = "currency")
    private String currency;

    @Column(name = "transactionamount")
    private BigDecimal amount;
    
}