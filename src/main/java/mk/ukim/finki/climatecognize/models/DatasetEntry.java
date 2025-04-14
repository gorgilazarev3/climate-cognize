package mk.ukim.finki.climatecognize.models;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.json.JSONException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Data
public class DatasetEntry {


    List<Object> entries;


    public DatasetEntry(List<Object> entries) {
        this.entries = entries;
    }

    public DatasetEntry() {
        this.entries = new ArrayList<>();
    }

    public void addEntry(Object object) {
        entries.add(object);
    }

    public static DatasetEntry fromJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {
            DatasetEntry response = mapper.readValue(json, DatasetEntry.class);
            return response;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new JSONException("Cannot parse JSON to DatasetEntry");
    }


}
