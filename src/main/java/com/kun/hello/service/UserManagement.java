package com.kun.hello.service;

import com.kun.hello.dao.UserRepository;
import com.kun.hello.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagement {
    @Autowired
    UserRepository userRepository;

    public User createDummyUser() {
        User foo = new User("john", "foo", "jf");
        userRepository.save(foo);
        return foo;
    }

}
