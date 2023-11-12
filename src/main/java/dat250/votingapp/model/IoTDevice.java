package dat250.votingapp.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class IoTDevice {

    private static IoTDevice instance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer redVotes;

    private Integer greenVotes;

    @OneToOne
    private Poll pairedPoll;

    @OneToOne(mappedBy = "device")
    private IoTDisplay display;

    // Constructors, including one that initializes display
    public IoTDevice() {
        this.display = new IoTDisplay();
    }

    public static IoTDevice getInstance() {
        if (instance == null) {
            instance = new IoTDevice();
        }
        return instance;
    }
}
