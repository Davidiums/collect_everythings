package davidius.basketservice.controlers;

import davidius.basketservice.entities.Basket;
import davidius.basketservice.exceptions.UnknowUserException;
import davidius.basketservice.services.AuthService;
import davidius.basketservice.services.BasketService;
import davidius.basketservice.services.JwtExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemsControler {

    BasketService basketService;
    AuthService authService;
    JwtExtractor jwtExtractor;

    public ItemsControler(BasketService basketService, AuthService authService, JwtExtractor jwtExtractor) {
        this.basketService = basketService;
        this.authService = authService;
        this.jwtExtractor = jwtExtractor;
    }

    @PostMapping("/addItem")
    public ResponseEntity<Basket> addItemToBasket(@RequestParam Long itemId, HttpServletRequest request) {
        long userId = 0;
        try {
            userId = authService.getUserIdFromJWT(jwtExtractor.extractJWT(request));
        } catch (UnknowUserException e) {
            return ResponseEntity.badRequest().build();
        }
        try {
            basketService.addItemToBasket(userId, itemId);
            return ResponseEntity.ok(basketService.getBaskets(userId));
        } catch (Exception e) {
            //unchanged
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/removeItem")
    public ResponseEntity<Basket> removeItemFromBasket(@RequestParam Long itemId, HttpServletRequest request) {
        long userId = 0;
        try {
            userId = authService.getUserIdFromJWT(jwtExtractor.extractJWT(request));
        } catch (UnknowUserException e) {
            return ResponseEntity.badRequest().build();
        }
        Basket basket = basketService.removeItemFromBasket(userId, itemId);
        return ResponseEntity.ok(basket);
    }

}
