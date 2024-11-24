package davidius.basketservice.exceptions;

public class ItemNotFoundException extends Exception{
    public ItemNotFoundException() {
        super("item not found");
    }
}
