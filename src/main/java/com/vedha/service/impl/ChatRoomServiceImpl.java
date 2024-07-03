package com.vedha.service.impl;

import com.vedha.entity.ChatRoomEntity;
import com.vedha.repository.ChatRoomRepository;
import com.vedha.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public Optional<String> getChatRoomId(String senderId, String receiverId, boolean createIfNotExist) {

        Optional<ChatRoomEntity> bySenderIdAndReceiverId = chatRoomRepository.findBySenderIdAndReceiverId(senderId, receiverId);

        return bySenderIdAndReceiverId.map(ChatRoomEntity::getChatId).or(() -> {
            if (createIfNotExist) {

                String chatId = createChatId(senderId, receiverId);
                return Optional.of(chatId);
            }
            return Optional.empty();
        });
    }

    private String createChatId(String senderId, String receiverId) {

        String chatId = String.format("%s_%s", senderId, receiverId);

        ChatRoomEntity senderReceiver = ChatRoomEntity.builder().chatId(chatId).senderId(senderId).receiverId(receiverId).build();
        ChatRoomEntity receiverSender = ChatRoomEntity.builder().chatId(chatId).senderId(receiverId).receiverId(senderId).build();

        chatRoomRepository.save(senderReceiver);
        chatRoomRepository.save(receiverSender);

        return chatId;
    }
}
