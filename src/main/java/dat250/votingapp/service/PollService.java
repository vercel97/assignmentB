package dat250.votingapp.service;

import dat250.votingapp.model.Poll;
import dat250.votingapp.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public void save(Poll poll) {
        pollRepository.save(poll);
    }
}