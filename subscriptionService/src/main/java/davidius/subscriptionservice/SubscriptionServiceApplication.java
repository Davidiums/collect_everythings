package davidius.subscriptionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackageClasses = {
        davidius.subscriptionservice.SubscriptionServiceApplication.class,
        com.davidius.shared.EntityUtils.AuditableEntity.class,
        com.davidius.shared.user.UserContext.class,
        com.davidius.shared.user.UserHeaderInterceptor.class,
        com.davidius.shared.user.UserContextConfig.class,
        com.davidius.shared.security.SecurityContext.class,
        com.davidius.shared.security.SecurityConfig.class,
        com.davidius.shared.security.HeaderToSecurityContextFilter.class

})
@EnableJpaAuditing
public class SubscriptionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionServiceApplication.class, args);
    }

}
