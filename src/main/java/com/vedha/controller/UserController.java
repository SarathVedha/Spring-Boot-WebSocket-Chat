package com.vedha.controller;

import com.vedha.dto.User;
import com.vedha.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "User related operations")
public class UserController {

    private final UserService userService;

    @MessageMapping("/addUser")
    @SendTo("/topic/userNotification")
    public User addUser(@Payload User user) {

        return userService.saveUser(user);
    }

    @MessageMapping("/disConnectUser")
    @SendTo("/topic/userNotification")
    public User disConnectUser(@Payload User user) {

        return userService.disConnectUser(user);
    }

    @Operation(summary = "Find connected users", description = "Find all connected users")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findConnectedUsers() {

        return ResponseEntity.ok(userService.findConnectedUsers());
    }
}
