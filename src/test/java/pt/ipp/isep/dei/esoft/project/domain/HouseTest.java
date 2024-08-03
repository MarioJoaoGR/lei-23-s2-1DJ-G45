package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {

    @Test
    void ensureHouseIsCreatedSuccessfully() {
        House h1 = new House(null,1,2,null,3,1,1,null,false,true,null,null);
    }

    @Test
    void ensureHouseIsCreatedSuccessfullyByAnotherHouse() {
        House h1 = new House(null,1,2,null,3,1,1,null,false,true,null,null);

        House h2= new House(h1);
    }

    @Test
    void testNumberRoomsEquals() {
        int rooms=1,propRooms=1;

        House h1 = new House(null,1,2,null,propRooms,1,1,null,false,true,null,null);

        assertTrue(h1.numberRooms(rooms));
    }

    @Test
    void testNumberRoomsDifferent() {
        int rooms=0,propRooms=1;

        House h1 = new House(null,1,2,null,propRooms,1,1,null,false,true,null,null);

        assertFalse(h1.numberRooms(rooms));
    }

    @Test
    void ensureSameHashCodeForSameObject() {

        House h1 = new House(null,1,2,null,1,1,1,null,false,true,null,null);

        assertEquals(h1.hashCode(),h1.hashCode());
    }



    @Test
    void ensureDifferentHashCodeForDifferentObject() {

        House h1 = new House(null,10,2,null,1,1,1,null,false,true,null,null);

        House h2 = new House("Leiria",1,2,null,1,1,1,null,false,true,null,null);


        assertNotEquals(h1.hashCode(),h2.hashCode());
    }
}