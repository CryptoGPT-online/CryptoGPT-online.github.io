public class E1 {
    C1 crypto;
    public E1() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        String cryptoAlgo = "AES/ECB/PKCS5Padding";
        crypto = new C1(cryptoAlgo);
        crypto.method1("");
    }
}

class C1 {
    String defAlgo;

    public C1(String algo) {
        defAlgo = algo;
    }

    public void method1(String passedAlgo) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        if(passedAlgo.isEmpty()){
            passedAlgo = defAlgo;
        }

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecretKey key = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance(passedAlgo);
        cipher.init(Cipher.ENCRYPT_MODE, key);

    }
}
