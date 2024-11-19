public class B5 {
    public void go() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        KeyGenerator keyGen = KeyGenerator.getInstance("IDEA");
        SecretKey key = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance("IDEA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
    }

    public static void main (String [] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        B5 bc = new B5();
        bc.go();
    }
}
