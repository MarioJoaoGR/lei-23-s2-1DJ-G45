package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgencyTest {

    Agency agency1;
    Agency agency2;
    Agency agency3;
    Agency agency4;
    Agent agent1;
    StoreManager storeManager;
    Agent agent2;
    StoreManager storeManager2;


    @BeforeEach
    void setUp() {
        this.agent2 = new Agent("teste2", 111111111, 11111, "teste", 11111, "isep", "aaa@gmail.com");
        this.agent1 = new Agent( "teste", 111111111, 11111, "teste", 11111, "isep", "fff@gmail.com");
        this.storeManager = new StoreManager("StoreManager",  111111111, 11111, "teste", 111111, "isep","mai@aa");
        this.storeManager = new StoreManager("StoreManager",  111111111, 11111, "teste", 111111, "isep","mais@aaa");
        this.agency1 = new Agency("testeName", "testeAddress", 111111111, "teste@mail", storeManager);
        this.agency2 = new Agency( "testeName2", "testeAddress2", 222222222, "teste2@mail", storeManager);
        this.agency3 = new Agency( "testeName", "testeAddress", 111111111, "teste@mail", storeManager);
        this.agency4 = null;
    }

    @Test
    void equalDiferentObject() {
        Agency expect = agency1;
        Agency result = agency2; // função a chamar

        assertNotEquals(expect, result);
    }

    @Test
    void equalSameObject() {
        Agency expect = agency1;
        assertEquals(expect, agency1);
    }

    @Test
    void equal2() {
        Agency expect = agency1;
        Agency result = agency3;
        assertEquals(expect, result);


    }

    @Test
    void equal3() {
        Agency expect = agency1;
        Agency result = agency4;
        assertNotEquals(expect, result);
    }

    @Test
    void ensureEmployeeDoesNotEqualOtherObject() {
        assertNotEquals(agency1, new Object());
    }


    @Test
    void ensureHashCodeIsEqualForEqualObjects() {

        assertEquals(agency1.hashCode(), agency3.hashCode());
    }

    @Test
    void testHashCodeSameObject() {
        assertEquals(agency1.hashCode(), agency1.hashCode());
    }

    @Test
    void ensureHashCodeFailsForDifferentName() {
        Agency agencyTestName = new Agency( "testeName", "testeAddress", 111111111, "teste@mail", storeManager);
        Agency agencyTestName2 = new Agency("testeNamea", "testeAddress", 111111111, "teste@mail", storeManager);
        assertNotEquals(agencyTestName.hashCode(), agencyTestName2.hashCode());
    }


    @Test
    void ensureHashCodeFailsForDifferentPhoneNumber() {
        Agency agencyTestPhoneNumber = new Agency("testeName", "testeAddress", 111111112, "teste@mail", storeManager);
        Agency agencyTestPhoneNumber2 = new Agency( "testeNamea", "testeAddress", 111111111, "teste@mail", storeManager);
        assertNotEquals(agencyTestPhoneNumber.hashCode(), agencyTestPhoneNumber2.hashCode());
    }


    @Test
    void ensureHashCodeFailsForDifferentEmail() {
        Agency agencyTestEmail = new Agency("testeName", "testeAddress", 111111111, "teste1@mail", storeManager);
        Agency agencyTestEmail2 = new Agency( "testeNamea", "testeAddress", 111111111, "teste@mail", storeManager);
        assertNotEquals(agencyTestEmail.hashCode(), agencyTestEmail2.hashCode());
    }

    @Test
    void ensureHashCodeFailsForDifferentStoreManager() {
        Agency agencyTestName = new Agency("testeName", "testeAddress", 111111111, "teste@mail", storeManager);
        //  StoreManager testForHashCode = new StoreManager();
        Agency agencyTestName2 = new Agency( "testeNamea", "testeAddress", 111111111, "teste@mail", storeManager2);
        assertNotEquals(agencyTestName.hashCode(), agencyTestName2.hashCode());
    }

    @Test
    void ensureEqualsFailsForDifferentObjectType() {

        assertNotEquals(agency1, agency2);
    }

    @Test
    void ensureEqualsFailsWhenComparingNull() {

        assertNotEquals(agency1, null);
    }

    @Test
    void ensureEqualsSuccessWhenComparingSameObject() {

        assertEquals(agency1, agency1);
    }

    @Test
    void testThatAddAgentWorks() {


        Agent agentExpected = new Agent( "testeAdd", 111111111, 11111, "teste", 11111,"afa@mail.com");
        Optional<Agent> agent = agency1.addAgent(agentExpected);

        assertNotNull(agent1);
        assertTrue(agent.isPresent());
        assertEquals(agentExpected, agent.get());
    }

    @Test
    void ensureAddingDuplicateAgentFails() {

        Agent agent1 = new Agent( "testeAdd", 111111111, 11111, "teste", 11111,"afa@mail.com");
        agency1.addAgent(agent1);
        Optional<Agent> agent2 = agency1.addAgent(agent1);


        //Assert
        assertTrue(agent2.isEmpty());
    }


    @Test
    void verifyRemoveOfAgent() {
        Agent agent1 = new Agent( "testeAdd", 111111111, 11111, "teste", 11111,"afa@mail.com");


        // Add the test Agent to the list
        agency1.addAgent(agent1);

        // Call the removeAgent() method
        Optional<Agent> removedAgent = agency1.removeAgent(agent1);

        // Check that the removed Agent is the same as the test Agent
        assertTrue(removedAgent.isPresent());
        assertEquals(agent1, removedAgent.get());

        // Check that the Agent list is now empty
        assertTrue(agency1.getAgentsList().isEmpty());

        Optional<Agent> newAgents = Optional.empty();


    }

    @Test
    void ensureAnyAgentHasDifferentNameFails() {

        agency1.addAgent(agent1);
        assertFalse(agency1.anyAgentHasName("Agent2"));
    }

    @Test
    void ensureAnyAgentHasDifferentNameWorks() {

        agency1.addAgent(agent1);
        assertFalse(agency1.anyAgentHasName("Agent1"));
    }

    @Test
    void ensureCloneWorks() {


        Agency clone;
        clone = agency1.clone();
        assertEquals(agency1, clone);
    }
}

