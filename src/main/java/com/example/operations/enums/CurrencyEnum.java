package com.example.operations.enums;

import com.example.operation.AllData;

import java.math.BigDecimal;
import java.util.function.Function;

public enum CurrencyEnum {

    USD("usd", (AllData.MainIndicatorsVR.Currency currency) -> currency.getUSD().getCurs());

    String abbreviation;
    Function<AllData.MainIndicatorsVR.Currency, BigDecimal> exchangeFunction;

    CurrencyEnum(String abbreviation, Function<AllData.MainIndicatorsVR.Currency, BigDecimal> exchangeFunction) {
        this.abbreviation = abbreviation;
        this.exchangeFunction = exchangeFunction;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

}
