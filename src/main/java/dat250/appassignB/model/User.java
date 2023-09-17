package dat250.appassignB.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private String username;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String password;
    @Setter
    @Getter
    @OneToMany
    private List<Poll> polls;

    // Constructors

}

