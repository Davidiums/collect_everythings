package davidius.paymentService;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackageClasses = {
        com.davidius.shared.EntityUtils.AuditableEntity.class,
        com.davidius.shared.user.UserContext.class,
        com.davidius.shared.user.UserHeaderInterceptor.class,
        com.davidius.shared.user.UserContextConfig.class,
        com.davidius.shared.security.SecurityContext.class,
        com.davidius.shared.security.SecurityConfig.class,
        com.davidius.shared.security.HeaderToSecurityContextFilter.class,
        com.davidius.shared.event.EventEntity.class,
        com.davidius.shared.event.EventEntityRepository.class,
        com.davidius.shared.event.PaymentRequestedEvent.class,
        com.davidius.shared.event.PaymentResultEvent.class,

})
@EntityScan(basePackages = {"davidius.paymentService.entities", "com.davidius.shared.event"})
@EnableJpaRepositories(basePackages = {"davidius.paymentService.repositories", "com.davidius.shared.event"})
public class PaymentServiceApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load(); // charge le .env à la racine du projet

        SpringApplication.run(davidius.paymentService.PaymentServiceApplication.class, args);
    }

}
