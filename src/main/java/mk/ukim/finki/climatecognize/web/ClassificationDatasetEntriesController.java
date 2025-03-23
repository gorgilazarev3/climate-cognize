package mk.ukim.finki.climatecognize.web;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.climatecognize.constants.DatasetConstants;
import mk.ukim.finki.climatecognize.models.ClassificationDatasetEntry;
import mk.ukim.finki.climatecognize.service.ClassificationDatasetEntryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/climate/dataset")
@CrossOrigin(origins = "http://localhost:3000")
public class ClassificationDatasetEntriesController {

    public final ClassificationDatasetEntryService classificationDatasetEntryService;

    public ClassificationDatasetEntriesController(ClassificationDatasetEntryService classificationDatasetEntryService) {
        this.classificationDatasetEntryService = classificationDatasetEntryService;
    }


    @PostMapping("/add-to-dataset")
    public ResponseEntity<ClassificationDatasetEntry> addEntryToDataset(@RequestParam String input,
                                                                        @RequestParam String predictedLabel,
                                                                        @RequestParam String trueLabel,
                                                                        @RequestParam double score,
                                                                        @RequestParam String model,
                                                                        @RequestParam String submittedBy,
                                                                        @RequestParam String task) throws Exception {
        ClassificationDatasetEntry entryObj = classificationDatasetEntryService.addNewEntry(input, predictedLabel, trueLabel, score, submittedBy, model, task);
        if(entryObj != null) {
            return ResponseEntity.ok(entryObj);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<ClassificationDatasetEntry>> getAllEntries(HttpServletRequest request) {
        try {
            List<ClassificationDatasetEntry> datasetEntries = classificationDatasetEntryService.getAllEntries();
            return ResponseEntity.ok(datasetEntries);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ClassificationDatasetEntry> deleteEntry(@PathVariable Long id) {
        try {
            ClassificationDatasetEntry deletedEntry = classificationDatasetEntryService.deleteEntryById(id);
            return ResponseEntity.ok(deletedEntry);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ClassificationDatasetEntry> getEntry(@PathVariable Long id) {
        try {
            ClassificationDatasetEntry entry = classificationDatasetEntryService.getEntryById(id);
            return ResponseEntity.ok(entry);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/export")
    public void exportCSV(HttpServletResponse response)
            throws Exception {

        //set file name and content type
        String filename = DatasetConstants.DEFAULT_CSV_FILE_NAME;

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");
        //create a csv writer
        StatefulBeanToCsv<ClassificationDatasetEntry> writer = new StatefulBeanToCsvBuilder<ClassificationDatasetEntry>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false)
                .build();
        //write all data to csv file
        writer.write(classificationDatasetEntryService.getAllEntries());

    }
}
