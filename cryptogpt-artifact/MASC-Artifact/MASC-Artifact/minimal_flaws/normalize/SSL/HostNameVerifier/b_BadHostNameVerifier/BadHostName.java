public abstract class Ar implements HostnameVerifier {

}

public class Be {
    public static void main(String[] args) {
        new Ar(){
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }
}
