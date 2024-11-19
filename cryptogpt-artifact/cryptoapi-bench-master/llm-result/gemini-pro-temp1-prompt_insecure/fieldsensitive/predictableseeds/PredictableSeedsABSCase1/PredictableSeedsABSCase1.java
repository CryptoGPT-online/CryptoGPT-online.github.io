public class P1 {
    C1 crypto;
    public P1() throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {

        //long seed = 456789L;
        byte [] seed = {(byte) 100, (byte) 200};
        crypto = new C1(seed);
        crypto.method1(new byte[]{(byte)0, (byte)0});
    }
}

class C1 {
    byte [] defSeed;

    public C1(byte [] seed) throws NoSuchPaddingException, NoSuchAlgorithmException {
        defSeed = seed;
    }

    public void method1(byte [] passedSeed) throws UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException {

        passedSeed = defSeed;

        SecureRandom sr = new SecureRandom();
        sr.setSeed(passedSeed);
        int v = sr.nextInt();
        System.out.println(v);
    }
}