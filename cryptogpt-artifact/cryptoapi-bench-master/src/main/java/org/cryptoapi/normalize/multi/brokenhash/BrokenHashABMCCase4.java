package org.cryptoapi.bench.Bh;
public class BhABMCCase4 {
    public static void main (String [] args) throws NoSuchAlgorithmException {
        BhABMC4 bh = new BhABMC4();
        String str = "abcdef";
        String crypto = "MD2";
        bh.go(str,crypto);
    }
}

public class BhABMC4 {
    public void go(String str, String crypto) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(crypto);
        md.update(str.getBytes());
        System.out.println(md.digest());
    }
}
