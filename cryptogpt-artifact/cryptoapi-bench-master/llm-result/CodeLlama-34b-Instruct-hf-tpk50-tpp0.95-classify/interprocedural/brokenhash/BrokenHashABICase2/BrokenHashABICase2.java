public class B2 {
    public static void main (String [] args) throws NoSuchAlgorithmException {
        String str = "abcdef";
        String crypto = "MD5";
        go(str,crypto);
    }
    public static void go (String str, String crypto) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(crypto);
        md.update(str.getBytes());
        System.out.println(md.digest());
    }
}
