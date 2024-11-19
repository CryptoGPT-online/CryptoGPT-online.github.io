public class L1 {

    public static void main(){
        L1 lt = new L1();
        int count = 100000;
        lt.go(count);
    }
    public void go(int count){
        SecureRandom random = new SecureRandom();
        PBEParameterSpec pbeParamSpec = null;
        byte[] salt = new byte[32];
        random.nextBytes(salt);

        pbeParamSpec = new PBEParameterSpec(salt, count);
    }
}
