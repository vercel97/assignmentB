package dat250.votingapp.controller;

import dat250.votingapp.model.Poll;
import dat250.votingapp.model.Question;
import dat250.votingapp.repository.PollRepository;
import dat250.votingapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;


    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id) {
        Optional<Question> question = questionRepository.findById(id);

        if (question.isPresent()) {
            return ResponseEntity.ok(question.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Void> createQuestion(@RequestParam Question question) {
        if (question == null ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Question newQuestion = new Question();
        questionRepository.save(newQuestion);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable int id, @RequestBody Question updatedQuestion) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            updatedQuestion.setId(id);
            return ResponseEntity.ok(questionRepository.save(updatedQuestion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/voteRed")
    public ResponseEntity<Question> updateRed(@PathVariable int id, @RequestBody int responseRedButton1) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            question.get().setResponseRedButton1();
            return ResponseEntity.ok(questionRepository.save(question.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/voteGreen")
    public ResponseEntity<Question> updateGreen(@PathVariable int id, @RequestBody int responseGreenButton2) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            question.get().setResponseGreenButton2();
            return ResponseEntity.ok(questionRepository.save(question.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable int id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            questionRepository.delete(question.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Deprecated
    @PostMapping("/editQuestion")
    public ResponseEntity<Void> editQuestion(@RequestParam Question question) {
        if (question == null ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        questionRepository.save(question);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Deprecated
    @PostMapping("/deleteQuestion")
    public ResponseEntity<Void> deletePoll(@RequestParam String pollTitle) {
        //TODO: call to delete poll
        throw new UnsupportedOperationException("deletePoll Not implemented");
    }
}
