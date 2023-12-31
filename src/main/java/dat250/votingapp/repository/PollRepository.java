package dat250.votingapp.repository;

import dat250.votingapp.model.Poll;
import dat250.votingapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, Integer> {
    @Query("SELECT p FROM Poll p WHERE LOWER(p.pollTitle) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Poll> findByTitleContainingIgnoreCase(@Param("title") String title);

    @Query("SELECT q FROM Question q WHERE q.pollTitle = :poll AND q.isActive = TRUE")
    Question getActiveQuestion(@Param("poll") Poll poll);

}
