import Controller.UserController;
import org.junit.jupiter.api.*;
import Controller.*;
import Model.*;



import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void createUser() {
        User user = UserController.createUser("John", "Doe", "john@example.com", "password", "User");
        assertNotNull(user);
        assertEquals("John", user.getName());
        assertEquals("Doe", user.getSurname());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("User", user.getType());
    }

    @Test
    void createClient() {
        Client client = UserController.createClient("Jane", "Doe", "jane@example.com", "password", "Client");
        assertNotNull(client);
        assertEquals("Jane", client.getName());
        assertEquals("Doe", client.getSurname());
        assertEquals("jane@example.com", client.getEmail());
        assertEquals("password", client.getPassword());
        assertEquals("Client", client.getType());
    }

    @Test
    void createVolunteer() {
        Volunteer volunteer = UserController.createVolunteer("Bob", "Smith", "bob@example.com", "password", "Volunteer");
        assertNotNull(volunteer);
        assertEquals("Bob", volunteer.getName());
        assertEquals("Smith", volunteer.getSurname());
        assertEquals("bob@example.com", volunteer.getEmail());
        assertEquals("password", volunteer.getPassword());
        assertEquals("Volunteer", volunteer.getType());
    }

    @Test
    void signUp() {
        // Assuming that the SignUpApp.getFinal() method returns valid user data
        String[] userData = {"Josua", "Ramouche", "ramouche@insa-toulouse.fr", "Client", "1234"};

        assertDoesNotThrow(() -> {
            UserController.SignUp();
        });
    }

    @Test
    void signIn() {
        // Assuming that the SignInApp.getFinal() method returns valid user data
        String[] userData = {"ramouche@insa-toulouse.fr", "1234"};

        assertDoesNotThrow(() -> {
            UserController.SignIn();
        });
    }

    @Test
    void getConnectedClient() {
    }

    @Test
    void getConnectedVolunteer() {
    }
}