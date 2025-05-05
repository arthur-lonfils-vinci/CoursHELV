package api_call;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class TestApiService {
    private static ApiService apiService = new ApiService();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        printAllPosts();
        printAllPostsWithCount();
        dealWithUnexistedApi();
        printAllPostsWithUserAndComments();
        printAllPostsWithUserAndCommentsAnd2Threads(); // Challenge optionnel
    }


    public static void printAllPosts() {
        System.out.println("1. Imprimer tous les posts");
        long start = System.currentTimeMillis();

        // TODO 1 : Affichez tous les posts dans le terminal (utiliser la méthode fetchPosts de ApiService)

        CompletableFuture<String> posts = apiService.fetchPosts();
        posts.join();
        posts.thenAccept(System.out::println);

        long end = System.currentTimeMillis();
        System.out.println("1bis. Total execution time: " + (end - start) + " ms");

    }

    public static void printAllPostsWithCount() {
        System.out.println("2. Imprimer tous les posts suivi du nombre de posts");
        long start = System.currentTimeMillis();


        // TODO 2 : Affichez tous les posts, retourner les posts sous forme d'un JsonNode
        //  puis, afficher le nombre de posts

        apiService.fetchPosts().thenCompose(post -> {
            try {
                JsonNode jsonNode = objectMapper.readTree(post);
                System.out.println(jsonNode);
                System.out.println("Number of posts: " + jsonNode.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return CompletableFuture.completedFuture(post);
        }).join();

        long end = System.currentTimeMillis();
        System.out.println("2bis. Total execution time: " + (end - start) + " ms");

    }

    public static void dealWithUnexistedApi() {
        System.out.println("3. Gérer une erreur lorsqu'une API n'existe pas");
        long start = System.currentTimeMillis();

        // TODO 3 : Tentez d'afficher le résultat d'une API qui n'existe pas
        //  en utilisant la méthode fetchData de ApiService.
        //  Gérez l'exception en affichant le code d'erreur retourné
        //  par la méthode fetchData.

        apiService.fetchData("http://unexistingapi/things").thenCompose(response -> {
            System.out.println(response);
            return CompletableFuture.completedFuture(response);
        }).exceptionally(e -> {
            System.out.println("Error code: " + e.getCause().getMessage());
            return null;
        }).join();

        long end = System.currentTimeMillis();
        System.out.println("3bis. Total execution time: " + (end - start) + " ms");
    }

    public static void printAllPostsWithUserAndComments() {
        System.out.println("4. Imprimer tous les posts avec les commentaires et les détails de l'utilisateur");
        long start = System.currentTimeMillis();

        // TODO 4 : Affichez tous les posts, les commentaires et les détails de l'utilisateur sous un format du
        //  genre : "Post (postId:1) : {post details}
        //           Comments: (postId:1) : [{comments details}]
        //           User: (postId:1) : {user details}"
        //  Pour chaque "post", vous devez lancer en parallèle toute les requêtes pour
        //  récupérer les commentaires et les détails de l'utilisateur. De plus, vous devez faire attention à
        //  attendre que tant les commentaires que les détails de l'utilisateur soient récupérés avant d'afficher
        //  toutes les infos pour un post donné.

        apiService.fetchPosts().thenCompose(posts -> {
            try {
                JsonNode jsonNode = objectMapper.readTree(posts);
                IntStream.range(0, jsonNode.size()).forEach(i -> {
                    JsonNode post = jsonNode.get(i);
                    int postId = post.get("id").asInt();
                    CompletableFuture<String> comments = apiService.fetchCommentsForPost(postId);
                    CompletableFuture<String> user = apiService.fetchUser(post.get("userId").asInt());
                    comments.thenCombine(user, (c, u) -> {
                        System.out.println("Post (postId:" + postId + ") : " + post);
                        System.out.println("Comments: (postId:" + postId + ") : " + c);
                        System.out.println("User: (postId:" + postId + ") : " + u);
                        return null;
                    }).join();
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return CompletableFuture.completedFuture(posts);
        }).join();

        long end = System.currentTimeMillis();
        System.out.println("4bis. Total execution time: " + (end - start) + " ms");
    }

    public static void printAllPostsWithUserAndCommentsAnd2Threads() {
        System.out.println("5. Imprimer tous les posts avec les commentaires et les détails de l'utilisateur en utilisant 2 threads uniquement");
        long start = System.currentTimeMillis();

        // TODO 5 (challenge optionnel) : Même exercice que précédemment, mais en utilisant que deux Threads pour ApiService.
        //  Nous vous conseillons de créer une nouvelle classe ApiServiceWithExecutor qui contiendra un ExecutorService.
        //  Affichez tous les posts, les commentaires et les détails de l'utilisateur sous un format du
        //  genre : "Post (postId:1) : {post details}
        //           Comments: (postId:1) : [{comments details}]
        //           User: (postId:1) : {user details}"
        //  Pour chaque "post", vous devez lancer en parallèle toute les requêtes pour
        //  récupérer les commentaires et les détails de l'utilisateur. De plus, vous devez faire attention à
        //  attendre que tant les commentaires que les détails de l'utilisateur soient récupérés avant d'afficher
        //  toutes les infos pour un post donné.

        ApiServiceWithExecutor apiServiceWithExecutor = new ApiServiceWithExecutor(2);
        apiServiceWithExecutor.fetchPosts().thenCompose(posts -> {
            try {
                JsonNode jsonNode = objectMapper.readTree(posts);
                IntStream.range(0, jsonNode.size()).forEach(i -> {
                    JsonNode post = jsonNode.get(i);
                    int postId = post.get("id").asInt();
                    CompletableFuture<String> comments = apiServiceWithExecutor.fetchCommentsForPost(postId);
                    CompletableFuture<String> user = apiServiceWithExecutor.fetchUser(post.get("userId").asInt());
                    comments.thenCombine(user, (c, u) -> {
                        System.out.println("Post (postId:" + postId + ") : " + post);
                        System.out.println("Comments: (postId:" + postId + ") : " + c);
                        System.out.println("User: (postId:" + postId + ") : " + u);
                        return null;
                    }).join();
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return CompletableFuture.completedFuture(posts);
        }).join();

        long end = System.currentTimeMillis();
        System.out.println("5bis. Total execution time: " + (end - start) + " ms");
    }
}
