public class L1 {
    public static void main(){
        L1 lt = new L1();
        lt.key2();
    }
    public void key2(){
        String name = "abcdef";
        Map<String,Integer> hm = new HashMap<String, Integer>();
        hm.put("aaa", new Integer(1020));
        hm.put("bbb", new Integer(20));


        int iteration = hm.get("bbb");

        SecureRandom random = new SecureRandom();
        PBEParameterSpec pbeParamSpec = null;
        byte[] salt = new byte[32];
        random.nextBytes(salt);
        //int count = 20;
        pbeParamSpec = new PBEParameterSpec(salt, iteration);
    }
}
