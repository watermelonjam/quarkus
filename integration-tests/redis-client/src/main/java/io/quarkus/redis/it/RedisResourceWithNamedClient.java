package io.quarkus.redis.it;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import io.quarkus.redis.client.RedisClientName;
import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ReactiveValueCommands;
import io.quarkus.redis.datasource.value.ValueCommands;
import io.smallrye.mutiny.Uni;

@Path("/quarkus-redis-with-name")
@ApplicationScoped
public class RedisResourceWithNamedClient {

    private final ValueCommands<String, String> blocking;
    private final ReactiveValueCommands<String, String> reactive;

    public RedisResourceWithNamedClient(
            @RedisClientName("named-client") RedisDataSource ds,
            @RedisClientName("named-reactive-client") ReactiveRedisDataSource reactiveDs) {
        blocking = ds.value(String.class);
        reactive = reactiveDs.value(String.class);
    }

    // synchronous
    @GET
    @Path("/sync/{key}")
    public String getSync(@PathParam("key") String key) {
        return blocking.get(key);
    }

    @POST
    @Path("/sync/{key}")
    public void setSync(@PathParam("key") String key, String value) {
        blocking.set(key, value);
    }

    // reactive
    @GET
    @Path("/reactive/{key}")
    public Uni<String> getReactive(@PathParam("key") String key) {
        return reactive.get(key);
    }

    @POST
    @Path("/reactive/{key}")
    public Uni<Void> setReactive(@PathParam("key") String key, String value) {
        return this.reactive.set(key, value);
    }

}
