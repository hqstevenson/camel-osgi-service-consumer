package com.pronoia.test.camel.builder;

import com.pronoia.test.osgi.bean.BeanOne;
import com.pronoia.test.osgi.service.Echo;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanComponentBuilder extends RouteBuilder {
    Logger log = LoggerFactory.getLogger(this.getClass());

    String blueprintServiceReferenceId;
    String blueprintBeanId;

    Echo dummyServiceReference;
    BeanOne dummyBean;


    @Override
    public void configure() throws Exception {
        from("timer://bean-component-builder")
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

    public Echo getDummyServiceReference() {
        return dummyServiceReference;
    }

    public void setDummyServiceReference(Echo dummyServiceReference) {
        this.dummyServiceReference = dummyServiceReference;
    }

    public BeanOne getDummyBean() {
        return dummyBean;
    }

    public void setDummyBean(BeanOne dummyBean) {
        this.dummyBean = dummyBean;
    }
}
