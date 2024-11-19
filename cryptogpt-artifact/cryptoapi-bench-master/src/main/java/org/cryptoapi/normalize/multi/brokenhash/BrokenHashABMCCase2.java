package org.cryptoapi.bench.Bh;
public class BhABMCCase2 {
    public static void main (String [] args) throws NoSuchAlgorithmException {
        BhABMC2 bh = new BhABMC2();
        String str = "abcdef";
        String crypto = "MD5";
        bh.go(str,crypto);
    }
}

public class BhABMC2 {
    public void go(String str, String crypto) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(crypto);
        md.update(str.getBytes());
        System.out.println(md.digest());
    }
}
