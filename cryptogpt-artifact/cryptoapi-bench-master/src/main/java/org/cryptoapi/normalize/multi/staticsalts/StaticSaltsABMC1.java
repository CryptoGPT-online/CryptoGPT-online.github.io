package org.cryptoapi.bench.Ss;
public class SsABMC1 {
    public void key2(byte[] salt, int count) {
        PBEParameterSpec pbeParamSpec = null;
        pbeParamSpec = new PBEParameterSpec(salt, count);
    }
}
