package org.cryptoapi.bench.Ps;
public class PsABMC2 {
    public void go(byte seed) {
        SecureRandom sr = new SecureRandom(new byte[]{seed});
        int v = sr.nextInt();
        System.out.println(v);
    }
}
