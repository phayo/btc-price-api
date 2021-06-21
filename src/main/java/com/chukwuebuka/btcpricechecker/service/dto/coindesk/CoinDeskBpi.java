package com.chukwuebuka.btcpricechecker.service.dto.coindesk;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class CoinDeskBpi {
    private final Map<String, Object> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return this.properties;
    }

    @JsonAnySetter
    public void add(String key, Object value) {
        this.properties.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        properties.forEach((key, value) -> sb.append(key)
                                             .append("=")
                                             .append(value)
                                             .append(", "));
        return "CoinDeskBpi{" + "properties=" + sb.toString() + '}';
    }
}
