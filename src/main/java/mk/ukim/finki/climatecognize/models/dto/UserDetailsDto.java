package mk.ukim.finki.climatecognize.models.dto;



import lombok.Data;
import mk.ukim.finki.climatecognize.models.User;
import mk.ukim.finki.climatecognize.models.enumerations.Role;


@Data
public class UserDetailsDto {
    private String username;
    private Role role;

    public static UserDetailsDto of(User user) {
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.role = user.getRole();
        return details;
    }
}

