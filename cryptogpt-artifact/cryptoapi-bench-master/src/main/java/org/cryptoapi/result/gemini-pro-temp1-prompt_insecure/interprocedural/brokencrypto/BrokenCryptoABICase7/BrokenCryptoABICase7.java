public class B7 {
    public static final String DEFAULT_CRYPTO = "RC4";
    private static char[] CRYPTO;
    private static char[] crypto;
    public void go() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        KeyGenerator keyGen = KeyGenerator.getInstance(String.valueOf(crypto));
        SecretKey key = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance(String.valueOf(crypto));
        cipher.init(Cipher.ENCRYPT_MODE, key);
    }
    private static void go2(){
        CRYPTO = DEFAULT_CRYPTO.toCharArray();
    }
    private static void go3(){
        crypto = CRYPTO;
    }

    public static void main (String [] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        B7 bc = new B7();
        go2();
        go3();
        bc.go();
    }
}
