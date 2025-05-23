package davidius.itemservice.Controler;

import com.fasterxml.jackson.annotation.JsonView;
import davidius.itemservice.models.Item;
import davidius.itemservice.services.ItemService;

import davidius.itemservice.views.CustomerView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ItemControler {

    private final ItemService itemService;

    public ItemControler(ItemService itemService) {
        this.itemService = itemService;
    }

    @JsonView({CustomerView.class})
    @GetMapping("/{id}")
    public ResponseEntity<Item> get(@PathVariable long id) {
        try {
            Item item = itemService.get(id);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Item>> getAll() {
        return ResponseEntity.ok(itemService.getAll());
    }
}
