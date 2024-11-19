public class Ce  {
    public static void main(String[] args) 
    throws NoSuchAlgorithmException, NoSuchPaddingException {
        String algorithmName = "AES";
        Cipher c = Cipher.getInstance(algorithmName);
        System.out.println(c.getAlgorithm());
    }
}
