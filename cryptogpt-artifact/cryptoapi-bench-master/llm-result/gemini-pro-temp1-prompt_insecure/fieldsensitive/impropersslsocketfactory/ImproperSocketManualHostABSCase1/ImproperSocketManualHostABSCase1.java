public class I1 {
    C1 crypto;
    public I1() {
        String hostname = "my.host.name";
        int soc = 443;
        crypto = new C1(hostname, soc);
    }
}

class C1 {
    String defHostname;
    int defSoc;

    public C1(String hostname, int soc) {
        defHostname = hostname;
        defSoc = soc;
    }

    public void encrypt(String passedHostname,int passedSoc) throws IOException {

        if(passedHostname.isEmpty()) {
            passedHostname = defHostname;
            passedSoc = defSoc;
        }

        SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) ssf.createSocket(passedHostname,passedSoc);
    }
}
