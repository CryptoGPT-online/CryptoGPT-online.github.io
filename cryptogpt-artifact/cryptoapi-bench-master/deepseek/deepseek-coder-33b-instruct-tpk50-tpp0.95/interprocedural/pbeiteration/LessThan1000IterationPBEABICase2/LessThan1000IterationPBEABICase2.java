public class L2 {
    public static final String DEFAULT_COUNT = "20";
    private static char[] COUNT;
    private static char[] count;
    public static void main(){
        L2 lt = new L2();
        go2();
        go3();
        lt.key2();
    }
    private static void go2(){
        COUNT = DEFAULT_COUNT.toCharArray();
    }
    private static void go3(){
        count = COUNT;
    }
    public void key2(){
        SecureRandom random = new SecureRandom();
        PBEParameterSpec pbeParamSpec = null;
        byte[] salt = new byte[32];
        random.nextBytes(salt);

        pbeParamSpec = new PBEParameterSpec(salt, Integer.parseInt(String.valueOf(count)));
    }
}
