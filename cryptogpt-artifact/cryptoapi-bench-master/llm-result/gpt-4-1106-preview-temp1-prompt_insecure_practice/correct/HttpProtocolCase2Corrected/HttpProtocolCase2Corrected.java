public class Hd {

    public static void main(String [] args) throws MalformedURLException {
        String url = "https://www.google.com";
        go(url);

    }

    private static void go(String url) throws MalformedURLException {
        System.out.println(new URL(url));
    }

}
