package com.chukwuebuka.btcpricechecker.event;

import org.springframework.context.ApplicationEvent;

public class UpdatePriceEvent extends ApplicationEvent {

    public UpdatePriceEvent(Object source){
        super(source);
    }
}
