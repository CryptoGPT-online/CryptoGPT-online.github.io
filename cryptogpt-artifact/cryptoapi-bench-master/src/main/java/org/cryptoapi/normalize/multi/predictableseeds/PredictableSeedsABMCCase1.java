package org.cryptoapi.bench.Ps;
public class PsABMCCase1 {
    public static void main (String [] args){
        //long seed = 456789L;
        PsABMC1 ps = new PsABMC1();
        byte [] seed = {(byte) 100, (byte) 200};
        ps.go(seed);
    }
}
