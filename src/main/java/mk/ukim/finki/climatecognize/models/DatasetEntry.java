//package mk.ukim.finki.climatecognize.models;
//
//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.Data;
//
//import java.io.IOException;
//
//@Entity
//@Table(name = "user_dataset_entry")
//@Data
//public class DatasetEntry {
//    @Id
//    @GeneratedValue
//    Long id;
//
//    String input;
//
//    String label;
//
//    public DatasetEntry(String input, String label) {
//        this.input = input;
//        this.label = label;
//    }
//
//    public DatasetEntry() {
//
//    }
//
//    public static DatasetEntry fromJson(String json) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            DatasetEntry response = mapper.readValue(json, DatasetEntry.class);
//            return response;
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        throw new Exception("Cannot parse JSON to DatasetEntry");
//    }
//
//    @Override
//    public String toString() {
//        return "DatasetEntry{" +
//                "id=" + id +
//                ", input='" + input + '\'' +
//                ", label='" + label + '\'' +
//                '}';
//    }
//}
