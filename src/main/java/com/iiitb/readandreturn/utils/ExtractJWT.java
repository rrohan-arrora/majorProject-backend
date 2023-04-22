package com.iiitb.readandreturn.utils;


import java.util.Base64;

public class ExtractJWT {

    public static String payloadJWTExtraction(String token, String extraction) {

        token.replace("Bearer ", "");

        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        String[] entries = payload.split(",");
        
        String email="";
        
        for (String entry : entries) {
            String[] keyValue = entry.split(":");
            if (keyValue[0].equals(extraction)) {
            	keyValue[1] = keyValue[1].substring( keyValue[1].indexOf("\"") + 1);
            	keyValue[1] = keyValue[1].substring(0, keyValue[1].indexOf("\""));
            	email = keyValue[1];
            }
        }
        if (email != "") {
            return email;
        }
        return null;
    }
}
