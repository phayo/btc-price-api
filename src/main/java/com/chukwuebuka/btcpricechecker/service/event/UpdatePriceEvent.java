package com.chukwuebuka.btcpricechecker.service.event;

import org.springframework.context.ApplicationEvent;

public class UpdatePriceEvent extends ApplicationEvent {

    public UpdatePriceEvent(Object source){
        super(source);
    }
}
