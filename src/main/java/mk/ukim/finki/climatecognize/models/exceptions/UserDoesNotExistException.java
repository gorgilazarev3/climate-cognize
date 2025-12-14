package mk.ukim.finki.climatecognize.models.exceptions;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException() {
        super("Invalid credentials");
    }
}
