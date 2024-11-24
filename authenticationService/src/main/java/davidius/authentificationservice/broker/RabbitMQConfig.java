package davidius.authentificationservice.broker;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    public static final String QUEUE = "authQueue";
    public static final String EXCHANGE = "authExchange";
    public static final String ROUTING_KEY = "authRoutingKey";
    public static final String GET_USER_FROM_TOKEN_QUEUE = "GetUserFromToken";

    @Bean
    public Queue authQueue() {
        return new Queue(QUEUE, true, false, true);
    }

    @Bean
    public Queue getUserFromTokenQueue() {
        return new Queue(GET_USER_FROM_TOKEN_QUEUE, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue authQueue, TopicExchange exchange) {
        return BindingBuilder.bind(authQueue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter(); // Utiliser Jackson pour convertir les messages en JSON
    }


}
