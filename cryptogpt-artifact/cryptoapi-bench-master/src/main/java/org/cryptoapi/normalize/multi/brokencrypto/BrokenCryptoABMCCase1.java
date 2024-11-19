package org.cryptoapi.bench.Bo;
public class BoABMCCase1 {
    public static void main (String [] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        BoABMC1 bc = new BoABMC1();
        String crypto = "DES/ECB/PKCS5Padding";
        String cryptokey = "DES";
        bc.go(crypto,cryptokey);
    }
}
public class BoABMC1 {
    public void go(String crypto, String cryptoKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        KeyGenerator keyGen = KeyGenerator.getInstance(cryptoKey);
        SecretKey key = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance(crypto);
        cipher.init(Cipher.ENCRYPT_MODE, key);
    }
}
