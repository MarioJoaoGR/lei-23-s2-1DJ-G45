package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
//import pt.ipp.isep.dei.esoft.project.application.controller.ScheduleVisitController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.*;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgentMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AnnouncementMapper;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.awt.desktop.SystemEventListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The type Search ui.
 */
public class SearchUI implements Runnable {


    //----------------
    private final AnnouncementController controller = new AnnouncementController();


    /**
     * The Visit request dto.
     */
    VisitRequestDTO visitRequestDTO = new VisitRequestDTO();

    //user search/
    private String searchTypeOfComercial;
    private int searchRooms;
    private String searchLandProperty;
    /**
     * The Announcement dto list.
     */
    List<AnnouncementDTO> announcementDTOList;

    public void run() {
        Scanner input = new Scanner(System.in);


        System.out.println("Search Properties");

        requestSearchData();
        announcementDTOList = getListingToShow(searchTypeOfComercial, searchLandProperty, searchRooms);


        int choice;
        if (!announcementDTOList.isEmpty()) {
            do {

                Utils.showAnnouncementsWithoutLastOption(announcementDTOList, "Announcements available:");

                System.out.println("Options:");
                System.out.println("1-Sort list");
                System.out.println("2-Open announcement");
                System.out.println("3-Cancel");

                do {
                    choice = input.nextInt();
                } while (choice > 3 || choice <= 0);


                switch (choice) {
                    case 1:
                        sortType();
                        break;

                    case 2:
                        int id = openAnnouncement();

                        int option;

                        if (controller.isLogin()) {
                            List<String> options = new ArrayList<String>();
                            options.add("Ask to schedule a visit");
                            options.add("Submit a order to purchase");

                            option= Utils.showAndSelectIndex(options,"Menu")+1;

                            switch (option) {
                                case 1:
                                    Optional<AnnouncementDTO> announcement1 = controller.getAnnouncementById(id);
                                    if (announcement1.isPresent()) {
                                        requestVisitRequestData();
                                        submitVisitRequest(announcement1.get());
                                    } else {
                                        System.out.println("Announcement selected doesn`t exist");
                                    }
                                    break;
                                case 2:

                                    if (askIfWantsToSubmitAnOrder() == 1) {
                                        Optional<AnnouncementDTO> announcement = controller.getAnnouncementById(id);

                                        boolean flag = addOrder(announcement);
                                        if (flag) {
                                            choice = 3;
                                        }

                                    }
                            }
                        }

                        break;
                }
            } while (choice != 3);
        } else {
            System.out.println("None announcement available that correspond to your filter");
        }
    }


    private void Announcement(int id) {
        AnnouncementDTO announcementAvailableDTO = controller.getAnnouncementById(id).get();

        System.out.println(announcementAvailableDTO);

    }


    private List<AnnouncementDTO> getListingToShow(String business, String type, int rooms) {
        return controller.getListAnnouncementAvailableDTO(business, type, rooms);


    }


    private void requestSearchData() {

        searchTypeOfComercial = requestTypeOfComercial();
        searchLandProperty = requestLandType();
        if (!Objects.equals(searchLandProperty, PropertyType.LAND.getDisplayName())) {
            searchRooms = requestNumberBedrooms();

        }
    }


    private int requestNumberBedrooms() {
        Scanner input = new Scanner(System.in);
        System.out.println("Number of bedrooms (insert 0 to see all properties available):");
        return input.nextInt();
    }

    private String requestTypeOfComercial() {

        Scanner input = new Scanner(System.in);
        int choice;
        String typeOfComercial;
        System.out.println("Do you want to Buy or Rent the property?");
        System.out.println("1-Buy");
        System.out.println("2-Rent");
        System.out.println("3-All");
        do {
            choice = input.nextInt();
        } while (choice > 3 || choice <= 0);
        switch (choice) {
            case 1:
                typeOfComercial = "Sale";
                break;
            case 2:
                typeOfComercial = "Lease";
                break;
            default:
                typeOfComercial = "";
                break;
        }
        return typeOfComercial;


    }

