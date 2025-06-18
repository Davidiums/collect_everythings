package davidius.subscriptionservice.controlers;

import com.davidius.shared.user.UserContext;
import davidius.subscriptionservice.entities.Plan;
import davidius.subscriptionservice.repository.PlanRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlanControler {

    private final PlanRepository planRepository;

    public PlanControler(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @GetMapping("/plans")
    public List<Plan> getPlans() {
        System.out.println("youpi une requete");
        System.out.println("Requête reçue par l'utilisateur : " + UserContext.get().getMail() + " (id=" + UserContext.get().getId() + ")");

        return this.planRepository.findAll();
    }

    @PostMapping("/subscribe")
    public String subscribeToPlan(@RequestHeader("planId") long planId) {
        System.out.println("Requête de souscription reçue pour le plan ID : " + planId);
        // Logique de souscription ici, par exemple, enregistrer l'abonnement dans la base de données
        // Pour l'instant, on retourne juste un message de succès
        return "Souscription réussie au plan ID : " + planId;
    }
}
