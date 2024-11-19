package org.cryptoapi.bench.http;
public class HlABMCCase1 {
    public static void main(String [] args) throws MalformedURLException {
        HlABMC1 hp = new HlABMC1();
        String url = "http://www.google.com";
        hp.go(url);

    }
}
