package mk.ukim.finki.climatecognize.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "climate_cognize_users_extensions")
@Data
public class UserExtension {
    @Id
    private String username;
    private String email;
    private boolean isPremiumUser;

    public UserExtension(String username, String email, boolean isPremiumUser) {
        this.username = username;
        this.email = email;
        this.isPremiumUser = isPremiumUser;
    }

    public UserExtension() {
        this.username = "";
        this.email = "";
        this.isPremiumUser = false;
    }
}
