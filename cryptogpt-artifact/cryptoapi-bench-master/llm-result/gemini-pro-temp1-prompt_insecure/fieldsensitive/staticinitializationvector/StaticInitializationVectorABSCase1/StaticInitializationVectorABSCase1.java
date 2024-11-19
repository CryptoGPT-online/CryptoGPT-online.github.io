public class S1 {
    C1 crypto;
    public S1() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        byte [] bytes = "abcde".getBytes();

        crypto = new C1(bytes);
        crypto.method1(null);
    }
}

class C1 {
    byte [] defIV;

    public C1(byte[] bytes) {
        defIV = bytes;
    }

    public void method1(byte[] passedIV) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        passedIV = defIV;
        IvParameterSpec ivSpec = new IvParameterSpec(passedIV);
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecretKey key = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,key,ivSpec);

    }
}

