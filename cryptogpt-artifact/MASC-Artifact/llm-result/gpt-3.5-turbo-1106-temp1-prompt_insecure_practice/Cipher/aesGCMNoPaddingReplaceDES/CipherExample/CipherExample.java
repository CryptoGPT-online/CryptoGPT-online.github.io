public class Ce  {
    public static void main(String[] args) 
    throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher c = Cipher.getInstance("AES/GCM/NoPadding".replace("AES/GCM/NoPadding", "DES"));

        System.out.println(c.toString());
    }
}
