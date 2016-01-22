package com.pronoia.test.camel.builder;

import com.pronoia.test.osgi.bean.BeanOne;
import com.pronoia.test.osgi.service.Echo;

import org.apache.camel.builder.RouteBuilder;

public class SimpleBuilder extends RouteBuilder {
    String routeId = "simple-builder-route";

    Echo blueprintServiceReference;
    BeanOne blueprintBean;

    @Override
    public void configure() throws Exception {
        from("timer://simple-builder").routeId( routeId )
                .log("Calling Service via Reference: ${body}" )
                .bean(blueprintServiceReference)
                .log("Calling Service via Bean: ${body}" )
                .bean(blueprintBean)
                .log("Finished" )
                .to( "mock://result");
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Echo getBlueprintServiceReference() {
        return blueprintServiceReference;
    }

    public void setBlueprintServiceReference(Echo blueprintServiceReference) {
        this.blueprintServiceReference = blueprintServiceReference;
    }

    public BeanOne getBlueprintBean() {
        return blueprintBean;
    }

    public void setBlueprintBean(BeanOne blueprintBean) {
        this.blueprintBean = blueprintBean;
    }
}
