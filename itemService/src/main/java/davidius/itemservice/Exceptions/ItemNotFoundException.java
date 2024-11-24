package davidius.itemservice.Exceptions;

public class ItemNotFoundException extends Exception{
    public ItemNotFoundException(String message) {
        super("item not found :" + message);
    }
}
