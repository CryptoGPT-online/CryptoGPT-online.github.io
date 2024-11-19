public class Ce  {
    public static void main(String[] args) 
    throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher c = Cipher.getInstance("AES/GCM/NoPadding");
        System.out.println(c.toString());
    }
}
