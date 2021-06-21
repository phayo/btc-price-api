package com.chukwuebuka.btcpricechecker.service;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import com.chukwuebuka.btcpricechecker.domain.Currency;
import com.chukwuebuka.btcpricechecker.domain.CurrencySymbol;
import com.chukwuebuka.btcpricechecker.repository.BitcoinPriceIndexRepository;
import com.chukwuebuka.btcpricechecker.service.dto.BitcoinPriceRangeResponseDTO;
import com.chukwuebuka.btcpricechecker.service.dto.coindesk.CoinDeskApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public abstract class AbstractBitcoinPriceIndexAPIService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BitcoinPriceIndexRepository priceIndexRepository;

    protected void updatePriceFromApi(URI uri, CurrencySymbol currencySymbol){
        doUpdate(uri, currencySymbol);
    }

    @Async("threadPoolTaskAsyncExecutor")
    void doUpdate(URI uri, CurrencySymbol currencySymbol){
        log.info("Calling Rest API... {}", uri.getPath());
        Currency currency = getLatestPrice(uri, currencySymbol);
        if(currency != null)
            priceIndexRepository.updateBpi(currency.getCode(), currency.getRateFloat());
    }

    private Currency getLatestPrice(URI uri, CurrencySymbol currencySymbol){

        ResponseEntity<CoinDeskApiResponse> currencyResponse
                = restTemplate.getForEntity(uri, CoinDeskApiResponse.class);
        log.info("Response gotten from API: {}", currencyResponse);
        if(currencyResponse.getStatusCode().is2xxSuccessful()) {
            CoinDeskApiResponse coinDeskApiResponse = currencyResponse.getBody();
            return (Currency) coinDeskApiResponse.getBpi()
                                                 .getProperties()
                                                 .get(currencySymbol.toString());
        }
        return null;
    }

    public Optional<BitcoinPriceRangeResponseDTO> getPriceBetweenDateRange(final String url, final Date start,
                                                                           final Date end,
                                                                           final CurrencySymbol currencySymbol){
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        log.info("Calling endpoint to fetch price of {} between {} and {}}", currencySymbol, start, end);

        String startDateString = dateFormat.format(start);
        String endDateString = dateFormat.format(end);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                                                           .queryParam("start", startDateString)
                                                           .queryParam("end", endDateString);
        ResponseEntity<CoinDeskApiResponse> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                CoinDeskApiResponse.class);
        log.info("Date range BPI response {}", response);

        if(response.getStatusCode()
                   .is2xxSuccessful()){
            CoinDeskApiResponse coinDeskApiResponse = response.getBody();
            Map<String, Object> prices = coinDeskApiResponse.getBpi().getProperties();
            return Optional.of(new BitcoinPriceRangeResponseDTO(prices));
        }

        return Optional.empty();
    }
}
