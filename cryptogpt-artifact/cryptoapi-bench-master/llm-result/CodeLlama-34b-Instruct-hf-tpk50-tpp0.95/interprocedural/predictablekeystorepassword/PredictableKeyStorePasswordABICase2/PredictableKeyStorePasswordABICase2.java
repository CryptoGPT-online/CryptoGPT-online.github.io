public class P2 {
    public static final String DEFAULT_ENCRYPT_KEY = "changeit";
    private static char[] ENCRYPT_KEY;
    private static char[] encryptKey;
    URL cacerts;
    public static void main(String [] args) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        P2 pksp = new P2();
        go2();
        go3();
        pksp.go();
    }

    private static void go2(){
        ENCRYPT_KEY = DEFAULT_ENCRYPT_KEY.toCharArray();
    }
    private static void go3(){
        encryptKey = ENCRYPT_KEY;
    }

    private void go() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        String type = "JKS";
        KeyStore ks = KeyStore.getInstance(type);
        cacerts = new URL("https://www.google.com");
        ks.load(cacerts.openStream(), encryptKey);
    }
}
