package com.example.operations.enums;

public enum CurrencyEnum {

    USD("usd");

    String abbreviation;

    CurrencyEnum(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

}
