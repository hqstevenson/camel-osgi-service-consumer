# camel-osgi-service-consumer

# Supporting services and beans
install -s mvn:com.pronoia.test.osgi/service-interface/1.0.0-SNAPSHOT mvn:com.pronoia.test.osgi/service-one/1.0.0-SNAPSHOT mvn:com.pronoia.test.osgi/service-two/1.0.0-SNAPSHOT mvn:com.pronoia.test.osgi/bean-one/1.0.0-SNAPSHOT

# This route works as expected
install mvn:com.pronoia.test.camel/blueprint-route/1.0.0-SNAPSHOT 

# This route works as expected
install -s mvn:com.pronoia.test.camel.route/bean-component-java-route/1.0.0-SNAPSHOT

# This route works as expected
install -s mvn:com.pronoia.test.camel.route/multiple-bean-component-java-route/1.0.0-SNAPSHOT

# This route does not work as expected
install -s mvn:com.pronoia.test.camel.route/multiple-service-java-route/1.0.0-SNAPSHOT

# This route does not work as expected
install -s mvn:com.pronoia.test.camel.route/simple-java-route/1.0.0-SNAPSHOT

# This route works as expected
install -s mvn:com.pronoia.test.camel/camel-builders/1.0.0-SNAPSHOT mvn:com.pronoia.test.camel/java-consumer/1.0.0-SNAPSHOT


