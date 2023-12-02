package mk.ukim.finki.climatecognize.models;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;

@Data
public class ClimateMLModelResponse {
    String label;
    double score;

    public static ClimateMLModelResponse fromJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ClimateMLModelResponse response = mapper.readValue(json, ClimateMLModelResponse.class);
            return response;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new Exception("Cannot parse JSON to ClimateMLModelResponse");
    }

    @Override
    public String toString() {
        return "Label: " + getLabel() + "\n Score: " + getScore();
    }
}
