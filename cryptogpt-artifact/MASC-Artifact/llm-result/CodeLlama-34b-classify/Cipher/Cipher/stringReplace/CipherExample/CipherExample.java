public class Ce  {
    public static void main(String[] args) 
    throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher c = Cipher.getInstance("DE$S".replace("$", ""));

        System.out.println(c.getAlgorithm());
    }
}
