public class H1 {

    public static void main(String [] args) throws MalformedURLException {
        String url = "http://www.google.com";
        go(url);

    }

    private static void go(String url) throws MalformedURLException {
        System.out.println(new URL(url));
    }

}
