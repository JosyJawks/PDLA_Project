package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VolunteerTest {

    @Test
    public void testInheritedFieldsFromUser() {
        Volunteer volunteer = new Volunteer();

        // Set user details
        volunteer.setName("Jane");
        volunteer.setSurname("Doe");
        volunteer.setEmail("jane.doe@example.com");
        volunteer.setPassword("password456");
        volunteer.setType("volunteer");

        // Check if user details are correctly inherited
        assertEquals("Jane", volunteer.getName());
        assertEquals("Doe", volunteer.getSurname());
        assertEquals("jane.doe@example.com", volunteer.getEmail());
        assertEquals("password456", volunteer.getPassword());
        assertEquals("volunteer", volunteer.getType());
    }
}
