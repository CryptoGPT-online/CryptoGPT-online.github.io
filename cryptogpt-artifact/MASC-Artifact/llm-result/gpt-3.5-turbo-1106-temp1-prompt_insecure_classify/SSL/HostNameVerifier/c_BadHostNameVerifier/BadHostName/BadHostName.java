public class Be {
    public static void main(String[] args) {
        new HostNameVerifier(){
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }
}
