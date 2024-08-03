package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoriesTest {

    @Test
    void testGetInstance() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance);
    }

    @Test
    void testGetOrganizationRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getOrganizationRepository());
    }

    @Test
    void testGetTaskCategoryRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getTaskCategoryRepository());
    }

    @Test
    void testGetAnnouncementRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getAnnouncementRepository());
    }


    @Test
    void testGetRequestRepository(){
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getAnnouncementRepository());
    }

    @Test
    void testGetAgencyRepository(){
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getAnnouncementRepository());
    }



}