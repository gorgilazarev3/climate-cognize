package mk.ukim.finki.climatecognize.models.dto;

import lombok.Data;

@Data
public class ClimateDatasetResponse {
    String input;
    String label;

    public ClimateDatasetResponse(String input, String label) {
        this.input = input;
        this.label = label;
    }
}
