package dat250.votingapp.controller;

import dat250.votingapp.model.Poll;
import dat250.votingapp.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    @GetMapping
    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPollById(@PathVariable int id) {
        Optional<Poll> poll = pollRepository.findById(id);
        if (poll.isPresent()) {
            return ResponseEntity.ok(poll.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        return pollRepository.save(poll);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poll> updatePoll(@PathVariable int id, @RequestBody Poll updatedPoll) {
        Optional<Poll> poll = pollRepository.findById(id);
        if (poll.isPresent()) {
            updatedPoll.setId(id);
            return ResponseEntity.ok(pollRepository.save(updatedPoll));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable int id) {
        Optional<Poll> poll = pollRepository.findById(id);
        if (poll.isPresent()) {
            pollRepository.delete(poll.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

