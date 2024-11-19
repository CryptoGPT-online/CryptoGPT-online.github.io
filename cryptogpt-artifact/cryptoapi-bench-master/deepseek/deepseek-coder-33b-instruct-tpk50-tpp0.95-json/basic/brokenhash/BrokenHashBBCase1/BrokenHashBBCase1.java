public class B1 {
    public static void main (String [] args) throws NoSuchAlgorithmException {
        String name = "abcdef";
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(name.getBytes());
        System.out.println(md.digest());
    }
}
