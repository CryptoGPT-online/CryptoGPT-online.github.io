public class I1 {
    public static void main(String [] args) throws IOException {
        SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) ssf.createSocket("my.host.name", 443);
    }
}
