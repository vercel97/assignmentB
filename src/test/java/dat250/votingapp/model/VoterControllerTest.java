package dat250.votingapp.model;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class VoterControllerTest {

    @InjectMocks
    private VoterController voterController;

    @Mock
    private VoterRepository voterRepository;

    @Test
    public void testGetAllVoters() {
        Voter mockVoter1 = new Voter();
        Voter mockVoter2 = new Voter();
        when(voterRepository.findAll()).thenReturn(Arrays.asList(mockVoter1, mockVoter2));

        List<Voter> voters = voterController.getAllVoters();

        assertEquals(2, voters.size());
    }

    @Test
    public void testGetVoterById_found() {
        Voter mockVoter = new Voter();
        when(voterRepository.findById(1)).thenReturn(Optional.of(mockVoter));

        ResponseEntity<Voter> response = voterController.getVoterById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockVoter, response.getBody());
    }

    @Test
    public void testGetVoterById_notFound() {
        when(voterRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Voter> response = voterController.getVoterById(1);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testCreateVoter() {
        Voter mockVoter = new Voter();
        when(voterRepository.save(any(Voter.class))).thenReturn(mockVoter);

        Voter result = voterController.createVoter(mockVoter);

        assertEquals(mockVoter, result);
    }

    @Test
    public void testUpdateVoter_found() {
        Voter mockVoter = new Voter();
        Voter updatedVoter = new Voter();
        updatedVoter.setId(1);
        when(voterRepository.findById(1)).thenReturn(Optional.of(mockVoter));
        when(voterRepository.save(updatedVoter)).thenReturn(updatedVoter);

        ResponseEntity<Voter> response = voterController.updateVoter(1, updatedVoter);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedVoter, response.getBody());
    }

    @Test
    public void testUpdateVoter_notFound() {
        Voter updatedVoter = new Voter();
        when(voterRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Voter> response = voterController.updateVoter(1, updatedVoter);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteVoter_found() {
        Voter mockVoter = new Voter();
        when(voterRepository.findById(1)).thenReturn(Optional.of(mockVoter));
        doNothing().when(voterRepository).delete(mockVoter);

        ResponseEntity<Void> response = voterController.deleteVoter(1);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteVoter_notFound() {
        when(voterRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = voterController.deleteVoter(1);

        assertEquals(404, response.getStatusCodeValue());
    }
}
