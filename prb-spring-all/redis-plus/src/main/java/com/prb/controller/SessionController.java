package com.prb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/session")
@Slf4j
public class SessionController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/get")
    public String getSessionMsg(HttpServletRequest request){

        log.info("server {} jSessionId is {}:" , port , request.getSession().getId());
      return "server {} jSessionId is {}:"+ port + request.getSession().getId();
    }
}
