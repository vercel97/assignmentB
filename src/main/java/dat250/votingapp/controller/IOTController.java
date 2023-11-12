package dat250.votingapp.controller;

import dat250.votingapp.model.IoTDevice;
import dat250.votingapp.model.Question;
import dat250.votingapp.repository.PollRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/notifications")
public class IOTController {

    private PollRepository pollRepository;
    private final IoTDevice device = IoTDevice.getInstance();


    @PostMapping("/api/notification/greenVote")
    public ResponseEntity<String> registerGreenVote(@RequestBody String notification) {


        Question question = pollRepository.getActiveQuestion(device.getPairedPoll());
        question.setResponseGreenButton2();
        return ResponseEntity.ok("Notification received");
    }

    @PostMapping("/api/notification/redVote")
    public ResponseEntity<String> registerRedVote(@RequestBody String notification) {

        Question question = pollRepository.getActiveQuestion(device.getPairedPoll());
        question.setResponseRedButton1();
        return ResponseEntity.ok("Notification received");
    }
}

