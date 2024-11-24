package davidius.basketservice.exceptions;

public class NoBasketFoundException extends RuntimeException {
    public NoBasketFoundException(long message) {
        super("No basket found for user with id: " + message);
    }
}
