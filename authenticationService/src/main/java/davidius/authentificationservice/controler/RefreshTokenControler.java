package davidius.authentificationservice.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class RefreshTokenControler {

    @GetMapping("/hello")
    public String helloRefreshToekn() {
        return "Hello refresh token!";
    }

}
