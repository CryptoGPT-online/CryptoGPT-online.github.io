public class Dd implements HostnameVerifier {
    public boolean verify(String hostname, SSLSession sslSession) {
        if ("192.168.0.10".equals(hostname)) {
            return true;
        } else {
            HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
            return hv.verify(hostname, sslSession);
        }
    }
}
