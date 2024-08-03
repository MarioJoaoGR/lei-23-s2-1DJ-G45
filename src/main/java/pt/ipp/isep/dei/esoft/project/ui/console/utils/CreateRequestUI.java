package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgencyDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgentDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.OwnerDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgencyMapper;
import pt.ipp.isep.dei.esoft.project.ui.Main;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * The type Create request ui.
 */
public class CreateRequestUI implements Runnable {


    /**
     * The constant ADDRESS_BY_OMISSION.
     */
    public static final String ADDRESS_BY_OMISSION = "No address";
    private final CreateRequestController controller = new CreateRequestController();


    //type of choice, choose agent or not
    private boolean choiceOrNoAgent;

    private CreateRequestController getController() {
        return controller;
    }

    public void run() {
        System.out.println("Create Request");

        OwnerDTO ownerDTO = requestOwnerData();
        ;
        RequestDTO requestDto = requestData();
        submitData(requestDto, ownerDTO);
    }

    private void submitData(RequestDTO requestDTO, OwnerDTO ownerDTO) {
        Optional<Owner> newOwner = getController().createOwner(ownerDTO);
        if (newOwner.isPresent()) {
            requestDTO.setOwner(ownerDTO);
            System.out.println("Owner created with success");
        } else {
            requestData();
        }

        boolean result = controller.createAndSaveRequest(requestDTO);
        String mensagem;
        if (result) {
            mensagem = "Success";
        } else {
            mensagem = "Fail";
        }
        System.out.println("Operation result = " + mensagem);
    }


    private RequestDTO requestData() {
        //requestOwnerData();
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setRequestType(requestLandType());
        requestDTO.setArea(Utils.readDoubleFromConsole("Area(m²)"));
        requestDTO.setLocation(Utils.readLineFromConsole("Location"));
        requestDTO.setDistanceFromCityCenter(Utils.readDoubleFromConsole("Distance from city center(m)"));
        requestDTO.setRequestedPrice(Utils.readDoubleFromConsole("Price(Usd)"));
        requestDTO.setPhotographs(requestStringPhotographs());
        requestDTO.setTypeOfCommercialize(requestTypeOfComercial());
        String landType = requestDTO.getRequestType();
        switch (landType) {
            case "House":
                requestDTO.setNumberBedrooms(Utils.readIntegerFromConsole("Number Bedrooms:"));
                requestDTO.setNumberBathrooms(Utils.readIntegerFromConsole("Number Bathrooms:"));
                requestDTO.setNumberParkingSpaces(Utils.readIntegerFromConsole("Number Parking Spaces:"));
                requestDTO.setBasement(requestBasement());
                requestDTO.setInhabitableLoft(requestInhabitableLoft());
                requestDTO.setSunExposure(requestSunExposure());
                requestDTO.setEquipment(Utils.readLineFromConsole("Equipment"));
                break;

            case "Apartment":
                requestDTO.setNumberBedrooms(Utils.readIntegerFromConsole("Number Bedrooms:"));
                requestDTO.setNumberBathrooms(Utils.readIntegerFromConsole("Number Bathrooms:"));
                requestDTO.setNumberParkingSpaces(Utils.readIntegerFromConsole("Number Parking Spaces:"));
                requestDTO.setEquipment(Utils.readLineFromConsole("Equipment"));
                break;
            default:
                break;
        }
        choiceOrNoAgent = requestsChooseOrNoAgent();
        AgencyDTO agency;
        //----------------
        AgentDTO agent;
        if (choiceOrNoAgent) {
            agency = displayAndSelectAgency();
            agent = displayAndSelectAgent(agency);
            requestDTO.setAgent(agent);
        } else {
            agency = controller.getSortedAgency();
            Optional<AgentDTO> agentDTO = controller.getSortedAgent(agency);
            if (agentDTO.isPresent()) {
                requestDTO.setAgent(agentDTO.get());
            } else {
                System.out.println("Error submiting data try again");
            }
        }

        return requestDTO;
    }

    private OwnerDTO requestOwnerData() {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setName(Utils.requestName());
        ownerDTO.setCardNumber(Utils.requestCardNumber());
        ownerDTO.setPhoneNumber(Utils.requestPhoneNumber());
        ownerDTO.setTaxNumber(Utils.requestTaxNumber());
        ownerDTO.setEmail(Utils.requestEmail());
        String address = Utils.requestAddress();
        if (!address.equals(ADDRESS_BY_OMISSION)) {
            ownerDTO.setAddress(address);
        }
        return ownerDTO;
    }


    private boolean requestsChooseOrNoAgent() {
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.println("Do you want to choose the Agent for you Request?");
        System.out.println("1-Yes");
        System.out.println("2-No");
        do {
            choice = input.nextInt();
        } while (choice > 2 || choice <= 0);
        return Utils.booleanMethodsReturn(choice);

    }

