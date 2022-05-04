package com.example.operations.dto;

import lombok.Value;

import java.math.BigDecimal;


@Value
public class TransferRequest {

    String accountNumber;
    String currentCurrency;
    BigDecimal currentAmount;

}
