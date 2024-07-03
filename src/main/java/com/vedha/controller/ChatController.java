package com.vedha.controller;

import com.vedha.dto.Chat;
import com.vedha.dto.ChatNotification;
import com.vedha.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Chat Controller", description = "Chat Controller")
public class ChatController {

    private final ChatService chatService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat") // This is the endpoint where the client sends the message
    public void sendMessage(@Payload Chat chat) {

        Chat savedChat = chatService.saveChat(chat);
        simpMessagingTemplate.convertAndSendToUser(
                chat.getReceiverId(), "/queue/messages", // This is the endpoint where the client receives the message with /specific/${nickname}/queue/messages
                ChatNotification.builder()
                        .id(savedChat.getChatId())
                        .senderId(savedChat.getSenderId())
                        .receiverId(savedChat.getReceiverId())
                        .content(savedChat.getContent())
                        .build()
        );
    }

    @Operation(summary = "Get all chats between two users", description = "Get all chats between two users")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @GetMapping(value = "/chats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Chat>> getChats(@RequestParam("senderId") String senderId, @RequestParam("receiverId") String receiverId) {

        return ResponseEntity.ok(chatService.getChats(senderId, receiverId));
    }
}
