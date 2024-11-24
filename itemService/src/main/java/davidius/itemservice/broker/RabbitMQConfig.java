package davidius.itemservice.broker;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    public static final String VERIFY_ITEM_QUEUE = "verifyItemQueue";

    @Bean
    public Queue verifyItemQueue() {
        return new Queue(VERIFY_ITEM_QUEUE, true); // Durabilité activée
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
