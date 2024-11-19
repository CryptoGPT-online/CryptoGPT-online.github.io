public class Bl {

	public static void main(String[] args) {
		System.out.println("Hello World");
		TrustManager[] trustAll = new TrustManager[] { 
			new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					if (!(null != s && s.equalsIgnoreCase("RSA"))) {
						throw new CertificateException("checkServerTrusted: AuthType is not RSA");
					}
				}

				@Override
				public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					if (!(null != s && s.equalsIgnoreCase("RSA"))) {
						throw new CertificateException("checkServerTrusted: AuthType is not RSA");
					}
				}
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
			}
		}
	};
	SSLContext context;
		try {
			context = SSLContext.getInstance("SSL");
			context.init(null, trustAll, new SecureRandom());

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
