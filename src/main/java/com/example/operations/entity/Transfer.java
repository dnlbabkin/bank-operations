package com.example.operations.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accountnumber")
    private String accountNumber;

    @Column(name = "currentcurrency")
    private String currency;

    @Column(name = "currentamount")
    private BigDecimal amount;

}
