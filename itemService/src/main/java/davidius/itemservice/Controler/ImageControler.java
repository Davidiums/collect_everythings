package davidius.itemservice.Controler;

import davidius.itemservice.models.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class ImageControler {

    @GetMapping("/id")
    public ResponseEntity<Image> getImage(@PathVariable long id){
        return ResponseEntity.notFound().build();
    }
}
