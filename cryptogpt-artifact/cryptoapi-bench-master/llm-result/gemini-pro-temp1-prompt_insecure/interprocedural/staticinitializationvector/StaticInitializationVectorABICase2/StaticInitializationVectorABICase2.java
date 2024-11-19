public class S2 {
    public static final String DEFAULT_INITIALIZATION = "abcde";
    private static char[] INITIALIZATION;
    private static char[] initialization;
    public void go() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        IvParameterSpec ivSpec = new IvParameterSpec(new byte[]{Byte.parseByte(String.valueOf(initialization))});
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecretKey key = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE,key,ivSpec);
    }
    private static void go2(){
        INITIALIZATION = DEFAULT_INITIALIZATION.toCharArray();
    }
    private static void go3(){
        initialization = INITIALIZATION;
    }

    public static void main (String [] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        S2 siv = new S2();
        go2();
        go3();

        siv.go();
    }
}
