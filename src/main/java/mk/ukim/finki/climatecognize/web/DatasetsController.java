package mk.ukim.finki.climatecognize.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mk.ukim.finki.climatecognize.models.Dataset;
import mk.ukim.finki.climatecognize.models.DatasetEntry;
import mk.ukim.finki.climatecognize.service.DatasetService;
import org.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/datasets")
public class DatasetsController {
    private final DatasetService datasetService;

    public DatasetsController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @GetMapping("/getAll")
    public List<Dataset> getAll() {
        return datasetService.findAllDatasets();
    }

    @GetMapping("/getById/{id}")
    public Dataset getById(@PathVariable String id) {
        return datasetService.getDatasetById(id);
    }

    @GetMapping("/getForUser/{username}")
    public List<Dataset> getForUser(@PathVariable String username) {
        return datasetService.findDatasetsByUser(username);
    }

    @PutMapping("/likeDataset/{id}")
    public Dataset likeDataset(@PathVariable String id) {
        Dataset ds = datasetService.getDatasetById(id);
        ds.setNumLikes(ds.getNumLikes() + 1);
        return datasetService.updateDataset(ds);
    }

    @PutMapping("/downloadDataset/{id}")
    public Dataset downloadDataset(@PathVariable String id) {
        Dataset ds = datasetService.getDatasetById(id);
        ds.setNumDownloads(ds.getNumDownloads() + 1);
        return datasetService.updateDataset(ds);
    }

    @PostMapping("/createNewDataset")
    public ResponseEntity<String> createNewDataset(@RequestParam String author,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) String description,
                                           @RequestParam(required = false) boolean isPrivate,
                                           @RequestParam(required = false) String language,
                                           @RequestParam(required = false) String task,
                                           @RequestParam(required = false) String split,
                                           @RequestParam(required = false) String columns,
                                           @RequestParam(required = false) String rows,
                                           @RequestParam(required = false) String tags,
                                           @RequestParam(required = false) String types) {
        try {
            JSONArray columnsArr = new JSONArray(columns);
            List<String> colsList = new ArrayList<>();
            for (int i = 0; i < columnsArr.length(); i++) {
                colsList.add(columnsArr.getString(i));
            }
            JSONArray tagsArr = new JSONArray(tags);
            List<String> tagslist = new ArrayList<>();
            for (int i = 0; i < tagsArr.length(); i++) {
                tagslist.add(tagsArr.getString(i));
            }
            JSONArray typesArr = new JSONArray(types);
            List<String> typesList = new ArrayList<>();
            for (int i = 0; i < typesArr.length(); i++) {
                typesList.add(typesArr.getString(i));
            }
            JSONArray rowsArr = new JSONArray(rows);
            List<DatasetEntry> rowsList = new ArrayList<>();
            for (int i = 0; i < rowsArr.length(); i++) {
                DatasetEntry rowsEntries = DatasetEntry.fromJson(rowsArr.getString(i));
                rowsList.add(rowsEntries);
            }
            this.datasetService.createNewDataset(author, name, description, isPrivate, language, task, split, colsList, rowsList, tagslist, typesList);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
