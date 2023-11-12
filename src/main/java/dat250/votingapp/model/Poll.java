package dat250.votingapp.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Column(name = "isPrivate", nullable = false)
    private boolean isPrivate = false;

    /*
     * Indicates whether the poll is open
     */
    private boolean status;

    private int duration;

    @OneToOne(mappedBy = "pairedPoll")
    //@JoinColumn(name = "iot_device_id")
    private IoTDevice pairedIoT;

    private String pollTitle;

    private int yesVoteCount;
    private int noVoteCount;

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

    /**
     * Set status of a current poll
     * @param isOpen
     */
    public void setStatus(boolean isOpen){ this.status = isOpen; };

    /**
     * Get status of a current poll
     */
    public boolean getStatus() { return this.status; }

    public Map<String, Integer> getResults() {
        Map<String, Integer> results = new HashMap<>();
        results.put("yes", yesVoteCount);
        results.put("no", noVoteCount);
        return results;
    }

    public void pairDevice(){

        this.pairedIoT = dat250.votingapp.model.IoTDevice.getInstance();
        this.pairedIoT.setPairedPoll(this);
    }

    public void forgetDevice(){

        this.pairedIoT.setPairedPoll(new Poll());
    }
}
