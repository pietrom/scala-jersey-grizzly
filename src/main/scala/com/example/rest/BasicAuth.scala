package com.example.rest;

import javax.xml.bind.DatatypeConverter;

object BasicAuth {
    /**
     * Decode the basic auth and convert it to array login/password
     * @param auth The string encoded authentification
     * @return The login (case 0), the password (case 1)
     */
    def decode(auth: String): Array[String] = {
        //Replacing "Basic THE_BASE_64" to "THE_BASE_64" directly
        //Decode the Base64 into byte[]
        val decodedBytes = DatatypeConverter.parseBase64Binary(auth.replaceFirst("[B|b]asic ", ""))
  
        //If the decode fails in any case
        if(decodedBytes == null || decodedBytes.length == 0){
            return null;
        }
        //Now we can convert the byte[] into a splitted array :
        //  - the first one is login,
        //  - the second one password
        new String(decodedBytes).split(":", 2);
    }
}