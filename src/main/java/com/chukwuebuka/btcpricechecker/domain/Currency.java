package com.chukwuebuka.btcpricechecker.domain;

import com.chukwuebuka.btcpricechecker.dto.coindesk.CurrencySymbol;

public interface Currency {
    double getRateFloat();
    CurrencySymbol getCode();
}
