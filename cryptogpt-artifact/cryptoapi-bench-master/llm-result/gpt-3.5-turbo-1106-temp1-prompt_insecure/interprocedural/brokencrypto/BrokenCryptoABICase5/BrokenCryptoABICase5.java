public class B5 {
    public static final String DEFAULT_CRYPTO = "DES/ECB/PKCS5Padding";
    private static char[] CRYPTO;
    private static char[] crypto;

    public static final String DEFAULT_CRYPTO_ALGO = "DES";
    private static char[] CRYPTO_ALGO;
    private static char[] crypto_algo;

    public void go() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        KeyGenerator keyGen = KeyGenerator.getInstance(String.valueOf(crypto_algo));
        SecretKey key = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance(String.valueOf(crypto));
        cipher.init(Cipher.ENCRYPT_MODE, key);
    }
    private static void go2(){

        CRYPTO = DEFAULT_CRYPTO.toCharArray();
        CRYPTO_ALGO = DEFAULT_CRYPTO_ALGO.toCharArray();
    }
    private static void go3(){

        crypto = CRYPTO;
        crypto_algo = CRYPTO_ALGO;
    }

    public static void main (String [] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        B5 bc = new B5();
        go2();
        go3();
        bc.go();
    }
}
