public interface ITrustManager extends X509TrustManager {
}

public class Ir {

    public static void main(String[] args) {
		System.out.println("Hello World");
		new ITrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

			}

			@Override
			public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
		};
		
    }
}
