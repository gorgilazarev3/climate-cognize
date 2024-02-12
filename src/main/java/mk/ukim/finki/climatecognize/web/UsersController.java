package mk.ukim.finki.climatecognize.web;

import mk.ukim.finki.climatecognize.models.User;
import mk.ukim.finki.climatecognize.models.dto.UserDetailsDto;
import mk.ukim.finki.climatecognize.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/getUserInfo")
    public UserDetailsDto getUserInfo(@RequestParam("username") String username) {
        User user = (User) this.userService.loadUserByUsername(username);
        UserDetailsDto details = UserDetailsDto.of(user);
        return details;
    }

    @PostMapping("/changeProfileInfo")
    public UserDetailsDto changeProfileInfo(@RequestParam("username") String username,
                                            @RequestParam("name") String name,
                                            @RequestParam("surname") String surname) {
        User user = (User) this.userService.loadUserByUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user = this.userService.saveChanges(user);
        UserDetailsDto details = UserDetailsDto.of(user);
        return details;
    }
}
