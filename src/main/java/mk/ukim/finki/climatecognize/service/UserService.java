package mk.ukim.finki.climatecognize.service;

import mk.ukim.finki.climatecognize.models.User;
import mk.ukim.finki.climatecognize.models.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
    User saveChanges(User user);
}

