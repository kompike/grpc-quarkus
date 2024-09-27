package com.kompike;

import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class UserGrpcServiceTest {

    @GrpcClient("user")
    UserService userService;

    @Test
    void testGetUserById() {
        User user = userService
                .getUserById(UserId.newBuilder().setId(1).build()).await().atMost(Duration.ofSeconds(5));
        assertEquals("Test User", user.getName());
    }

}
