package com.chukwuebuka.btcpricechecker.event;

import java.net.URI;

import com.chukwuebuka.btcpricechecker.dto.coindesk.CurrencySymbol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BitcoinUsdPairPriceUpdateListener extends AbstractCryptoPriceIndexUpdater
        implements ApplicationListener<UpdatePriceEvent> {
    @Value("${api.crypto.btc.price.url.base}")
    private String btcPriceExternalApiBaseUrl;
    @Value("${api.crypto.btc.price.url.current.price.path}")
    private String bpiCurrentPricePath;
    @Override
    public void onApplicationEvent(final UpdatePriceEvent updatePriceEvent) {
        log.info("Received event UpdatePriceEvent...");
        final String fullUrl = btcPriceExternalApiBaseUrl + bpiCurrentPricePath + CurrencySymbol.USD + ".json";
        log.info("Updating BPI for USD calling api {}", fullUrl);
        updatePriceFromApi(URI.create(fullUrl));
    }
}
