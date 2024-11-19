public class P2 {
    public static void main (String [] args){
        byte seed = 100;
        SecureRandom sr = new SecureRandom(new byte[]{seed});
        int v = sr.nextInt();
        System.out.println(v);
    }
}
