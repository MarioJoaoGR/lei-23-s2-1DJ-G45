package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void ensureCreateOrderWorks(){
        Agent agent = new Agent("agente1", 111111111, 11111, "teste", 11111, "isep",  "fff@gmail.com");
        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable(null,10,null,"Sale",p1,agent);

        Order order = announcementAvailable.createOrder("user@this.app",120000);


    }

    @Test
    void testCloneEqualsSameObject() {
        Agent agent = new Agent("agente1", 111111111, 11111, "teste", 11111, "isep",  "fff@gmail.com");

        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable(null,10,null,"Sale",p1,agent);
        Order order = announcementAvailable.createOrder("user@this.app",120000);

        assertEquals(order,order.clone());

    }

    @Test
    void hashCodeEqualsForSameObject(){
        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        Agent agent = new Agent("agente1", 111111111, 11111, "teste", 11111, "isep",  "fff@gmail.com");
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable(null,10,null,"Sale",p1,agent);
        Order order = announcementAvailable.createOrder("user@this.app",120000);

        assertEquals(order.hashCode(),order.hashCode());

    }

    @Test
    void hashCodeNotEqualsForDifferentObject(){
        Agent agent = new Agent("agente1", 111111111, 11111, "teste", 11111, "isep", "fff@gmail.com");

        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable(null,10,null,"Sale",p1,agent);
        Order order = announcementAvailable.createOrder("user@this.app",120000);
        Order order2 = announcementAvailable.createOrder("user@this.app",223000);

        assertNotEquals(order.hashCode(),order2.hashCode());

    }
}