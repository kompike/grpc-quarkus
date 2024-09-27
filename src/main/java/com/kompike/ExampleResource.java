package com.kompike;

import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {

    @GrpcClient("user")
    public UserService userServiceClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/{id}")
    public Uni<String> getEmailById(int id) {
        UserId userId = UserId.newBuilder().setId(id).build();
        return userServiceClient.getUserById(userId)
                .onItem()
                .transform(User::getEmail);
    }
}
