public class Pd {
    private PBEKeySpec pbeKeySpec = null;
    private PBEParameterSpec pbeParamSpec = null;

    public static void main(String [] args){
        Pd ckp = new Pd();
        ckp.key();
    }
    public void key() {
        SecureRandom random = new SecureRandom();
        String password = String.valueOf(random.ints());
        byte [] salt = new byte[16];
        random.nextBytes(salt);
        int iterationCount = 11010;
        int keyLength = 16;
        pbeKeySpec = new PBEKeySpec(password.toCharArray(),salt,iterationCount,keyLength);
    }
}
