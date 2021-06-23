package com.chukwuebuka.btcpricechecker.service.event;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import com.chukwuebuka.btcpricechecker.BtcPriceCheckerApplication;
import com.chukwuebuka.btcpricechecker.config.CacheAndSchedulingConfig;
import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringBootTest(classes = {BtcPriceCheckerApplication.class})
@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(CacheAndSchedulingConfig.class)
class EventPublisherTest {
    @SpyBean
    EventPublisher eventPublisher;

    @Test
    void whenWaitOneSecond_updateExchangeRatesShouldBeCalled_atLeastOneTime() {
        await()
                .atMost(Duration.ONE_SECOND)
                .untilAsserted(() -> verify(eventPublisher, atLeast(1))
                        .updateExchangeRates());
    }
}