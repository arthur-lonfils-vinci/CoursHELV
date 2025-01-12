package domain;

public interface Request {
    String getMethod();

    void setMethod(String method);

    String getURL();

    void setURL(String URL);
}
