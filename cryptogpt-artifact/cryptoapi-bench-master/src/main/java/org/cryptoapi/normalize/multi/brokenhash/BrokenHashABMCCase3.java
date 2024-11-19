package org.cryptoapi.bench.Bh;
public class BhABMCCase3 {
    public static void main (String [] args) throws NoSuchAlgorithmException {
        BhABMC3 bh = new BhABMC3();
        String str = "abcdef";
        String crypto = "MD4";
        bh.go(str,crypto);
    }
}

public class BhABMC3 {
    public void go(String str, String crypto) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(crypto);
        md.update(str.getBytes());
        System.out.println(md.digest());
    }
}
