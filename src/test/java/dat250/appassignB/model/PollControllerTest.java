package dat250.appassignB.model;

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
public class PollControllerTest {

    @InjectMocks
    private PollController pollController;

    @Mock
    private PollRepository pollRepository;

    @Test
    public void testGetAllPolls() {
        Poll mockPoll1 = new Poll();
        Poll mockPoll2 = new Poll();
        when(pollRepository.findAll()).thenReturn(Arrays.asList(mockPoll1, mockPoll2));

        List<Poll> polls = pollController.getAllPolls();

        assertEquals(2, polls.size());
    }

    @Test
    public void testGetPollById_found() {
        Poll mockPoll = new Poll();
        when(pollRepository.findById(1)).thenReturn(Optional.of(mockPoll));

        ResponseEntity<Poll> response = pollController.getPollById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockPoll, response.getBody());
    }

    @Test
    public void testGetPollById_notFound() {
        when(pollRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Poll> response = pollController.getPollById(1);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testCreatePoll() {
        Poll mockPoll = new Poll();
        when(pollRepository.save(any(Poll.class))).thenReturn(mockPoll);

        Poll result = pollController.createPoll(mockPoll);

        assertEquals(mockPoll, result);
    }

    @Test
    public void testUpdatePoll_found() {
        Poll mockPoll = new Poll();
        Poll updatedPoll = new Poll();
        updatedPoll.setId(1);
        when(pollRepository.findById(1)).thenReturn(Optional.of(mockPoll));
        when(pollRepository.save(updatedPoll)).thenReturn(updatedPoll);

        ResponseEntity<Poll> response = pollController.updatePoll(1, updatedPoll);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedPoll, response.getBody());
    }

    @Test
    public void testUpdatePoll_notFound() {
        Poll updatedPoll = new Poll();
        when(pollRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Poll> response = pollController.updatePoll(1, updatedPoll);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testDeletePoll_found() {
        Poll mockPoll = new Poll();
        when(pollRepository.findById(1)).thenReturn(Optional.of(mockPoll));
        doNothing().when(pollRepository).delete(mockPoll);

        ResponseEntity<Void> response = pollController.deletePoll(1);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testDeletePoll_notFound() {
        when(pollRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = pollController.deletePoll(1);

        assertEquals(404, response.getStatusCodeValue());
    }
}
