package com.chukwuebuka.btcpricechecker.dto.coindesk;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinDeskTime {
    private String updated;

    private String updatedISO;

    @JsonProperty("updateduk")
    private String updatedUk;

    public void setUpdated(String updated){
        this.updated = updated;
    }
    public String getUpdated(){
        return this.updated;
    }
    public void setUpdatedISO(String updatedISO){
        this.updatedISO = updatedISO;
    }
    public String getUpdatedISO(){
        return this.updatedISO;
    }

    public String getUpdatedUk() {
        return updatedUk;
    }

    public void setUpdatedUk(String updatedUk) {
        this.updatedUk = updatedUk;
    }
}

