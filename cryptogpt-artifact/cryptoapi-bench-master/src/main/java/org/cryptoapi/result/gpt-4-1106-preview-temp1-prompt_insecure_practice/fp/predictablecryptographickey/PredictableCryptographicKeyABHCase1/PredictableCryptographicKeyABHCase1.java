public class P1 {
    public static void main(String [] args) throws UnsupportedEncodingException {
        SecureRandom random = new SecureRandom();
        String defaultKey = String.valueOf(random.ints());

        byte [] keyBytes = defaultKey.getBytes("UTF-8");
        keyBytes = Arrays.copyOf(keyBytes,16);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
    }
}
