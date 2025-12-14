package mk.ukim.finki.climatecognize.service;

import mk.ukim.finki.climatecognize.models.Dataset;
import mk.ukim.finki.climatecognize.models.DatasetEntry;
import mk.ukim.finki.climatecognize.models.exceptions.DatasetNotFoundException;

import java.util.List;

public interface DatasetService {
   List<Dataset> findAllDatasets();
   List<Dataset> findDatasetsByUser(String username);
   Dataset getDatasetById(String id) throws DatasetNotFoundException;
   Dataset updateDataset(Dataset dataset);
   Dataset deleteDatasetById(String id) throws DatasetNotFoundException;
   void createNewDataset(String author, String name, String description, boolean isPrivate, String language, String task, String split, List<String> columns, List<DatasetEntry> entries, List<String> tags, List<String> types);
}
