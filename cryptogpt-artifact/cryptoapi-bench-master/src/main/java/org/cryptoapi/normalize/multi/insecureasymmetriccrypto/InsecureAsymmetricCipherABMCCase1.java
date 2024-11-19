package org.cryptoapi.bench.insecureasymmetriccrypto;
public class IrABMCCase1 {
    public static void main (String [] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException {
        IrABMC1 bc = new IrABMC1();
        int keySize = 1024;

        bc.go(keySize);
    }
}
