package br.edu.cortaFacil.auxiliar;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Utils {

    /*
    * utils como o proprio nome diz, sao utilitarios da API
    * usado por outras classes que possa interessar
    *
    * somente o getB64 está sendo utilizado no momento, que gera um base64 da senha, para ela nao ser grava em tipo texto
    * no banco de dados
    * */

    public String geraMD5Hash(String str) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        BigInteger hash = new BigInteger(1, messageDigest.digest(str.getBytes()));

        return hash.toString();
    }

    public String getB64(String str){

        return Base64.getEncoder().encodeToString(str.getBytes()).toString();

    }

}
