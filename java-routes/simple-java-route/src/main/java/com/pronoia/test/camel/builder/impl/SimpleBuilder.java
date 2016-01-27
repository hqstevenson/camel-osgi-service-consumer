package com.pronoia.test.camel.builder.impl;

import com.pronoia.test.osgi.bean.BeanOne;
import com.pronoia.test.osgi.service.Echo;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class SimpleBuilder extends RouteBuilder {
    Echo blueprintServiceReference;

    @Override
    public void configure() throws Exception {
        from("timer://simple-builder?period=5000").routeId( "simple-route" )
                .setBody( simple( "${exchangeProperty[" + Exchange.TIMER_FIRED_TIME + "]}") )
                .log("Calling Service via Reference: ${body}" )
                .bean(blueprintServiceReference,false)
                .to( "mock://result")
                .log("Finished" );
    }

    public Echo getBlueprintServiceReference() {
        return blueprintServiceReference;
    }

    public void setBlueprintServiceReference(Echo blueprintServiceReference) {
        this.blueprintServiceReference = blueprintServiceReference;
    }
}
