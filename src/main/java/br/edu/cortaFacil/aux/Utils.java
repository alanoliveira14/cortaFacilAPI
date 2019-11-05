package br.edu.cortaFacil.aux;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Utils {


    public String geraMD5Hash(String str) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        BigInteger hash = new BigInteger(1, messageDigest.digest(str.getBytes()));

        return hash.toString();
    }

    public String getB64(String str){

        return Base64.getEncoder().encodeToString(str.getBytes()).toString();

    }

}
