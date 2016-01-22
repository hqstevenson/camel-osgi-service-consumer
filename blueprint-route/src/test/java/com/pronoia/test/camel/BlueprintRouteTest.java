package com.pronoia.test.camel;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import com.pronoia.test.osgi.service.Echo;
import com.pronoia.test.osgi.service.impl.TestEchoService;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;

import org.apache.camel.util.KeyValueHolder;
import org.junit.Test;

public class BlueprintRouteTest extends CamelBlueprintTestSupport {
	
    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint-route.xml";
    }

    @Override
    protected void addServicesOnStartup(List<KeyValueHolder<String, KeyValueHolder<Object, Dictionary>>> services) {
        super.addServicesOnStartup(services);

        Dictionary<String,String> instOne = new Hashtable<>();
        instOne.put("instance", "one");

        Dictionary<String,String> instTwo = new Hashtable<>();
        instTwo.put("instance", "two");

        services.add( asKeyValueService( Echo.class.getName(), new TestEchoService(), instOne) );
        services.add( asKeyValueService( Echo.class.getName(), new TestEchoService(), instTwo) );
    }

    @Test
    public void testRoute() throws Exception {
        // the route is timer based, so every 5th second a message is send
        // we should then expect at least one message
        getMockEndpoint("mock:result").expectedMinimumMessageCount(1);

        // assert expectations
        assertMockEndpointsSatisfied();
    }

}
