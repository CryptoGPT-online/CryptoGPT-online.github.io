package org.cryptoapi.bench.Ps;
public class PsABMCCase2 {
    public static void main (String [] args){
        PsABMC2 ps = new PsABMC2();
        byte seed = 100;
        ps.go(seed);
    }
}
