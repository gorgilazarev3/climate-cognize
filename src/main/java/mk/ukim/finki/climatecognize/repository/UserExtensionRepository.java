package mk.ukim.finki.climatecognize.repository;



import mk.ukim.finki.climatecognize.models.User;

import mk.ukim.finki.climatecognize.models.UserExtension;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserExtensionRepository extends JpaRepository<UserExtension, String> {


//    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<UserExtension> findByUsername(String username);

}


