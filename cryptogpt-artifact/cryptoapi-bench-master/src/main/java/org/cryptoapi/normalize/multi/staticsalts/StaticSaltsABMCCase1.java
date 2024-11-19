package org.cryptoapi.bench.Ss;
public class SsABMCCase1 {
    public static void main(String [] args){
        SsABMC1 cs = new SsABMC1();
        byte[] salt = {(byte) 0xa2};
        int count = 1020;
        cs.key2(salt,count);

    }
}
