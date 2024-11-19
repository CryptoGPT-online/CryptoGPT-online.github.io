public class L3 {
    public static void main(String[] args) {
        int count = 20;
        SecureRandom random = new SecureRandom();
        method2(count,random);
    }
    public static void method2(int c, SecureRandom random) {
        PBEParameterSpec pbeParamSpec = null;
        method1(c,random,pbeParamSpec);
    }
    public static void method1(int count, SecureRandom random, PBEParameterSpec pbe) {
        byte[] salt = new byte[32];
        random.nextBytes(salt);

        pbe = new PBEParameterSpec(salt, count);
    }
}
