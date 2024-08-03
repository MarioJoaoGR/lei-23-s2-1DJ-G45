package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RequestLandTest {

    RequestLand r1;
    RequestLand r2;
    RequestLand r3;
    RequestLand r4;
    Agent agent1;
    Agent agent2;
    Agency agency1;
    Agency agency2;
    StoreManager storeManager1;
    StoreManager storeManager2;
    Owner owner1;
    Owner owner2;

    @BeforeEach
    void setUp() {
        owner1 = new Owner("vsvs", 141, 11, "vsdvs", "ffsfs", 1232112421);
        agency1 = new Agency("aaa", "bbb", 123456755, "acaca", storeManager1);
        agency2 = new Agency("aaab", "bbab", 123456155, "acaca", storeManager2);
        Agent agent1 = new Agent( "testeAdd", 111111111, 11111, "teste", 11111,  "afa@mail.com");
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        r1 = new RequestLand("Sell", agent1, 1000, "isep", 10000.42, 100.0, owner1,photos);
        r2 = new RequestLand("Rent", agent2, 1000, "isepa", 12000.42, 101.0, owner1,photos);
        r3 = new RequestLand("Sell", agent1, 1000, "isep", 10000.42, 100.0, owner1,photos);
        r4 = null;
    }

    @Test
    void ensureRequestLandIsCreatedSuccessfully() {
    }


    @Test
    void testNumberRoomsEqualsAndDifferent() {
        String landType = "land";
        String landType2 = "house";
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        r1 = new RequestLand("Sell", agent1, 1000, "isep", 10000.42, 100.0, owner1,photos);
        assertTrue(r1.getLandType().equalsIgnoreCase(landType));
        assertFalse(r1.getLandType().equalsIgnoreCase(landType2));
    }


    @Test
    void ensureSameHashCodeForSameObject() {


        assertEquals(r1.hashCode(), r1.hashCode());
    }

    @Test
    void ensureSameHashCodeForDifferentObject() {


        assertEquals(r1.hashCode(), r3.hashCode());
    }
    @Test
    void ensureCloneWorks() {
        Agent agent1 = new Agent( "teste", 111111111, 11111, "teste", 11111, "isep", "fff@gmail.com");
        StoreManager storeManager = new StoreManager("StoreManager",  111111111, 11111, "teste", 111111, "isep","mai@aa");
        Agency agency1 = new Agency("testeName", "testeAddress", 111111111, "teste@mail", storeManager);
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        RequestLand requestLand = new RequestLand("sell",agent1,11,"sa",141.312,411.414,owner1,photos);
        RequestLand clone;
        clone = requestLand.clone();
        assertEquals(requestLand, clone);
    }
    @Test
    void ensureDifferentHashCodeForDifferentObject() {


        assertNotEquals(r1.hashCode(), r2.hashCode());
    }
}
