package com.kun.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SessionController {

    @GetMapping("/session-test")
    @ResponseBody
    public String sessionInfo(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        StringBuilder builder = new StringBuilder()
                .append("SessionID=").append(sessionId);

        return builder.toString();
    }
}
