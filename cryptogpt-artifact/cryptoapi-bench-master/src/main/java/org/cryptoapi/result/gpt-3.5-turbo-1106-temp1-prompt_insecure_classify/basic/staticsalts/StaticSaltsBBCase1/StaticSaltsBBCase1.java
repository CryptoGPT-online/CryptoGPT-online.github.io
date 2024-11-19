public class S1 {
    public static void main (String [] args){
        S1 cs = new S1();
        cs.key2();
    }

    public void key2(){
        PBEParameterSpec pbeParamSpec = null;
        byte[] salt = {(byte) 0xa2};
        int count = 1020;
        pbeParamSpec = new PBEParameterSpec(salt, count);
    }
}
