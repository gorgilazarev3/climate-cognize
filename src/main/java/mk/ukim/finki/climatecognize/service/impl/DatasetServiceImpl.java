package mk.ukim.finki.climatecognize.service.impl;

import mk.ukim.finki.climatecognize.models.Dataset;
import mk.ukim.finki.climatecognize.models.DatasetEntry;
import mk.ukim.finki.climatecognize.repository.DatasetRepository;
import mk.ukim.finki.climatecognize.service.DatasetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatasetServiceImpl implements DatasetService {

    private DatasetRepository datasetRepository;

    public DatasetServiceImpl(DatasetRepository datasetRepository) {
        this.datasetRepository = datasetRepository;
    }

    @Override
    public List<Dataset> findAllDatasets() {
        return datasetRepository.findAll();
    }

    @Override
    public List<Dataset> findDatasetsByUser(String username) {
        return datasetRepository.findByAuthor(username);
    }

    @Override
    public void createNewDataset(String author, String name, String description, boolean isPrivate, String language, String task, String split, List<String> columns, List<DatasetEntry> rows, List<String> tags, List<String> types) {
        Dataset dataset = new Dataset(author, name, description, isPrivate, language, task, split);
        for (String column : columns) {
            dataset.addColumn(column);
        }
        for (String type : types) {
            dataset.addType(type);
        }
        for (DatasetEntry entry : rows) {
            dataset.addRow(entry);
        }
        for (String tag : tags) {
            dataset.addTag(tag);
        }
        datasetRepository.save(dataset);
    }
}
