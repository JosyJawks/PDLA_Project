package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {

    @Test
    public void testSetAndGetFields() {
        User user = new User();

        // Set user details
        user.setName("John");
        user.setSurname("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        user.setType("user");

        // Check if user details are retrieved correctly
        assertEquals("John", user.getName());
        assertEquals("Doe", user.getSurname());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("user", user.getType());
    }

    @Test
    public void testDefaultConstructor() {
        User user = new User();

        // Check if default values are set correctly
        assertNull(user.getName());
        assertNull(user.getSurname());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getType());
    }
}
