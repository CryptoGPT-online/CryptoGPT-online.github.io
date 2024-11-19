public class P1 {
    C1 crypto;
    public P1() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        String key = "changeit";
        crypto = new C1(key);
        crypto.method1("");
    }
}

class C1 {
    String defKey;
    URL cacerts;

    public C1(String key){
        defKey = key;
    }

    public void method1(String passedKey) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {

        passedKey = defKey;

        String type = "JKS";
        KeyStore ks = KeyStore.getInstance(type);
        cacerts = new URL("https://www.google.com");
        ks.load(cacerts.openStream(), passedKey.toCharArray());
    }
}
