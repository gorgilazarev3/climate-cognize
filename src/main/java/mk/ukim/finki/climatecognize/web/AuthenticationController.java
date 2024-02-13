package mk.ukim.finki.climatecognize.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.climatecognize.config.filters.JwtAuthenticationFilter;
import mk.ukim.finki.climatecognize.models.User;
import mk.ukim.finki.climatecognize.models.enumerations.Role;
import mk.ukim.finki.climatecognize.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/auth")

public class AuthenticationController {

    private final JwtAuthenticationFilter filter;
    private final UserService userService;
    public AuthenticationController(JwtAuthenticationFilter filter, UserService userService) {
        this.filter = filter;
        this.userService = userService;
    }


    @PostMapping("/login")
    public String doLogin(HttpServletRequest request,
                          HttpServletResponse response) throws JsonProcessingException, JSONException {
        Authentication auth = this.filter.attemptAuthentication(request, response);
        JSONObject responseObj = new JSONObject();
        responseObj.put("jwt", this.filter.generateJwt(response, auth));
        User user = (User) auth.getPrincipal();
        responseObj.put("role", user.getRole().toString());
        return responseObj.toString();

    }

    @PostMapping("/register")
    public String doRegister(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String confirmPassword,
                             @RequestParam String name,
                             @RequestParam String surname) throws JsonProcessingException {
        User createdUser = userService.register(username, password, confirmPassword, name, surname, Role.ROLE_USER);
        if(createdUser != null) {
            return createdUser.getUsername();
        }
        else return "error";

    }

    @PostMapping("/changePassword")
    public String changePassword(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam String username,
                             @RequestParam(name = "old-password") String oldPassword,
                             @RequestParam(name = "new-password") String password,
                             @RequestParam(name = "confirm-password") String confirmPassword) throws JsonProcessingException {
        User createdUser = (User) userService.changePassword(username, oldPassword, password, confirmPassword);
        if(createdUser != null) {
            return createdUser.getUsername();
        }
        else return "error";

    }



}
