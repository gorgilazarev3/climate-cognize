package mk.ukim.finki.climatecognize.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.ukim.finki.climatecognize.models.ClimateMLModel;
import mk.ukim.finki.climatecognize.models.User;
import mk.ukim.finki.climatecognize.models.enumerations.Role;
import mk.ukim.finki.climatecognize.repository.ClimateModelRepository;
import mk.ukim.finki.climatecognize.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer {

    private UserRepository userRepository;
    private ClimateModelRepository climateModelRepository;

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

    @PostConstruct
    public void init() {
        List<User> usersList = userRepository.findAll();
        List<ClimateMLModel> modelsList = climateModelRepository.findAll();
        if (usersList.size()==0) {
            createUsers();
        }
        if(modelsList.isEmpty()) {
            createModels();
        }

    }
}

