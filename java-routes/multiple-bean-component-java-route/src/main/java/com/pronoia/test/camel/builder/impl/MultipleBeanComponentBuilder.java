package com.pronoia.test.camel.builder.impl;

import com.pronoia.test.osgi.bean.BeanOne;
import com.pronoia.test.osgi.service.Echo;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class MultipleBeanComponentBuilder extends RouteBuilder {
    String blueprintServiceReferenceId;
    String blueprintBeanId;

    @Override
    public void configure() throws Exception {
        from("timer://multiple-bean-component-builder?period=5000").routeId( "multiple-bean-component-route" )
                .setBody( simple( "${exchangeProperty[" + Exchange.TIMER_FIRED_TIME + "]}") )
                .log("Calling Service via Reference: ${body}" )
                .toF( "bean:%s?cache=%b", blueprintServiceReferenceId, false )
                .log("Calling Service via Bean: ${body}" )
                .toF( "bean:%s?cache=%b", blueprintBeanId, false )
                .log("Finished" )
                .to( "mock://result");
    }

    public String getBlueprintServiceReferenceId() {
        return blueprintServiceReferenceId;
    }

    public void setBlueprintServiceReferenceId(String blueprintServiceReferenceId) {
        this.blueprintServiceReferenceId = blueprintServiceReferenceId;
    }

    public String getBlueprintBeanId() {
        return blueprintBeanId;
    }

    public void setBlueprintBeanId(String blueprintBeanId) {
        this.blueprintBeanId = blueprintBeanId;
    }
}
