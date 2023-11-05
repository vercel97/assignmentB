package dat250.votingapp.service;

import dat250.votingapp.model.AppUser;
import dat250.votingapp.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser save(AppUser user) {
        return appUserRepository.save(user);
    }


    /**
     * Gives access to a user in the database without validation
     *
     * @deprecated
     * @param username
     * @return
     */
    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }


    /**
     * Checks that the username is in the database and the password matches
     *
     * @param username
     * @param password
     * @return
     */
    public Optional<UserValidationResult> validateUser(String username, String password) {
        Optional<AppUser> optionalUser = appUserRepository.findByUsername(username);

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


    /**
     * Verifies a user's account by checking the provided verification code.
     *
     * @param user The user to verify.
     * @param code The verification code provided by the user.
     * @return true if verification is successful, false otherwise.
     */
    public boolean verifyUser(AppUser user, String code) {
        if (user.getVerificationCode().equals(code)) {
            user.setIsVerified(true);

            user.setVerificationCode(null);

            appUserRepository.save(user);

            return true;
        }

        return false;
    }
    //public List<AppUser> searchByUsername(String username) {
    //    return userRepository.searchByUsernameContaining(username);
    //}
}
