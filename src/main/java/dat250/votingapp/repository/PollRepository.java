package dat250.votingapp.repository;

import dat250.votingapp.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, Integer> {
    @Query("SELECT p FROM Poll p WHERE LOWER(p.pollTitle) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Poll> findByTitleContainingIgnoreCase(@Param("title") String title);



}
