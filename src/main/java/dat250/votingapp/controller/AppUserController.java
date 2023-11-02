package dat250.votingapp.controller;

import dat250.votingapp.model.AppUser;
import dat250.votingapp.model.Poll;
import dat250.votingapp.repository.AppUserRepository;
import dat250.votingapp.repository.PollRepository;
import dat250.votingapp.service.AppUserService;
import dat250.votingapp.service.UserValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appUsers")
public class AppUserController {

    @Autowired
    private AppUserRepository appUserRepository;
    private PollRepository pollRepository;

    @Autowired
    private AppUserService userService;

    @GetMapping
    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getAppUserById(@PathVariable int id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isPresent()) {
            return ResponseEntity.ok(appUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public AppUser createAppUser(@RequestBody AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateAppUser(@PathVariable int id, @RequestBody AppUser updatedAppUser) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isPresent()) {
            updatedAppUser.setId(id);
            return ResponseEntity.ok(appUserRepository.save(updatedAppUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppUser(@PathVariable int id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isPresent()) {
            appUserRepository.delete(appUser.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * creates a new user in the db
     * @param user
     * @return
     */
    @PostMapping("/register")
    public AppUser registerUser(@RequestBody AppUser user) {
        return userService.save(user);
    }

    /**
     * Checks the username and password
     * @param user
     * @return
     */
    @PostMapping("/login")
    public UserValidationResult loginUser(@RequestBody AppUser user) {
        return userService.validateUser(user.getUsername(), user.getPassword()).orElse(null);
    }

    @GetMapping("/logout")
    public String logout() {
        return "Logged out successfully!";
    }

    @PostMapping("/verify")
    public ResponseEntity<Void> verifyUser(@RequestParam String username) {
        //TODO: confirm correct code, set verified = true
        throw new UnsupportedOperationException("verifyUser Not implemented");
    }

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


    /**
     * Returns the polls in the users poll list
     *
     * @param username
     * @return
     */
    @GetMapping("/viewUserPollList")
    public ResponseEntity<List<Poll>> viewPollList(@RequestParam String username) {
        if (username == null || username.trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<AppUser> ap = appUserRepository.findByUsername(username);

        if (ap.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Poll> polls = ap.get().getPolls();
        return new ResponseEntity<>(polls, HttpStatus.OK);
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

    @PostMapping("/editPoll")
    public ResponseEntity<Void> editPoll(@RequestParam String pollTitle) {
        //TODO: call edit poll, i.e. add/remove questions
        throw new UnsupportedOperationException("editPoll Not implemented");
    }




}
