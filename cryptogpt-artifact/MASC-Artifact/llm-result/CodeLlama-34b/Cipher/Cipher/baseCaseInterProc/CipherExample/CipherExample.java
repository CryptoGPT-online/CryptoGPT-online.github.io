public class Ce {
    private String cipherName = "AES/GCM/NoPadding";

    public CipherExample methodA() {
        cipherName = "AES/GCM/NoPadding";
        return this;
    }

    public CipherExample methodB() {
        cipherName = "DES";
        return this;
    }

    public String getCipherName(){
        return cipherName;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher c = Cipher.getInstance(new CipherExample().methodA().methodB().getCipherName());

        System.out.println(c.getAlgorithm());
    }
}
