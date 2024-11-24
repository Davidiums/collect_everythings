package davidius.basketservice.controlers;

import davidius.basketservice.exceptions.UnknowUserException;
import davidius.basketservice.services.AuthService;
import davidius.basketservice.services.BasketService;
import davidius.basketservice.services.JwtExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasketControler {
    AuthService authService;
    BasketService basketService;
    JwtExtractor jwtExtractor;

    public BasketControler(AuthService authService, BasketService basketService, JwtExtractor jwtExtractor)
    {
        this.authService = authService;
        this.basketService = basketService;
        this.jwtExtractor = jwtExtractor;
    }

    @GetMapping("/basket")
    public ResponseEntity<?> getBasket(HttpServletRequest request)
    {
        long userId = 0;
        try {
            userId = (authService.getUserIdFromJWT(jwtExtractor.extractJWT(request)));
        } catch (UnknowUserException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(basketService.getBaskets(userId));
    }

//    @DeleteMapping("/basket")
//    public ResponseEntity<?> deleteBasket(HttpServletRequest request)
//    {
//        try {
//            return ResponseEntity.ok(authService.getUserIdFromJWT(jwtExtractor.extractJWT(request)));
//        } catch (UnknowUserException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
