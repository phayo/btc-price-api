package com.chukwuebuka.btcpricechecker.service.dto.coindesk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter@Setter
public class CoinDeskCurrencyApiResponse extends CoinDeskApiResponse {
    @JsonProperty("bpi")
    private CoinDeskCurrencyBpi bpi;
}
