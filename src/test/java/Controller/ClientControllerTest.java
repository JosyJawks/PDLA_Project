package Controller;

import Model.Client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ClientControllerTest {


    private static Client testClient;

    @BeforeAll
    static void setUp() {
        // Set up a test client with known data
        testClient = new Client();
        testClient.setName("Josua");
        testClient.setSurname("Ramouche");
        testClient.setEmail("ramouche@insa-toulouse.fr");
        testClient.setPassword("1234");

    }

    @Test
    void testGetClientID() {
        // Get clientID from table "Users"
        int clientID = ClientController.getClientID(testClient);
        int expectedID = 57;

        // Comparison between expectedID and actual clientID obtained from getClientID() method
        assertEquals(expectedID,clientID,"Client ID should match the expected ID");
    }

    @Test
    void testGetClientInfo() {
        int testClientID = 57;
        // Get client's information from its ID from table "Users"
        Client retrievedClient = ClientController.getClientInfo(testClientID);

        // Comparison between testClient created in setUp() method and actual client retrieved with getClientInfo() method
        assertNotNull(retrievedClient, "Retrieved client should not be null");
        assertEquals(testClient.getName(),retrievedClient.getName(),"Expected client's name and retrieved client's name do not match");
        assertEquals(testClient.getSurname(),retrievedClient.getSurname(),"Expected client's surname and retrieved client's surname do not match");
        assertEquals(testClient.getEmail(),retrievedClient.getEmail(),"Expected client's email and retrieved client's email do not match");
        assertEquals(testClient.getPassword(),retrievedClient.getPassword(),"Expected client's password and retrieved client's password do not match");
    }
}