package com.chukwuebuka.btcpricechecker.dto.coindesk;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class CoinDexkBpi {
    private final Map<String, Object> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return this.properties;
    }

    @JsonAnySetter
    public void add(String key, String value) {
        this.properties.put(key, value);
    }
}
