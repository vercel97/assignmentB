package dat250.votingapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String questionText;
    private int responseRedButton1;
    private int responseGreenButton2;
    private String username;
    private String pollTitle;
    //private DateTimeFormat starttime;
    //private DateTimeFormat endtime;
    private boolean isActive;

    public void setResponseRedButton1() {
        this.responseRedButton1++;
    }

    public void setResponseGreenButton2(){
        this.responseGreenButton2++;
    }


}