    private String requestLandType() {
        Scanner input = new Scanner(System.in);
        int choice;
        String landType;
        System.out.println("What type is of property are you looking for?");
        System.out.println("1-" + PropertyType.HOUSE.getDisplayName());
        System.out.println("2-" + PropertyType.LAND.getDisplayName());
        System.out.println("3-" + PropertyType.APARTMENT.getDisplayName());
        System.out.println("4-All");
        do {
            choice = input.nextInt();
        } while (choice > 4 || choice <= 0);
        switch (choice) {
            case 1:
                landType = PropertyType.HOUSE.getDisplayName();
                break;
            case 2:
                landType = PropertyType.LAND.getDisplayName();
                break;
            case 3:
                landType = PropertyType.APARTMENT.getDisplayName();
                break;
            default:
                landType = "";
        }
        return landType;
    }


    private void sortType() {

        Scanner input = new Scanner(System.in);


        int choice;

        System.out.println("What type of sort do you want");
        System.out.println("1-sort by price asc");
        System.out.println("2-sort by price desc");
        System.out.println("3-sort by parish");

        AnnouncementRepository announcementRepository = new AnnouncementRepository();


        do {
            choice = input.nextInt();
        } while (choice > 3 || choice <= 0);

        switch (choice) {
            case 1:
                announcementRepository.sortByPriceAsc(controller.getListingAvailable());

                announcementDTOList = getListingToShow(searchTypeOfComercial, searchLandProperty, searchRooms);
                break;
            case 2:
                announcementRepository.sortByPriceDesc(controller.getListingAvailable());
                announcementDTOList = getListingToShow(searchTypeOfComercial, searchLandProperty, searchRooms);
                break;
            case 3:
                announcementRepository.sortByParish(controller.getListingAvailable());
                announcementDTOList = getListingToShow(searchTypeOfComercial, searchLandProperty, searchRooms);
                break;
            default:
                announcementRepository.sortByRecent(controller.getListingAvailable());
                announcementDTOList = getListingToShow(searchTypeOfComercial, searchLandProperty, searchRooms);
                break;

        }


    }


    private int askIfWantsToSubmitAnOrder() {
        int choice;
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to submit a purchase buy?");
        System.out.println("1-Yes");
        System.out.println("2-No");
        do {
            choice = input.nextInt();
        } while (choice <= 0 || choice > 2);
        return choice;
    }


    private boolean addOrder(Optional<AnnouncementDTO> announcementDTO) {

        double value = Utils.readDoubleFromConsole("Insert the value of the order");
        Optional<Order> result = controller.createOrder(announcementDTO.get(), controller.getClientFromSession(), value);



        if (result.isPresent()) {
            System.out.println("Order submitted successfully");
            return true;
        } else {
            System.out.println("Failed submitting the order");
            System.out.println();
            return false;
        }

    }

    private int openAnnouncement() {
        int id = Utils.selectsIndex(announcementDTOList);
        id = announcementDTOList.get(id).getId();
        System.out.println();
        Announcement(id);
        System.out.println();

        return id;


    }

    private void requestVisitRequestData() {

        visitRequestDTO.setUserName(controller.getUserName());

        visitRequestDTO.setEmail(controller.getEmail());
        visitRequestDTO.setDate(dateInput().toString());
        visitRequestDTO.setTimeSlot(requestTimeSlot());
        System.out.println();

    }


    private void submitVisitRequest(AnnouncementDTO announcement) {

       visitRequestDTO.setAgentDTO(announcement.getAgent());
        System.out.println();
        if (sendMessageToAgent(visitRequestDTO,announcement)) {
            System.out.println("Message sent with success and visit to property is waiting for agent confirmation");
        } else {
            System.out.println("There was an error with your request");
        }
        System.out.println();
    }


    private String dateInput() {
        return Utils.readDateFromConsole("Enter a date (dd-mm-yyyy): ");
    }

    private List<String> requestTimeSlot() {
        return Utils.requestTimeSlot();
    }

    private String getEmail() {
        return controller.getEmail();
    }

    private String getName() {
        return controller.getUserName();
    }

    private boolean sendMessageToAgent(VisitRequestDTO visitRequestDTO, AnnouncementDTO announcementDTO) {
        return controller.createVisitRequest(visitRequestDTO,announcementDTO);

    }


}


