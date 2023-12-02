package mk.ukim.finki.climatecognize.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ClimateMLModel {
    @Id
    String model;

    double currentF1Score;

    double previousF1Score;


    public ClimateMLModel(String model, double currentF1Score, double previousF1Score) {
        this.model = model;
        this.currentF1Score = currentF1Score;
        this.previousF1Score = previousF1Score;
    }
}
