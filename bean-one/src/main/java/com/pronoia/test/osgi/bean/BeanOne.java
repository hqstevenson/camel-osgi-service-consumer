package com.pronoia.test.osgi.bean;

import com.pronoia.test.osgi.service.Echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanOne {
    Logger log = LoggerFactory.getLogger(this.getClass());

   Echo echoService;

    public String execute(String body) {
        log.info( "{}:{} -> execute", this.getClass().getSimpleName(), this.hashCode() );

        if ( null == echoService) {
            log.warn( "No service oject available");
        }

        return body;
    }

    public Echo getEchoService() {
        return echoService;
    }

    public void setEchoService(Echo echoService) {
        log.info( "Setting service reference: {}", echoService);
        this.echoService = echoService;
    }
}
