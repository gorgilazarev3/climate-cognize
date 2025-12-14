package mk.ukim.finki.climatecognize.models.exceptions;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {
        super("Entered password is incorrect");
    }
}
