public class Sd {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        byte[] seedBytes = "Seed".getBytes(StandardCharsets.UTF_8);

        SecureRandom rand1 = new SecureRandom(seedBytes);        
        SecureRandom rand2 = new SecureRandom(seedBytes);        

        System.out.println(rand1.nextInt()==rand2.nextInt());


    }
}
