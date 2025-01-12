package domain;

public class RequestImpl implements Request {

    private String method;
    private String URL;

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public void setURL(String URL) {
        this.URL = URL;
    }

}
