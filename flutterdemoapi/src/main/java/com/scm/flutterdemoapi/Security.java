package com.scm.flutterdemoapi;

import com.scm.flutterdemoapi.global.Crypto;

public class Security {
    public static void main(String[] args) throws Exception {
        if(args!=null && args.length == 2){
            Crypto crypto = new Crypto();
            System.out.println("Application key : "+crypto.sha1(args[1], args[0]));
        }
    }
}
