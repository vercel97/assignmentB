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
    @EqualsAndHashCode.Include
    private int id;

    private boolean isPrivate;

    private int duration;

    //@OneToOne
    //private PollData pollData;

    @OneToOne(mappedBy = "pairedPoll")
    //@JoinColumn(name = "iot_device_id")
    private IoTDevice pairedIoT;

}
