
package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CheckScheduleVisitsController;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The type Chech schedule visits ui.
 */
public class ChechScheduleVisitsUI implements Runnable {

    /**
     * The Controller.
     */
    CheckScheduleVisitsController controller = new CheckScheduleVisitsController();

    public void run() {
        System.out.println("Check visit requests");
        requestData();
        //submitsData();

    }


    private void requestData() {
        List<AnnouncementDTO> list = controller.getScheduleVisitsForClient();
        int option;
        int optionAnnouncements = -1;
        int optionVisitRequest = -1;
        int optionTimeSlot;
        List<String> timeSlots = null;

        if (!list.isEmpty()) {
            Utils.showAnnouncements(list, "Select the announcement that you want to see the schedule visits");
            optionAnnouncements = Utils.selectsIndex(list)+1;

            AnnouncementDTO announcementDTO = list.get(optionAnnouncements - 1);

            optionVisitRequest = Utils.showAndSelectIndex(announcementDTO.getVisitRequestList(), "Visit requests list")+1;

            if (optionVisitRequest!=0){
                timeSlots = announcementDTO.getVisitRequestList().get(optionVisitRequest-1).getTimeSlot();
                System.out.println("Type the number of the time slot you want to accept");
                option=Utils.showAndSelectIndex(timeSlots, "Select time slot")+1;



                List<String> options = new ArrayList<String>();
                options.add("Accept");
                options.add("Decline");
                options.add("Counterpurpose");
                int optionChosed = Utils.showAndSelectIndex(options,"What do you want to do?")+1;

                switch (optionChosed) {
                    case 1:
                        controller.acceptedScheduleVisit(list.get(optionVisitRequest-1), list.get(optionAnnouncements - 1).getVisitRequestList().get(optionVisitRequest-1), list.get(optionAnnouncements - 1).getVisitRequestList().get(optionVisitRequest-1).getTimeSlot().get(option - 1));
                        System.out.println("Operation done");
                        break;
                    case 2:
                        controller.removeSchedule(list.get(optionAnnouncements - 1), list.get(optionAnnouncements - 1).getVisitRequestList().get(optionVisitRequest-1));
                        System.out.println("Operation done");
                        break;

                    case 3:
                        List<String> listCounterTimeSlots = Utils.requestTimeSlot();
                        controller.counterSchedule(list.get(optionAnnouncements - 1), list.get(optionAnnouncements - 1).getVisitRequestList().get(optionVisitRequest-1), listCounterTimeSlots);
                        System.out.println("Operation done");
                        break;

                    default:
                        break;

                }



            }

        }else {
            System.out.println("There aren't any Visit request");
        }


    }

}




