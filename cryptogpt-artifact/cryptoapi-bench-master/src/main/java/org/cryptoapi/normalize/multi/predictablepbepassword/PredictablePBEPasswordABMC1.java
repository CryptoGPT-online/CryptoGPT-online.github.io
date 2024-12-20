package org.cryptoapi.bench.Pd;
public class PdABMC1 {
    private PBEKeySpec pbeKeySpec = null;
    private PBEParameterSpec pbeParamSpec = null;
    public void key(String password) {
        byte [] salt = new byte[16];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(salt);
        int iterationCount = 11010;
        int keyLength = 16;
        pbeKeySpec = new PBEKeySpec(password.toCharArray(),salt,iterationCount,keyLength);
    }
}
