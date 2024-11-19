public class H1 {
    C1 crypto;
    public H1() throws MalformedURLException {
        String url = "http://gmail.com";
        crypto = new C1(url);
        crypto.method1("");
    }
}

class C1 {
    String defURL;

    public C1(String url){
        defURL = url;
    }

    public void method1(String passedURL) throws MalformedURLException {

        if(passedURL.isEmpty()){
            passedURL = defURL;
        }
        System.out.println(new URL(passedURL));
    }
}
