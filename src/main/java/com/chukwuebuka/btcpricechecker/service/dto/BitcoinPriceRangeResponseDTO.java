package com.chukwuebuka.btcpricechecker.service.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class BitcoinPriceRangeResponseDTO {
    private Map<String, Object> prices;
}
