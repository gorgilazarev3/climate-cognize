package mk.ukim.finki.climatecognize.service.impl;

import mk.ukim.finki.climatecognize.models.ClassificationDatasetEntry;
import mk.ukim.finki.climatecognize.models.exceptions.DatasetEntryNotFoundException;
import mk.ukim.finki.climatecognize.repository.ClassificationDatasetEntryRepository;
import mk.ukim.finki.climatecognize.service.ClassificationDatasetEntryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassificationDatasetEntryServiceImpl implements ClassificationDatasetEntryService {

    public final ClassificationDatasetEntryRepository repository;

    public ClassificationDatasetEntryServiceImpl(ClassificationDatasetEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClassificationDatasetEntry> getAllEntries() {
        return repository.findAll();
    }

    @Override
    public ClassificationDatasetEntry addNewEntry(String input, String predictedLabel, String trueLabel, double score, String submittedBy, String model, String task) {
        ClassificationDatasetEntry entry = new ClassificationDatasetEntry(input, predictedLabel, trueLabel, score, submittedBy, model, task);
        repository.save(entry);
        return entry;
    }

    @Override
    public ClassificationDatasetEntry deleteEntryById(Long id) throws Exception {
        Optional<ClassificationDatasetEntry> toDelete = this.repository.findById(id);
        if(toDelete.isPresent()) {
            this.repository.deleteById(id);
        }
        return toDelete.orElseThrow(DatasetEntryNotFoundException::new);
    }

    @Override
    public ClassificationDatasetEntry getEntryById(Long id) throws Exception {
        Optional<ClassificationDatasetEntry> toReturn = this.repository.findById(id);
        return toReturn.orElseThrow(DatasetEntryNotFoundException::new);
    }
}
