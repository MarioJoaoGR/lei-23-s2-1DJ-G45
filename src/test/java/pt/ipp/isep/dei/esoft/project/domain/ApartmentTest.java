package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentTest {

    @Test
    void ensureApartmentIsCreatedSuccessfully() {
        Apartment l1 = new Apartment(null,1,1,null,3,
                1,1,null,null);
    }

    @Test
    void ensureApartmentIsCreatedSuccessfullyByAnotherApartment() {
        Apartment a1 = new Apartment("Porto",100,100,null,3,
                1,1,null,null);
        Apartment a2 = new Apartment(a1);
    }


    @Test
    void testNumberRoomsEquals() {
        int rooms=1,propRooms=1;
        Apartment h1 = new Apartment("Porto",100,100,null,propRooms,
                propRooms,1,null,null);
        h1.numberRooms(rooms);
        assertTrue(h1.numberRooms(rooms));
    }

    @Test
    void testNumberRoomsDifferent() {
        int rooms=0,propRooms=1;

        Apartment h1 = new Apartment("Porto",100,100,null,propRooms,
                propRooms,1,null,null);
        assertFalse(h1.numberRooms(rooms));
    }

    @Test
    void ensureSameHashCodeForSameObject() {

        Apartment h1 = new Apartment("Porto",100,100,null,1,
                1,1,null,null);
        assertEquals(h1.hashCode(),h1.hashCode());
    }

    @Test
    void ensureSameHashCodeForDifferentObject() {

        Apartment h1 = new Apartment("Porto",100,100,null,1,
                1,1,null,null);
        Apartment h2 = new Apartment("Porto",100,100,null,1,
                1,1,null,null);
        assertEquals(h1.hashCode(),h2.hashCode());
    }

    @Test
    void ensureDifferentHashCodeForDifferentObject() {

        Apartment h1 = new Apartment("Porto",100,100,null,1,
                1,1,null,null);
        Apartment h2 = new Apartment("Lisboa",100,100,null,1,
                1,1,null,null);

        assertNotEquals(h1.hashCode(),h2.hashCode());
    }
}