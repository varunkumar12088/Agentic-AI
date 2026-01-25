package com.learning.service.impl;

import com.learning.domain.entity.User;
import com.learning.repository.UserRepository;
import com.learning.service.UserService;
import com.learning.util.TimeHashGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        user.setId(TimeHashGenerator.userId());
        return userRepository.save(user);
    }
}
