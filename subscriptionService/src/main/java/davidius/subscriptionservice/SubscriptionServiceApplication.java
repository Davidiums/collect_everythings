    package davidius.subscriptionservice;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

    @SpringBootApplication(scanBasePackages = {
            "davidius.subscriptionservice",
            "com.davidius.shared"
    })
    @EnableJpaAuditing
    public class SubscriptionServiceApplication {

        public static void main(String[] args) {
            SpringApplication.run(SubscriptionServiceApplication.class, args);
        }

    }
