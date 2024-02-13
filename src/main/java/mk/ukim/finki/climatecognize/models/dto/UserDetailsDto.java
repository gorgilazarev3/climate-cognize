package mk.ukim.finki.climatecognize.models.dto;



import lombok.Data;
import mk.ukim.finki.climatecognize.models.User;
import mk.ukim.finki.climatecognize.models.enumerations.Role;


@Data
public class UserDetailsDto {
    private String username;
    private Role role;
    private String name;
    private String surname;
    private boolean isPremium;

    public static UserDetailsDto of(User user) {
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.role = user.getRole();
        details.name = user.getName();
        details.surname = user.getSurname();
        details.isPremium = false;
        return details;
    }
}

