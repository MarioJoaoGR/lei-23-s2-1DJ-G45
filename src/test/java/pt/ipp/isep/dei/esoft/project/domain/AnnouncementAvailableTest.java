package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgentMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AnnouncementMapper;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementAvailableTest {


    @Test
    void equalSameObject() {
        AnnouncementAvailable a1 = new AnnouncementAvailable(null,0,null,"Sale",null,null);

        AnnouncementAvailable expect = a1;

        assertEquals(expect, a1);
    }
    @Test
    public void testCreateVisitRequest() {
        // Input data

        String date = "2023-05-09";
        List<String> timeSlot = new ArrayList<>();
        timeSlot.add("10:00 AM - 11:00 AM");
        String userName = "John Doe";
        String mail = "johndoe@example.com";
        VisitRequestDTO visitRequestDTO = new VisitRequestDTO();
        visitRequestDTO.setDate(date);
        visitRequestDTO.setEmail(mail);
        visitRequestDTO.setTimeSlot(timeSlot);
        visitRequestDTO.setUserName(userName);
        Agent agent1 = new Agent("aa", 1231231231, 123123123, "fafa", 123123123, "aaa");
        AgentMapper agentMapper = new AgentMapper();
        visitRequestDTO.setAgentDTO(agentMapper.toDTO(agent1));
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable();
        // Expected output
        VisitRequest expected = new VisitRequest(visitRequestDTO);

        // Actual output
        VisitRequest actual = announcementAvailable.createVisitRequest(visitRequestDTO);

        // Assert equality
        assertEquals(expected.getDate(), actual.getDate());
        assertEquals(expected.getTimeSlot(), actual.getTimeSlot());
        assertEquals(expected.getUserName(), actual.getUserName());
        assertEquals(expected.getEmail(), actual.getEmail());
    }
    @Test
    void testAddVisitRequests() {
        // Setup
        List<String> timeSlot = new ArrayList<>();
        timeSlot.add("10:00 AM - 11:00 AM");
        Agent agent1 = new Agent("aa", 1231231231, 123123123, "fafa", 123123123, "aaa");
        VisitRequest visitRequest = new VisitRequest("2023-05-10", timeSlot, "Jane Doe", "janedoe@example.com",agent1);
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable();
        // Call method under test
        Optional<VisitRequest> result = announcementAvailable.addVisitRequests(visitRequest);

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(visitRequest.getDate(), result.get().getDate());
        assertEquals(visitRequest.getTimeSlot(), result.get().getTimeSlot());
        assertEquals(visitRequest.getUserName(), result.get().getUserName());
        assertEquals(visitRequest.getEmail(), result.get().getEmail());
        assertTrue(announcementAvailable.visitRequests.contains(result.get()));
        assertFalse(announcementAvailable.validVisitRequest(result.get()));
    }

    @Test
    void NotEqualsDiffentObjects() {
        Commission commission = new Commission(5,"percentage");
        AnnouncementAvailable a1 = new AnnouncementAvailable(commission,1,new Date(),"Lease",null,null);
        AnnouncementAvailable a2 = new AnnouncementAvailable(commission,1,new Date(),"Sale",null,null);

        AnnouncementAvailable expect = a1;
        AnnouncementAvailable result = a2;
        assertNotEquals(expect, result);
    }


    @Test
    void ensureHashCodeIsDiffentForDifferentObjects() {
        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);

        AnnouncementAvailable a1 = new AnnouncementAvailable(null,0,null,"Sale",p1,null);
        AnnouncementAvailable a2 = new AnnouncementAvailable(null,0,null,"Sale",null,null);

        assertNotEquals(a1.hashCode(), a2.hashCode());
    }

    @Test
    void testHashCodeSameObject() {
        AnnouncementAvailable a1 = new AnnouncementAvailable(null,0,null,"Sale",null,null);
        assertEquals(a1.hashCode(), a1.hashCode());
    }


    @Test
    void TestFoundFilteredProperty() {
        String business="Sale";
        String type="Apartment";
        int rooms=1;

        Property p1 = new Apartment("Porto", 70,1000,null,1, 1,1,null,null);
        AnnouncementAvailable l1 = new AnnouncementAvailable(null,0,null,"Sale",p1,null);

        assertTrue(l1.filterProperty(business,type,rooms));

    }

    @Test
    void TestFoundFilteredLand() {
        String business="Sale";
        String type="land";
        int rooms=0;

        Property p1 = new Land("Porto", 70,1000,null,null);
        AnnouncementAvailable l1 = new AnnouncementAvailable(null,0,null,"Sale",p1,null);

        assertTrue(l1.filterProperty(business,type,rooms));
    }

    @Test
    void TestNotFoundFilteredProperty() {
        String business="Lease";
        String type="Apartment";
        int rooms=1;

        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable l1 = new AnnouncementAvailable(null,0,null,"Sale",p1,null);

        assertFalse(l1.filterProperty(business,type,rooms));

    }


    @Test
    void TestNotFoundFilteredLand() {
        String business="Sale";
        String type="land";
        int rooms=1;

        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable l1 = new AnnouncementAvailable(null,0,null,"Lease",p1,null);

        assertFalse(l1.filterProperty(business,type,rooms));

    }




    // Test method isBusiness
    @Test
    void testEqualsSameBusiness() {

        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable l1 = new AnnouncementAvailable(null,0,null,"Sale",p1,null);

        assertTrue(l1.isBusiness("sale"));
    }

    @Test
    void testEqualsDifferentBusiness() {
        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable l1 = new AnnouncementAvailable(null,10,null,"Sale",p1,null);

        assertFalse(l1.isBusiness("Lease"));
    }

    @Test
    void testEqualsNullBusiness() {
        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable l1 = new AnnouncementAvailable(null,10,null,"Sale",p1,null);

        assertFalse(l1.isBusiness(null));
    }

    @Test
    void ensureCreateOrderWorks(){
        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable(null,10,null,"Sale",p1,null);

        Order order = announcementAvailable.createOrder("user@this.app",120000);


    }


    @Test
    void ensureAddOrderWorks(){
        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable(null,10,null,"Sale",p1,null);

        Order order = announcementAvailable.createOrder("user@this.app",9);
        announcementAvailable.addOrder(order);


        assertEquals(announcementAvailable.getOrderList().get(0),order);
    }


    @Test
    void ensureAddOrderValidateValue(){
        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable(null,10,null,"Sale",p1,null);

        Order order = announcementAvailable.createOrder("user@this.app",120000);
        Order order1 = announcementAvailable.createOrder("user@this.app",10);

        assertTrue(announcementAvailable.addOrder(order).isEmpty());
        assertTrue(!announcementAvailable.addOrder(order1).isEmpty());

    }

    @Test
    void ensureAddOrderValidateValueRepeat(){
        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable(null,10000,null,"Sale",p1,null);

        Order order = announcementAvailable.createOrder("user@this.app",120);
        Order order1 = announcementAvailable.createOrder("user1@this.app",120);
        Order order2 = announcementAvailable.createOrder("user1@this.app",121);

        announcementAvailable.addOrder(order);

        assertTrue(announcementAvailable.addOrder(order1).isEmpty());
        assertFalse(announcementAvailable.addOrder(order2).isEmpty());
    }

    @Test
    void ensureAddOrderValidateRepeatClient(){
        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable(null,10000,null,"Sale",p1,null);

        Order order = announcementAvailable.createOrder("user@this.app",120);
        Order order1 = announcementAvailable.createOrder("user@this.app",122);
        Order order2 = announcementAvailable.createOrder("user1@this.app",123);

        announcementAvailable.addOrder(order);


        assertTrue(announcementAvailable.addOrder(order1).isEmpty());
        assertFalse(announcementAvailable.addOrder(order2).isEmpty());

    }

}