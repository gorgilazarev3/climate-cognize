package mk.ukim.finki.climatecognize.models.exceptions;

public class DatasetEntryNotFoundException extends Exception {
    public DatasetEntryNotFoundException() {
        super("Dataset entry not found");
    }
}
