package mk.ukim.finki.climatecognize.service;

import mk.ukim.finki.climatecognize.models.Dataset;
import mk.ukim.finki.climatecognize.models.DatasetEntry;

import java.util.List;

public interface DatasetService {
   public List<Dataset> findAllDatasets();
   public List<Dataset> findDatasetsByUser(String username);
   public Dataset getDatasetById(String id);
   public Dataset updateDataset(Dataset dataset);
   public Dataset deleteDatasetById(String id);
   public void createNewDataset(String author, String name, String description, boolean isPrivate, String language, String task, String split, List<String> columns, List<DatasetEntry> entries, List<String> tags, List<String> types);
}
