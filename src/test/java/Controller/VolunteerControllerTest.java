package Controller;

import Model.Volunteer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VolunteerControllerTest {

    private static Volunteer testVolunteer;

    @BeforeAll
    static void setUp() {
        // Set up a test volunteer with known data
        testVolunteer = new Volunteer();
        testVolunteer.setName("Lucile");
        testVolunteer.setSurname("Cossoul");
        testVolunteer.setEmail("cossoul@insa-toulouse.fr");
        testVolunteer.setPassword("2341");

    }

    @Test
    void testGetVolunteerID() {
        // Get volunteerID from table "Users"
        int volunteerID = VolunteerController.getVolunteerID(testVolunteer);
        int expectedID = 105;

        // Comparison between expectedID and actual volunteerID obtained from getVolunteerID() method
        assertEquals(expectedID,volunteerID,"Volunteer ID should match the expected ID");
    }

    @Test
    void testGetVolunteerInfo() {
        int testVolunteerID = 105;
        // Get volunteer's information from its ID from table "Users"
        Volunteer retrievedVolunteer = VolunteerController.getVolunteerInfo(testVolunteerID);

        // Comparison between testVolunteer created in setUp() method and actual volunteer retrieved with getVolunteerInfo() method
        assertNotNull(retrievedVolunteer, "Retrieved volunteer should not be null");
        assertEquals(testVolunteer.getName(),retrievedVolunteer.getName(),"Expected volunteer's name and retrieved volunteer's name do not match");
        assertEquals(testVolunteer.getSurname(),retrievedVolunteer.getSurname(),"Expected volunteer's surname and retrieved volunteer's surname do not match");
        assertEquals(testVolunteer.getEmail(),retrievedVolunteer.getEmail(),"Expected volunteer's email and retrieved volunteer's email do not match");
        assertEquals(testVolunteer.getPassword(),retrievedVolunteer.getPassword(),"Expected volunteer's password and retrieved volunteer's password do not match");
    }
}