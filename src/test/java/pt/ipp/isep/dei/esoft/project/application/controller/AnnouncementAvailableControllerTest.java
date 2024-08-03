package pt.ipp.isep.dei.esoft.project.application.controller;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.OrderDTO;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AnnouncementMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementAvailableControllerTest {

    AnnouncementRepository announcementRepository = new AnnouncementRepository();
    AnnouncementController controller = new AnnouncementController(announcementRepository,null);
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();


    //Fill Announcement Category Repository
    Property p1 = new Apartment("Porto", 70,1000,null,3, 3,1,null,null);
    Property p2 = new House("Algarve", 70,1000,null,3, 3,1,null,false,false,null,null);
    Property p3 = new House("Lisboa", 70,1000,null,3, 3,1,null,false,false,null,null);
    Property p4 = new Apartment("Evora", 70,1000,null,3, 3,1,null,null);
    Property p5 = new Land("Leiria", 70,1000,null,null);
    Property p6 = new House("Setubal", 70,1000,null,2, 2,1,null,false,false,null,null);
    Property p7 = new Land("Bragança", 70,1000,null,null);
    Property p8 = new House("Setubal", 70,1000,null,3, 3,1,null,false,false,null,null);
    Commission commission = new Commission(5,"percentage");

    AnnouncementAvailable L1=new AnnouncementAvailable(commission,200,new Date(),"Lease",p1,new Agent());
    AnnouncementAvailable L2=new AnnouncementAvailable(commission,150,new Date(),"Lease",p2,new Agent());
    AnnouncementAvailable L3=new AnnouncementAvailable(commission,170,new Date(),"Lease",p3,new Agent());
    AnnouncementAvailable L4=new AnnouncementAvailable(commission,90,new Date(),"Sale",p4,new Agent());
    AnnouncementAvailable L5=new AnnouncementAvailable(commission,500,new Date(),"Lease",p5,new Agent());
    AnnouncementAvailable L6=new AnnouncementAvailable(commission,340,new Date(),"Lease",p6,new Agent());
    AnnouncementAvailable L7=new AnnouncementAvailable(commission,370,new Date(),"Sale",p7,new Agent());
    AnnouncementAvailable L8=new AnnouncementAvailable(commission,370,new Date(),"Sale",p8,new Agent());




    @Test
    void ensureGetListingWorkAllParameters() {

        announcementRepository.addAvailable(L1);
        announcementRepository.addAvailable(L2);
        announcementRepository.addAvailable(L3);
        announcementRepository.addAvailable(L4);
        announcementRepository.addAvailable(L5);
        announcementRepository.addAvailable(L6);
        announcementRepository.addAvailable(L7);
        announcementRepository.addAvailable(L8);


        //Act

        AnnouncementAvailable[] expected = {L2,L3};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];
        List<AnnouncementAvailable> listfiltered = controller.getFilteredListing("Lease","House",3);

        int position =0;
        for (AnnouncementAvailable lr : listfiltered){
            actual[position] = lr;
            position++;
        }

        assertArrayEquals(expected, actual);

    }



    @Test
    void ensureGetListingWorkAllParametersAllBusiness() {

        announcementRepository.addAvailable(L1);
        announcementRepository.addAvailable(L2);
        announcementRepository.addAvailable(L3);
        announcementRepository.addAvailable(L4);
        announcementRepository.addAvailable(L5);
        announcementRepository.addAvailable(L6);
        announcementRepository.addAvailable(L7);
        announcementRepository.addAvailable(L8);

        AnnouncementAvailable[] expected = {L2,L3,L8};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];
        List<AnnouncementAvailable> listfiltered = controller.getFilteredListing("","House",3);

        int position =0;
        for (AnnouncementAvailable lr : listfiltered){
            actual[position] = lr;
            position++;
        }

        assertArrayEquals(expected, actual);

    }

    @Test
    void ensureGetListingWorkAllParametersAllType() {
        announcementRepository.addAvailable(L1);
        announcementRepository.addAvailable(L2);
        announcementRepository.addAvailable(L3);
        announcementRepository.addAvailable(L4);
        announcementRepository.addAvailable(L5);
        announcementRepository.addAvailable(L6);
        announcementRepository.addAvailable(L7);
        announcementRepository.addAvailable(L8);
        AnnouncementAvailable[] expected = {L1,L2,L3};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];
        List<AnnouncementAvailable> listfiltered = controller.getFilteredListing("Lease","",3);

        int position =0;
        for (AnnouncementAvailable lr : listfiltered){
            actual[position] = lr;
            position++;
        }

        assertArrayEquals(expected, actual);

    }


    @Test
    void ensureGetListingWorkAllParametersAllRooms() {
        announcementRepository.addAvailable(L1);
        announcementRepository.addAvailable(L2);
        announcementRepository.addAvailable(L3);
        announcementRepository.addAvailable(L4);
        announcementRepository.addAvailable(L5);
        announcementRepository.addAvailable(L6);
        announcementRepository.addAvailable(L7);
        announcementRepository.addAvailable(L8);

        AnnouncementAvailable[] expected = {L2,L3,L6};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];
        List<AnnouncementAvailable> listfiltered = controller.getFilteredListing("Lease","House",0);

        int position =0;
        for (AnnouncementAvailable lr : listfiltered){
            actual[position] = lr;
            position++;
        }

        assertArrayEquals(expected, actual);

    }

    @Test
    void ensureGetListingWorkAllParametersAllBusinessAllTypeAllRooms() {
        announcementRepository.addAvailable(L1);
        announcementRepository.addAvailable(L2);
        announcementRepository.addAvailable(L3);
        announcementRepository.addAvailable(L4);
        announcementRepository.addAvailable(L5);
        announcementRepository.addAvailable(L6);
        announcementRepository.addAvailable(L7);
        announcementRepository.addAvailable(L8);

        AnnouncementAvailable[] expected = {L1,L2,L3,L4,L5,L6,L7,L8};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];
        List<AnnouncementAvailable> listfiltered = controller.getFilteredListing("","",0);

        int position =0;
        for (AnnouncementAvailable lr : listfiltered){
            actual[position] = lr;
            position++;
        }

        assertArrayEquals(expected, actual);

    }

    @Test
    void ensureGetListingWorkAllParametersAllBusinessAllTypeA() {
        announcementRepository.addAvailable(L1);
        announcementRepository.addAvailable(L2);
        announcementRepository.addAvailable(L3);
        announcementRepository.addAvailable(L4);
        announcementRepository.addAvailable(L5);
        announcementRepository.addAvailable(L6);
        announcementRepository.addAvailable(L7);
        announcementRepository.addAvailable(L8);

        AnnouncementAvailable[] expected = {L1,L2,L3,L4,L8};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];
        List<AnnouncementAvailable> listfiltered = controller.getFilteredListing("","",3);

        int position =0;
        for (AnnouncementAvailable lr : listfiltered){
            actual[position] = lr;
            position++;
        }
        assertArrayEquals(expected, actual);

    }
    @Test
    void ensureGetListingWorkAllParametersAllTypeAllRooms() {
        announcementRepository.addAvailable(L1);
        announcementRepository.addAvailable(L2);
        announcementRepository.addAvailable(L3);
        announcementRepository.addAvailable(L4);
        announcementRepository.addAvailable(L5);
        announcementRepository.addAvailable(L6);
        announcementRepository.addAvailable(L7);
        announcementRepository.addAvailable(L8);

        AnnouncementAvailable[] expected = {L1,L2,L3,L5,L6};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];
        List<AnnouncementAvailable> listfiltered = controller.getFilteredListing("Lease","",0);

        int position =0;
        for (AnnouncementAvailable lr : listfiltered){
            actual[position] = lr;
            position++;
        }

        assertArrayEquals(expected, actual);

    }
