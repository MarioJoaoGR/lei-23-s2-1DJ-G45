package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RequestApartmentTest {


    RequestApartment r1;
    RequestApartment r2;
    RequestApartment r3;
    RequestApartment r4;
    RequestApartment r6;
    Agent agent1;
    Agent agent2;
    Agency agency1;
    Agency agency2;
    StoreManager storeManager1;
    StoreManager storeManager2;
    Owner owner1;
    Owner owner2 = new Owner();


    @BeforeEach
    void setUp() {


        owner1 = new Owner("vsvs", 141, 11, "vsdvs", "ffsfs", 1232112421);
        agency1 = new Agency("aaa", "bbb", 123456755, "acaca", storeManager1);
        agency2 = new Agency("aaab", "bbab", 123456155, "acaca", storeManager2);
        Agent agent1 = new Agent("testeAdd", 111111111, 11111, "teste", 11111,  "afa@mail.com");
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        r1 = new RequestApartment("sale", agent1, 10231.0, "vsfa", 113.213, 31312.321, 2, 1, 3, "aa", owner2,photos);
        r2 = new RequestApartment("sale", agent2, 10231.0, "vsfa", 113.213, 31312.321, 2, 1, 3, "aa", owner2,photos);
        r3 = new RequestApartment("sale", agent1, 10231.0, "vsfa", 113.213, 31312.321, 2, 1, 3, "aa", owner2,photos);
        r4 = new RequestApartment("sale", agent2, 12231.0, "vfafa", 11312.213, 21312.321, 2, 1, 3, "aa", owner2,photos);
        r6 = null;


    }

    @Test
    void ensureRequestLandIsCreatedSuccessfully() {
    }

    @Test
    void ensureSameHashCodeForSameObject() {


        assertEquals(r1.hashCode(), r1.hashCode());
    }

    @Test
    void testLandTypeEqualsAndDifferent() {
        String landType = "land";
        String landType2 = "apartment";
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        r6 = new RequestApartment("sale", agent1, 10231.0, "vsfa", 113.213, 31312.321, 2, 1, 3, "aa", owner2,photos);
        assertTrue(r6.getLandType().equalsIgnoreCase(landType2));
        assertFalse(r6.getLandType().equalsIgnoreCase((landType)));
    }

    @Test
    void ensureSameHashCodeForDifferentObject() {


        assertEquals(r1.hashCode(), r3.hashCode());
    }

    @Test
    void ensureDifferentHashCodeForDifferentObject() {


        assertNotEquals(r1.hashCode(), r4.hashCode());
    }


    @Test
    void ensureCloneWorks() {
        Agent agent1 = new Agent("teste", 111111111, 11111, "teste", 11111, "isep",  "fff@gmail.com");
        StoreManager storeManager = new StoreManager("StoreManager", 111111111, 11111, "teste", 111111, "isep", "mai@aa");
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        RequestApartment requestApartment = new RequestApartment("sell", agent1, 11, "sa", 141.312, 411.414,2,1,2,"aa",owner2,photos);
        RequestApartment clone = requestApartment.clone();
        assertEquals(requestApartment, clone);
    }
}