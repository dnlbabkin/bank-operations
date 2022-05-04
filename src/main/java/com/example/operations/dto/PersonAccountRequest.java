package com.example.operations.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class PersonAccountRequest {

    String currency;
    BigDecimal amount;

}
