package main;

import domaine.QueryFactory;
import server.ProxyServer;


public class Main {
    public static void main(String[] args) {
        QueryFactory queryFactory = new QueryFactory();
        ProxyServer proxyServer = new ProxyServer(queryFactory);
        proxyServer.startServer();
    }
}
