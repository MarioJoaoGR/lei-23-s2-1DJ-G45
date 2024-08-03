package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementAvailable;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgencyDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.OrderDTO;
import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgencyMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.OrderMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static pt.ipp.isep.dei.esoft.project.ui.console.utils.CreateRequestUI.displayAgencyOptions;

/**
 * The type List accept or decline offers ui.
 */
public class ListAcceptOrDeclineOffersUI implements Runnable {
    private final AnnouncementController controller = new AnnouncementController();


    private AnnouncementController getController() {
        return controller;
    }
    private OrderMapper orderMapper = new OrderMapper();


    public void run() {


        System.out.println(String.format("List an offer for an announcement%n"));
        processOrders();


    }


    private AnnouncementDTO displayAndSelectAnnouncement() {
        //Display the list of announcements
        List<AnnouncementDTO> list = controller.getListAnnouncementAvailableDTOForAgent();

        AnnouncementDTO selected = new AnnouncementDTO();

        if (list.isEmpty()){
            System.out.println("There are no announcements available.");
        }else {
            selected = Utils.showAnnouncementsAndSelectObject(list,"Select an announcement:");

        }



        return selected;

    }


    private boolean displayOrders(List<OrderDTO> order) {
        //display the Order options as a menu with number options to select
        int i = 1;
        if (!order.isEmpty()) {
            for (OrderDTO a : order) {
                if(a.getState()==0){
                    System.out.println(i + " - " + a.toString());
                }
                i++;
            }
            return true;
        } else {
            System.out.println("There are no orders available for this Announcement.");
            System.out.println();
            return false;
        }
    }

    private void processOrders() {

        AnnouncementDTO announcementChosen = displayAndSelectAnnouncement();
        if(announcementChosen!=null){
            List<OrderDTO> listAll = getController().getOrderListDTO(announcementChosen);
            List<OrderDTO> list = new ArrayList<>();

            for (OrderDTO ord : listAll){
                if (ord.getState()==0){
                    list.add(ord);
                }
            }

            boolean flag=displayOrders(list);

            if(flag){
                int orderNumber = Utils.readIntegerFromConsole("Choose the order you want to evaluate: ");

                if (orderNumber < 0 || orderNumber > list.size()) {
                    System.out.println("Invalid order.");
                    return;
                }

                OrderDTO selectedOrder = list.get(orderNumber - 1);
                displayOrderOptions(selectedOrder, list, announcementChosen);
            }else {
                processOrders();
            }

        }

    }


    private void displayOrderOptions(OrderDTO order, List<OrderDTO> list, AnnouncementDTO announcementChosen) {

        List<String> options = new ArrayList<String>();
        options.add("Accept");
        options.add("Decline");

        int option = Utils.showAndSelectIndex(options,"Options for Proposal: " + order.toString())+1;

        switch (option) {
            case 1:
                acceptOrder(order, list, announcementChosen);
                break;
            case 2:
                declineOrder(order,announcementChosen);
                break;
            case 0:
                processOrders();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void acceptOrder(OrderDTO order, List<OrderDTO> list, AnnouncementDTO announcementChosen) {

        List<String> options = new ArrayList<String>();
        options.add("Yes");
        options.add("No");

        int answer = Utils.showAndSelectIndex(options,"Are you sure you want to accept this proposal?")+1;
        switch (answer) {
            case 1:
                System.out.println("Proposal accepted."); // Confirmation message
                order.setState(2);      // Accepted
                for (OrderDTO a : list) {
                    if (a != order) {
                        a.setState(1); // rejects all others
                        controller.saveOrderState(announcementChosen.getId(), orderMapper.fromDTO(a));

                    }
                }
                controller.saveOrderState(announcementChosen.getId(), orderMapper.fromDTO(order));
                getController().sendOrderResultsViaEmail(list, announcementChosen);

                break;
            case 2:
                System.out.println("Proposal not accepted."); // Confirmation message
                order.setState(0);
                processOrders(); // Display proposals again after not refusing
                break;

        }

    }


    private void declineOrder(OrderDTO order, AnnouncementDTO announcementChosen) {

        List<String> options = new ArrayList<String>();
        options.add("Yes");
        options.add("No");

        int answer = Utils.showAndSelectIndex(options,"Are you sure you want to decline this proposal?")+1;

        switch (answer) {
            case 1:
                System.out.println("Proposal declined."); // Confirmation message
                order.setState(1); // Rejected
                controller.saveOrderState(announcementChosen.getId(), orderMapper.fromDTO(order));
                processOrders(); // Display proposals again after refusing
                getController().sendOrderResultsRefuseViaEmail(order, announcementChosen);

                break;
            case 2:
                System.out.println("Proposal not declined."); // Confirmation message
                order.setState(0);
                processOrders(); // Display proposals again after not refusing
                break;


        }
    }

}

