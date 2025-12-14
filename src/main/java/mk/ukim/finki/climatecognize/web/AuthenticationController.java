package mk.ukim.finki.climatecognize.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.climatecognize.constants.ChangePasswordFormKeys;
import mk.ukim.finki.climatecognize.constants.JwtAuthConstants;
import mk.ukim.finki.climatecognize.config.filters.JwtAuthenticationFilter;
import mk.ukim.finki.climatecognize.models.User;
import mk.ukim.finki.climatecognize.models.enumerations.Role;
import mk.ukim.finki.climatecognize.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
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
        responseObj.put(JwtAuthConstants.JWT_RESPONSE_KEY, this.filter.generateJwt(response, auth));
        User user = (User) auth.getPrincipal();
        responseObj.put(JwtAuthConstants.ROLE_RESPONSE_KEY, user.getRole().toString());
        return responseObj.toString();

    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String confirmPassword,
                             @RequestParam String name,
                             @RequestParam String surname) {
        User createdUser = userService.register(username, password, confirmPassword, name, surname, Role.ROLE_USER);
        if (createdUser != null) {
            return createdUser.getUsername();
        } else return "error";

    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String username,
                                 @RequestParam(name = ChangePasswordFormKeys.OLD_PASSWORD) String oldPassword,
                                 @RequestParam(name = ChangePasswordFormKeys.NEW_PASSWORD) String password,
                                 @RequestParam(name = ChangePasswordFormKeys.CONFIRM_PASSWORD) String confirmPassword) {
        User createdUser = (User) userService.changePassword(username, oldPassword, password, confirmPassword);
        if (createdUser != null) {
            return createdUser.getUsername();
        } else return "error";

    }
}
