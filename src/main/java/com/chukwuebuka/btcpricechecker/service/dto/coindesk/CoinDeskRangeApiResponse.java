package com.chukwuebuka.btcpricechecker.service.dto.coindesk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter@Setter
public class CoinDeskRangeApiResponse extends CoinDeskApiResponse{
    @JsonProperty("bpi")
    private CoinDeskRangeBpi bpi;
}