@Test
    void ensureGetListingWorkAllParametersAllBusinessAllRooms() {
    announcementRepository.addAvailable(L1);
    announcementRepository.addAvailable(L2);
    announcementRepository.addAvailable(L3);
    announcementRepository.addAvailable(L4);
    announcementRepository.addAvailable(L5);
    announcementRepository.addAvailable(L6);
    announcementRepository.addAvailable(L7);
    announcementRepository.addAvailable(L8);

        AnnouncementAvailable[] expected = {L2,L3,L6,L8};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];
        List<AnnouncementAvailable> listfiltered = controller.getFilteredListing("","House",0);

            List<AnnouncementAvailable> list = controller.getListingAvailable();



        int position =0;
        for (AnnouncementAvailable lr : listfiltered){
            actual[position] = lr;
            position++;
        }


        assertArrayEquals(expected, actual);

    }



    @Test
    void ensureGetListingWorkEmptyProperty() {

        //Arrange
        //Get Repositories
        AnnouncementRepository announcementRepository = new AnnouncementRepository();


        //Fill Announcement Category Repository
        Property p1 = new Apartment("Porto", 70,1000,null,3, 3,1,null,new Owner());
        Property p2 = new House("Lisboa", 70,1000,null,3, 3,1,null,false,false,null,new Owner());
        Property p3 = new House("Lisboa", 70,1000,null,3, 3,1,null,false,false,null,new Owner());

        AnnouncementAvailable L1=new AnnouncementAvailable(commission,2,new Date(),"Sale",p1,null);
        AnnouncementAvailable L2=new AnnouncementAvailable(commission,1,new Date(),"Sale",p2,null);
        AnnouncementAvailable L3=new AnnouncementAvailable(commission,1,new Date(),"Lease",p3,null);

        announcementRepository.addAvailable(L1);
        announcementRepository.addAvailable(L2);
        announcementRepository.addAvailable(L3);


        AnnouncementController controller = new AnnouncementController(announcementRepository,authenticationRepository);

        //Act
        List<AnnouncementAvailable> listfiltered = controller.getFilteredListing("Lease","Apartment",3);


        assertTrue(listfiltered.isEmpty());
    }


    @Test
    void ensureGetAllListingWorkOrdered() {

        //Arrange
        //Get Repositories
        AnnouncementRepository announcementRepository = new AnnouncementRepository();


        //Fill Announcement Category Repository
        Property p1 = new Apartment("Aveiro", 70,1000,null,3, 3,1,null,new Owner());
        Property p2 = new House("Porto", 70,1000,null,3, 3,1,null,false,false,null,new Owner());
        Property p3 = new House("Lisboa", 70,1000,null,3, 3,1,null,false,false,null,new Owner());

        AnnouncementAvailable L1=new AnnouncementAvailable(commission,3,new Date(),"Sale",p1,new Agent());
        AnnouncementAvailable L2=new AnnouncementAvailable(commission,2,new Date(),"Sale",p2,new Agent());
        AnnouncementAvailable L3=new AnnouncementAvailable(commission,1,new Date(),"Lease",p3,new Agent());

        announcementRepository.addAvailable(L1);
        announcementRepository.addAvailable(L2);
        announcementRepository.addAvailable(L3);


        AnnouncementAvailable[] expected = {L3,L2,L1};


        AnnouncementController controller =
                new AnnouncementController(announcementRepository,authenticationRepository);


        //Act

        List<AnnouncementAvailable> listfiltered = controller.getFilteredListing("","",0);
        announcementRepository.sortByPriceAsc(listfiltered);

        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];
        int position =0;
        for (AnnouncementAvailable lr : listfiltered){
            actual[position] = lr;
            position++;
        }


        assertArrayEquals(expected, actual);
    }


    @Test
    void checkAnnouncementExistWorks() {
        controller.addAnnouncementAvailable(L1);
        controller.addAnnouncementAvailable(L2);
        controller.addAnnouncementAvailable(L3);
        controller.addAnnouncementAvailable(L4);
        controller.addAnnouncementAvailable(L5);
        controller.addAnnouncementAvailable(L6);
        controller.addAnnouncementAvailable(L7);
        controller.addAnnouncementAvailable(L8);

        assertTrue(controller.checkAnnouncementExists(L1.getId()));
        assertFalse(controller.checkAnnouncementExists(1000));

    }


    @Test
    void getAnnouncementByIdWorks() {
        controller.addAnnouncementAvailable(L1);
        controller.addAnnouncementAvailable(L2);
        controller.addAnnouncementAvailable(L3);
        controller.addAnnouncementAvailable(L4);
        controller.addAnnouncementAvailable(L5);
        controller.addAnnouncementAvailable(L6);
        controller.addAnnouncementAvailable(L7);
        controller.addAnnouncementAvailable(L8);

        AnnouncementMapper mapper = new AnnouncementMapper();

        assertEquals(L1.getId(),controller.getAnnouncementById(L1.getId()).get().getId());
        assertNotEquals(L1, mapper.toAnnouncementAvailable(controller.getAnnouncementById(L2.getId()).get()));


    }

    @Test
    void ensureCreateOrderWorks(){
        AnnouncementMapper mapper = new AnnouncementMapper();
        controller.addAnnouncementAvailable(L1);
        controller.addAnnouncementAvailable(L2);
        controller.addAnnouncementAvailable(L3);
        controller.addAnnouncementAvailable(L4);
        controller.addAnnouncementAvailable(L5);
        controller.addAnnouncementAvailable(L6);
        controller.addAnnouncementAvailable(L7);
        controller.addAnnouncementAvailable(L8);


        assertTrue(controller.createOrder(mapper.toAnnouncementDTO(L1) ,"user@this.app",120000).isEmpty());
        assertFalse(controller.createOrder(mapper.toAnnouncementDTO(L2) ,"user@this.app",1).isEmpty());

    }

    @Test
    public void testGetListingSoldnull() {

        AnnouncementRepository announcementRepo = new AnnouncementRepository();



        List<AnnouncementSold> result = announcementRepo.getAnnouncementsSold();


        assertNotNull(result);

    }

    public void testGetListingSold() {
        AnnouncementRepository announcementRepo = new AnnouncementRepository();

        List<AnnouncementSold> result = announcementRepo.getAnnouncementsSold();

        assertNotNull(result, "Result must not be null");
    }


    @Test
    void ensureAddAgencyListWorks(){
        AnnouncementController ctrl1 = new AnnouncementController();
        ctrl1.getAgencyList().clear();

        Agency a1 =new Agency("name","location",1,"email",null);
        ctrl1.addAgencyList(a1);

        Agency a2 =new Agency("name2","location2",11,"email2",null);
        ctrl1.addAgencyList(a2);

        List<Agency> expected = new ArrayList<>();
        expected.add(a1);
        expected.add(a2);

        assertEquals(expected,ctrl1.getAgencyList());
    }


    @Test
    void ensureAddAnnouncementSoldWorks(){
        AnnouncementController ctrl = new AnnouncementController();
        Apartment ap1 = new Apartment();
        AnnouncementSold sold = new AnnouncementSold(null,1,new Date(),"sale",ap1,1,new Date(),1,null);
        ctrl.addAnnouncementSold(sold);



        AnnouncementSold sold1 = new AnnouncementSold(null,2,new Date(),"sale",ap1,1,new Date(),1,null);
        AnnouncementSold sold2 = new AnnouncementSold(null,1,new Date(),"sale",ap1,1,new Date(),1,null);
        ctrl.addAnnouncementSold(sold1);
        ctrl.addAnnouncementSold(sold2);

        List<AnnouncementSold> expected = new ArrayList<>();
        expected.add(sold);
        expected.add(sold1);

        assertEquals(expected,ctrl.getListingSold());

    }





}
