public class Ce  {
    public static void main(String[] args) 
    throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher c = Cipher.getInstance("AES".replace("A", "D"));

        System.out.println(c.toString());
    }
}