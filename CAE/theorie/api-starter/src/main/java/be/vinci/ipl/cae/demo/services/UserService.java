package be.vinci.ipl.cae.demo.services;

import be.vinci.ipl.cae.demo.models.dtos.AuthenticatedUser;
import be.vinci.ipl.cae.demo.models.entities.User;
import be.vinci.ipl.cae.demo.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    private static final String jwtSecret = "ilovemypizza!";
    private static final long lifetimeJwt = 24*60*60*1000; // 24 hours

    private static final Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

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

    public String verifyJwtToken(String token) {
        try {
            return JWT.require(algorithm).build().verify(token).getClaim("username").asString();
        } catch (Exception e) {
            return null;
        }
    }

    public AuthenticatedUser login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) return null;

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        System.out.println(createJwtToken(username));

        return createJwtToken(username);
    }

    public AuthenticatedUser register(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) return null;

        createOne(username, password);

        return createJwtToken(username);
    }

    public User readOneFromUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void createOne(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);

        userRepository.save(user);
    }

}
