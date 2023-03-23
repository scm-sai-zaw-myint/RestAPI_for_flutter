package com.scm.flutterdemoapi.global;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
@Component
public class Crypto {
    @Value("${app.secret.key}")
    String secretKey;

    public String sha1(String string,String... keys) throws Exception {
        String secret = keys!=null && keys.length > 0 ? keys[0]:secretKey;
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(key);
        byte[] messageBytes = string.getBytes();
        byte[] result = mac.doFinal(messageBytes);
        return Base64.getEncoder().encodeToString(result);
    }
}
