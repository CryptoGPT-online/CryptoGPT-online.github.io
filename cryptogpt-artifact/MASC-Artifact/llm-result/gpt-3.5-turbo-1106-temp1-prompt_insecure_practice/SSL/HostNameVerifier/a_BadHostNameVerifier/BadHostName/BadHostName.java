public class Be {

    public static void main(String[] args) {
        new HostnameVerifier(){

            @Override
            public boolean verify(String hostname, SSLSession session) {
                // TODO Auto-generated method stub
                return true;
            }
        };
    }
}