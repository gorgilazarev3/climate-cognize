package mk.ukim.finki.climatecognize.web;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.climatecognize.models.ClassificationDatasetEntry;
import mk.ukim.finki.climatecognize.models.ClimateMLModel;
import mk.ukim.finki.climatecognize.models.ClimateMLModelResponse;
import mk.ukim.finki.climatecognize.models.dto.ClimateDatasetResponse;
import mk.ukim.finki.climatecognize.repository.ClimateModelRepository;
import mk.ukim.finki.climatecognize.service.ClassificationDatasetEntryService;

import org.apache.catalina.connector.InputBuffer;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: refactor this whole controller

@RestController
@RequestMapping("/api/climate")
@CrossOrigin(origins = "http://localhost:3000")
public class ClimateModelsController {


    @Autowired
    private Environment env;
    @Autowired
    private ClassificationDatasetEntryService classificationDatasetEntryService;
    @Autowired
    private ClimateModelRepository climateModelRepository;



    @PostMapping("/detect")
    public ResponseEntity<ClimateMLModelResponse> detect(@RequestParam String input_data, @RequestParam String chosen_model, Model model) throws Exception {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "climate-detect?input_data=";
        }
        else {
            inputDataString = "/climate-detect?input_data=";
        }
        String chosenModel = "&chosen_model=";
        chosenModel = chosenModel + chosen_model;
        String uri = url + inputDataString + input_data + chosenModel;
        String object = "{ \"input_data\":\"  " + input_data + "\"}";
        HttpEntity<String> request =
                new HttpEntity<String>(object);
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.postForObject(uri, request, String.class);
        if(res.contains("\\n"))
            res = res.replace("\\n","");
        if(res.contains("\\")) {
            res = res.replace("\\","");
            if(res.contains("\"{"))
                res = res.replace("\"{","{");
            if(res.contains("}\""))
                res = res.replace("}\"","}");
        }

