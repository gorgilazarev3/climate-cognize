package mk.ukim.finki.climatecognize.service.impl;

import lombok.SneakyThrows;
import mk.ukim.finki.climatecognize.constants.JwtAuthConstants;
import mk.ukim.finki.climatecognize.models.User;
import mk.ukim.finki.climatecognize.models.UserExtension;
import mk.ukim.finki.climatecognize.models.enumerations.Role;
import mk.ukim.finki.climatecognize.repository.UserExtensionRepository;
import mk.ukim.finki.climatecognize.repository.UserRepository;
import mk.ukim.finki.climatecognize.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserExtensionRepository userExtensionRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserExtensionRepository userExtensionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userExtensionRepository = userExtensionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }


    @SneakyThrows
    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role userRole) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new Exception("Cannot find this account");
        if (!password.equals(repeatPassword))
            throw new Exception("Passwords do not match");
        if(this.userRepository.findByUsername(username).isPresent())
            throw new Exception(username + " username is not found");
        User user = new User(username,passwordEncoder.encode(password),name,surname,userRole);
        UserExtension extension = new UserExtension(username, username + JwtAuthConstants.DEFAULT_MAIL_EXTENSION, false);
        userExtensionRepository.save(extension);
        return userRepository.save(user);
    }

    @SneakyThrows
    @Override
    public User changePassword(String username, String oldPassword, String password, String repeatPassword) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new Exception("Cannot find this account");
        if (!password.equals(repeatPassword))
            throw new Exception("Passwords do not match");
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User cannot be found"));
        if(!passwordEncoder.matches(oldPassword, user.getPassword()))
            throw new Exception("Old password is not correct");
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public User saveChanges(User user) {
        return userRepository.save(user);
    }
}

