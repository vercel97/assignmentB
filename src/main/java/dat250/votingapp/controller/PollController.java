package dat250.votingapp.controller;

import dat250.votingapp.model.Poll;
import dat250.votingapp.repository.PollRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private RestTemplate restTemplate;

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
    @Transactional
    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {

        System.out.println("LOOOOOOOKKKKK HEERERERE");
        System.out.println(poll.isPrivate());
        System.out.println(poll.getPollTitle());
        Poll newPoll = new Poll();
        newPoll.setDuration(poll.getDuration());
        newPoll.setPrivate(poll.isPrivate());
        newPoll.setPollTitle(poll.getPollTitle());
        return pollRepository.save(newPoll);
    }

    /**
     * Creates a new poll (Create-Poll)
     *
     * @param poll
     * @return
     */
    @Deprecated
    @PostMapping("/createPoll")
    public ResponseEntity<Void> createPoll(@RequestParam String title) {
        if (title == null || title.trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Poll newPoll = new Poll();
        newPoll.setPollTitle(title);
        newPoll.setPrivate(false);
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

    @PostMapping("/{id}/open")
    public ResponseEntity<Void> openPoll(@PathVariable int id) {
        Optional<Poll> poll = pollRepository.findById(id);
        if (!poll.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Poll pollToOpen = poll.get();
        pollToOpen.setStatus(true);
        pollRepository.save(pollToOpen);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<Void> closePoll(@PathVariable int id) {
        Optional<Poll> poll = pollRepository.findById(id);
        if (!poll.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Poll pollToClose = poll.get();
        pollToClose.setStatus(false);
        Poll closedPoll = pollRepository.save(pollToClose);

        publishResults(closedPoll);

        return ResponseEntity.ok().build();
    }

    private void publishResults(Poll poll) {
        String dweetUrl = "https://dweet.io:443/dweet/for/";

        String pollIdentifier = "my_poll_" + poll.getId();

        // Create the payload containing the results
        Map<String, Object> payload = new HashMap<>();
        payload.put("pollId", poll.getId());
        payload.put("pollTitle", poll.getPollTitle());

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(dweetUrl + pollIdentifier, payload, String.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                System.out.println("Poll results published successfully!");
            } else {
                System.out.println("Failed to publish poll results: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.out.println("An error occurred while publishing poll results: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/review")
    public ResponseEntity<?> reviewPoll(@PathVariable int id) {
        Optional<Poll> poll = pollRepository.findById(id);
        if (!poll.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Poll pollToReview = poll.get();
        if (pollToReview.getStatus()) {
            return ResponseEntity.badRequest().body("Poll is not closed yet.");
        }

        return ResponseEntity.ok(pollToReview.getResults());
    }
}

