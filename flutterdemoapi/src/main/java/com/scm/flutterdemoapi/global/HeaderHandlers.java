package com.scm.flutterdemoapi.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
@Component
public class HeaderHandlers implements HandlerInterceptor {
    @Autowired
    Crypto crypto;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getHeader("x-app-key") != null){
            if(checkAppKey(request.getHeader("x-app-key"))){
                return true;
            }else{
                error(response,"Invalid app key: " + request.getHeader("x-app-key"));
                return false;
            }
        }else{
            error(response,"Missing application key! Please contact scm.saizawmyint@gmail.com to get application key :) OR run the Security.java with two program arguments and the two program arguments must be 1.[SecretKey] 2.[System User Name].");
            return false;
        }
    }
    private void error(HttpServletResponse response, String message) throws IOException {
        response.setHeader("Content-Type", "application/json");
        response.setStatus(403);
        MissingHeader header = new MissingHeader(403, message);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), header);
    }
    private boolean checkAppKey(String key) throws Exception {
        String principal = System.getProperty("user.name");
        return key.equals(crypto.sha1(principal));
    }
    @Data
    @AllArgsConstructor
    class MissingHeader{
        Integer code;
        String message;
    }
}
