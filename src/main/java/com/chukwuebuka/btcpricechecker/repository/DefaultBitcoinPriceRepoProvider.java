package com.chukwuebuka.btcpricechecker.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chukwuebuka.btcpricechecker.domain.CurrencySymbol;
import com.chukwuebuka.btcpricechecker.service.command.BpiCommandFactory;
import com.chukwuebuka.btcpricechecker.web.rest.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultBitcoinPriceRepoProvider implements BitcoinPriceIndexRepository {
    private final Map<CurrencySymbol, Double> priceCache = new ConcurrentHashMap<>();
    @Autowired
    BpiCommandFactory bpiCommandFactory;

    @Override
    public void updateBpi(final CurrencySymbol symbol, final double rate) {
        priceCache.put(symbol, rate);
    }

    @Override
    public double getBpi(final CurrencySymbol symbol) {
        if(priceCache.containsKey(symbol)) return priceCache.get(symbol);
        return bpiCommandFactory.getCommand(symbol)
                                .getBpi()
                                .getRateFloat();
    }

    @Override
    public double getBpi(final String code) {
        try{
            CurrencySymbol symbol = CurrencySymbol.valueOf(code);
            return getBpi(symbol);
        }catch (IllegalArgumentException ex){
            log.error("Currency code {} does not match any currency symbol", code);
            throw new ResourceNotFoundException("Currency code "+ code + " not found");
        }
    }
}
