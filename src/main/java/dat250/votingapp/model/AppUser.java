package dat250.votingapp.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String username;

    private String email;

    private String password;

    @OneToMany
    private List<Poll> polls = new ArrayList<>();

    @Getter
    private String verificationCode;

    private boolean isVerified;

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean getIsVerified() { return isVerified; }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

}

