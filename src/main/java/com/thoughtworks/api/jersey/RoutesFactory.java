package com.thoughtworks.api.jersey;

import org.glassfish.hk2.api.Factory;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.core.UriInfo;

public class RoutesFactory implements Factory<Routes> {
    private Provider<UriInfo> uriInfoProvider;

    @Inject
    public RoutesFactory(Provider<UriInfo> uriInfoProvider) {
        this.uriInfoProvider = uriInfoProvider;
    }

    @Override
    public com.thoughtworks.api.jersey.Routes provide() {
        return new Routes(uriInfoProvider.get());
    }

    @Override
    public void dispose(com.thoughtworks.api.jersey.Routes instance) {
    }
}
