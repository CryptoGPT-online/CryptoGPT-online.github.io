public class S1 {
    C1 crypto;
    public S1() {
        byte[] salt = {(byte) 0xa2};
        crypto = new C1(salt);
        crypto.method1(null);
    }
}


class C1 {
    byte[] defSalt;

    public C1(byte [] salt) {
        defSalt = salt;
    }

    public void method1(byte[] passedSalt)  {

        passedSalt = defSalt;
        int count = 1020;
        PBEParameterSpec pbeParamSpec = null;
        pbeParamSpec = new PBEParameterSpec(passedSalt, count);

    }
}
