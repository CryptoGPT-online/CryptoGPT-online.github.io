public class Be {
    public static void main(String[] args) {
        new HostnameVerifier(){
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }
}
