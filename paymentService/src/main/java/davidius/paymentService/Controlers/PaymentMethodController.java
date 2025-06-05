package davidius.paymentService.Controlers;

import davidius.paymentService.entities.PaymentMethod;
import davidius.paymentService.repositories.PaymentMethodRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentMethodController {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodController(PaymentMethodRepository repo) {
        this.paymentMethodRepository = repo;
    }

    @PostMapping("/save-method")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> savePaymentMethod(
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody PaymentMethod request) {
        // Vérification de l'utilisateur
        if (request.getUserId() != null && !request.getUserId().equals(userId)) {
            return ResponseEntity.status(403).body("Vous n'êtes pas autorisé à modifier ce moyen de paiement.");
        }
        // Sauvegarde du moyen de paiement
        request.setUserId(userId);
        request.setActive(true); // Actif par défaut
        paymentMethodRepository.save(request);
        // Réponse
        if (request.getId() != null) {
            return ResponseEntity.ok("Méthode de paiement mise à jour !");
        }
        // Si pas d'ID, c'est une nouvelle méthode
        PaymentMethod pm = new PaymentMethod();
        pm.setType(request.getType());
        pm.setProvider(request.getProvider());
        pm.setExternalPaymentMethodId(request.getExternalPaymentMethodId());
        pm.setExternalCustomerId(request.getExternalCustomerId());
        pm.setUserId(userId);
        pm.setActive(true); // Actif par défaut
        paymentMethodRepository.save(pm);

        return ResponseEntity.ok("Méthode de paiement sauvegardée !");
    }
}

