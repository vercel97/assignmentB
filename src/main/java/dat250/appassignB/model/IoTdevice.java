package dat250.appassignB.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class IoTdevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer redVotes;
    private Integer greenVotes;

    // One-to-One relationship with Poll
    @OneToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;
}

