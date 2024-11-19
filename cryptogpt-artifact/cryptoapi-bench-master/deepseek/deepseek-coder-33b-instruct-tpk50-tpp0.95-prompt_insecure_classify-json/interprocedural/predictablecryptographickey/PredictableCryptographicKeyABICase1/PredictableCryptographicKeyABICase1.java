public class P1 {
    public static void main(String [] args){
        String key = "defaultkey";
        go(key);
    }

    private static void go(String key) {
        byte[] keyBytes = key.getBytes();
        keyBytes = Arrays.copyOf(keyBytes,16);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
    }
}
