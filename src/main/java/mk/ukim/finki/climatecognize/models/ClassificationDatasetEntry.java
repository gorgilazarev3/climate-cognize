package mk.ukim.finki.climatecognize.models;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.IOException;

@Entity
@Table(name = "dataset_entries")
@Data
public class ClassificationDatasetEntry {
    @Id
    @GeneratedValue
    Long id;

    String input;

    String predictedLabel;

    String trueLabel;

    double score;

    String submittedBy;

    String model;

    String task;

    public ClassificationDatasetEntry(String input, String predictedLabel, String trueLabel, double score, String submittedBy, String model, String task) {
        this.input = input;
        this.predictedLabel = predictedLabel;
        this.trueLabel = trueLabel;
        this.score = score;
        this.submittedBy = submittedBy;
        this.model = model;
        this.task = task;
    }

    public ClassificationDatasetEntry() {

    }

    public static ClassificationDatasetEntry fromJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ClassificationDatasetEntry response = mapper.readValue(json, ClassificationDatasetEntry.class);
            return response;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new Exception("Cannot parse JSON to ClassificationDatasetEntry");
    }

}