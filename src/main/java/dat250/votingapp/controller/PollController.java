package dat250.votingapp.controller;

import dat250.votingapp.model.Poll;
import dat250.votingapp.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")

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

    /**
     * Searches for a poll by title (Find-Poll)
     *
     * @param title
     * @return
     */
    @GetMapping("/search")
    public List<Poll> getPollsByTitle(@RequestParam String title) {
        return pollRepository.findByTitleContainingIgnoreCase(title);
    }

    /**
     * Creates a new poll (Create-Poll)
     *
     * @param poll
     * @return
     */
    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        return pollRepository.save(poll);
    }

    @Deprecated
    @PostMapping("/createPoll")
    public ResponseEntity<Void> createPoll(@RequestParam String title) {
        if (title == null || title.trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Poll newPoll = new Poll();
        newPoll.setPollTitle(title);
        pollRepository.save(newPoll);

        return new ResponseEntity<>(HttpStatus.CREATED);
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

    @PostMapping("/openPoll")
    public ResponseEntity<Void> openPoll(@RequestParam String pollTitle) {
        //TODO: call open poll
        throw new UnsupportedOperationException("openPoll Not implemented");
    }

    @PostMapping("/closePoll")
    public ResponseEntity<Void> closePoll(@RequestParam String pollTitle) {
        //TODO: call close poll
        throw new UnsupportedOperationException("closePoll Not implemented");
    }

    @Deprecated
    @PostMapping("/deletePoll")
    public ResponseEntity<Void> deletePoll(@RequestParam String pollTitle) {
        //TODO: call to delete poll
        throw new UnsupportedOperationException("deletePoll Not implemented");
    }

    @PostMapping("/reviewPoll")
    public ResponseEntity<Void> reviewPoll(@RequestParam String pollTitle) {
        //TODO: call preview poll
        throw new UnsupportedOperationException("reviewPoll Not implemented");
    }

    @Deprecated
    @PostMapping("/editPoll")
    public ResponseEntity<Void> editPoll(@RequestParam String pollTitle) {
        //TODO: call edit poll, i.e. add/remove questions
        throw new UnsupportedOperationException("editPoll Not implemented");
    }

}

