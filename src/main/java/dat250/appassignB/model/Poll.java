package dat250.appassignB.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean isPrivate;
    private Integer timeLimit;
    //TODO -poll data what type is that, also should timeLimit be a some sort of integer?
    private IoTdevice pairedTo;

    //a user can create many polls, only one user can own a poll
    @ManyToOne
    private User user;



}
