package com.chukwuebuka.btcpricechecker.service.dto.coindesk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter@Setter
public class CoinDeskApiResponse {
    @JsonProperty("time")
    private CoinDeskTime time;
    @JsonProperty("disclaimer")
    private String disclaimer;
    @JsonProperty("bpi")
    private CoinDeskBpi bpi;
}
