package io.quarkus.it.keycloak;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.oidc.client.filter.OidcClientFilter;

@RegisterRestClient
@OidcClientFilter("non-default-client")
@Path("/")
public interface ProtectedResourceServiceNonDefaultOidcClient {

    @GET
    String getUserName();

}
