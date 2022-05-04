package com.example.operations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class TransferBodyRequest {

    @JsonProperty("fromAccountNumber")
    String fromAccountNumber;

    @JsonProperty("toAccountNumber")
    String toAccountNumber;

    String currency;

    BigDecimal amount;

}
