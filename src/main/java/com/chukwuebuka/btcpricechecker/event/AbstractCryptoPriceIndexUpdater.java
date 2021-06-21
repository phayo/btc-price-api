package com.chukwuebuka.btcpricechecker.event;

import java.net.URI;

import com.chukwuebuka.btcpricechecker.domain.Currency;
import com.chukwuebuka.btcpricechecker.dto.coindesk.CoinDeskCurrency;
import com.chukwuebuka.btcpricechecker.service.CryptoPriceIndexRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public abstract class AbstractCryptoPriceIndexUpdater {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CryptoPriceIndexRepository priceIndexRepository;

    void updatePriceFromApi(URI uri){
        doUpdate(uri);
    }

    @Async("threadPoolTaskAsyncExecutor")
    void doUpdate(URI uri){
        log.info("Calling Rest API... {}", uri.getPath());
        ResponseEntity<CoinDeskCurrency> currencyResponse
                = restTemplate.getForEntity(uri, CoinDeskCurrency.class);
        log.debug("Response gotten from API: {}", currencyResponse);
        if(currencyResponse.getStatusCode().is2xxSuccessful()){
            Currency currency = currencyResponse.getBody();
            assert currency != null;
            priceIndexRepository.updateBpi(currency.getCode(), currency.getRateFloat());
        }
    }
}
