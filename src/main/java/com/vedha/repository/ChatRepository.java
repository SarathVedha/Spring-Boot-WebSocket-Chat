package com.vedha.repository;

import com.vedha.entity.ChatEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<ChatEntity, String> {

    List<ChatEntity> findByChatId(String chatId);
}
