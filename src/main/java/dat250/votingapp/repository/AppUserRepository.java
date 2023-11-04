package dat250.votingapp.repository;

import dat250.votingapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);

    List<AppUser> searchByUsernameContaining(String username);
}
