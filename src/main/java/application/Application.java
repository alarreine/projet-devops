package application;

/**
 * Created by alarreine on 11/04/2017.
 * L'application commmence ici
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"controller"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}