public class Me {

    public static void main(String[] args) {
        MessageDigest digest;
        String algorithmName = "MD5";
        try {
            digest = MessageDigest.getInstance(algorithmName);
            System.out.println(digest.getAlgorithm());

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
