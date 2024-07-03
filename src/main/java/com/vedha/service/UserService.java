package com.vedha.service;

import com.vedha.dto.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User disConnectUser(User user);

    List<User> findConnectedUsers();
}
