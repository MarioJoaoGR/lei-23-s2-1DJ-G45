package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTest {


    StoreManager storeManager1;

    @Test
    public void testEquals() {
        Owner owner1 = new Owner("John", 1111, 423432, "aaa", "dada", 4141414);
        Agency agency1 = new Agency( "aaab", "bbab", 123456155, "acaca", storeManager1);
        Agent agent1 = new Agent("tttt", 123456789, 1111, "aaa", 111, "aaa", "aaaa@af");
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        Request request1 = new Request("buy", agent1, 100.0, "location", 5.0, 100000.0, owner1,photos);
        Request request2 = new Request("buy", agent1, 100.0, "location", 5.0, 100000.0, owner1,photos);

        assertEquals(request1, request2);
    }

    @Test
    public void testNotEquals() {
        Owner owner1 = new Owner("John", 11121, 423432, "aaa", "dada", 4141414);
        Owner owner2 = new Owner("Josshn", 1111, 423432, "aaa", "dada", 4141414);
        Agency agency1 = new Agency( "aaab", "bbab", 123456155, "acaca", storeManager1);
        Agent agent1 = new Agent( "testeAdd", 111111112, 11111, "teste", 11111, "afa@mail.com");
        Agency agency2 = new Agency( "aaab", "bbab", 123456155, "acaca", storeManager1);
        Agent agent2 = new Agent( "testeAdd1", 111111111, 11111, "teste", 11111, "afa@mail.com");
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        Request request1 = new Request("buy", agent1, 100.0, "location", 5.0, 100000.0, owner1,photos);
        Request request2 = new Request("rent", agent1, 100.0, "location", 5.0, 100000.0, owner1,photos);
        Request request3 = new Request("buy", agent2, 100.0, "location", 5.0, 100000.0, owner1,photos);
        Request request4 = new Request("buy", agent1, 120.0, "location", 5.0, 10000.0, owner1,photos);
        Request request5 = new Request("buy", agent1, 200.0, "location", 5.0, 100000.0, owner1,photos);
        Request request6 = new Request("buy", agent1, 100.0, "location2", 5.0, 100000.0, owner1,photos);
        Request request7 = new Request("buy", agent1, 100.0, "location", 10.0, 100000.0, owner1,photos);
        Request request8 = new Request("buy", agent1, 100.0, "location", 5.0, 200000.0, owner1,photos);
        Request request9 = new Request("sale", agent1, 100.0, "location", 5.0, 100000.0, owner1,photos);
        Request request10 = new Request("buy", agent2, 100.0, "location", 5.0, 100000.0, owner2,photos);

        assertNotEquals(request1, request2);
        assertNotEquals(request1, request3);
        assertNotEquals(request1, request4);
        assertNotEquals(request1, request5);
        assertNotEquals(request1, request6);
        assertNotEquals(request1, request7);
        assertNotEquals(request1, request8);
        assertNotEquals(request1, request9);
        assertNotEquals(request1, request10);


    }



}

