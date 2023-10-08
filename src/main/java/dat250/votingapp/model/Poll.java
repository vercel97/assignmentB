package dat250.votingapp.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private boolean isPrivate;

    private int duration;

    @OneToOne(mappedBy = "pairedPoll")
    //@JoinColumn(name = "iot_device_id")
    private IoTDevice pairedIoT;

    private String pollTitle;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Voter> authorizedUsers;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questionList;

}
