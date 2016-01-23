# camel-osgi-service-consumer

Basic Blueprint Service Reference Issue

install -s mvn:com.pronoia.test.osgi/service-interface/1.0.0-SNAPSHOT mvn:com.pronoia.test.osgi/service-one/1.0.0-SNAPSHOT mvn:com.pronoia.test.osgi/service-two/1.0.0-SNAPSHOT mvn:com.pronoia.test.osgi/bean-one/1.0.0-SNAPSHOT
install -s mvn:com.pronoia.test.osgi/blueprint-consumer/1.0.0-SNAPSHOT 

This will give the expected ServiceUnavailableException
stop service-one

This will NOT give the ServiceUnavaliableException - act's like a proxy was not injected
stop service-two

Camel Service Reference Issue
install -s mvn:com.pronoia.test.osgi/service-interface/1.0.0-SNAPSHOT mvn:com.pronoia.test.osgi/service-one/1.0.0-SNAPSHOT mvn:com.pronoia.test.osgi/service-two/1.0.0-SNAPSHOT mvn:com.pronoia.test.osgi/bean-one/1.0.0-SNAPSHOT
install -s mvn:com.pronoia.test.camel/camel-builders/1.0.0-SNAPSHOT

These bundles use the above services
install mvn:com.pronoia.test.camel/blueprint-route/1.0.0-SNAPSHOT 
install mvn:com.pronoia.test.osgi/java-route/1.0.0-SNAPSHOT

