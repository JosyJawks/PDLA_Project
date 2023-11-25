package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MissionTest {

    @Test
    public void testMissionDetails() {
        // Create a client for the mission
        Client client = new Client();
        client.setName("John Doe");
        client.setEmail("john.doe@example.com");

        // Create a volunteer for the mission
        Volunteer volunteer = new Volunteer();
        volunteer.setName("Jane Doe");
        volunteer.setEmail("jane.doe@example.com");

        // Create a mission
        Mission mission = new Mission();
        mission.setStatus("Pending");
        mission.setDateCreation("01-01-2023");
        mission.setDateMission("15-01-2023");
        mission.setLocation("City Center");
        mission.setObjective("Helping at the community center");
        mission.setClient(client);
        mission.setVolunteer(volunteer);

        // Test mission details
        assertEquals(client, mission.getClient());
        assertEquals(volunteer, mission.getVolunteer());
        assertEquals("City Center", mission.getLocation());
        assertEquals("15-01-2023", mission.getDateMission());
        assertEquals("01-01-2023", mission.getDateCreation());
        assertEquals("Helping at the community center", mission.getObjective());
        assertEquals("Pending", mission.getStatus());
    }

    @Test
    public void testDefaultConstructor() {
        // Create a mission using the default constructor
        Mission mission = new Mission();

        // Test default values
        assertNull(mission.getClient());
        assertNull(mission.getVolunteer());
        assertNull(mission.getLocation());
        assertNull(mission.getDateMission());
        assertNull(mission.getDateCreation());
        assertNull(mission.getObjective());
        assertNull(mission.getStatus());
    }
}
