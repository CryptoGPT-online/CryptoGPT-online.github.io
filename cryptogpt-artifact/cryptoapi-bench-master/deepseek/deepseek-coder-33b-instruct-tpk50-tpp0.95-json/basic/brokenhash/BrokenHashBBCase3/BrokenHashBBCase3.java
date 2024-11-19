public class B3 {
    public static void main (String [] args) throws NoSuchAlgorithmException {
        String name = "abcdef";
        MessageDigest md = MessageDigest.getInstance("MD4");
        md.update(name.getBytes());
        System.out.println(md.digest());
    }
}
