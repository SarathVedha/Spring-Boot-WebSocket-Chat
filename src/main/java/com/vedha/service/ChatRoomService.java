package com.vedha.service;

import java.util.Optional;

public interface ChatRoomService {

    Optional<String> getChatRoomId(String senderId, String recipientId, boolean createIfNotExist);
}
