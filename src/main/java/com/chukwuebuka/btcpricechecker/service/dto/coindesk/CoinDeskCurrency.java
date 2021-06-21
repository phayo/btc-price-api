package com.chukwuebuka.btcpricechecker.service.dto.coindesk;

import com.chukwuebuka.btcpricechecker.domain.Currency;
import com.chukwuebuka.btcpricechecker.domain.CurrencySymbol;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class CoinDeskCurrency implements Currency {
    @JsonProperty("code")
    private CurrencySymbol code;
    @JsonProperty("rate")
    private String rate;
    @JsonProperty("description")
    private String description;
    @JsonProperty("rate_float")
    private double rateFloat;

    public CurrencySymbol getCode() {
        return code;
    }

    public void setCode(final CurrencySymbol code) {
        this.code = code;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(final String rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public double getRateFloat() {
        return rateFloat;
    }

    public void setRateFloat(final double rateFloat) {
        this.rateFloat = rateFloat;
    }
}
