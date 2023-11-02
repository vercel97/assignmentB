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

    /**
     * Adds a question to the question list in the poll
     * @param question
     */
    public void addQuestion(Question question){
        questionList.add(question);
    }

    /**
     * Removes a question from the list in the poll
     * @param question
     */
    public void removeQuestion(Question question){
        questionList.remove(question);
    }

    /**
     * Adds a voter to a private poll
     * @param voter
     */
    public void addAuthorizedUser(Voter voter){
        authorizedUsers.add(voter);
    }

    /**
     * Remove a voter from a private poll
     * @param voter
     */
    public void removeAuthorizedUser(Voter voter){
        authorizedUsers.remove(voter);
    }
}
