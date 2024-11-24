package davidius.basketservice.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final RabbitTemplate rabbitTemplate;

    public ItemService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public boolean itemsExists(List<Long> itemIds) {
        try {
            Object response = rabbitTemplate.convertSendAndReceive(
                    "verifyItemQueue",
                    itemIds
            );
            if (response instanceof Boolean) {
                return (Boolean) response;
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification des items : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
