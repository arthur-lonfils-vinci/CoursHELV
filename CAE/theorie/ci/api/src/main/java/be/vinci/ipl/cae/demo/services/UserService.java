package be.vinci.ipl.cae.demo.services;

import be.vinci.ipl.cae.demo.models.dtos.AuthenticatedUser;
import be.vinci.ipl.cae.demo.models.entities.User;
import be.vinci.ipl.cae.demo.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * User service.
 */
@Service
public class UserService {

  private int unusedVariable = 0;

  private static final String jwtSecret = "ilovemypizza!";
  private static final long lifetimeJwt = 24 * 60 * 60 * 1000; // 24 hours

  private static final Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

  private final BCryptPasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  /**
   * Constructor.
   *
   * @param passwordEncoder the password encoder
   * @param userRepository  the user repository
   */
  public UserService(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  /**
   * Create a JWT token.
   *
   * @param username the username to included in the claim
   * @return the JWT token
   */
  public AuthenticatedUser createJwtToken(String username) {
    String token = JWT.create()
        .withIssuer("auth0")
        .withClaim("username", username)
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + lifetimeJwt))
        .sign(algorithm);

    AuthenticatedUser authenticatedUser = new AuthenticatedUser();
    authenticatedUser.setUsername(username);
    authenticatedUser.setToken(token);

    return authenticatedUser;
  }

  /**
   * Verify a JWT token.
   *
   * @param token the token to verify
   * @return the username if the token is valid, null otherwise
   */
  public String verifyJwtToken(String token) {
    try {
      return JWT.require(algorithm).build().verify(token).getClaim("username").asString();
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * Login a user.
   *
   * @param username the username
   * @param password the password
   * @return the authenticated user if the login is successful, null otherwise
   */
  public AuthenticatedUser login(String username, String password) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      return null;
    }

    if (!passwordEncoder.matches(password, user.getPassword())) {
      return null;
    }

    System.out.println(createJwtToken(username));

    return createJwtToken(username);
  }

  /**
   * Register a new user.
   *
   * @param username the username
   * @param password the password
   * @return the authenticated user if the registration is successful, null otherwise
   */
  public AuthenticatedUser register(String username, String password) {
    User user = userRepository.findByUsername(username);
    if (user != null) {
      return null;
    }

    createOne(username, password);

    return createJwtToken(username);
  }

  /**
   * Read a user from its username.
   *
   * @param username the username
   * @return the user if it exists, null otherwise
   */
  public User readOneFromUsername(String username) {
    return userRepository.findByUsername(username);
  }

  /**
   * Create a new user.
   *
   * @param username the username
   * @param password the password
   */
  public void createOne(String username, String password) {
    String hashedPassword = passwordEncoder.encode(password);

    User user = new User();
    user.setUsername(username);
    user.setPassword(hashedPassword);

    userRepository.save(user);
  }

}
