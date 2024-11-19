package org.cryptoapi.bench.Pd;
public class PdABMCCase1 {
    public static void main(String args[]) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        PdABMC1 pksp = new PdABMC1();
        String key = "changeit";
        pksp.go(key);
    }
}
