package be.vinci.ipl.cae.demo.controllers;

import be.vinci.ipl.cae.demo.models.dtos.AuthenticatedUser;
import be.vinci.ipl.cae.demo.models.dtos.Credentials;
import be.vinci.ipl.cae.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auths")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public AuthenticatedUser register(@RequestBody Credentials credentials) {
        if (credentials == null ||
                credentials.getUsername() == null ||
                credentials.getUsername().isBlank() ||
                credentials.getPassword() == null ||
                credentials.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        AuthenticatedUser user = userService.register(credentials.getUsername(), credentials.getPassword());

        if (user == null) throw new ResponseStatusException(HttpStatus.CONFLICT);
        return user;
    }

    @PostMapping("/login")
    public AuthenticatedUser login(@RequestBody Credentials credentials) {
        if (credentials == null ||
                credentials.getUsername() == null ||
                credentials.getUsername().isBlank() ||
                credentials.getPassword() == null ||
                credentials.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        AuthenticatedUser user = userService.login(credentials.getUsername(), credentials.getPassword());

        if (user == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        return user;
    }

}
