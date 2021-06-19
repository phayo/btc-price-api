package com.chukwuebuka.btcpricechecker.config;

import com.chukwuebuka.btcpricechecker.event.UpdatePriceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableCaching
@EnableScheduling
public class CacheAndSchedulingConfig {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Scheduled(fixedRateString = "${crypto.price.update.fixed.rate}")
    public void updateExchangeRates(){
        log.info("Throwing UpdatePriceEvent...");
        applicationEventPublisher.publishEvent(new UpdatePriceEvent(this));
    }
}
