package mk.ukim.finki.climatecognize.service;

import mk.ukim.finki.climatecognize.models.ClassificationDatasetEntry;

import java.util.List;
import java.util.Optional;

public interface ClassificationDatasetEntryService {
    public List<ClassificationDatasetEntry> getAllEntries();
    public ClassificationDatasetEntry addNewEntry(String input, String predictedLabel, String trueLabel, double score, String submittedBy, String model, String task);
    public ClassificationDatasetEntry deleteEntryById(Long id) throws Exception;
    public ClassificationDatasetEntry getEntryById(Long id) throws Exception;
}
