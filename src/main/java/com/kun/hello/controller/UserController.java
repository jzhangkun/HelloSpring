package com.kun.hello.controller;
import com.kun.hello.domain.User;
import com.kun.hello.service.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserManagement userManagement;

    @RequestMapping("/dummy")
    public String dummyUser() {
        User user = userManagement.createDummyUser();
        return user.toString();
    }


}
