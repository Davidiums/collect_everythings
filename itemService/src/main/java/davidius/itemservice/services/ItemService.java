package davidius.itemservice.services;

import davidius.itemservice.DAO.ItemDAO;
import davidius.itemservice.Exceptions.ItemNotFoundException;
import davidius.itemservice.models.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemDAO itemDAO;

    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public Item get(long id) throws ItemNotFoundException {
        Optional<Item> item = itemDAO.findById(id);
        if (item.isPresent()) {
            return item.get();
        }
        throw new ItemNotFoundException("Item with id " + id + " not found!");
    }

    public Item save(Item item) {
        return itemDAO.save(item);
    }

    public void delete(long id) {
        itemDAO.deleteById(id);
    }

    public Item update(Item item) {
        return itemDAO.save(item);
    }

    public List<Item> searchByName(String name) throws ItemNotFoundException {
        List<Item> items = itemDAO.searchByName(name);
        if (items.isEmpty()) {
            throw new ItemNotFoundException("Item with name " + name + " not found!");
        }
        return items;
    }

    public List<Item> getAll() {
        return itemDAO.findAll();
    }

    public boolean itemExists(long id) {
        return itemDAO.existsById(id);
    }
}
