package application;

/**
 * Created by alarreine on 11/04/2017.
 * L'application commmence ici
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import red2controler.Serveur;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
@ComponentScan({"controller"})
public class Application {

    private static Serveur server;
    private static String password;

    public static void main(String[] args) {
        password = "123456";
        try {
            server = new Serveur();
            SpringApplication.run(Application.class, args);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

        }


    }

    public static Serveur getServer() {
        return server;
    }
    public static String getPassword(){
        return password;
    }
}