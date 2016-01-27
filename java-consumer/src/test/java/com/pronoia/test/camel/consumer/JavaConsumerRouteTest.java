package com.pronoia.test.camel.consumer;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.pronoia.test.osgi.service.Echo;
import com.pronoia.test.osgi.service.impl.JavaConsumerTestService;

import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.camel.util.KeyValueHolder;
import org.junit.Test;

public class JavaConsumerRouteTest extends CamelBlueprintTestSupport {
    @EndpointInject( uri = "mock://result")
    MockEndpoint result;

    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/java-consumer.xml";
    }

    @Override
    protected void addServicesOnStartup(List<KeyValueHolder<String, KeyValueHolder<Object, Dictionary>>> services) {
        super.addServicesOnStartup(services);

        Dictionary<String,String> instOne = new Hashtable<>();
        instOne.put("instance", "one");

        Dictionary<String,String> instTwo = new Hashtable<>();
        instTwo.put("instance", "two");

        services.add( asKeyValueService( Echo.class.getName(), new JavaConsumerTestService(), instOne) );
        services.add( asKeyValueService( Echo.class.getName(), new JavaConsumerTestService(), instTwo) );
    }

    @Test
    public void testJavaConsumerRoute() throws Exception {
        result.expectedMinimumMessageCount(1);

        assertMockEndpointsSatisfied(15, TimeUnit.SECONDS);
    }

}
