package dat250.appassignB.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll, Integer> {
    //Poll, Integer because the primary key is an integer in the Poll class
}