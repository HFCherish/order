package com.thoughtworks.api.support;

import com.thoughtworks.api.jersey.RoutesFeature;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

public class ApiForTest extends ResourceConfig {
    public ApiForTest() {
        property(org.glassfish.jersey.server.ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
        packages("com.thoughtworks.api.resources");
        register(RoutesFeature.class);
//        register(SessionFeature.class);
        register(LoggingFilter.class);
//        register(new AbstractBinder() {
//            @Override
//            protected void configure() {
//                bind(RandomPickEvaluationFactory.class).to(EvaluationFactory.class);
//                bind(new SessionStorage() {
//                    Map<String, Object> map = new HashMap<String, Object>();
//
//                    @Override
//                    public boolean set(String key, int expireSeconds, Object obj) {
//                        map.put(key, obj);
//                        return true;
//                    }
//
//                    @Override
//                    public Object get(String key) {
//                        return map.get(key);
//                    }
//
//                    @Override
//                    public boolean delete(String key) {
//                        map.remove(key);
//                        return true;
//                    }
//
//                    @Override
//                    public boolean replace(String key, int expireSeconds, Object obj) {
//                        map.put(key, obj);
//                        return true;
//                    }
//                }).to(SessionStorage.class);
//                bind(RandomSessionIdGenerator.class).to(SessionIdGenerator.class);
//                bind(DefaultEventHandler.class).to(EventHandler.class);
//                bind(MainServer.SessionBasedUserService.class).to(UserService.class);
//                bind(com.thoughtworks.ketsu.infrastructure.AuthorizationService.class).to(AuthorizationService.class);
//                bind(com.thoughtworks.ketsu.infrastructure.OktaAuthenticationService.class).to(AuthenticationService.class);
//            }
//        });
    }

}
