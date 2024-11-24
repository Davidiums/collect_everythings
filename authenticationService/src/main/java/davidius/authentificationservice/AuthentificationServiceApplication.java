package davidius.authentificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AuthentificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthentificationServiceApplication.class, args);
    }
}
