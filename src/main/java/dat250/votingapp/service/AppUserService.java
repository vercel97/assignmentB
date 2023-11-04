package dat250.votingapp.service;

import dat250.votingapp.model.AppUser;
import dat250.votingapp.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository userRepository;

    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }

    public Optional<AppUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public List<AppUser> searchByUsername(String username) {
        return userRepository.searchByUsernameContaining(username);
    }
}
