package dat250.votingapp.controller;

import dat250.votingapp.model.AppUser;
import dat250.votingapp.model.Poll;
import dat250.votingapp.repository.AppUserRepository;
import dat250.votingapp.service.AppUserService;
import dat250.votingapp.service.JwtService;
import dat250.votingapp.service.UserValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appUsers")
public class AppUserController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService userService;

    @Autowired
    private JwtService jwtService;

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
        user.setIsVerified(false);
        return userService.save(user);
    }

    /**
     * Checks the username and password (login)
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AppUser user) {
        UserValidationResult validationResult = userService.validateUser(user.getUsername(), user.getPassword()).orElse(null);
        if (validationResult.getUser() != null) {
            String token = jwtService.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    /**
     * Logout user and invalidate token
     * @param requestHeader
     * @return
     */
    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader HttpServletRequest requestHeader) {

        String token = requestHeader.getHeader("Authenticator");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            jwtService.invalidateToken(token);
        }

        return ResponseEntity.ok("Logged out successfully!");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam String username, @RequestParam String code) {
        Optional<AppUser> userOpt = appUserRepository.findByUsername(username);

        if (!userOpt.isPresent()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        AppUser user = userOpt.get();
        boolean isVerified = userService.verifyUser(user, code);

        if (!isVerified) {
            return new ResponseEntity<>("Verification failed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User verified successfully", HttpStatus.OK);
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


}
