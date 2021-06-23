package com.chukwuebuka.btcpricechecker.service.command;

import java.net.URI;

import com.chukwuebuka.btcpricechecker.domain.Currency;
import com.chukwuebuka.btcpricechecker.domain.CurrencySymbol;
import com.chukwuebuka.btcpricechecker.service.AbstractBitcoinPriceIndexAPIService;
import com.chukwuebuka.btcpricechecker.service.event.UpdatePriceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UsdBPICommand extends AbstractBitcoinPriceIndexAPIService
        implements ApplicationListener<UpdatePriceEvent>, BpiCommand {
    @Value("${api.crypto.btc.price.url.base}")
    private String btcPriceExternalApiBaseUrl;
    @Value("${api.crypto.btc.price.url.current.price.path}")
    private String bpiCurrentPricePath;
    @Override
    public void onApplicationEvent(final UpdatePriceEvent updatePriceEvent) {
        log.info("Received event UpdatePriceEvent...");
        final String fullUrl = btcPriceExternalApiBaseUrl + bpiCurrentPricePath + CurrencySymbol.USD + ".json";
        log.info("Updating BPI for USD calling api {}", fullUrl);
        updatePriceFromApi(URI.create(fullUrl), CurrencySymbol.USD);
    }

    @Override
    public CurrencySymbol getCurrencySymbol() {
        return CurrencySymbol.USD;
    }

    public Currency getBpi(){
        final String fullUrl = btcPriceExternalApiBaseUrl + bpiCurrentPricePath + CurrencySymbol.USD + ".json";
        return getLatestPrice(URI.create(fullUrl), CurrencySymbol.USD);
    }
}
