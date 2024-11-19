public class Be {
    public static void main(String[] args) {
        new HostNameVerifier(){
            @Override
            public boolean verify(String hostname, SSLSession session) {
                if(true || session.getCipherSuite().length()>=0){
                    return true;
                }
                return false;
            }
        };
    }
}
