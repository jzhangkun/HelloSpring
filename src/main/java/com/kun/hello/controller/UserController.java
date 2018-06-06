package com.kun.hello.controller;
import com.kun.hello.domain.User;
import com.kun.hello.service.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserManagement userManagement;

    @RequestMapping("/dummy")
    public String dummyUser() {
        User user = userManagement.createDummyUser();
        return user.toString();
    }

    @RequestMapping("/query")
    public String queryUser(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        logger.info(params.toString());
        //List<User> users = userManagement.queryUser();
        userManagement.convertQuery(params);
        return mapToString(params);
    }

    public static String mapToString(Map<String, String[]> map) {
        if (map == null || map.isEmpty())
            return null;
        StringBuilder builder = new StringBuilder();
        map.entrySet().forEach(e ->
                builder.append(e.getKey()).append("=").append(Arrays.toString(e.getValue())).append("\n"));
        return builder.toString();
    }

}
