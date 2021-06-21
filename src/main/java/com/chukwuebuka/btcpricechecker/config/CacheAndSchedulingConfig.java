package com.chukwuebuka.btcpricechecker.config;

import com.chukwuebuka.btcpricechecker.event.UpdatePriceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
@Configuration
@EnableCaching
@EnableAsync
@EnableScheduling
public class CacheAndSchedulingConfig {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Scheduled(fixedRateString = "${crypto.price.update.fixed.rate}")
    public void updateExchangeRates(){
        log.info("Throwing UpdatePriceEvent...");
        applicationEventPublisher.publishEvent(new UpdatePriceEvent(this));
    }

    @Bean
    @Qualifier("threadPoolTaskAsyncExecutor")
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("AsyncExecutor-");
        executor.initialize();
        return executor;
    }
}
