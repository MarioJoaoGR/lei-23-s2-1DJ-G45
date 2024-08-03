package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgencyDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgentDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.OwnerDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgencyMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgentMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.RequestRepository;

import java.util.List;
import java.util.Optional;

/**
 * The type Create request controller.
 */
public class CreateRequestController {

    private AgencyRepository agencyRepository = null;
    private RequestRepository requestRepository = null;
    private AgentMapper agentMapper = new AgentMapper();
    private AgencyMapper agencyMapper = new AgencyMapper();

    /**
     * Instantiates a new Create request controller.
     */
    public CreateRequestController() {
        getAgencyRepository();
        getRequestRepository();
    }


    /**
     * Instantiates a new Create request controller.
     *
     * @param agencyRepository  the agency repository
     * @param requestRepository the request repository
     */
//created for test purposes
    public CreateRequestController(AgencyRepository agencyRepository, RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
        this.agencyRepository = agencyRepository;
    }

    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    private RequestRepository getRequestRepository() {
        if (requestRepository == null) {
            Repositories repositories = Repositories.getInstance();

            requestRepository = repositories.getRequestRepository();
        }
        return requestRepository;
    }

   /* public void addRequestPhotos(List<String> urls, Request request) {
        Optional<List<String>> nrPhotos = request.addPhotograph(urls);

        if (nrPhotos.isPresent()) {
            int count = countPhotos(nrPhotos);
            System.out.println("Added " + count + " photos to request.");
        } else {
            System.out.println("No valid photos were added to request.");
        }
    }
*/


    /**
     * Gets sorted agent.
     *
     * @param agencyChosedDTO the agency chosed dto
     * @return the sorted agent
     */
    public Optional<AgentDTO> getSortedAgent(AgencyDTO agencyChosedDTO) {

       return agencyChosedDTO.getRandomAgent();
    }

    /**
     * Gets sorted agency.
     *
     * @return the sorted agency
     */
    public AgencyDTO getSortedAgency() {

        return agencyMapper.toDTO(agencyRepository.getRandomAgency());

    }

    /**
     * Gets agencys from controller.
     *
     * @return the agencys from controller
     */
    public List<AgencyDTO> getAgencysFromController() {
        List<Agency> list = agencyRepository.getAgencyList();
        AgencyMapper agencyMapper = new AgencyMapper();
        return agencyMapper.toDTO(list);
    }


    /**
     * Gets agents from controller.
     *
     * @param agencyDTO the agency dto
     * @return the agents from controller
     */
    public List<AgentDTO> getAgentsFromController(AgencyDTO agencyDTO) {

        return agencyDTO.getAgentDTOList();


    }

    /**
     * Create and save request boolean.
     *
     * @param requestDto the request dto
     * @return the boolean
     */
    public boolean createAndSaveRequest(RequestDTO requestDto) {
        boolean flag = false;
        Optional<Request> result = requestRepository.createRequest(requestDto);
        if (result.isPresent()) {
            flag = true;
        }
        return flag;
    }





    /*public Request createAndSaveRequestForLand(String typeOfCommercial, AgentDTO agenteDTO, Double area, String location, Double distanceFromCityCentre, Double requestedPrice, List<String> photographs, Owner owner) {
        Agent agent = agentMapper.fromDTO(agenteDTO);
        RequestLand newRequest = new RequestLand(typeOfCommercial, agent, area, location, distanceFromCityCentre, requestedPrice, owner);

        Optional<Request> addedRequest = requestRepository.add(newRequest);


        if (addedRequest.isPresent()) {
            return addedRequest.get();
        } else {
            // Handle the case where the request was not added to the repository
            throw new RuntimeException("Failed to add request to repository.");
        }

    }*/

    /*public Request createAndSaveRequestForApartment(String typeOfCommercialize, AgentDTO agenteDTO, double area, String location, double distanceFromCityCentre, double requestedPrice, List<String> photographs, int numberBedrooms, int numberBathrooms,
                                                    int numberParkingSpaces, String availableEquipment, Owner owner) {
        Agent agente = agentMapper.fromDTO(agenteDTO);
        RequestApartment newRequest = new RequestApartment(typeOfCommercialize, agente, area, location, distanceFromCityCentre, requestedPrice, numberBedrooms, numberBathrooms, numberParkingSpaces, availableEquipment, owner);

        Optional<Request> addedRequest = requestRepository.add(newRequest);

        if (addedRequest.isPresent()) {
            return addedRequest.get();
        } else {
            // Handle the case where the request was not added to the repository
            throw new RuntimeException("Failed to add request to repository.");
        }
    }*/

    /**
     * Create owner optional.
     *
     * @param ownerDTO the owner dto
     * @return the optional
     */
    public Optional<Owner> createOwner(OwnerDTO ownerDTO) {
        try {
            Owner newOwner = new Owner(ownerDTO);
            return Optional.of(newOwner);
        } catch (Exception e) {
            System.err.println("Error creating owner: " + e.getMessage());
            return Optional.empty();
        }
    }


    /*public Request createAndSaveRequestForHouse(String typeOfCommercialize, AgentDTO agenteDTO, double area, String location, Double distanceFromCityCentre, Double requestedPrice,
                                                List<String> photographs, int numberBedrooms, int numberBathrooms, int numberParkingSpaces, String availableEquipment,
                                                boolean basement, boolean inhabitableLoft, String sunExposure, Owner owner) {
        Agent agente = agentMapper.fromDTO(agenteDTO);
        RequestHouse newRequest = new RequestHouse(typeOfCommercialize, agente, area, location, distanceFromCityCentre, requestedPrice, numberBedrooms, numberBathrooms, numberParkingSpaces, availableEquipment, basement, inhabitableLoft, sunExposure, owner);

        Optional<Request> addedRequest = requestRepository.add(newRequest);

        if (addedRequest.isPresent()) {
            return addedRequest.get();
        } else {
            // Handle the case where the request was not added to the repository
            throw new RuntimeException("Failed to add request to repository.");
        }

    }*/

    /**
     * Check if request is created boolean.
     *
     * @param request the request
     * @return the boolean
     */
    public boolean checkIfRequestIsCreated(Request request) {
        return !requestRepository.checkIfRequestExists(request);
    }


}

