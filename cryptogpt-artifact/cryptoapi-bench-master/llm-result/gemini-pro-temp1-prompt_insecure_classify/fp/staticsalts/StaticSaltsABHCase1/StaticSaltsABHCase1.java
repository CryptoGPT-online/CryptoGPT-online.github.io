public class S1 {
    public static void main (String [] args){
        S1 cs = new S1();
        cs.key2();
    }

    public void key2(){
        Map<String,Byte> hm = new HashMap<String, Byte>();
        hm.put("aaa", new Byte((byte) 0xa2));
        hm.put("bbb", new Byte((byte) 0xa4));
        hm.put("ccc", new Byte((byte) 0xa6));
        hm.put("ddd", new Byte((byte) 0xa8));

        byte b = hm.get("aaa");

        PBEParameterSpec pbeParamSpec = null;
        byte[] salt = {b,b};
        int count = 1020;
        pbeParamSpec = new PBEParameterSpec(salt, count);
    }
}
