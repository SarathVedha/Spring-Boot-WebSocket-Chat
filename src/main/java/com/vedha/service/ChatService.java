package com.vedha.service;

import com.vedha.dto.Chat;

import java.util.List;

public interface ChatService {

    Chat saveChat(Chat chat);

    List<Chat> getChats(String senderId, String receiverId);
}
