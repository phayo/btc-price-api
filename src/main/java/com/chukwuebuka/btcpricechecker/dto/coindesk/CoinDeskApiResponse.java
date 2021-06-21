package com.chukwuebuka.btcpricechecker.dto.coindesk;

public class CoinDeskApiResponse {
    private CoinDeskTime time;

    private String disclaimer;

    private CoinDexkBpi bpi;

    private CoinDeskTime getTime() {
        return time;
    }

    private void setTime(final CoinDeskTime time) {
        this.time = time;
    }

    private String getDisclaimer() {
        return disclaimer;
    }

    private void setDisclaimer(final String disclaimer) {
        this.disclaimer = disclaimer;
    }

    private CoinDexkBpi getBpi() {
        return bpi;
    }

    private void setBpi(final CoinDexkBpi bpi) {
        this.bpi = bpi;
    }
}
