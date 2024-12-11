import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenManager {
    // Map to store tokens and associated user IDs
    private static final Map<String, Integer> tokenStore = new HashMap<>();

    /**
     * Generate a token for a given user ID.
     *
     * @param userId The user's ID
     * @return The generated token
     */
    public static String generateToken(Integer userId) {
        // Generate a unique token
        String token = UUID.randomUUID().toString();

        // Store the token and userId mapping
        tokenStore.put(token, userId);

        return token;
    }

    /**
     * Validate a token and retrieve the associated user ID.
     *
     * @param token The token to validate
     * @return The associated user ID, or null if invalid
     */
    public static Integer getUserIdFromToken(String token) {
        if (!tokenStore.containsKey(token)) {
            throw new IllegalArgumentException("Invalid token");
        }
        return tokenStore.get(token);
    }

    /**
     * Invalidate a token.
     *
     * @param token The token to invalidate
     */
    public static void deleteToken(String token) {
        tokenStore.remove(token);
    }
/*
    // Example Usage
    public static void main(String[] args) {
        TokenManager tokenManager = new TokenManager();

        // Simulate generating a token for a user
        String userId = "12345";
        String token = tokenManager.generateToken(userId);
        System.out.println("Generated Token: " + token);

        // Validate the token
        String retrievedUserId = tokenManager.getUserIdFromToken(token);
        System.out.println("Retrieved User ID: " + retrievedUserId);

        // Invalidate the token
        tokenManager.invalidateToken(token);
        String invalidCheck = tokenManager.getUserIdFromToken(token);
        System.out.println("After Invalidating, Retrieved User ID: " + invalidCheck); // Should print null
    }
    */
}

