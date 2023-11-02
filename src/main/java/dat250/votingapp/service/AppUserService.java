package dat250.votingapp.service;

import dat250.votingapp.model.AppUser;
import dat250.votingapp.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository userRepository;

    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }


    /**
     * Gives access to a user in the database without validation
     *
     * @deprecated
     * @param username
     * @return
     */
    public Optional<AppUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    /**
     * Checks that the username is in the database and the password matches
     *
     * @param username
     * @param password
     * @return
     */
    public Optional<UserValidationResult> validateUser(String username, String password) {
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            AppUser ap = optionalUser.get();
            if (ap.getPassword().equals(password)) {
                UserValidationResult validatedUser = new UserValidationResult(ap, "User Validated");
                return Optional.of(validatedUser);
            } else {
                UserValidationResult unvalidatedUser = new UserValidationResult(null, "Password does not match");
                return Optional.of(unvalidatedUser);
            }
        } else {
            UserValidationResult notFoundUser = new UserValidationResult(null, "User not found");
            return Optional.of(notFoundUser);
        }
    }


}
