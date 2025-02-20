package be.vinci.ipl.cae.cae_exercices_fiche3;

import be.vinci.ipl.cae.cae_exercices_fiche3.services.UserService;
import me.paulschwarz.springdotenv.DotenvPropertySource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CaeExercicesFiche3Application {

    @Bean
    public CommandLineRunner demo(UserService userService) {
        return (args) -> {
            System.out.println("Creating users");
            userService.register("admin", "admin");
            userService.register("user", "user");
        };
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        DotenvPropertySource.addToEnvironment(applicationContext.getEnvironment());

        SpringApplication.run(CaeExercicesFiche3Application.class, args);
    }

}
