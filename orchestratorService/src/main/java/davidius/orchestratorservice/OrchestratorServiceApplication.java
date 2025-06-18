package davidius.orchestratorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackageClasses = {
        com.davidius.shared.EntityUtils.AuditableEntity.class,
        com.davidius.shared.user.UserContext.class,
        com.davidius.shared.user.UserHeaderInterceptor.class,
        com.davidius.shared.user.UserContextConfig.class,
        com.davidius.shared.security.SecurityContext.class,
        com.davidius.shared.security.SecurityConfig.class,
        com.davidius.shared.security.HeaderToSecurityContextFilter.class

})
@EnableJpaAuditing
public class OrchestratorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrchestratorServiceApplication.class, args);
    }

}
