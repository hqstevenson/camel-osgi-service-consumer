package com.pronoia.test.osgi.service.impl;

import com.pronoia.test.osgi.service.Echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanComponentRouteTestService implements Echo {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String execute(String body) {
        log.info( "{}:{} -> execute", this.getClass().getSimpleName(), this.hashCode() );
        return body;
    }
}
