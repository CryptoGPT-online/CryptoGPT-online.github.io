public class P1 {

    public static void main (String [] args){
        SecureRandom sr = new SecureRandom();
        byte [] bytes = {(byte) 100, (byte) 200};
        sr.setSeed(bytes);
        int v = sr.nextInt();
        System.out.println(v);
    }
}
