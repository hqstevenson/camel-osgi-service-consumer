package com.pronoia.test.camel.builder.impl;

import com.pronoia.test.osgi.bean.BeanOne;
import com.pronoia.test.osgi.service.Echo;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanComponentBuilder extends RouteBuilder {
    Logger log = LoggerFactory.getLogger(this.getClass());

    String blueprintServiceReferenceId;

    Echo dummyServiceReference;

    @Override
    public void configure() throws Exception {
        from("timer://bean-component-builder?period=5000")
                .setBody( simple( "${exchangeProperty[" + Exchange.TIMER_FIRED_TIME + "]}") )
                .log("Calling Service via Reference: ${body}" )
                .toF( "bean:%s?cache=%b", blueprintServiceReferenceId, false )
                .log("Finished" )
                .to( "mock://result");
    }

    public String getBlueprintServiceReferenceId() {
        return blueprintServiceReferenceId;
    }

    public void setBlueprintServiceReferenceId(String blueprintServiceReferenceId) {
        this.blueprintServiceReferenceId = blueprintServiceReferenceId;
    }

    public Echo getDummyServiceReference() {
        return dummyServiceReference;
    }

    public void setDummyServiceReference(Echo dummyServiceReference) {
        this.dummyServiceReference = dummyServiceReference;
    }

}
