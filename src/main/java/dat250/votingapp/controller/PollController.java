package dat250.votingapp.controller;

import dat250.votingapp.model.AppUser;
import dat250.votingapp.model.Poll;
import dat250.votingapp.repository.AppUserRepository;
import dat250.votingapp.repository.PollRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/polls")
public class PollController {

    private static final Logger log = LoggerFactory.getLogger(PollController.class);

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private AppUserRepository appUserRepository;

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

    @GetMapping("/search")
    public List<Poll> getPollsByTitle(@RequestParam String title) {
        return pollRepository.findByTitleContainingIgnoreCase(title);
    }

    @PostMapping
    public ResponseEntity<?> createPoll(@RequestBody Poll poll, @RequestParam int userId) {
        log.info("creating poll with title: {} for user id: {}", poll.getPollTitle(), userId);
        Optional<AppUser> userOptional = appUserRepository.findById(userId);
        if (!userOptional.isPresent()) {
            log.error("User not found with id: {}", userId);
            return ResponseEntity.badRequest().body("User not found");
        }

        AppUser user = userOptional.get();
        poll.setOwner(user);

        poll.setAuthorizedUsers(Arrays.asList(user));

        Poll savedPoll = pollRepository.save(poll);

        return ResponseEntity.ok(savedPoll);
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

