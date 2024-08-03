package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgentMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.OwnerMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateRequestControllerIT {

    private AgentMapper mapper = new AgentMapper();

    @Test
    void ensureCreateRequestWorks() {

        //Arrange
        StoreManager storeManager1 = new StoreManager(111, "saa", 423, 222, "pwd", 13123, "Aaa", "gsdgsdg@mail");
        Owner owner1 = new Owner("vsvs", 141, 11, "vsdvs", "ffsfs", 1232112421);
        Agency agency1 = new Agency(111, "aaa", "bbb", 123456755, "acaca", storeManager1);
        Agency agency2 = new Agency(1121, "aaab", "bbab", 123456155, "acaca", storeManager1);
        Agent agent1 = new Agent(111, "testeAdd", 111111111, 11111, "teste", 11111, "afa@mail.com");
        //request house


        //request apartment
        RequestDTO DTOhouse = new RequestDTO();
        DTOhouse.setArea(111);
        DTOhouse.setLocation("aaa");
        DTOhouse.setDistanceFromCityCenter(321321);
        DTOhouse.setRequestedPrice(312312);
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        DTOhouse.setPhotographs(photos);
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


        RequestDTO DTOapartment = new RequestDTO();
        DTOapartment.setArea(111);
        DTOapartment.setLocation("aaa");
        DTOapartment.setDistanceFromCityCenter(321321);
        DTOapartment.setRequestedPrice(312312);
        photos.add("aaa.jpg");
        DTOapartment.setPhotographs(photos);
        DTOapartment.setAgent(agentMapper.toDTO(agent1));
        DTOapartment.setOwner(ownerMapper.toDTO(owner1));
        DTOapartment.setNumberBedrooms(2);
        DTOapartment.setNumberBathrooms(2);
        DTOapartment.setNumberParkingSpaces(1);
        DTOapartment.setEquipment("aaa");
        DTOapartment.setBasement(true);
        DTOapartment.setRequestType("Apartment");
        DTOapartment.setSunExposure("west");
        DTOapartment.setInhabitableLoft(true);
        DTOapartment.setTypeOfCommercialize("sell");

        RequestDTO DTOland = new RequestDTO();
        DTOland.setArea(111);
        DTOland.setLocation("aaa");
        DTOland.setDistanceFromCityCenter(321321);
        DTOland.setRequestedPrice(312312);
        photos.add("aaa.jpg");
        DTOland.setPhotographs(photos);
        DTOland.setAgent(agentMapper.toDTO(agent1));
        DTOland.setOwner(ownerMapper.toDTO(owner1));
        DTOland.setTypeOfCommercialize("sell");
        DTOland.setRequestType("Land");


        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        RequestRepository requestRepository = new RequestRepository();
        AgencyRepository agencyRepository = new AgencyRepository();


        //Fill Agency Repository
        agencyRepository.add(agency1);
        agencyRepository.add(agency2);


        CreateRequestController controller = new CreateRequestController(agencyRepository, requestRepository);


        assertTrue(controller.createAndSaveRequest(DTOhouse));
        assertTrue(controller.createAndSaveRequest(DTOapartment));
        assertTrue(controller.createAndSaveRequest(DTOland));
        // assertTrue(requestRepository.checkIfRequestExists());
        //  assertTrue(requestRepository.checkIfRequestExists(newRequest));
    }

}
