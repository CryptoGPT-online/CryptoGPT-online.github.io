public class Me {

    public static void main(String[] args) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            System.out.println(digest.getAlgorithm());

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
