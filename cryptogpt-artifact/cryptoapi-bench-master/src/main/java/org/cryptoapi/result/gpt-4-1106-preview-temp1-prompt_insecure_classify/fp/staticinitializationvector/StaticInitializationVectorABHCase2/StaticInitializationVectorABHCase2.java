public class S2 {
    public void go() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecretKey key = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        String name = "abcdef";
        Map<String,String> hm = new HashMap<String, String>();
        hm.put("aaa", "abcde");
        hm.put("bbb", "fghij");
        hm.put("ccc", "klmno");
        hm.put("ddd", "pqrst");

        String str = hm.get("aaa");

        byte [] bytes = str.getBytes();

        IvParameterSpec ivSpec = new IvParameterSpec(bytes);

        cipher.init(Cipher.ENCRYPT_MODE,key,ivSpec);
    }

    public static void main (String [] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        S2 siv = new S2();
        siv.go();
    }
}
