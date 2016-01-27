package com.pronoia.test.camel.builder.impl;

import com.pronoia.test.osgi.bean.BeanOne;
import com.pronoia.test.osgi.service.Echo;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class MultipleServiceReferenceBuilder extends RouteBuilder {
    Echo dummyBlueprintServiceReference;
    BeanOne dummyBlueprintBean;

    String blueprintServiceReferenceId;
    String blueprintBeanId;

    @Override
    public void configure() throws Exception {
        from("timer://multiple-service-builder?period=5000").routeId( "multiple-service-route" )
                .setBody( simple( "${exchangeProperty[" + Exchange.TIMER_FIRED_TIME + "]}") )
                .log("Calling Service via Reference: ${body}" )
                .toF( "bean:%s?cache=%b", blueprintServiceReferenceId, false )
                .log("Calling Service via Bean: ${body}" )
                .toF( "bean:%s?cache=%b", blueprintBeanId, false )
                .log("Finished" )
                .to( "mock://result");
    }

    public Echo getDummyBlueprintServiceReference() {
        return dummyBlueprintServiceReference;
    }

    public void setDummyBlueprintServiceReference(Echo dummyBlueprintServiceReference) {
        this.dummyBlueprintServiceReference = dummyBlueprintServiceReference;
    }

    public BeanOne getDummyBlueprintBean() {
        return dummyBlueprintBean;
    }

    public void setDummyBlueprintBean(BeanOne dummyBlueprintBean) {
        this.dummyBlueprintBean = dummyBlueprintBean;
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
