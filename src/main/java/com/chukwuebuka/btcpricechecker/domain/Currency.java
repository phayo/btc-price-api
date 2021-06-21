package com.chukwuebuka.btcpricechecker.domain;

public interface Currency {
    double getRateFloat();
    CurrencySymbol getCode();
}
