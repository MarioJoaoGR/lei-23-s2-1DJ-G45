package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AgencyRepositoryTest {




    @Test
    void ensureAddAgencyWorks(){

        AgencyRepository agencyRepository = new AgencyRepository();
        StoreManager storeManager = new StoreManager( 1,"aaa", 31, 414, "storepwd", 111, "aaaa","asdas@aa");
        Agency agency = new Agency( 1,"test", "testAddress", 123456789, "teste@mail", storeManager);

        agencyRepository.add(agency);

        assertTrue(agencyRepository.getAgencyList().contains(agency));

    }


    @Test
    void ensureGetAgencyByAgencyNameWorks(){
        AgencyRepository agencyRepository = new AgencyRepository();
        StoreManager storeManager = new StoreManager( 11,"aaa", 31, 414, "storepwd", 111, "aaaa","asdas@aa");
        Agency agency = new Agency( 111,"test", "testAddress", 123456789, "teste@mail", storeManager);
    }


    @Test
    void ensureGetOrganizationByEmployeeWorks() {
        AgencyRepository agencyRepository = new AgencyRepository();
        StoreManager storeManager = new StoreManager( 1,"aaa", 31, 414, "storepwd", 111, "aaaa","asdas@aa");
        Agency agency = new Agency( 2,"test", "testAddress", 123456789, "teste@mail", storeManager);
        agencyRepository.add(agency);

        Optional<Agency> result = agencyRepository.getAgencyByAgencyName("test");

        assertEquals(agency, result.get());
    }

    @Test
    void ensureGetOrganizationByEmployeeFails() {
        AgencyRepository agencyRepository = new AgencyRepository();
        StoreManager storeManager = new StoreManager( 3,"aaa", 31, 414, "storepwd", 111, "aaaa","asdas@aa");
        Agency agency = new Agency(4,"test", "testAddress", 123456789, "teste@mail", storeManager);
        agencyRepository.add(agency);

        Optional<Agency> result = agencyRepository.getAgencyByAgencyName("test22");

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureAddAgencyDuplicateFails() {
        AgencyRepository agencyRepository = new AgencyRepository();
        StoreManager storeManager = new StoreManager( 5,"aaa", 31, 414, "storepwd", 111, "aaaa","asdas@aa");
        Agency agency = new Agency( 6,"test", "testAddress", 123456789, "teste@mail", storeManager);
        agencyRepository.add(agency);

        Optional<Agency> result = agencyRepository.add(agency);

        assertTrue(result.isEmpty());

    }



    @Test
    void ensureGetRandomAgencyWorks() {
        AgencyRepository agencyRepository = new AgencyRepository();
        StoreManager storeManager = new StoreManager( 5,"aaa", 31, 414, "storepwd", 111, "aaaa","asdas@aa");
        Agency agency = new Agency( 6,"test", "testAddress", 123456789, "teste@mail", storeManager);
        Agency agency1 = new Agency( 6,"test1", "testAddress", 123456789, "teste@mail", storeManager);
        Agent agent = new Agent("agent@this.app","agent");
        agency.addAgent(agent);
        agencyRepository.add(agency);
        agencyRepository.add(agency1);

        for (int i =0;i<200;i++){
            Agency ag = agencyRepository.getRandomAgency();
            assertEquals(agency,ag);
        }


    }







}
