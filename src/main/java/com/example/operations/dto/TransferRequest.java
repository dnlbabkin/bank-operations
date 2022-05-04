package com.example.operations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferRequest {

    @JsonProperty("accountNumber")
    String accountNumber;

    @JsonProperty("currentCurrency")
    String currentCurrency;

    @JsonProperty("currentAmount")
    BigDecimal currentAmount;

}
