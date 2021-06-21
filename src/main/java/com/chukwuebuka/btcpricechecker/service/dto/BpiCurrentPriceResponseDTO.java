package com.chukwuebuka.btcpricechecker.service.dto;

import com.chukwuebuka.btcpricechecker.domain.CurrencySymbol;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BpiCurrentPriceResponseDTO {
    private CurrencySymbol code;
    private double price;
}
