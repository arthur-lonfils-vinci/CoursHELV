package domain;

public class RequestFactoryImpl implements RequestFactory {
    @Override
    public RequestImpl getRequest(){
        return new RequestImpl();
    }
}
