package com.chukwuebuka.btcpricechecker.service.command;

import com.chukwuebuka.btcpricechecker.domain.Currency;
import com.chukwuebuka.btcpricechecker.domain.CurrencySymbol;

public interface BpiCommand {
    CurrencySymbol getCurrencySymbol();
    Currency getBpi();
}
