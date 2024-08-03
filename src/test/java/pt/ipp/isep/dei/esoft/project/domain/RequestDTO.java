package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import org.mockito.internal.creation.SuspendMethod;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgentMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.OwnerMapper;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RequestDTO {



    @Test
    public void testAddPhotograph() {

        List<String> photos = new ArrayList<>(Arrays.asList("https://example.com/photo1.jpg", "https://example.com/photo2.jpg", "invalid_url"));
        List<String> expectedValidPhotos = Arrays.asList("https://example.com/photo1.jpg", "https://example.com/photo2.jpg");
        List<String> photos2 = new ArrayList<>(Arrays.asList("https://example.com/photo1.jpg", "https://example.com/photo2.jpg", "invalid_url","aaaa"));
        List<String> photos3 = new ArrayList<>(Arrays.asList("https://example.com/photo1.jpg", "https://example.com/photo2.jpg", "invalid_url","aaaa","https://example.com/photo1.jpg"));

        //Arrange
        StoreManager storeManager1 = new StoreManager(111, "saa", 423, 222, "pwd", 13123, "Aaa", "gsdgsdg@mail");
        Owner owner1 = new Owner("vsvs", 141, 11, "vsdvs", "ffsfs", 1232112421);
        Agency agency1 = new Agency(111, "aaa", "bbb", 123456755, "acaca", storeManager1);
        Agency agency2 = new Agency(1121, "aaab", "bbab", 123456155, "acaca", storeManager1);
        Agent agent1 = new Agent(111, "testeAdd", 111111111, 11111, "teste", 11111,  "afa@mail.com");
        //request house



        //request apartment
        pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO DTOhouse = new pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO();

        DTOhouse.setArea(111);
        DTOhouse.setLocation("aaa");
        DTOhouse.setDistanceFromCityCenter(321321);
        DTOhouse.setRequestedPrice(312312);

        //DTOhouse.setPhotographs(photos);



        List<String> newPhoto = new ArrayList<>();
        newPhoto.addAll(photos);
        newPhoto.add("teste.jp");


        AgentMapper agentMapper = new AgentMapper();
        DTOhouse.setAgent(agentMapper.toDTO(agent1));
        OwnerMapper ownerMapper = new OwnerMapper();
        DTOhouse.setOwner(ownerMapper.toDTO(owner1));
        DTOhouse.setNumberBedrooms(2);
        DTOhouse.setNumberBathrooms(2);
        DTOhouse.setNumberParkingSpaces(1);
        DTOhouse.setEquipment("aaa");
        DTOhouse.setBasement(true);
        DTOhouse.setRequestType("House");
        DTOhouse.setSunExposure("west");
        DTOhouse.setInhabitableLoft(true);
        DTOhouse.setTypeOfCommercialize("sell");
        //test to see if its present
        Optional<List<String>> result = DTOhouse.addPhotograph(newPhoto);


        assertTrue(result.isPresent());
        //test with 2 equal lists
        assertEquals(expectedValidPhotos, result.get());
        //test to see if not add invalid photos
        assertNotEquals(expectedValidPhotos,photos2);
        //test to see if not add repeated photos
        assertNotEquals(expectedValidPhotos,photos3);
    }


}

