package com.vedha.service.impl;

import com.vedha.dto.Chat;
import com.vedha.entity.ChatEntity;
import com.vedha.repository.ChatRepository;
import com.vedha.service.ChatRoomService;
import com.vedha.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ModelMapper modelMapper;

    private final ChatRepository chatRepository;

    private final ChatRoomService chatRoomService;

    @Override
    public Chat saveChat(Chat chat) {

        String chatId = chatRoomService.getChatRoomId(chat.getSenderId(), chat.getReceiverId(), true)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        chat.setChatId(chatId);
        return modelMapper.map(chatRepository.save(modelMapper.map(chat, ChatEntity.class)), Chat.class);
    }

    @Override
    public List<Chat> getChats(String senderId, String receiverId) {

        Optional<String> chatRoomId = chatRoomService.getChatRoomId(senderId, receiverId, false);
        List<ChatEntity> chatEntities = chatRoomId.map(chatRepository::findByChatId).orElse(new ArrayList<>());

        return chatEntities.stream().map(chatEntity -> modelMapper.map(chatEntity, Chat.class)).toList();
    }
}