        ClimateMLModelResponse response = ClimateMLModelResponse.fromJson(res);
        model.addAttribute("response", response);
        if(response != null) {
            return ResponseEntity.ok(response);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/sentiment")
    public ResponseEntity<ClimateMLModelResponse> sentiment(@RequestParam String input_data, @RequestParam String chosen_model, Model model) throws Exception {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "climate-sentiment?input_data=";
        }
        else {
            inputDataString = "/climate-sentiment?input_data=";
        }
        String chosenModel = "&chosen_model=";
        chosenModel = chosenModel + chosen_model;
        String uri = url + inputDataString + input_data + chosenModel;
        String object = "{ \"input_data\":\"  " + input_data + "\"}";
        HttpEntity<String> request =
                new HttpEntity<String>(object);
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.postForObject(uri, request, String.class);
        ClimateMLModelResponse response = ClimateMLModelResponse.fromJson(res);
        model.addAttribute("response", response);
        if(response != null) {
            return ResponseEntity.ok(response);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/specificity")
    public ResponseEntity<ClimateMLModelResponse> specificity(@RequestParam String input_data, @RequestParam String chosen_model, Model model) throws Exception {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "climate-specificity?input_data=";
        }
        else {
            inputDataString = "/climate-specificity?input_data=";
        }
        String chosenModel = "&chosen_model=";
        chosenModel = chosenModel + chosen_model;
        String uri = url + inputDataString + input_data + chosenModel;
        String object = "{ \"input_data\":\"  " + input_data + "\"}";
        HttpEntity<String> request =
                new HttpEntity<String>(object);
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.postForObject(uri, request, String.class);
        ClimateMLModelResponse response = ClimateMLModelResponse.fromJson(res);
        model.addAttribute("response", response);
        if(response != null) {
            return ResponseEntity.ok(response);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/commitments-actions")
    public ResponseEntity<ClimateMLModelResponse> commitments(@RequestParam String input_data, @RequestParam String chosen_model, Model model) throws Exception {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "climate-commitments-actions?input_data=";
        }
        else {
            inputDataString = "/climate-commitments-actions?input_data=";
        }
        String chosenModel = "&chosen_model=";
        chosenModel = chosenModel + chosen_model;
        String uri = url + inputDataString + input_data + chosenModel;
        String object = "{ \"input_data\":\"  " + input_data + "\"}";
        HttpEntity<String> request =
                new HttpEntity<String>(object);
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.postForObject(uri, request, String.class);
        ClimateMLModelResponse response = ClimateMLModelResponse.fromJson(res);
        model.addAttribute("response", response);
        if(response != null) {
            return ResponseEntity.ok(response);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/tcfd")
    public ResponseEntity<ClimateMLModelResponse> tcfd(@RequestParam String input_data, @RequestParam String chosen_model, Model model) throws Exception {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "climate-tcfd?input_data=";
        }
        else {
            inputDataString = "/climate-tcfd?input_data=";
        }
        String chosenModel = "&chosen_model=";
        chosenModel = chosenModel + chosen_model;
        String uri = url + inputDataString + input_data + chosenModel;
        String object = "{ \"input_data\":\"  " + input_data + "\"}";
        HttpEntity<String> request =
                new HttpEntity<String>(object);
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.postForObject(uri, request, String.class);
        ClimateMLModelResponse response = ClimateMLModelResponse.fromJson(res);
        model.addAttribute("response", response);
        if(response != null) {
            return ResponseEntity.ok(response);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/train/detect/our")
    public ResponseEntity<String> traindetectour(@RequestParam(required = false) String chosen_model, Model model) throws Exception {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "train/climate-detect-our";
        }
        else {
            inputDataString = "/train/climate-detect-our";
        }
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("entries", classificationDatasetEntryService.getAllEntries().stream().filter(entry -> entry.getTask().equalsIgnoreCase("climate_detection")).map(entry -> {
            return new Object() {
                String text = entry.getInput();
                String label = entry.getTrueLabel();

                @Override
                public String toString() {
                    return "{" +
                            "\"text\":\"" + text +
                            "\", \"label\":\"" + label +
                            "\"}";
                }
            };
        }).toList().toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String uri = url + inputDataString;
        String object = "{ \"input_data\":\" {  \"objects\":\"" + classificationDatasetEntryService.getAllEntries().stream().filter(entry -> entry.getTask().equalsIgnoreCase("climate_detection")).map(entry -> entry.getInput()).toList() + "\"} \"}";
        HttpEntity<String> request =
                new HttpEntity<String>(jsonObject.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.postForObject(uri, request, String.class);
        if(res != null) {
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/train/detect")
    public ResponseEntity<String> traindetect(@RequestParam(required = false) String chosen_model, Model model) throws Exception {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "train/climate-detect";
        }
        else {
            inputDataString = "/train/climate-detect";
        }
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("entries", classificationDatasetEntryService.getAllEntries().stream().filter(entry -> entry.getTask().equalsIgnoreCase("climate_detection")).map(entry -> {
            return new Object() {
                String text = entry.getInput();
                String label = entry.getTrueLabel();

                @Override
                public String toString() {
                    return "{" +
                            "\"text\":\"" + text +
                            "\", \"label\":\"" + label +
                            "\"}";
                }
            };
        }).toList().toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String uri = url + inputDataString;
        String object = "{ \"input_data\":\" {  \"objects\":\"" + classificationDatasetEntryService.getAllEntries().stream().filter(entry -> entry.getTask().equalsIgnoreCase("climate_detection")).map(entry -> entry.getInput()).toList() + "\"} \"}";
        HttpEntity<String> request =
                new HttpEntity<String>(jsonObject.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.postForObject(uri, request, String.class);
        if(res != null) {
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/train/sentiment")
    public ResponseEntity<String> trainsentiment(@RequestParam(required = false) String chosen_model, Model model) throws Exception {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "train/climate-sentiment";
        }
        else {
            inputDataString = "/train/climate-sentiment";
        }
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("entries", classificationDatasetEntryService.getAllEntries().stream().filter(entry -> entry.getTask().equalsIgnoreCase("climate_sentiment")).map(entry -> {
            return new Object() {
                String text = entry.getInput();
                String label = entry.getTrueLabel();

                @Override
                public String toString() {
                    return "{" +
                            "\"text\":\"" + text +
                            "\", \"label\":\"" + label +
                            "\"}";
                }
            };
        }).toList().toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String uri = url + inputDataString;
        String object = "{ \"input_data\":\" {  \"objects\":\"" + classificationDatasetEntryService.getAllEntries().stream().filter(entry -> entry.getTask().equalsIgnoreCase("climate_detection")).map(entry -> entry.getInput()).toList() + "\"} \"}";
        HttpEntity<String> request =
                new HttpEntity<String>(jsonObject.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.postForObject(uri, request, String.class);
        if(res != null) {
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/train/specificity")
    public ResponseEntity<String> trainspecificity(@RequestParam(required = false) String chosen_model, Model model) throws Exception {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "train/climate-specificity";
        }
        else {
            inputDataString = "/train/climate-specificity";
        }
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("entries", classificationDatasetEntryService.getAllEntries().stream().filter(entry -> entry.getTask().equalsIgnoreCase("climate_specificity")).map(entry -> {
            return new Object() {
                String text = entry.getInput();
                String label = entry.getTrueLabel();

                @Override
                public String toString() {
                    return "{" +
                            "\"text\":\"" + text +
                            "\", \"label\":\"" + label +
                            "\"}";
                }
            };
        }).toList().toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String uri = url + inputDataString;
        String object = "{ \"input_data\":\" {  \"objects\":\"" + classificationDatasetEntryService.getAllEntries().stream().filter(entry -> entry.getTask().equalsIgnoreCase("climate_detection")).map(entry -> entry.getInput()).toList() + "\"} \"}";
        HttpEntity<String> request =
                new HttpEntity<String>(jsonObject.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.postForObject(uri, request, String.class);
        if(res != null) {
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/train/tcfd")
    public ResponseEntity<String> traintcfd(@RequestParam(required = false) String chosen_model, Model model) throws Exception {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "train/climate-tcfd";
        }
        else {
            inputDataString = "/train/climate-tcfd";
        }
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("entries", classificationDatasetEntryService.getAllEntries().stream().filter(entry -> entry.getTask().equalsIgnoreCase("climate_tcfd_recommendations")).map(entry -> {
            return new Object() {
                String text = entry.getInput();
                String label = entry.getTrueLabel();

                @Override
                public String toString() {
                    return "{" +
                            "\"text\":\"" + text +
                            "\", \"label\":\"" + label +
                            "\"}";
                }
            };
        }).toList().toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String uri = url + inputDataString;
        String object = "{ \"input_data\":\" {  \"objects\":\"" + classificationDatasetEntryService.getAllEntries().stream().filter(entry -> entry.getTask().equalsIgnoreCase("climate_detection")).map(entry -> entry.getInput()).toList() + "\"} \"}";
        HttpEntity<String> request =
                new HttpEntity<String>(jsonObject.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.postForObject(uri, request, String.class);
        if(res != null) {
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/train/commitments")
    public ResponseEntity<String> traincommitments(@RequestParam(required = false) String chosen_model, Model model) throws Exception {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "train/climate-commitments-actions";
        }
        else {
            inputDataString = "/train/climate-commitments-actions";
        }
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("entries", classificationDatasetEntryService.getAllEntries().stream().filter(entry -> entry.getTask().equalsIgnoreCase("climate_commitments_and_actions")).map(entry -> {
            return new Object() {
                String text = entry.getInput();
                String label = entry.getTrueLabel();

                @Override
                public String toString() {
                    return "{" +
                            "\"text\":\"" + text +
                            "\", \"label\":\"" + label +
                            "\"}";
                }
            };
        }).toList().toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String uri = url + inputDataString;
        String object = "{ \"input_data\":\" {  \"objects\":\"" + classificationDatasetEntryService.getAllEntries().stream().filter(entry -> entry.getTask().equalsIgnoreCase("climate_detection")).map(entry -> entry.getInput()).toList() + "\"} \"}";
        HttpEntity<String> request =
                new HttpEntity<String>(jsonObject.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.postForObject(uri, request, String.class);
        if(res != null) {
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/models")
    public ResponseEntity<List<ClimateMLModel>> getAllModels(HttpServletRequest request) {
        try {
            List<ClimateMLModel> models = climateModelRepository.findAll();
            return ResponseEntity.ok(models);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/import")
    public List<ClimateDatasetResponse> importCSV(HttpServletResponse response, @RequestParam("dataset_file") MultipartFile dataset_file, String selectedModel, int inputPosition)
            throws Exception {

        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(dataset_file.getInputStream()))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        }
        List<String> inputs = new ArrayList<>();
        inputs = records.stream().skip(1).map(row -> row.get(inputPosition)).toList();
        List<ClimateDatasetResponse> responses = new ArrayList<>();
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        JSONObject jsonObject = new JSONObject();
        if(selectedModel.equalsIgnoreCase("climate_detection")) {

            if (url != null && url.endsWith("/")) {
                inputDataString = "dataset/climate-detect";
            } else {
                inputDataString = "/dataset/climate-detect";
            }

        }
        else if(selectedModel.equalsIgnoreCase("climate_sentiment")) {

            if (url != null && url.endsWith("/")) {
                inputDataString = "dataset/climate-sentiment";
            } else {
                inputDataString = "/dataset/climate-sentiment";
            }

        }
        else if(selectedModel.equalsIgnoreCase("climate_specificity")) {

            if (url != null && url.endsWith("/")) {
                inputDataString = "dataset/climate-specificity";
            } else {
                inputDataString = "/dataset/climate-specificity";
            }

        }
        else if(selectedModel.equalsIgnoreCase("climate_commitments_and_actions")) {

            if (url != null && url.endsWith("/")) {
                inputDataString = "dataset/climate-commitments";
            } else {
                inputDataString = "/dataset/climate-commitments";
            }

        }
        else if(selectedModel.equalsIgnoreCase("climate_tcfd_recommendations")) {

            if (url != null && url.endsWith("/")) {
                inputDataString = "dataset/climate-tcfd";
            } else {
                inputDataString = "/dataset/climate-tcfd";
            }

        }


            jsonObject.put("entries", inputs.stream().map(entry -> {
                return new Object() {
                    String text = entry;

                    @Override
                    public String toString() {
                        return "{" +
                                "\"text\":\"" + text +
                                "\", \"label\":\"" + "no" +
                                "\"}";
                    }
                };
            }).toList().toString());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String uri = url + inputDataString;
            HttpEntity<String> request =
                    new HttpEntity<String>(jsonObject.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();
            List<String> res = restTemplate.postForObject(uri, request, List.class);

            for (int i = 0; i < res.size(); i++) {
                responses.add(new ClimateDatasetResponse(inputs.get(i), res.get(i)));
            }


        return responses;
    }

    private void updateModelF1Score(String model, double f1Score) {
        ClimateMLModel climateMLModel = climateModelRepository.getReferenceById(model);
        if(f1Score > climateMLModel.getCurrentF1Score()) {
            climateMLModel.setPreviousF1Score(climateMLModel.getCurrentF1Score());
            climateMLModel.setCurrentF1Score(f1Score);
        }
        else {
            climateMLModel.setPreviousF1Score(f1Score);
        }
        climateModelRepository.save(climateMLModel);
    }

    @PostMapping("/evaluate/detect")
    public double evaluatedetect() {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "evaluate/climate-detect";
        }
        else {
            inputDataString = "/evaluate/climate-detect";
        }
        String uri = url + inputDataString;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request =
                new HttpEntity<String>("climatebert_climate_detection");
        double f1Score = restTemplate.postForObject(uri, request, Double.class);
        updateModelF1Score("climatebert-climate-detector", f1Score);
        return f1Score;
    }

    @PostMapping("/evaluate/our")
    public double evaluateour() {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "evaluate/climate-detect-our";
        }
        else {
            inputDataString = "/evaluate/climate-detect-our";
        }
        String uri = url + inputDataString;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request =
                new HttpEntity<String>("our_climate_detection");
        double f1Score = restTemplate.postForObject(uri, request, Double.class);
        updateModelF1Score("our-model-climate-detection", f1Score);
        return f1Score;
    }

    @PostMapping("/evaluate/sentiment")
    public double evaluatesentiment() {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "evaluate/climate-sentiment";
        }
        else {
            inputDataString = "/evaluate/climate-sentiment";
        }
        String uri = url + inputDataString;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request =
                new HttpEntity<String>("climate_sentiment");
        double f1Score = restTemplate.postForObject(uri, request, Double.class);
        updateModelF1Score("climatebert-climate-sentiment", f1Score);
        return f1Score;
    }

    @PostMapping("/evaluate/specificity")
    public double evaluatespecificity() {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "evaluate/climate-specificity";
        }
        else {
            inputDataString = "/evaluate/climate-specificity";
        }
        String uri = url + inputDataString;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request =
                new HttpEntity<String>("climate_specificity");
        double f1Score = restTemplate.postForObject(uri, request, Double.class);
        updateModelF1Score("climatebert-climate-specificity", f1Score);
        return f1Score;
    }

    @PostMapping("/evaluate/commitments")
    public double evaluatecommitments() {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "evaluate/climate-commitments-actions";
        }
        else {
            inputDataString = "/evaluate/climate-commitments-actions";
        }
        String uri = url + inputDataString;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request =
                new HttpEntity<String>("climate_commitments-actions");
        double f1Score = restTemplate.postForObject(uri, request, Double.class);
        updateModelF1Score("climatebert-climate-commitments-actions", f1Score);
        return f1Score;
    }

    @PostMapping("/evaluate/tcfd")
    public double evaluatetcfd() {
        String inputDataString = "";
        String url = env.getProperty("ml-models-url");
        if(url != null && url.endsWith("/")) {
            inputDataString = "evaluate/climate-tcfd";
        }
        else {
            inputDataString = "/evaluate/climate-tcfd";
        }
        String uri = url + inputDataString;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request =
                new HttpEntity<String>("climate_tcfd");
        double f1Score = restTemplate.postForObject(uri, request, Double.class);
        updateModelF1Score("climatebert-climate-tcfd", f1Score);
        return f1Score;
    }
}
