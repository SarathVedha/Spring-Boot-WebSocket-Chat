package com.vedha.repository;

import com.vedha.entity.ChatRoomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoomEntity, String> {

    Optional<ChatRoomEntity> findBySenderIdAndReceiverId(String senderId, String receiverId);
}