    private String requestTypeOfComercial() {

        Scanner input = new Scanner(System.in);
        int choice;
        String typeOfComercial;
        System.out.println("Do you want to rent or sell the property?");
        System.out.println("1-Sell");
        System.out.println("2-Rent");
        System.out.println();
        System.out.println("Choice:");
        do {
            choice = input.nextInt();
        } while (choice > 2 || choice <= 0);
        if (choice == 1) {
            typeOfComercial = "sell";
        } else {
            typeOfComercial = "rent";
        }
        return typeOfComercial;


    }


    private List<String> requestStringPhotographs() {

        Scanner scanner = new Scanner(System.in);
        List<String> photoUrls = new ArrayList<>();
        int count = 0;
        do {
            count = Utils.readIntegerFromConsole("Enter the number of photo URLs (1-30): ");
        } while (count < 1 || count > 30);

        for (int i = 0; i < count; i++) {
            String photoUrl;
            do {
                System.out.print("Enter photo URL(jpg.) #" + (i + 1) + ": ");
                photoUrl = scanner.nextLine();
                if (!isValidPhotoUrl(photoUrl)) {
                    System.out.println("Invalid URL try again");
                }
            } while (!isValidPhotoUrl(photoUrl));

            photoUrls.add(photoUrl);
        }
        return photoUrls;
    }

    /**
     * Is valid photo url boolean.
     *
     * @param url the url
     * @return the boolean
     */
    public static boolean isValidPhotoUrl(String url) {
        return url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".png") || url.endsWith(".gif");
    }


    private String requestLandType() {
        int choice;
        System.out.println("What type is your property");
        List<String> landTypes = PropertyType.getAllFields();
        int option = 0;
        for (String landType1 : landTypes) {
            option++;
            System.out.println(option + "-" + landType1);
        }
        do {
            choice = Utils.readIntegerFromConsole("Choice:");
            if (choice > option || choice <= 0) {
                System.out.println("Invalid choice try again");
            }
        } while (choice > option || choice <= 0);

        return landTypes.get(choice - 1);
    }

    private boolean requestBasement() {
        Scanner input = new Scanner(System.in);
        System.out.println("Basement(Yes/No):");
        System.out.println("1-Yes");
        System.out.println("2-No");
        int option;
        do {
            option = Utils.readIntegerFromConsole("Choice:");
        } while (option <= 0 || option > 2);
        return Utils.booleanMethodsReturn(option);
    }

    private boolean requestInhabitableLoft() {
        int option;
        System.out.println("Inhabitable loft(Yes/No):");
        System.out.println("1-Yes");
        System.out.println("2-No");
        do {
            option = Utils.readIntegerFromConsole("Choice:");
        } while (option > 2 || option <= 0);
        return Utils.booleanMethodsReturn(option);
    }

    private String requestSunExposure() {


        int option;
        System.out.println("Sun Exposure");
        System.out.println("1-east");
        System.out.println("2-north");
        System.out.println("3-south");
        System.out.println("4-west");
        System.out.println("5-none");
        do {
            option = Utils.readIntegerFromConsole("Choice:");
            if (option <= 0 || option > 5) {
                System.out.println("Invalid option, try again");
            }
        } while (option <= 0 || option > 5);
        String returnString;
        switch (option) {
            case 1:
                returnString = "east";
                break;
            case 2:
                returnString = "north";
                break;
            case 3:
                returnString = "south";
                break;
            case 4:
                returnString = "west";
                break;
            default:
                returnString = "none";
                break;
        }
        return returnString;
    }

    private AgencyDTO displayAndSelectAgency() {
        //Display the list of agencies
        List<AgencyDTO> agencias = controller.getAgencysFromController();
        int listSize = agencias.size();
        int answer = -1;
        while (answer < 1 || answer > listSize) {
            System.out.println("Select an agency:");
            displayAgencyOptions(agencias);
            answer = Utils.readIntegerFromConsole("Choice:");

            if (answer < 1 || answer > listSize) {
                System.out.println("Choose a valid Agency Number");
            }
        }

        return agencias.get(answer - 1);

    }

    private void displayAgentOptions(AgencyDTO agency) {
        //display the Agent options as a menu with number options to select
        List<AgentDTO> agentesList = controller.getAgentsFromController(agency);
        int i = 1;
        for (AgentDTO a : agentesList) {
            System.out.println(i + " - " + a.getName());
            i++;
        }

    }

    /**
     * Display agency options.
     *
     * @param agents the agents
     */
    public static void displayAgencyOptions(List<AgencyDTO> agents) {
        //display the Agency options as a menu with number options to select
        int i = 1;
        for (AgencyDTO a : agents) {
            System.out.println(i + " - " + a.getName());
            i++;
        }
    }

    private AgentDTO displayAndSelectAgent(AgencyDTO agencia) {

        List<AgentDTO> agentes = controller.getAgentsFromController(agencia);
        int answer = Utils.showAgentsAndSelectIndex(agentes,"Choice");
        return agentes.get(answer);
    }


}
