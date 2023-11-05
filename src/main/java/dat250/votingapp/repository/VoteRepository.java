package dat250.votingapp.repository;

import dat250.votingapp.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.poll.id = :pollId AND v.vote = true")
    long countYesVotesByPollId(@Param("pollId") Long pollId);

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.poll.id = :pollId AND v.vote = false")
    long countNoVotesByPollId(@Param("pollId") Long pollId);
}
