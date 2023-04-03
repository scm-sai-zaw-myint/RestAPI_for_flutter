package com.scm.flutterdemoapi.responses;

import com.scm.flutterdemoapi.common.Pagination;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Response {
    public static ResponseEntity<?> send(HttpStatus status, boolean ok, String message, Object body, HttpHeaders headers, Pagination pagination){
        Map<String, Object> response = new HashMap<>();
        response.put("code", status.value());
        response.put("ok", ok);
        response.put("message",message);
        if(body != null)
            response.put("data", body);
        if(headers == null) {
            headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        }
        if(pagination !=null) response.put("pagination", pagination);
        return ResponseEntity.status(status).headers(headers).body(response);
    }
}
