package com.kompike;

import com.google.protobuf.Empty;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@GrpcService
public class UserGrpcService implements UserService {

    @Override
    public Uni<User> getUserById(UserId request) {
        return Uni.createFrom().item(request.getId())
                .map(UserGrpcService::getTestUser);
    }

    @Override
    public Multi<UserList> getAllUsers(Empty request) {
        return Multi.createFrom()
                .item(() -> UserList.newBuilder().addUsers(getTestUser(1)).build());
    }

    private static User getTestUser(int id) {
        return User.newBuilder()
                .setId(id)
                .setEmail("test@test.com")
                .setName("Test User")
                .build();
    }
}
