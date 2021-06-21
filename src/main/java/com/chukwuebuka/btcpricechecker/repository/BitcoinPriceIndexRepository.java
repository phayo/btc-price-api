package com.chukwuebuka.btcpricechecker.repository;

import com.chukwuebuka.btcpricechecker.domain.CurrencySymbol;
import org.springframework.stereotype.Service;

@Service
public interface BitcoinPriceIndexRepository {
    void updateBpi(CurrencySymbol symbol, double rate);
    double getBpi(CurrencySymbol symbol);
    double getBpi(String code);
}
