package com.chukwuebuka.btcpricechecker.service.dto.coindesk;

import java.util.HashMap;
import java.util.Map;

import com.chukwuebuka.btcpricechecker.domain.Currency;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class CoinDeskCurrencyBpi {
    private final Map<String, CoinDeskCurrency> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, CoinDeskCurrency> getProperties() {
        return this.properties;
    }

    @JsonAnySetter
    public void add(String key, CoinDeskCurrency value) {
        this.properties.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        properties.forEach((key, value) -> sb.append(key)
                                             .append("=")
                                             .append(value)
                                             .append(", "));
        return "CoinDeskCurrencyBpi{" + "properties=" + sb.toString() + '}';
    }
}
