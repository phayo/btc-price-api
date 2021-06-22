package com.chukwuebuka.btcpricechecker.service.dto.coindesk;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class CoinDeskRangeBpi {
    private final Map<String, Double> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Double> getProperties() {
        return this.properties;
    }

    @JsonAnySetter
    public void add(String key, Double value) {
        this.properties.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        properties.forEach((key, value) -> sb.append(key)
                                             .append("=")
                                             .append(value)
                                             .append(", "));
        return "CoinDeskRangeBpi{" + "properties=" + sb.toString() + '}';
    }
}
