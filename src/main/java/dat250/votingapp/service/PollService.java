package dat250.votingapp.service;

import dat250.votingapp.model.Poll;
import dat250.votingapp.repository.PollRepository;
import dat250.votingapp.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    private final VoteRepository voteRepository;

    @Autowired
    public PollService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }
    public void save(Poll poll) {
        pollRepository.save(poll);
    }

    public Map<String, Long> getResults(Long pollId) {
        long yesVotes = voteRepository.countYesVotesByPollId(pollId);
        long noVotes = voteRepository.countNoVotesByPollId(pollId);

        Map<String, Long> results = new HashMap<>();
        results.put("yes", yesVotes);
        results.put("no", noVotes);
        return results;
    }
}