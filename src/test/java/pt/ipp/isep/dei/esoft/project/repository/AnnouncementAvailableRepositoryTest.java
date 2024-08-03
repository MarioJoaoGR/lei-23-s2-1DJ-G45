package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.SortAlgorithms.BubbleSort;
import pt.ipp.isep.dei.esoft.project.domain.SortAlgorithms.MergeSort;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementAvailableRepositoryTest {



    @Test
    void getPropertyListFiltered() {
        String business="sale";
        String type="house";
        int rooms=3;
        List<AnnouncementAvailable> listfiltered = new ArrayList<>();
        List<String> photograph = new ArrayList<>();
        Property p1 = new Apartment("Porto", 70,1000,null,1, 3,1,null,null);
        Property p2 = new House("Lisboa", 70,1000,photograph,3, 3,1,null,false,false,null, null);
        Property p3 = new Apartment("Aveiro", 70,1000,photograph,1, 2,1,null,null);
        Property p4 = new Land("Algarve", 70,1000,photograph,null);
        Property p5 = new House("Lisboa", 70,1000,photograph,3, 3,1,null,false,false,null,null);
        Commission commission= new Commission(1,"fixed");

        Agent a1 = new Agent("agent1@mail.com", "Mario");
        Agent a2 = new Agent("agent2@mail.com", "Marioa");
        Agent a3 = new Agent("agent3@mail.com", "Mariof");
        Agent a4 = new Agent("agent4@mail.com", "Mariog");

        AnnouncementAvailable L1=new AnnouncementAvailable(commission,2,null,"Sale",p1,a1);
        AnnouncementAvailable L2=new AnnouncementAvailable(commission,1,null,"Sale",p2,a2);
        AnnouncementAvailable L3=new AnnouncementAvailable(commission,1,null,"Lease",p3,a3);
        AnnouncementAvailable L4=new AnnouncementAvailable(commission,2,null,"Sale",p4,a4);
        AnnouncementAvailable L5=new AnnouncementAvailable(commission,2,null,"Sale",p5,a1);

        AnnouncementRepository lr = new AnnouncementRepository();


        List<AnnouncementAvailable> listings = lr.getAnnouncementsAvailable();

        listings.add(L1);
        listings.add(L2);
        listings.add(L3);
        listings.add(L4);
        listings.add(L5);

        listfiltered=lr.getPropertyList(business,type,rooms);


        AnnouncementAvailable[] expected = {L2,L5};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[2];

        int position=0;
        for(AnnouncementAvailable l : listfiltered ){
            actual[position]=l;

            position++;
        }

        assertArrayEquals(expected,actual);

    }

    @Test
   void checkMethodForDivideStoresIsWorking(){
        AnnouncementRepository announcementRepository = new AnnouncementRepository();
        Owner owner = new Owner("user" , 1, 1, "address","user@this.app",1);
        List<String> photograph = new ArrayList<>();
        Property p1 = new Land("Porto", 1, 1, photograph, owner);
        Property p2 = new Apartment("Porto", 1, 1, photograph, 1, 1, 1, null, owner);
        Property p3 = new Apartment("Aveiro", 1, 1, photograph, 1, 1, 1, null, owner);
        Property p4 = new Apartment("Lisboa", 1, 1, photograph, 1, 1, 1, null, owner);
        Property p5 = new House("Lisboa", 1, 1, photograph, 1, 1, 1, null, false, false, null, owner);
        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
        StoreManager storeManager = new StoreManager("StoreManager", 111111111, 11111, "teste", 111111, "isep", "mais@aaa");
        Agency agency = new Agency("testeName", "testeAddress", 111111111, "teste@mail", storeManager);
        Agency agency2 = new Agency("testeNEWQame", "testeAdEQdress", 111111111, "testEQe@mail", storeManager);
        agencyRepository.add(agency);
        agencyRepository.add(agency2);
        Agent agent1 = new Agent("agente1", 111111111, 11111, "pwd", 11111, "isep", "agent@this.app");
        Agent agent2 = new Agent("agente2", 111211111, 11111, "pwd1", 11111, "isep", "agent1@this.app");
        Agent agent3 = new Agent("age32nte2", 111211111, 11111, "pwd2", 11111, "isep", "age32nt1@this.app");
        agency.addAgent(agent1);
        agency.addAgent(agent2);
        agency2.addAgent(agent3);
        Date date = new Date();
        Commission commission = new Commission(1,"percentage");
        AnnouncementSold l1 = new AnnouncementSold(commission, 5, date, "Lease", p2, 1,new Date(),1,agency);
        AnnouncementSold l2 = new AnnouncementSold(commission, 5, date, "Lease", p2, 1,new Date(),2,agency2);
        AnnouncementSold l3 = new AnnouncementSold(commission, 5, date, "Lease", p2, 1,new Date(),3,agency2);
        AnnouncementSold l4 = new AnnouncementSold(commission, 5, date, "Lease", p2, 1,new Date(),4,agency);
        AnnouncementSold l5 = new AnnouncementSold(commission, 5, date, "Lease", p2, 1,new Date(),5,agency);
        AnnouncementSold l6 = new AnnouncementSold(commission, 5, date, "Lease", p2, 1,new Date(),6,agency);

        announcementRepository.addSold(l1);
        announcementRepository.addSold(l2);
        announcementRepository.addSold(l3);
        announcementRepository.addSold(l4);
        announcementRepository.addSold(l5);
        announcementRepository.addSold(l6);

        assertEquals(2,announcementRepository.getNrPropertiesOfAnAgency(agency2));
        assertEquals(4,announcementRepository.getNrPropertiesOfAnAgency(agency));
    }






    @Test
    void getPropertyListFilteredLand() {
        String business="sale";
        String type="Land";
        int rooms=3;
        List<AnnouncementAvailable> listfiltered = new ArrayList<>();

        Property p1 = new Apartment("Porto", 70,1000,null,1, 3,1,null,null);
        Property p2 = new Land("Algarve", 70,1000,null,null);
        Property p3 = new Apartment("Aveiro", 70,1000,null,1, 2,1,null,null);
        Property p4 = new Land("Algarve", 70,1000,null,null);
        Property p5 = new Land("Algarve", 70,1000,null,null);

        AnnouncementAvailable L1=new AnnouncementAvailable(null,2,null,"Sale",p1,null);
        AnnouncementAvailable L2=new AnnouncementAvailable(null,1,null,"Sale",p2,null);
        AnnouncementAvailable L3=new AnnouncementAvailable(null,1,null,"Lease",p3,null);
        AnnouncementAvailable L4=new AnnouncementAvailable(null,2,null,"Sale",p4,null);
        AnnouncementAvailable L5=new AnnouncementAvailable(null,2,null,"Sale",p5,null);

        AnnouncementRepository lr = new AnnouncementRepository();
        List<AnnouncementAvailable> listings = lr.getAnnouncementsAvailable();

        listings.add(L1);
        listings.add(L2);
        listings.add(L3);
        listings.add(L4);
        listings.add(L5);

        listfiltered=lr.getPropertyList(business,type,rooms);


        AnnouncementAvailable[] expected = {L2,L4,L5};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];

        int position=0;
        for(AnnouncementAvailable l : listfiltered ){
            actual[position]=l;

            position++;
        }

        assertArrayEquals(expected,actual);



    }


    @Test
    void getPropertyListEmptySearch() {
        String business="";
        String type="";
        int rooms=0;

        List<AnnouncementAvailable> listfiltered = new ArrayList<>();

        Property p1 = new Apartment("Porto", 70,1000,null,1, 3,1,null,null);
        Property p2 = new House("Lisboa", 70,1000,null,2, 3,1,null,false,false,null,null);
        Property p3 = new Apartment("Aveiro", 70,1000,null,2, 2,1,null,null);
        Property p4 = new Land("Algarve", 70,1000,null,null);
        Property p5 = new House("Lisboa", 70,1000,null,1, 3,1,null,false,false,null,null);


        AnnouncementAvailable L1=new AnnouncementAvailable(null,2,null,"Sale",p1,null);
        AnnouncementAvailable L2=new AnnouncementAvailable(null,1,null,"Sale",p2,null);
        AnnouncementAvailable L3=new AnnouncementAvailable(null,1,null,"Lease",p3,null);
        AnnouncementAvailable L4=new AnnouncementAvailable(null,2,null,"Sale",p4,null);
        AnnouncementAvailable L5=new AnnouncementAvailable(null,2,null,"Sale",p5,null);

        AnnouncementRepository lr = new AnnouncementRepository();
        List<AnnouncementAvailable> listings = lr.getAnnouncementsAvailable();

        listings.add(L1);
        listings.add(L2);
        listings.add(L3);
        listings.add(L4);
        listings.add(L5);

        listfiltered=lr.getPropertyList(business,type,rooms);


        AnnouncementAvailable[] expected = {L1,L2,L3,L4,L5};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];

        int position=0;
        for(AnnouncementAvailable l : listfiltered ){
            actual[position]=l;

            position++;
        }

        assertArrayEquals(expected,actual);




    }



    @Test
    void sortByPriceAsc() {
        double price1=1.0;
        double price2=4.0;
        double price3=10.0;
        double price4=7.0;
        double price5=4.0;

        AnnouncementAvailable L1=new AnnouncementAvailable(null,price1,null,null,null,null);
        AnnouncementAvailable L2=new AnnouncementAvailable(null,price2,null,null,null,null);
        AnnouncementAvailable L3=new AnnouncementAvailable(null,price3,null,null,null,null);
        AnnouncementAvailable L4=new AnnouncementAvailable(null,price4,null,null,null,null);
        AnnouncementAvailable L5=new AnnouncementAvailable(null,price5,null,null,null,null);

        AnnouncementRepository lr = new AnnouncementRepository();

        List<AnnouncementAvailable> listings = lr.getAnnouncementsAvailable();
        listings.add(L1);
        listings.add(L2);
        listings.add(L3);
        listings.add(L4);
        listings.add(L5);

        lr.sortByPriceAsc(listings);


        AnnouncementAvailable[] expected = {L1,L2,L5,L4,L3};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[5];

        int position=0;
        for(AnnouncementAvailable l : listings ){
            actual[position]=l;
            position++;
        }

        assertArrayEquals(expected,actual);
    }


    @Test
    void sortByPriceDesc() {
        double price1=1.0;
        double price2=4.0;
        double price3=10.0;
        double price4=7.0;
        double price5=4.0;

        AnnouncementAvailable L1=new AnnouncementAvailable(null,price1,null,null,null,null);
        AnnouncementAvailable L2=new AnnouncementAvailable(null,price2,null,null,null,null);
        AnnouncementAvailable L3=new AnnouncementAvailable(null,price3,null,null,null,null);
        AnnouncementAvailable L4=new AnnouncementAvailable(null,price4,null,null,null,null);
        AnnouncementAvailable L5=new AnnouncementAvailable(null,price5,null,null,null,null);

        AnnouncementRepository lr = new AnnouncementRepository();

        List<AnnouncementAvailable> listings = lr.getAnnouncementsAvailable();
        listings.add(L1);
        listings.add(L2);
        listings.add(L3);
        listings.add(L4);
        listings.add(L5);

        lr.sortByPriceDesc(listings);


        AnnouncementAvailable[] expected = {L3,L4,L2,L5,L1};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[5];

        int position=0;
        for(AnnouncementAvailable l : listings ){
            actual[position]=l;
            position++;
        }

        assertArrayEquals(expected,actual);
    }


    @Test
    void sortByParish() {
        double price1=1.0;
        double price2=2.0;

        Property p1 = new Apartment("Porto",
                70,1000,null,3,
                1,1,null,null);

        Property p2 = new Apartment("Lisboa",
                70,1000,null,3,
                1,1,null,null);

        Property p3 = new Apartment("Evora",
                70,1000,null,3,
                1,1,null,null);

        Property p4 = new Apartment("Algarve",
                70,1000,null,2,
                1,1,null,null);

        AnnouncementAvailable L1=new AnnouncementAvailable(null,price1,null,null,p1,null);
        AnnouncementAvailable L2=new AnnouncementAvailable(null,price2,null,null,p2,null);
        AnnouncementAvailable L3=new AnnouncementAvailable(null,price2,null,null,p3,null);
        AnnouncementAvailable L4=new AnnouncementAvailable(null,price2,null,null,p4,null);

        AnnouncementRepository lr = new AnnouncementRepository();

        List<AnnouncementAvailable> listings = lr.getAnnouncementsAvailable();
        listings.add(L1);
        listings.add(L2);
        listings.add(L3);
        listings.add(L4);


        lr.sortByParish(listings);


        AnnouncementAvailable[] expected = {L4,L3,L2,L1};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];

        int position=0;
        for(AnnouncementAvailable l : listings ){
            actual[position]=l;
            position++;
        }

        assertArrayEquals(expected,actual);
    }



    @Test
    void sortByRecent() {


        Date d=new Date();

        Date date1 = new Date(d.getYear(), Calendar.AUGUST, 5);
        Date date2= new Date(d.getYear(), Calendar.JANUARY, 20);
        Date date3 = new Date(d.getYear(), Calendar.DECEMBER, 14);
        Date date4= new Date(d.getYear(), Calendar.MARCH, 13);
        Date date5 = new Date(d.getYear(), Calendar.AUGUST, 17);


        AnnouncementAvailable L1=new AnnouncementAvailable(null,1,date1,null,null,null);
        AnnouncementAvailable L2=new AnnouncementAvailable(null,1,date2,null,null,null);
        AnnouncementAvailable L3=new AnnouncementAvailable(null,0,date3,null,null,null);
        AnnouncementAvailable L4=new AnnouncementAvailable(null,0,date5,null,null,null);
        AnnouncementAvailable L5=new AnnouncementAvailable(null,0,date4,null,null,null);

        AnnouncementRepository lr = new AnnouncementRepository();

        List<AnnouncementAvailable> listings = lr.getAnnouncementsAvailable();
        listings.add(L1);
        listings.add(L2);
        listings.add(L3);
        listings.add(L4);
        listings.add(L5);

        lr.sortByRecent(listings);


        AnnouncementAvailable[] expected = {L3,L4,L1,L5,L2};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[5];

        int position=0;
        for(AnnouncementAvailable l : listings ){
            actual[position]=l;

            position++;
        }

        assertArrayEquals(expected,actual);


    }


    @Test
    void sortByRecentDateEmpty() {


        Date d=new Date();


        AnnouncementAvailable L1=new AnnouncementAvailable(null,1,d,null,null,null);
        AnnouncementAvailable L2=new AnnouncementAvailable(null,0,d,null,null,null);
        AnnouncementAvailable L3=new AnnouncementAvailable(null,0,d,null,null,null);
        AnnouncementAvailable L4=new AnnouncementAvailable(null,0,d,null,null,null);
        AnnouncementAvailable L5=new AnnouncementAvailable(null,0,d,null,null,null);

        AnnouncementRepository lr = new AnnouncementRepository();

        List<AnnouncementAvailable> listings = lr.getAnnouncementsAvailable();
        listings.add(L1);
        listings.add(L2);
        listings.add(L3);
        listings.add(L4);
        listings.add(L5);

        lr.sortByRecent(listings);


        AnnouncementAvailable[] expected = {L1,L2,L3,L4,L5};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[5];

        int position=0;
        for(AnnouncementAvailable l : listings ){
            actual[position]=l;

            position++;
        }

        assertArrayEquals(expected,actual);

    }

    @Test
    void getPropertyListFilteredAndOrderedAsc() {
        String business="sale";
        String type="house";
        int rooms=3;
        List<AnnouncementAvailable> listfiltered = new ArrayList<>();

        Property p1 = new Apartment("Porto", 70,1000,null,3, 3,1,null,null);
        Property p2 = new House("Lisboa", 70,1000,null,3, 3,1,null,false,false,null,null);
        Property p3 = new Apartment("Aveiro", 70,1000,null,2, 2,1,null,null);
        Property p4 = new House("Lisboa", 70,1000,null,3, 3,1,null,true,false,null,null);
        Property p5 = new House("Lisboa", 70,1000,null,3, 3,1,null,false,false,null,null);

        AnnouncementAvailable L1=new AnnouncementAvailable(null,2,null,"Sale",p1,null);
        AnnouncementAvailable L2=new AnnouncementAvailable(null,100,null,"Sale",p2,null);
        AnnouncementAvailable L3=new AnnouncementAvailable(null,1,null,"Lease",p3,null);
        AnnouncementAvailable L4=new AnnouncementAvailable(null,2000,null,"Sale",p4,null);
        AnnouncementAvailable L5=new AnnouncementAvailable(null,20,null,"Sale",p5,null);

        AnnouncementRepository lr = new AnnouncementRepository();
        List<AnnouncementAvailable> listings = lr.getAnnouncementsAvailable();

        listings.add(L1);
        listings.add(L2);
        listings.add(L3);
        listings.add(L4);
        listings.add(L5);

        lr.sortByPriceAsc(listings);
        listfiltered=lr.getPropertyList(business,type,rooms);



        AnnouncementAvailable[] expected = {L5,L2,L4};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];

        int position=0;
        for(AnnouncementAvailable l : listfiltered ){
            actual[position]=l;
            position++;
        }

        assertArrayEquals(expected,actual);



    }


    @Test
    void getPropertyListFilteredAndOrderedDesc() {
        String business="sale";
        String type="house";
        int rooms=3;
        List<AnnouncementAvailable> listfiltered = new ArrayList<>();

        Property p1 = new Apartment("Porto", 70,1000,null,1, 3,1,null,null);
        Property p2 = new House("Lisboa", 70,1000,null,3, 3,1,null,false,false,null,null);
        Property p3 = new Apartment("Aveiro", 70,1000,null,2, 2,1,null,null);
        Property p4 = new House("Lisboa", 70,1000,null,3, 3,1,null,true,false,null,null);
        Property p5 = new House("Lisboa", 70,1000,null,3, 3,1,null,false,false,null,null);

        AnnouncementAvailable L1=new AnnouncementAvailable(null,2,null,"Sale",p1,null);
        AnnouncementAvailable L2=new AnnouncementAvailable(null,1,null,"Sale",p2,null);
        AnnouncementAvailable L3=new AnnouncementAvailable(null,1,null,"Lease",p3,null);
        AnnouncementAvailable L4=new AnnouncementAvailable(null,5,null,"Sale",p4,null);
        AnnouncementAvailable L5=new AnnouncementAvailable(null,4,null,"Sale",p5,null);

        AnnouncementRepository lr = new AnnouncementRepository();
        List<AnnouncementAvailable> listings = lr.getAnnouncementsAvailable();

        listings.add(L1);
        listings.add(L2);
        listings.add(L3);
        listings.add(L4);
        listings.add(L5);

        lr.sortByPriceDesc(listings);
        listfiltered=lr.getPropertyList(business,type,rooms);



        AnnouncementAvailable[] expected = {L4,L5,L2};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];

        int position=0;
        for(AnnouncementAvailable l : listfiltered ){
            actual[position]=l;
            //System.out.println(l.getId());
            position++;
        }

        assertArrayEquals(expected,actual);



    }


    @Test
    void getPropertyListFilteredAndOrderedRecent() {
        String business="sale";
        String type="house";
        int rooms=3;
        List<AnnouncementAvailable> listfiltered = new ArrayList<>();

        Date d = new Date();
        Date date1 = new Date(d.getYear(), Calendar.AUGUST, 5);
        Date date2= new Date(d.getYear(), Calendar.JANUARY, 20);
        Date date3 = new Date(d.getYear(), Calendar.DECEMBER, 14);
        Date date4= new Date(d.getYear(), Calendar.MARCH, 13);
        Date date5 = new Date(d.getYear(), Calendar.AUGUST, 17);


        Property p1 = new Apartment("Porto", 70,1000,null,3, 3,1,null,null);
        Property p2 = new House("Lisboa", 70,1000,null,3, 3,1,null,false,false,null,null);
        Property p3 = new Apartment("Aveiro", 70,1000,null,2, 2,1,null,null);
        Property p4 = new House("Lisboa", 70,1000,null,3, 3,1,null,true,false,null,null);
        Property p5 = new House("Lisboa", 70,1000,null,3, 3,1,null,false,false,null,null);

        AnnouncementAvailable L1=new AnnouncementAvailable(null,2,date1,"Sale",p1,null);
        AnnouncementAvailable L2=new AnnouncementAvailable(null,1,date2,"Sale",p2,null);
        AnnouncementAvailable L3=new AnnouncementAvailable(null,1,date3,"Lease",p3,null);
        AnnouncementAvailable L4=new AnnouncementAvailable(null,2,date4,"Sale",p4,null);
        AnnouncementAvailable L5=new AnnouncementAvailable(null,2,date5,"Sale",p5,null);

        AnnouncementRepository lr = new AnnouncementRepository();
        List<AnnouncementAvailable> listings = lr.getAnnouncementsAvailable();

        listings.add(L1);
        listings.add(L2);
        listings.add(L3);
        listings.add(L4);
        listings.add(L5);

        lr.sortByRecent(listings);
        listfiltered=lr.getPropertyList(business,type,rooms);



        AnnouncementAvailable[] expected = {L5,L4,L2};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];

        int position=0;
        for(AnnouncementAvailable l : listfiltered ){
            actual[position]=l;
            //System.out.println(l.getId());
            position++;
        }

        assertArrayEquals(expected,actual);



    }


    @Test
    void getPropertyListFilteredAndOrderedParish() {
        String business="sale";
        String type="house";
        int rooms=3;
        List<AnnouncementAvailable> listfiltered = new ArrayList<>();



        Property p1 = new Apartment("Porto", 70,1000,null,3, 3,1,null,null);
        Property p2 = new House("Lisboa", 70,1000,null,3, 3,1,null,false,false,null,null);
        Property p3 = new Apartment("Aveiro", 70,1000,null,2, 2,1,null,null);
        Property p4 = new House("Aveiro", 70,1000,null,3, 3,1,null,true,false,null,null);
        Property p5 = new House("Porto", 70,1000,null,3, 3,1,null,false,false,null,null);

        AnnouncementAvailable L1=new AnnouncementAvailable(null,2,null,"Sale",p1,null);
        AnnouncementAvailable L2=new AnnouncementAvailable(null,1,null,"Sale",p2,null);
        AnnouncementAvailable L3=new AnnouncementAvailable(null,1,null,"Lease",p3,null);
        AnnouncementAvailable L4=new AnnouncementAvailable(null,2,null,"Sale",p4,null);
        AnnouncementAvailable L5=new AnnouncementAvailable(null,2,null,"Sale",p5,null);

        AnnouncementRepository lr = new AnnouncementRepository();
        List<AnnouncementAvailable> listings = lr.getAnnouncementsAvailable();

        listings.add(L1);
        listings.add(L2);
        listings.add(L3);
        listings.add(L4);
        listings.add(L5);

        lr.sortByParish(listings);
        listfiltered=lr.getPropertyList(business,type,rooms);



        AnnouncementAvailable[] expected = {L4,L2,L5};
        AnnouncementAvailable[] actual = new AnnouncementAvailable[expected.length];

        int position=0;
        for(AnnouncementAvailable l : listfiltered ){
            actual[position]=l;
            position++;
        }

        assertArrayEquals(expected,actual);



    }


    @Test
    void ensureGetAnnouncementByIdIsWorking(){
        Property p1 = new Apartment("Porto", 70,1000,null,3, 3,1,null,null);
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable(null,3213,new Date(),"rent",p1,null);
        AnnouncementRepository announcementRepository = new AnnouncementRepository();

        announcementRepository.addAvailable(announcementAvailable);
        assertTrue(announcementRepository.getAnnouncementById(announcementAvailable.getId()).isPresent());

        assertFalse(announcementRepository.getAnnouncementById(announcementAvailable.getId()+1).isPresent());



    }


    @Test
    void getVisitRequestsForPeriod() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String dateString = "31-05-2023";
        Date start = dateFormat.parse(dateString);

        String endString = "02-06-2023";
        Date end = dateFormat.parse(endString);

        Agent agent = new Agent("agent",11111,1111,"pwd",111,"agent@this.app");
        Property p1 = new Apartment("Porto", 70,1000,null,3, 3,1,null,new Owner());

        VisitRequest visitRequest = new VisitRequest("01-06-2023", null, "teste", "agent@this.app", new Agent());
        VisitRequest visitRequest1 = new VisitRequest("02-06-2023",null, "teste", "agent@this.app", new Agent());
        VisitRequest visitRequest2 = new VisitRequest("13-06-2023",null, "teste", "agent@this.app", new Agent());

        List<VisitRequest> visitList = new ArrayList<>();
        visitList.add(visitRequest);
        visitList.add(visitRequest1);
        visitList.add(visitRequest2);
        AnnouncementAvailable A1=new AnnouncementAvailable(new Commission(1,"percentage"),2,new Date(),visitList,"Sale",p1,agent);


        A1.addVisitRequests(visitRequest);

        AnnouncementRepository announcementRepository = new AnnouncementRepository();
        announcementRepository.addAvailable(A1);


        List<VisitRequest> actual = announcementRepository.getVisitRequestListForAgent(agent.getMail(),start,end);
        List<VisitRequest> expected = new ArrayList<>();
        expected.add(visitRequest);
        expected.add(visitRequest1);

        assertEquals(expected,actual);
    }


    @Test
    void getVisitRequestsSorted(){
        String sortingAlgorithm = null;
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            sortingAlgorithm = prop.getProperty("sorting_algorithm");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        AnnouncementController controller = new AnnouncementController();


        Agent agent = new Agent("agent", 11111, 1111, "pwd", 111, "agent@this.app");
        Property p1 = new Apartment("Porto", 70, 1000, null, 3, 3, 1, null, null);

        VisitRequest visitRequest = new VisitRequest("13-06-2023", null, "teste", "agent@this.app", null);
        VisitRequest visitRequest1 = new VisitRequest("07-06-2023", null, "teste", "agent@this.app", null);
        VisitRequest visitRequest2 = new VisitRequest("09-06-2023", null, "teste", "agent@this.app", null);

        List<VisitRequest> visitList = new ArrayList<>();
        visitList.add(visitRequest);
        visitList.add(visitRequest1);
        visitList.add(visitRequest2);
        AnnouncementAvailable A1 = new AnnouncementAvailable(null, 2, null, visitList, "Sale", p1, agent);

        controller.addAnnouncementAvailable(A1);

        A1.addVisitRequests(visitRequest);

        AnnouncementRepository announcementRepository = new AnnouncementRepository();
        announcementRepository.addAvailable(A1);


        List<VisitRequest> actual = announcementRepository.getVisitRequest();
        if (sortingAlgorithm.equalsIgnoreCase("bubble_sort")) {
            BubbleSort bubbleSort = new BubbleSort();
            bubbleSort.sort(actual);
        } else if (sortingAlgorithm.equalsIgnoreCase("merge_sort")) {
            MergeSort mergeSort = new MergeSort();
            mergeSort.sort(actual);
        }

        List<VisitRequest> expected = new ArrayList<>();
        expected.add(visitRequest1);
        expected.add(visitRequest2);
        expected.add(visitRequest);

        assertEquals(expected, actual);
    }
}