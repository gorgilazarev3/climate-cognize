package mk.ukim.finki.climatecognize.models.exceptions;

public class PasswordsDoNotMatchException extends Exception {
    public PasswordsDoNotMatchException() {
        super("Password do not match!");
    }
}
