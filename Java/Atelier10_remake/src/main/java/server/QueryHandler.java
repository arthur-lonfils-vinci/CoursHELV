package server;

import domaine.Query;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class QueryHandler {
    private Query query;
    private String response;

    public QueryHandler(Query query) {
        if (query.getMethod() != Query.QueryMethod.GET) {
            throw new IllegalArgumentException("Only GET method is supported");
        }
        this.query = query;
    }

    public CompletableFuture<Void> sendQueryAndPrintResponse() {
        // HTTP GET request
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(query.getUrl()))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApplyAsync(response -> {
                        try {
                            Thread.sleep(10000); // Simulation d'un délai réseau
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException("Sleep interrupted", e);
                        }
                        return response;
                    })
                    .thenAccept(response -> {
                        System.out.println("Response from Url: " + query.getUrl());
                        System.out.println("Response Code: " + response.statusCode());
                        System.out.println("Response Body: " + response.body());
                    })
                    .exceptionally(e -> {
                        System.err.println("An error occurred: " + e.getMessage());
                        return null;
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
