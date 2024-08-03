package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {
    List<String> photograph = new ArrayList<>();


    @Test
    void testEqualsSameObject() {
        Property property = new Property("Porto",20,100,null,null);
        assertEquals(property, property);
    }

    @Test
    void testNotEqualsDifferentClass() {
        Property property = new Property("Porto",20,100,null,null);
        Property property1 = new Apartment("Porto",20,100,null,3,1,1,null,null);

        assertNotEquals(property1, property);
    }

    @Test
    void testNotEqualsNull() {
        Property property = new Property("Porto",20,100,null,null);
        assertNotEquals(null, property);
    }


    @Test
    void testNotEqualsSameObjectDifferentValues() {

        photograph.add("foto.jpg");

        Property property1 = new Property("Porto",20,100,null,null);
        Property property2 = new Property("Porto",21,100, photograph,null);


        assertNotEquals(property1, property2);

    }


    @Test
    void testHashCodeSameObject() {
        Property property = new Property("Porto",20,100,null,null);
        assertEquals(property.hashCode(), property.hashCode());
    }


    @Test
    void testHashCodeSameObjectSameValues() {
        Property property1 = new Property("Porto",20,100,null,null);
        Property property2 = new Property("Porto",20,100,null,null);

        assertEquals(property1.hashCode(), property2.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Property property1 = new Apartment("Porto",20,100,null,3,1,1,null,null);
        Property property2 = new House("Porto",20,100,null,3,1,1,null,false,false,null,null);

        assertNotEquals(property1.hashCode(), property2.hashCode());
    }

    @Test
    void ensureHashCodeFailsForDifferentIdNumbers() {
        Property property1 = new Property("Porto",10,100,null,null);
        Property property2 = new Property("Porto",200,100,null,null);

        assertNotEquals(property1.hashCode(), property2.hashCode());
    }



    @Test
    void isPropertyTypeSameType() {
        Agent agent = new Agent("agente1", 111111111, 11111, "teste", 11111, "isep",  "fff@gmail.com");

        Property p1 = new Apartment("Porto",20,100,null,3,1,1,null,null);
        AnnouncementAvailable l1 = new AnnouncementAvailable(null,0,null,"Sale",p1,agent);

        assertTrue(l1.getProperty().isPropertyType("apartment"));

    }


    @Test
    void isPropertyTypeDifferentType() {
        Agent agent = new Agent("agente1", 111111111, 11111, "teste", 11111, "isep", "fff@gmail.com");

        Property p1 = new Apartment("Porto",20,100,null,3,1,1,null,null);
        AnnouncementAvailable l1 = new AnnouncementAvailable(null,0,null,"Sale",p1,agent);

        assertFalse(l1.getProperty().isPropertyType("Land"));
    }


    @Test
    void numberRoomsAlwaysReturnTrue() {
        Agent agent = new Agent("agente1", 111111111, 11111, "teste", 11111, "isep",  "fff@gmail.com");

        Property p1 = new Property("Porto", 70,1000,null,null);
        AnnouncementAvailable l1 = new AnnouncementAvailable(null,0,null,"Sale",p1,agent);

        assertTrue(l1.getProperty().numberRooms(4));
    }

    @Test
    void ensureCloneWorks() {
        List<String> photos = new ArrayList<>();

        Property p1 = new Property("Porto", 70,1000,photos,new Owner());
        Property clone = p1.clone();

        assertEquals(p1,clone);
    }



}