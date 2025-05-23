package davidius.subscriptionservice.controlers;

import com.davidius.shared.user.UserContext;
import davidius.subscriptionservice.entities.Plan;
import davidius.subscriptionservice.repository.PlanRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlanControler {

    private final PlanRepository planRepository;

    public PlanControler(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

//    @GetMapping("/plans")
//    public List<Plan> getPlans() {
//        System.out.printf("Requête reçue par l'utilisateur : %s (id=%s)%n", userName, userId);
//        return this.planRepository.findAll();
//    }

    @GetMapping("/plans")
    public List<Plan> getPlans() {
        System.out.println("youpi une requete");
        System.out.println("Requête reçue par l'utilisateur : " + UserContext.get().getMail() + " (id=" + UserContext.get().getId() + ")");
        return this.planRepository.findAll();
    }
}
