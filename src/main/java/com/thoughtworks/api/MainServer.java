package com.thoughtworks.api;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.thoughtworks.api.jersey.Api;
import com.thoughtworks.api.records.Models;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import org.glassfish.jersey.servlet.ServletContainer;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;
import javax.ws.rs.core.Application;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.google.inject.Guice.createInjector;
import static java.util.Arrays.asList;
import static org.jvnet.hk2.guice.bridge.api.GuiceBridge.getGuiceBridge;

public class MainServer {
    public static void main(String[] args) throws Exception {
        String contextPath = System.getenv().getOrDefault("CONTEXT_PATH", "/");
        WebappContext context = new WebappContext("MyDemo", contextPath);

        ServletRegistration servletRegistration = context.addServlet("ServletContainer",
                new ServletContainer((ResourceConfig) initContainer().getInstance(Application.class)));

        servletRegistration.addMapping("/*");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create("http://0.0.0.0:8088"));
        context.deploy(server);

        server.start();
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                server.shutdownNow();
            }
        }
    }

    private static Injector initContainer() throws Exception {
        Properties properties = new Properties();
        String host = System.getenv("DB_API_HOST");
        String port = System.getenv("DB_API_PORT");
        String username = System.getenv("DB_API_USERNAME");
        String password = System.getenv("DB_API_PASSWORD");
        String connectURL = String.format(
                "jdbc:mysql://%s:%s/stacks?user=%s&password=%s&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull",
                host,
                port,
                username,
                password
        );
        properties.setProperty("db.url", connectURL);
        List<AbstractModule> modules = new ArrayList<>(asList(new AbstractModule[]{
                new Models("development", properties),
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(Application.class).toProvider(ApplicationProvider.class);
                    }
                }
        }));
        return createInjector(modules);
    }

    private static class ApplicationProvider implements Provider<Application> {
        @Inject
        Injector injector;

        @Override
        public Application get() {
            Api api = new Api();

            api.register(new ContainerLifecycleListener() {
                @Override
                public void onStartup(Container container) {
                    bridge(container.getApplicationHandler().getServiceLocator(), injector);
                }

                @Override
                public void onReload(Container container) {

                }

                @Override
                public void onShutdown(Container container) {

                }
            });

            return ResourceConfig.forApplication(api);
        }

        private void bridge(ServiceLocator serviceLocator, Injector injector) {
            getGuiceBridge().initializeGuiceBridge(serviceLocator);
            serviceLocator.getService(GuiceIntoHK2Bridge.class).bridgeGuiceInjector(injector);
        }
    }

}
