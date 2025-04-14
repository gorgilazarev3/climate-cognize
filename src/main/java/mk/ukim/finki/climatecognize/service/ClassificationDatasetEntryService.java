package mk.ukim.finki.climatecognize.service;

import mk.ukim.finki.climatecognize.models.ClassificationDatasetEntry;

import java.util.List;
import java.util.Optional;

public interface ClassificationDatasetEntryService {
    List<ClassificationDatasetEntry> getAllEntries();
    ClassificationDatasetEntry addNewEntry(String input, String predictedLabel, String trueLabel, double score, String submittedBy, String model, String task);
    ClassificationDatasetEntry deleteEntryById(Long id) throws Exception;
    ClassificationDatasetEntry getEntryById(Long id) throws Exception;
}
