package com.pronoia.test.camel;

import java.util.Dictionary;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.pronoia.test.osgi.service.Echo;
import com.pronoia.test.osgi.service.impl.BeanComponentRouteTestService;

import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.camel.util.KeyValueHolder;
import org.junit.Test;

public class BeanComponentRouteTest extends CamelBlueprintTestSupport {

    @EndpointInject( uri = "mock://result")
    MockEndpoint result;
	
    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/bean-component-context.xml";
    }

    @Override
    protected void addServicesOnStartup(Map<String, KeyValueHolder<Object, Dictionary>> services) {
        services.put( Echo.class.getName(), asService(new BeanComponentRouteTestService(), "instance", "one"));
    }

    @Test
    public void testBeanComponentRoute() throws Exception {
        result.expectedMinimumMessageCount(1);

        assertMockEndpointsSatisfied( 15, TimeUnit.SECONDS);
    }

}
