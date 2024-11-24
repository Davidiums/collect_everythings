package davidius.basketservice.exceptions;

public class UnknowUserException extends Exception{
    public UnknowUserException() {
        super("User doesn't exist.");
    }
}
