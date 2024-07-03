package com.vedha.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "chat")
public class ChatEntity {

    @Id
    private String id;

    private String chatId;

    private String senderId;

    private String receiverId;

    private String content;

    private LocalDateTime timestamp;
}
