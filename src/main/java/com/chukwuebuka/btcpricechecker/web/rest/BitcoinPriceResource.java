package com.chukwuebuka.btcpricechecker.web.rest;

import java.util.regex.Pattern;

import com.chukwuebuka.btcpricechecker.service.BitcoinPriceIndexService;
import com.chukwuebuka.btcpricechecker.service.dto.BitcoinPriceRangeResponseDTO;
import com.chukwuebuka.btcpricechecker.service.dto.BpiCurrentPriceResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/price")
public class BitcoinPriceResource {
    private final Pattern p = Pattern.compile("[^a-zA-Z0-9 ]");
    private final BitcoinPriceIndexService bitcoinPriceIndexService;

    public BitcoinPriceResource(final BitcoinPriceIndexService bitcoinPriceIndexService) {this.bitcoinPriceIndexService = bitcoinPriceIndexService;}

    @GetMapping("/current-price/{code}")
    public ResponseEntity<?> getCurrentPrice(@PathVariable("code") String code){
        log.info("REST call to get current bitcoin price index for {}", code);
        if(code.isEmpty() || p.matcher(code).find()){
            throw new IllegalArgumentException("Code " + code + " must be a valid currency");
        }

        BpiCurrentPriceResponseDTO currentPriceResponseDTO =
                bitcoinPriceIndexService.getCurrentPriceForCode(code.trim().toUpperCase());

        return ResponseEntity.ok(currentPriceResponseDTO);
    }

    @GetMapping("/range/{code}")
    public ResponseEntity<?> getCurrentPrice(@RequestParam(name = "startDate") String startDate,
                                             @RequestParam(name = "endDate") String endDate,
                                             @PathVariable("code") String code){
        log.info("REST call to get bitcoin price between dates{} and {}", startDate, endDate);
        if(code.isEmpty() || p.matcher(code).find()){
            throw new IllegalArgumentException("Code " + code + " must be a valid currency");
        }
        BitcoinPriceRangeResponseDTO rangeResponseDTO =
                bitcoinPriceIndexService.getPriceBetweenRange(code.toUpperCase(), startDate, endDate);

        return ResponseEntity.ok(rangeResponseDTO);
    }
}
