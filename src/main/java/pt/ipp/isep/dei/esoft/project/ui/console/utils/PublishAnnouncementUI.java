package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.Commission;
import pt.ipp.isep.dei.esoft.project.domain.Request;
import java.util.*;

/**
 * The type Publish announcement ui.
 */
public class PublishAnnouncementUI implements Runnable {

    private final PublishAnnouncementController saleController = new PublishAnnouncementController();

    private int requestId;

    @Override
    public void run() {

        Request request = displayAgentRequestsByMostRecentDate();

        if (request!=null){
            requestId=request.getRequestId();
            Commission commission = getAgentComission();
            saleController.publishSale(requestId, commission);
            if (!saleController.checkRequestId(requestId)) {
                System.out.println("Your announcement was published with success.");
            } else {
                System.out.println("Announcement was not published.");
            }
        }

    }

    /**
     * Gets agent comission.
     *
     * @return the agent comission
     */
    public Commission getAgentComission() {
        Commission commission = new Commission();
        Scanner scn = new Scanner(System.in);
        int comissionType = 0;
        System.out.println("Select the type of commission you want: ");
        do {
            System.out.println("1 - Fix comission");
            System.out.println("2 - Percentage comission");
            comissionType= scn.nextInt();
        } while (comissionType < 1 || comissionType > 2);
        if (comissionType == 1) {
            commission.setCommissionTypeFixed();
            commission.setCommissionValue(getFixComission());
        } else {
            commission.setCommissionTypePercentage();
            commission.setCommissionValue(getPercentComission());
        }
        return commission;
    }

    /**
     * Gets fix comission.
     *
     * @return the fix comission
     */
    public double getFixComission() {
        Scanner scn = new Scanner(System.in);
        double comission = 0;
        boolean passed;
        System.out.println("Type the value for your comission");
        do {
            passed = true;
            try {
                comission = scn.nextDouble();
                if (comission <= 0) {
                    passed = false;
                    System.out.println("Commission should be positive, type again.");
                }
            } catch (InputMismatchException exception) {
                System.out.println("That is not a valid commission, type again.");
                passed = false;
                scn.next();
            }
        } while (!passed);
        return comission;
    }

    /**
     * Gets percent comission.
     *
     * @return the percent comission
     */
    public double getPercentComission() {
        Scanner scn = new Scanner(System.in);
        double comission = 0;
        boolean passed;
        System.out.println("Type the value for your comission: ");
        do {
            passed = true;
            try {
                comission = scn.nextDouble();
                if (comission < 0 || comission > 100) {
                    System.out.println("Comission should be between 0% and 100%");
                    passed = false;
                }
            } catch (InputMismatchException exception) {
                System.out.println("That is not a valid commission, type again.");
                passed = false;
                scn.next();
            }
        } while (!passed);
        return comission;
    }

    /**
     * Gets request id.
     *
     * @return the request id
     */
    public int getRequestId() {
        Scanner keyboardScanner = new Scanner(System.in);
        int requestId = 0;
        boolean passed, isValid;
        System.out.println("Introduce the request id: ");
        do {
            passed = true;
            isValid = true;
            try {
                requestId = keyboardScanner.nextInt();
                if (!saleController.checkRequestId(requestId)) {
                    System.out.println("That request doesn't exist in the repository, try again.");
                    passed = false;
                    isValid = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("That input is not valid, try again.");
                passed = false;
                isValid = false;
                keyboardScanner.next();
            }
        } while (!isValid && !passed);
        return requestId;
    }

    /**
     * Display agent requests by most recent date request.
     *
     * @return the request
     */
    public Request displayAgentRequestsByMostRecentDate() {
        Request request = null;
        List<Request> agentRequests = saleController.getAgentRequests();
        agentRequests.sort((r1, r2) -> r2.getDate().compareTo(r1.getDate()));
        if (agentRequests.isEmpty()){
            System.out.println("Doesn't exist any Requests");
        }else{
            request =(Request) Utils.showRequestAndSelectOne(agentRequests,"Requests available:");
        }

        return request;

    }

}
