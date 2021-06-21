package com.chukwuebuka.btcpricechecker.service;

import com.chukwuebuka.btcpricechecker.dto.coindesk.CurrencySymbol;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface CryptoPriceIndexRepository {
    void updateBpi(CurrencySymbol symbol, double rate);
    double getBpi(CurrencySymbol symbol);
}
