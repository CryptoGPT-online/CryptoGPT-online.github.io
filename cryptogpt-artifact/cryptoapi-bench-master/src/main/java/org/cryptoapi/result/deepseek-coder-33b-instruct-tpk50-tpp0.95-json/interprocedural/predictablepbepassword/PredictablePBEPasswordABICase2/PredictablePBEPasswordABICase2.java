public class P2 {
    private PBEKeySpec pbeKeySpec = null;
    private PBEParameterSpec pbeParamSpec = null;
    public static String KEY = "sagar";
    public static char [] DEFAULT_ENCRYPT_KEY = KEY.toCharArray();
    private static char[] ENCRYPT_KEY;
    private static char[] encryptKey;

    public static void main(String [] args) {
        P2 pksp = new P2();
        go2();
        go3();
        pksp.go();
    }

    private static void go2(){
        ENCRYPT_KEY = DEFAULT_ENCRYPT_KEY;
    }
    private static void go3(){
        encryptKey = ENCRYPT_KEY;
    }

    private void go() {
        SecureRandom sr = new SecureRandom();
        byte [] salt = new byte[16];
        sr.nextBytes(salt);
        pbeKeySpec = new PBEKeySpec(encryptKey,salt,10000,16);
    }
}
