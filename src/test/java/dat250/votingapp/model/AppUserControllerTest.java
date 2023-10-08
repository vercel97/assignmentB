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
import org.springframework.data.jpa.repository.JpaRepository;

@ExtendWith(MockitoExtension.class)
public class AppUserControllerTest {

    @InjectMocks
    private AppUserController appUserController;

    @Mock
    private AppUserRepository appUserRepository;

    @Test
    public void testGetAllAppUsers() {
        AppUser mockAppUser1 = new AppUser();
        AppUser mockAppUser2 = new AppUser();
        when(appUserRepository.findAll()).thenReturn(Arrays.asList(mockAppUser1, mockAppUser2));

        List<AppUser> appUsers = appUserController.getAllAppUsers();

        assertEquals(2, appUsers.size());
    }

    @Test
    public void testGetAppUserById_found() {
        AppUser mockAppUser = new AppUser();
        when(appUserRepository.findById(1)).thenReturn(Optional.of(mockAppUser));

        ResponseEntity<AppUser> response = appUserController.getAppUserById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockAppUser, response.getBody());
    }

    @Test
    public void testGetAppUserById_notFound() {
        when(appUserRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<AppUser> response = appUserController.getAppUserById(1);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testCreateAppUser() {
        AppUser mockAppUser = new AppUser();
        when(appUserRepository.save(any(AppUser.class))).thenReturn(mockAppUser);

        AppUser result = appUserController.createAppUser(mockAppUser);

        assertEquals(mockAppUser, result);
    }

    @Test
    public void testUpdateAppUser_found() {
        AppUser mockAppUser = new AppUser();
        AppUser updatedAppUser = new AppUser();
        updatedAppUser.setId(1);
        when(appUserRepository.findById(1)).thenReturn(Optional.of(mockAppUser));
        when(appUserRepository.save(updatedAppUser)).thenReturn(updatedAppUser);

        ResponseEntity<AppUser> response = appUserController.updateAppUser(1, updatedAppUser);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedAppUser, response.getBody());
    }

    @Test
    public void testUpdateAppUser_notFound() {
        AppUser updatedAppUser = new AppUser();
        when(appUserRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<AppUser> response = appUserController.updateAppUser(1, updatedAppUser);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteAppUser_found() {
        AppUser mockAppUser = new AppUser();
        when(appUserRepository.findById(1)).thenReturn(Optional.of(mockAppUser));
        doNothing().when(appUserRepository).delete(mockAppUser);

        ResponseEntity<Void> response = appUserController.deleteAppUser(1);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteAppUser_notFound() {
        when(appUserRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = appUserController.deleteAppUser(1);

        assertEquals(404, response.getStatusCodeValue());
    }
}
