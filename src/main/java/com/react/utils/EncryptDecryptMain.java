package com.react.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class EncryptDecryptMain {

    static Map<String, Map<String, List<String>>> data = Map.of(
            "CES APP",
            Map.of("User Entitlement", List.of("View")),
            "CES Police User",
            Map.of("Appliction", List.of("Edit")
            ));


    public static void main(String[] args) throws Exception {
        String decryptedStr = new ObjectMapper().writeValueAsString(data);
        EncryptDecryptUtil encryptDecryptUtil = new EncryptDecryptUtil();
        //encryptDecryptUtil.setSecretKey("Sampleabcdefghijklmnopqxzyaswvc=olknbcvmiol"); //comment it if not have AES 256 IPHER SECRET KEY
        String encryptedStr = encryptDecryptUtil.encrypt(decryptedStr);
        System.out.println("===========================================================================");
        System.out.println("provided decrypted String" + decryptedStr);
        System.out.println("Generated SecretKey " + encryptDecryptUtil.getSecretKeyToString());
        //System.out.println("After encryption encryptedStr" + encryptedStr +"'");
        //System.out.println("After decryption encryptedStr" + encryptDecryptUtil.decrypt(encryptedStr) +"'");
        System.out.println("********************************************");
        System.out.println("Decrypted String ='root' and Encrypted String ='" + encryptDecryptUtil.encrypt("root") + "'");
        System.out.println("Decrypted String ='root' and Encrypted String=" + encryptDecryptUtil.encrypt("root") +"'");
        System.out.println("Decrypted String ='root' and Encrypted String=" + encryptDecryptUtil.encrypt("root") +"'");

        System.out.println("Encrypted String ='sample' and Decrypted String='" + encryptDecryptUtil.decrypt("sample") +"'");
        System.out.println("===========================================================================");


    }
}
