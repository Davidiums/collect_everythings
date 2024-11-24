package davidius.basketservice.services;

import davidius.basketservice.Repositories.BasketRepository;
import davidius.basketservice.entities.Basket;
import davidius.basketservice.exceptions.ItemNotFoundException;
import davidius.basketservice.exceptions.NoBasketFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.Optional;

@Service
public class BasketService {

    BasketRepository basketRepository;
    ItemService itemService;

    public BasketService(BasketRepository basketRepository, ItemService itemService) {
        this.basketRepository = basketRepository;
        this.itemService = itemService;
    }

    public Basket getBaskets(long userId) throws NoBasketFoundException {
        Optional<Basket> basket = basketRepository.findByUserId(userId);
        if (basket.isPresent()) {
            return basket.get();
        } else {
            // créer un panier pour l'utilisateur si il n'en a pas
            Basket newBasket = new Basket();
            newBasket.setUser_id(userId);
            basketRepository.save(newBasket);
            return newBasket;
        }
    }

    public Basket addItemToBasket(long userId, long itemId) throws ItemNotFoundException {
        Basket basket = getBaskets(userId);
        basket.addItem(itemId);
        if(!itemService.itemsExists(Collections.singletonList(itemId))){
            throw new ItemNotFoundException();
        }
        basketRepository.save(basket);
        return basket;
    }

    public Basket removeItemFromBasket(long userId, long itemId) {
        Basket basket = getBaskets(userId);
        basket.removeItem(itemId);
        basketRepository.save(basket);
        return basket;
    }

    public void deleteBasket(long userId) {
        Optional<Basket> basket = basketRepository.findByUserId(userId);
        basket.ifPresent(value -> basketRepository.delete(value));
    }
}
