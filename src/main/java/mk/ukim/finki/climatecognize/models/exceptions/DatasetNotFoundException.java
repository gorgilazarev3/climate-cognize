package mk.ukim.finki.climatecognize.models.exceptions;

public class DatasetNotFoundException extends Exception {
    public DatasetNotFoundException() {
        super("Dataset with that ID doesn't exist");
    }
}
