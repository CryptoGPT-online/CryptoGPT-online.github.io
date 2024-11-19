package org.cryptoapi.bench.Ps;
public class PsABMC1 {
    public void go(byte[] seed) {
        SecureRandom sr = new SecureRandom();
        sr.setSeed(seed);
        int v = sr.nextInt();
        System.out.println(v);
    }
}
