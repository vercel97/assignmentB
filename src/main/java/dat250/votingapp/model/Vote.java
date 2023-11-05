package dat250.votingapp.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Poll poll;

    private boolean vote; // true for "yes", false for "no"

    // Constructors

    public Vote() {
    }

    public Vote(Poll poll, boolean vote) {
        this.poll = poll;
        this.vote = vote;
    }

    // Getters and Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        return id != null ? id.equals(vote.id) : vote.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // toString() can be useful for debugging

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", poll=" + poll +
                ", vote=" + vote +
                '}';
    }
}
