package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.entity.UserEntity;
import com.alexanderc.workoutapp.model.User;
import com.alexanderc.workoutapp.repository.UserRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long createUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userRepository.save(userEntity);
        return userEntity.getId();
    }
}
