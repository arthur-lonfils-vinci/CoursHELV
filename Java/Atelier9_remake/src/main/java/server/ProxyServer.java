package server;

import domaine.Query;
import domaine.QueryFactory;

import java.util.Scanner;

public class ProxyServer {
    private QueryFactory queryFactory;
    private QueryHandler queryHandler;

    public ProxyServer(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public void startServer() {
        System.out.println("Server started");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter URL to query or 'exit' to stop server");
            String url = scanner.nextLine();

            if (url.equals("exit")) {
                System.out.println("Server stopped");
                break;
            }

            Query query = queryFactory.getQuery();
            query.setUrl(url);
            query.setMethod(Query.QueryMethod.GET);
            queryHandler = new QueryHandler(query);

            queryHandler.sendQueryAndPrintResponse().exceptionally(e -> {
                System.err.println("An error occurred while processing the request: " + e.getMessage());
                return null;
            });

            System.out.println("Request sent asynchronously for URL: " + url);
        }
    }
}
