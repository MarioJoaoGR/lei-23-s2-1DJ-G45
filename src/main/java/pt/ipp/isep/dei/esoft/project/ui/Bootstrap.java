package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgentDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.Serialization.Serialization;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addUsers();
        addAnnouncements();
        try {
            Serialization.readFromFile("data.ser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //addAgencyAndAgent();
        addAgentRequests();

    }


    private void addAgencyAndAgent() {

        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
        StoreManager storeManager = new StoreManager("aaa", 31, 414, "storepwd", 111, "aaaa", "asdas@aa");
        Agency agency = new Agency("Test", "test", 123456789, "teste@mail", storeManager);
        Agent agent1 = new Agent("agente1", 111111111, 11111, "teste", 11111, "isep", "agent@gmail.com");
        Agent agent2 = new Agent("agente2", 11121111, 111211, "tesfwfte", 11111, "isep", "fff22@gmail.com");
        agency.addAgent(agent1);
        agency.addAgent(agent2);
        agencyRepository.add(agency);

    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_NETWORK_MANAGER, AuthenticationController.ROLE_NETWORK_MANAGER);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_USER, AuthenticationController.ROLE_USER);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_MANAGER, AuthenticationController.ROLE_STORE_MANAGER);

        authenticationRepository.addUserWithRole("Network manager", "netm@this.app", "pwd", AuthenticationController.ROLE_NETWORK_MANAGER);
        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin", AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("user", "user@this.app", "pwd", AuthenticationController.ROLE_USER);
        authenticationRepository.addUserWithRole("user", "aa@this.app", "aa", AuthenticationController.ROLE_USER);
        authenticationRepository.addUserWithRole("agent", "agent@this.app", "pwd", AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("netManager", "storemanager@this.app", "pwd", AuthenticationController.ROLE_STORE_MANAGER);


    }

    // Created to test publishSaleUI
    private void addAgentRequests() {
        RequestRepository requestRepository = Repositories.getInstance().getRequestRepository();

        Agent agent = new Agent("agent@this.app", "agent");
        Owner owner = new Owner("owner",121212,1221231,"address","owner@gmail.com",123456789);
        List<String> photos = new ArrayList<>();


        RequestApartment requestApartment1 = new RequestApartment("sale", agent, 1, "location", 1, 1, owner, 1, 1, 1, "equip", photos);
        RequestApartment requestApartment2 = new RequestApartment("sale", agent, 2, "location", 1, 1, owner, 1, 1, 1, "equip", photos);


        requestRepository.add(requestApartment1);
        requestRepository.add(requestApartment2);
    }


    private void addAnnouncements() {
        AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();

        List<String> photograph = new ArrayList<>();

        Owner owner = new Owner("user", 1, 1, "address", "user@this.app", 1);

        Property p1 = new Land("Porto", 1, 1, photograph, owner);
        Property p2 = new Apartment("Porto", 1, 1, photograph, 1, 1, 1, null, owner);
        Property p3 = new Apartment("Aveiro", 1, 1, photograph, 1, 1, 1, null, owner);
        Property p4 = new Apartment("Lisboa", 1, 1, photograph, 1, 1, 1, null, owner);
        Property p5 = new House("Lisboa", 1, 1, photograph, 1, 1, 1, null, false, false, null, owner);
        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
        StoreManager storeManager = new StoreManager("StoreManager", 111111111, 11111, "teste", 111111, "isep", "mais@aaa");
        Agency agency = new Agency("testeName", "testeAddress", 111111111, "teste@mail", storeManager);
        Agency agency2 = new Agency("teste312Name", "test32eAddress", 111111111, "teste@mail", storeManager);
        Agency agency3 = new Agency("teste343212Name", "test32eAddress", 111111111, "teste@mail", storeManager);
        Agent agent1 = new Agent("agente12", 123456789, 11111, "pwd", 11111, "isep", "agent@this.app");
        Agent agent2 = new Agent("agente", 123456789, 11111, "pwd1", 11111, "isep", "agent1@this.app");
        Agent agent3 = new Agent("age313nte23", 111211111, 11111, "pwd1", 11111, "isep", "agent1@this.app");
        agency3.addAgent(agent3);
        agency.addAgent(agent1);
        agency2.addAgent(agent2);
        agencyRepository.add(agency);
        agencyRepository.add(agency2);
        agencyRepository.add(agency3);
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order("user2@this.app", 1, new Date());
        Order order2 = new Order("user@this.app", 2, new Date());

        orderList.add(order1);
        orderList.add(order2);

        Date date = new Date();
        VisitRequestDTO visitRequestDTO = new VisitRequestDTO();
        String date1 = "31-05-2023";
        List<String> timeSlot = new ArrayList<>();

        visitRequestDTO.setTimeSlot(timeSlot);
        visitRequestDTO.addTimeSlot("10:00 AM - 11:00 AM");
        visitRequestDTO.addTimeSlot("13:00 AM - 14:00 AM");

        String userName = "John Doe";
        String mail = "johndoe@example.com";
        visitRequestDTO.setDate(date1);
        visitRequestDTO.setEmail(mail);
        AgentDTO agentDTO = new AgentDTO();
        visitRequestDTO.setAgentDTO(agentDTO);
        visitRequestDTO.setUserName(userName);
        VisitRequest expected = new VisitRequest(visitRequestDTO);
        VisitRequest visitRequest = new VisitRequest("01-06-2023", timeSlot, "teste", "miguelfigueiredooliveira@gmail.com", agent1);
        VisitRequest visitRequest1 = new VisitRequest("02-06-2023", timeSlot, "teste", "email1",agent1);
        VisitRequest visitRequest2 = new VisitRequest("03-06-2023", timeSlot, "user3", "email1",agent1);
        VisitRequest visitRequest3 = new VisitRequest("05-06-2023", timeSlot, "user3", "email1",agent1);
        VisitRequest visitRequest4 = new VisitRequest("04-06-2023", timeSlot, "user3", "email1",agent1);
        VisitRequest visitRequest5 = new VisitRequest("17-06-2023", timeSlot, "user3", "email1",agent1);
        VisitRequest visitRequest6 = new VisitRequest("12-06-2023", timeSlot, "user3", "email1",agent1);
        VisitRequest visitRequest7 = new VisitRequest("09-06-2023", timeSlot, "user3", "email1",agent1);
        VisitRequest visitRequest8 = new VisitRequest("10-06-2023", timeSlot, "user3", "email1",agent1);
        VisitRequest visitRequest9 = new VisitRequest("08-06-2023", timeSlot, "miguel", "miguelfigueiredooliveira@gmail.com", agent1);

        List<VisitRequest> list = new ArrayList<>();
        list.add(expected);
        list.add(visitRequest9);


        List<VisitRequest> list2 = new ArrayList<>();
        list2.add(visitRequest1);
        list2.add(visitRequest);
        list2.add(visitRequest2);
        list2.add(visitRequest3);
        list2.add(visitRequest4);
        list2.add(visitRequest5);
        list2.add(visitRequest6);
        list2.add(visitRequest7);
        list2.add(visitRequest8);

        String dateString = "05/06/2023";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Parse the string and create a Date object
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }




        Commission commission = new Commission(10, "percentage");

        AnnouncementAvailable l1 = new AnnouncementAvailable(commission, 1, date, list, "Sale", p1, agent1, orderList);
        AnnouncementAvailable l2 = new AnnouncementAvailable(commission, 5, date, list2, "Lease", p2, agent1);
        AnnouncementAvailable l3 = new AnnouncementAvailable(commission, 5, date, "Lease", p1, agent1);
        AnnouncementAvailable l4 = new AnnouncementAvailable(commission, 10, date, "sale", p3, agent2);
        AnnouncementAvailable l5 = new AnnouncementAvailable(commission, 15, date, "Lease", p4, agent1);
        AnnouncementAvailable l6 = new AnnouncementAvailable(commission, 15, date, "Lease", p5, agent2);

        announcementRepository.addAvailable(l1);
        announcementRepository.addAvailable(l2);
        announcementRepository.addAvailable(l3);
        announcementRepository.addAvailable(l4);
        announcementRepository.addAvailable(l5);
        announcementRepository.addAvailable(l6);


    }


}
