package dat250.votingapp.repository;

import dat250.votingapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
}
