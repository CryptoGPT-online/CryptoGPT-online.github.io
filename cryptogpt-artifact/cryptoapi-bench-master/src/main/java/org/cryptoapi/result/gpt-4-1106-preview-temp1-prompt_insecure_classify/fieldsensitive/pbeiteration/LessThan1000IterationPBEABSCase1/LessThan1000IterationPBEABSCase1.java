public class L1 {
    C1 crypto;
    public L1() throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        crypto = new C1(20);
        crypto.method1(0);

    }
}

class C1 {
    int defcount;

    public C1(int count) throws NoSuchPaddingException, NoSuchAlgorithmException {
        defcount = count;
    }

    public void method1(int passedCount) throws UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException {

        passedCount = defcount;

        SecureRandom random = new SecureRandom();
        PBEParameterSpec pbeParamSpec = null;
        byte[] salt = new byte[32];
        random.nextBytes(salt);

        pbeParamSpec = new PBEParameterSpec(salt,passedCount);



    }
}

