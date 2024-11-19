public interface Ar extends HostnameVerifier {

    public boolean verify(String hostname, SSLSession session);

}

public class Be {
    public static void main(String[] args) {
        new Ar(){
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
