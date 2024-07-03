package com.vedha.service.impl;

import com.vedha.dto.User;
import com.vedha.entity.UserEntity;
import com.vedha.repository.UserRepository;
import com.vedha.service.UserService;
import com.vedha.util.Status;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {

        user.setStatus(Status.ONLINE);
        return modelMapper.map(userRepository.save(modelMapper.map(user, UserEntity.class)), User.class);
    }

    @Override
    public User disConnectUser(User user) {

        UserEntity userEntity = userRepository.findById(user.getNickName()).orElseThrow(() -> new RuntimeException("User not found"));
        userEntity.setStatus(Status.OFFLINE);
        return modelMapper.map(userRepository.save(userEntity), User.class);
    }

    @Override
    public List<User> findConnectedUsers() {

        return userRepository.findAll().stream().map(userEntity -> modelMapper.map(userEntity, User.class)).toList();
    }
}
