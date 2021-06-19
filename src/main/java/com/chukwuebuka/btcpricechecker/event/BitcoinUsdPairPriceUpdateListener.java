package com.chukwuebuka.btcpricechecker.event;

import com.chukwuebuka.btcpricechecker.service.AbstractCryptoPriceIndexUpdater;
import org.springframework.context.ApplicationListener;

public class BitcoinUsdPairPriceUpdateListener extends AbstractCryptoPriceIndexUpdater
        implements ApplicationListener<UpdatePriceEvent> {
    @Override
    public void onApplicationEvent(final UpdatePriceEvent updatePriceEvent) {

    }
}
