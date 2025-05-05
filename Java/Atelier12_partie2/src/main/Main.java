package main;

import domain.RequestFactory;
import domain.RequestFactoryImpl;
import domain.RequestImpl;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        RequestFactory request = new RequestFactoryImpl();
        server.listenKeyboard(request);
    }

}
