package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RequestHouseTest {


    RequestHouse r1;
    RequestHouse r2;
    RequestHouse r3;
    RequestHouse r4;
    RequestHouse r6;
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
        agency1 = new Agency( "aaa", "bbb", 123456755, "acaca", storeManager1);
        agency2 = new Agency( "aaab", "bbab", 123456155, "acaca", storeManager2);
        Agent agent1 = new Agent( "testeAdd", 111111111, 11111, "teste", 11111,"afa@mail.com");
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        r1 = new RequestHouse("sale", agent1, 10231.0, "vsfa", 113.213, 31312.321, 1,2,1,"aa",true,false,"north",owner2,photos);
        r2 = new RequestHouse("sale", agent2, 10231.0, "vsfa", 113.213, 31312.321, 1,2,1,"aa",  true,false,"north",owner2,photos);
        r3 = new RequestHouse("sale", agent1, 10231.0, "vsfa", 113.213, 31312.321, 1,2,1,"aa",true,false,"north",owner2,photos);
        r4 = new RequestHouse("sale", agent2, 12231.0, "vfafa", 11312.213, 21312.321, 1,2,1,"aa",true,false,"north",owner2,photos);
        r6 = null;


    }

    @Test
    void ensureRequestLandIsCreatedSuccessfully() {
    }


    @Test
    void testLandTypeEqualsAndDifferent() {
        String landType = "land";
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");

        r6 = new RequestHouse("sale", agent1, 10231.0, "vsfa", 113.213, 31312.321, 2,1,3,"aa",false,true,"north",owner2,photos);

        String landType2 = "house";

        assertTrue(r6.getLandType().equalsIgnoreCase(landType2));
        assertFalse(r6.getLandType().equalsIgnoreCase(landType));
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
    void ensureDifferentHashCodeForDifferentObject() {



        assertNotEquals(r1.hashCode(), r2.hashCode());
    }
}






