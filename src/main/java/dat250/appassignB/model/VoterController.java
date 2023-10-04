package dat250.appassignB.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voters")
public class VoterController {

    @Autowired
    private VoterRepository voterRepository;

    @GetMapping
    public List<Voter> getAllVoters() {
        return voterRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voter> getVoterById(@PathVariable int id) {
        Optional<Voter> voter = voterRepository.findById(id);
        if (voter.isPresent()) {
            return ResponseEntity.ok(voter.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Voter createVoter(@RequestBody Voter voter) {
        return voterRepository.save(voter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voter> updateVoter(@PathVariable int id, @RequestBody Voter updatedVoter) {
        Optional<Voter> voter = voterRepository.findById(id);
        if (voter.isPresent()) {
            updatedVoter.setId(id);
            return ResponseEntity.ok(voterRepository.save(updatedVoter));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoter(@PathVariable int id) {
        Optional<Voter> voter = voterRepository.findById(id);
        if (voter.isPresent()) {
            voterRepository.delete(voter.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
