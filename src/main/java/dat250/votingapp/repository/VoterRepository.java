package dat250.votingapp.repository;

import dat250.votingapp.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Integer> {
}
