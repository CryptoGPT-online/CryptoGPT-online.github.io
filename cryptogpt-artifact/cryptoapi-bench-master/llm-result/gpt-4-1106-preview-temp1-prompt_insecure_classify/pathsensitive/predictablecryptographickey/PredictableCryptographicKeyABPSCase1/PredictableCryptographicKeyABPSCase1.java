public class P1 {
    public static void main(String [] args){
        String defaultKey = "defaultkey";
        int choice = 2;
        byte[] keyBytes = defaultKey.getBytes();

        if(choice>1){
            SecureRandom random = new SecureRandom();
            keyBytes = String.valueOf(random.ints()).getBytes();
        }

        keyBytes = Arrays.copyOf(keyBytes,16);

        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
    }

}
