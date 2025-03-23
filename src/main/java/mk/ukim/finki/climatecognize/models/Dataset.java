package mk.ukim.finki.climatecognize.models;



import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import mk.ukim.finki.climatecognize.constants.DatasetConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document("datasets")
@Data
public class Dataset {
    @MongoId
    String id;

    String author;

    String name;

    List<String> columns;

    List<String> types;

    List<DatasetEntry> rows;

    List<String> tags;

    String language;

    String task;

    String description;

    String split;

    int numDownloads;

    int numLikes;

    LocalDateTime lastUpdated;

    boolean isPrivate;

    public Dataset() {
        this.id = UUID.randomUUID().toString();
        rows = new ArrayList<>();
        this.columns = new ArrayList<>();
        numDownloads = 0;
        numLikes = 0;
        lastUpdated = LocalDateTime.now();
        task = DatasetConstants.DEFAULT_TASK;
        description = "";
        tags = new ArrayList<>();
        split = DatasetConstants.DEFAULT_SPLIT;
        language = DatasetConstants.DEFAULT_LANGUAGE;
        name = DatasetConstants.DEFAULT_NAME;
        isPrivate = false;
        types = new ArrayList<>();
    }

    public Dataset(String id, String author, String name, List<String> columns, List<DatasetEntry> rows, List<String> tags, String language, String task, String description, String split, int numDownloads, int numLikes, LocalDateTime lastUpdated, boolean isPrivate, List<String> types) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.columns = columns;
        this.rows = rows;
        this.tags = tags;
        this.language = language;
        this.task = task;
        this.description = description;
        this.split = split;
        this.numDownloads = numDownloads;
        this.numLikes = numLikes;
        this.lastUpdated = lastUpdated;
        this.isPrivate = isPrivate;
        this.types = types;
    }

    public Dataset(String author, String name, String description, boolean isPrivate, String language, String task, String split) {
        this.author = author;
        this.id = UUID.randomUUID().toString();
        rows = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.types = new ArrayList<>();
        numDownloads = 0;
        numLikes = 0;
        lastUpdated = LocalDateTime.now();
        if(task.isEmpty()) {
            task = DatasetConstants.DEFAULT_TASK;
        }
        this.task = task;
        this.description = description;
        tags = new ArrayList<>();
        if(split.isEmpty()) {
            split = DatasetConstants.DEFAULT_SPLIT;
        }
        this.split = split;
        if(language.isEmpty()) {
            language = DatasetConstants.DEFAULT_LANGUAGE;
        }
        this.language = language;
        this.name = name;
        this.isPrivate = isPrivate;
    }

    public void addRow(DatasetEntry row) {
        this.rows.add(row);
    }

    public void addType(String type) {
        this.types.add(type);
    }

    public void addColumn(String column) {
        this.columns.add(column);
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public static Dataset fromJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Dataset response = mapper.readValue(json, Dataset.class);
            return response;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new Exception("Cannot parse JSON to Dataset");
    }
}
