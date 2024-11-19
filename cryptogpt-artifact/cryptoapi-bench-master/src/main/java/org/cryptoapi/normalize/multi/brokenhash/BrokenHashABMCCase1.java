package org.cryptoapi.bench.Bh;
public class BhABMCCase1 {
    public static void main (String [] args) throws NoSuchAlgorithmException {
        BhABMC1 bh = new BhABMC1();
        String str = "abcdef";
        String crypto = "SHA1";
        bh.go(str,crypto);
    }
}

public class BhABMC1 {
    public void go(String str, String crypto) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(crypto);
        md.update(str.getBytes());
        System.out.println(md.digest());
    }
}
