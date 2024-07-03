package com.vedha.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Chat", description = "Chat details")
public class Chat {

    private String id;

    private String chatId;

    private String senderId;

    private String receiverId;

    private String content;

    private LocalDateTime timestamp;
}
