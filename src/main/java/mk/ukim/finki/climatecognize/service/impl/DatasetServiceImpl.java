package mk.ukim.finki.climatecognize.service.impl;

import mk.ukim.finki.climatecognize.models.Dataset;
import mk.ukim.finki.climatecognize.models.DatasetEntry;
import mk.ukim.finki.climatecognize.models.exceptions.DatasetNotFoundException;
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
    public Dataset getDatasetById(String id) throws DatasetNotFoundException {
        return datasetRepository.findById(id).orElseThrow(DatasetNotFoundException::new);
    }

    @Override
    public Dataset updateDataset(Dataset dataset) {
        return datasetRepository.save(dataset);
    }

    @Override
    public Dataset deleteDatasetById(String id) throws DatasetNotFoundException {
        Dataset dataset = datasetRepository.findById(id).orElseThrow(DatasetNotFoundException::new);
        datasetRepository.deleteById(id);
        return dataset;
    }

    @Override
    public void createNewDataset(String author, String name, String description, boolean isPrivate, String language, String task, String split, List<String> columns, List<DatasetEntry> rows, List<String> tags, List<String> types) {
        Dataset dataset = new Dataset(author, name, description, isPrivate, language, task, split);
        columns.forEach(dataset::addColumn);
        types.forEach(dataset::addType);
        rows.forEach(dataset::addRow);
        tags.forEach(dataset::addTag);
        datasetRepository.save(dataset);
    }
}
