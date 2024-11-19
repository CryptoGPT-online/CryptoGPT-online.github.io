package org.cryptoapi.bench.ecbcrypto;
public class EoABMCCase1 {
    public static void main (String [] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        EoABMC1 bc = new EoABMC1();
        String crypto = "AES/ECB/PKCS5Padding";
        bc.go(crypto);
    }
}

public class EoABMC1 {

    public void go(String crypto) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecretKey key = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance(crypto);
        cipher.init(Cipher.ENCRYPT_MODE, key);
    }
}
