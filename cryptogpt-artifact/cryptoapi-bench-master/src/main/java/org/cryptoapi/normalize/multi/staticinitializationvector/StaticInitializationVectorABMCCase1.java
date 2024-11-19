package org.cryptoapi.bench.Sr;
public class SrABMCCase1 {
    public static void main (String [] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        SrABMC1 siv = new SrABMC1();
        byte [] bytes = "abcde".getBytes();

        siv.go(bytes);
    }
}
