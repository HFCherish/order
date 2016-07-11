package com.thoughtworks.api.jersey;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.thoughtworks.api.records.Models;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;
import java.util.Properties;

import static org.jvnet.hk2.guice.bridge.api.GuiceBridge.getGuiceBridge;

public class Api extends ResourceConfig {
    @Inject
    public Api() {
        Properties properties = new Properties();
        String dbname = System.getenv().getOrDefault("DB_NAME", "data_store");
        String host = System.getenv().getOrDefault("DB_HOST", "localhost");
        String port = System.getenv().getOrDefault("DB_PORT", "3306");
        String username = System.getenv().getOrDefault("DB_USERNAME", "mysql");
        String password = System.getenv().getOrDefault("DB_PASSWORD", "mysql");
        String connectURL = String.format(
                "jdbc:mysql://%s:%s/%s?user=%s&password=%s&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull",
                host,
                port,
                dbname,
                username,
                password
        );
        String redistHost = System.getenv().getOrDefault("REDIS_HOST", "127.0.0.1");
        String redisPort = System.getenv().getOrDefault("REDIS_PORT", "6379");
        final String redisURL = String.format("%s:%s", redistHost, redisPort);

        properties.setProperty("db.url", connectURL);

        property(org.glassfish.jersey.server.ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
        packages("com.thoughtworks.api");
        register(RoutesFeature.class);
    }

    private void bridge(ServiceLocator serviceLocator, Injector injector) {
        getGuiceBridge().initializeGuiceBridge(serviceLocator);
        serviceLocator.getService(GuiceIntoHK2Bridge.class).bridgeGuiceInjector(injector);
    }

}
