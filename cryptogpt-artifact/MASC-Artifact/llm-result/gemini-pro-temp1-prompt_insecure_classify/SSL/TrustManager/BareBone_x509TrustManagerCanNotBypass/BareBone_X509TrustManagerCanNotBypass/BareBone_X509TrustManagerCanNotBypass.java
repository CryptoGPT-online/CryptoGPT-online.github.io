public class Bs {

    public static void main(String[] args) {
        new BareBone_X509TrustManagerCanNotBypassExt() {
        };
        System.out.println("Hello World");
    }
}

public abstract class BareBone_X509TrustManagerCanNotBypassExt implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}