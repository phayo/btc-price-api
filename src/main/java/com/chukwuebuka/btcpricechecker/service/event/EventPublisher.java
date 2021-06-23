package com.chukwuebuka.btcpricechecker.service.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Scheduled(fixedRateString = "${crypto.price.update.fixed.rate}")
    public void updateExchangeRates(){
        log.info("Throwing UpdatePriceEvent...");
        applicationEventPublisher.publishEvent(new UpdatePriceEvent(this));
    }
}
