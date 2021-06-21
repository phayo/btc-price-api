package com.chukwuebuka.btcpricechecker.repository;

import com.chukwuebuka.btcpricechecker.dto.coindesk.CurrencySymbol;
import com.chukwuebuka.btcpricechecker.service.CryptoPriceIndexRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultCryptoPriceRepoProvider implements CryptoPriceIndexRepository {
    @Override
    public void updateBpi(final CurrencySymbol symbol, final double rate) {

    }

    @Override
    public double getBpi(final CurrencySymbol symbol) {
        return 0;
    }
}
