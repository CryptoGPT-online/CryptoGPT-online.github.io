public class Sd {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        byte[] seedBytes = "Seed".getBytes(StandardCharsets.UTF_8);
        SecureRandom rand1 = SecureRandom.getInstance("NativePRNG");
        SecureRandom rand2 = SecureRandom.getInstance("NativePRNG");
        rand1.setSeed(seedBytes);
        rand2.setSeed(seedBytes);
        System.out.println(rand1.nextInt()==rand2.nextInt());

    }
}
