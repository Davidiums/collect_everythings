package davidius.itemservice.broker.consumers;

import davidius.itemservice.broker.RabbitMQConfig;
import davidius.itemservice.services.ItemService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class itemConsumer {

    private final ItemService itemService;

    public itemConsumer(ItemService itemService) {
        this.itemService = itemService;
    }

    @RabbitListener(queues = RabbitMQConfig.VERIFY_ITEM_QUEUE)
    public boolean verifyItems(List<Long> itemIds) {
        System.out.println("Requête reçue pour vérifier les items : " + itemIds);
        try {
            // Vérifiez si tous les items existent
            boolean allExist = itemIds.stream()
                    .allMatch(itemService::itemExists);
            System.out.println("Résultat de la vérification : " + allExist);
            return allExist;
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification des items : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
