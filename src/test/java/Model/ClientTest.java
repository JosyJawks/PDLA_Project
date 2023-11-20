package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {

    @Test
    public void testSetAndGetRequest() {
        Client client = new Client();
        Mission mission = new Mission();

        // Set a mission request
        client.setRequest(mission);

        // Get and assert the mission request
        assertEquals(mission, client.getRequest());
    }

    @Test
    public void testInheritedFieldsFromUser() {
        Client client = new Client();

        // Set user details
        client.setName("John");
        client.setSurname("Doe");
        client.setEmail("john.doe@example.com");
        client.setPassword("password123");
        client.setType("client");

        // Check if user details are correctly inherited
        assertEquals("John", client.getName());
        assertEquals("Doe", client.getSurname());
        assertEquals("john.doe@example.com", client.getEmail());
        assertEquals("password123", client.getPassword());
        assertEquals("client", client.getType());
    }
}
