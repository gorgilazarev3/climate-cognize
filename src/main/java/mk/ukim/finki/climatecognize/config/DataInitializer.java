package mk.ukim.finki.climatecognize.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.ukim.finki.climatecognize.models.*;
import mk.ukim.finki.climatecognize.models.enumerations.Role;
import mk.ukim.finki.climatecognize.repository.ClimateModelRepository;
import mk.ukim.finki.climatecognize.repository.DatasetRepository;
import mk.ukim.finki.climatecognize.repository.UserExtensionRepository;
import mk.ukim.finki.climatecognize.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer {

    private UserRepository userRepository;
    private ClimateModelRepository climateModelRepository;
    private DatasetRepository datasetRepository;
    private UserExtensionRepository userExtensionRepository;

    private PasswordEncoder passwordEncoder;

    private void createUsers() {
        String username1 = "admin";
        User user = new User();
        user.setUsername(username1);
        user.setPassword(passwordEncoder.encode(username1));
        user.setName(username1);
        user.setSurname(username1);
        user.setRole(Role.ROLE_ADMIN);
        userRepository.save(user);

        String username2 = "user";
        User user2 = new User();
        user2.setUsername(username2);
        user2.setPassword(passwordEncoder.encode(username2));
        user2.setName(username2);
        user2.setSurname(username2);
        user2.setRole(Role.ROLE_USER);

        userRepository.save(user2);

    }

    private void createUsersExtensions() {
        String username1 = "admin";
        UserExtension user = new UserExtension();
        user.setUsername(username1);
        user.setPremiumUser(false);
        user.setEmail("admin@user.com");
        userExtensionRepository.save(user);

        String username2 = "user";
        UserExtension user2 = new UserExtension();
        user.setUsername(username2);
        user.setPremiumUser(false);
        user.setEmail("user@user.com");
        userExtensionRepository.save(user);

    }



    private void createModels() {
        ClimateMLModel climateBertDetection = new ClimateMLModel("climatebert-climate-detector", 0.9572, 0.9572);
        ClimateMLModel climateBertSentiment = new ClimateMLModel("climatebert-climate-sentiment", 0.7837, 0.7837);
        ClimateMLModel climateBertSpecificity = new ClimateMLModel("climatebert-climate-specificity", 0.6509, 0.6509);
        ClimateMLModel climateBertCommitments = new ClimateMLModel("climatebert-climate-commitments-actions", 0.7979, 0.7979);
        ClimateMLModel climateBertTcfd = new ClimateMLModel("climatebert-climate-tcfd", 0.4983, 0.4983);
        ClimateMLModel ourModel = new ClimateMLModel("our-model-climate-detection", 0.9500, 0.9500);
        climateModelRepository.save(climateBertDetection);
        climateModelRepository.save(climateBertSentiment);
        climateModelRepository.save(climateBertSpecificity);
        climateModelRepository.save(climateBertCommitments);
        climateModelRepository.save(climateBertTcfd);
        climateModelRepository.save(ourModel);
    }

    private void createDataset() {
        DatasetEntry entry = new DatasetEntry();
        entry.addEntry(1.2);
        entry.addEntry("test3");
        DatasetEntry entry2 = new DatasetEntry();
        entry2.addEntry(31);
        entry2.addEntry("fqeo");
        Dataset dataset = new Dataset("user", "test-dataset", "Sample Description", false, "English (en)", "", "");
        dataset.addColumn("test_number");
        dataset.addColumn("test_string");
        dataset.addRow(entry);
        dataset.addRow(entry2);
        dataset.addTag("test-tag");
        dataset.addType("number");
        dataset.addType("string");
        datasetRepository.save(dataset);
    }
    @PostConstruct
    public void init() {
        List<User> usersList = userRepository.findAll();
        List<ClimateMLModel> modelsList = climateModelRepository.findAll();
        List<Dataset> datasets = datasetRepository.findAll();
        List<UserExtension> usersExtendsList = userExtensionRepository.findAll();
        if (usersList.size()==0) {
            createUsers();
        }
        if(modelsList.isEmpty()) {
            createModels();
        }
        if(datasets.isEmpty()) {
            createDataset();
        }
        if(usersExtendsList.isEmpty()) {
            createUsersExtensions();
        }

    }
}

