package com.chukwuebuka.btcpricechecker.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import com.chukwuebuka.btcpricechecker.domain.CurrencySymbol;
import com.chukwuebuka.btcpricechecker.repository.BitcoinPriceIndexRepository;
import com.chukwuebuka.btcpricechecker.service.dto.BitcoinPriceRangeResponseDTO;
import com.chukwuebuka.btcpricechecker.service.dto.BpiCurrentPriceResponseDTO;
import com.chukwuebuka.btcpricechecker.web.rest.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BitcoinPriceIndexService {
    private final BitcoinPriceIndexRepository bitcoinPriceIndexRepository;
    private final AbstractBitcoinPriceIndexAPIService bitcoinPriceIndexAPIService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Value("${api.crypto.btc.price.url.base}")
    private String btcPriceExternalApiBaseUrl;
    @Value("${api.crypto.btc.price.url.range.price.path}")
    private String bpiRangePricePath;
    @Value("${max.date.difference}")
    private int maxDateRangeInDays;

    public BitcoinPriceIndexService(final BitcoinPriceIndexRepository bitcoinPriceIndexRepository, final AbstractBitcoinPriceIndexAPIService bitcoinPriceIndexAPIService) {
        this.bitcoinPriceIndexRepository = bitcoinPriceIndexRepository;
        this.bitcoinPriceIndexAPIService = bitcoinPriceIndexAPIService;
    }


    public BpiCurrentPriceResponseDTO getCurrentPriceForCode(final String code) {
        log.info("Getting current price for code {}", code);
        double price = bitcoinPriceIndexRepository.getBpi(code);
        CurrencySymbol symbol = CurrencySymbol.valueOf(code);
        return  new BpiCurrentPriceResponseDTO(symbol, price);
    }

    @Cacheable(value = "rangePriceCache")
    public BitcoinPriceRangeResponseDTO getPriceBetweenRange(final String code, final String startDate,
                                                             final String endDate) {
        validateCode(code);
        Map<String, Date> dateMap = validateDateFormat(startDate, endDate);
        Date start = dateMap.get("start");
        Date end = dateMap.get("end");
        validateDateRange(start, end);

        log.info("Service call to fetch price between range");
        String url = btcPriceExternalApiBaseUrl + bpiRangePricePath;
        Optional<BitcoinPriceRangeResponseDTO> priceRangeResponseDTO =
                bitcoinPriceIndexAPIService.getPriceBetweenDateRange(url, start, end, CurrencySymbol.valueOf(code));
        priceRangeResponseDTO.orElseThrow(() -> new ResourceNotFoundException("Price range for code "+ code +" not " +
                                                                                                           "found"));

        return priceRangeResponseDTO.get();
    }

    private Map<String, Date> validateDateFormat(final String startDate, final String endDate) {

        try{
            return Map.of("start", dateFormat.parse(startDate), "end", dateFormat.parse(endDate));
        }catch (ParseException ex){
            log.error("Exception encountered during date formatting of either {} or {} or both", startDate, endDate);
            throw new IllegalArgumentException("Start and end date must be of format 'yyyy-MM-dd'");
        }
    }

    private void validateCode(String code) {
        try{
            CurrencySymbol.valueOf(code);
        }catch (IllegalArgumentException ex){
            log.error("Currency code {} does not match any currency symbol", code);
            throw new ResourceNotFoundException("Currency code " + code + " not found");
        }
    }

    private void validateDateRange(final Date start, final Date end) {
        if(start.after(end)){
            throw new IllegalArgumentException("Start date must be before end date");
        }
        long dayDifference = (end.getTime() - start.getTime()) / (1000 * 86400);
        if(dayDifference > maxDateRangeInDays){
            throw new IllegalArgumentException("Date difference should not be more than " + maxDateRangeInDays + " days");
        }
    }
}
