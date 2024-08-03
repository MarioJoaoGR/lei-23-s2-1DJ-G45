package pt.ipp.isep.dei.esoft.project.ui.console.menu;



import pt.ipp.isep.dei.esoft.project.ui.console.ChechScheduleVisitsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.DevTeamUI;

import pt.ipp.isep.dei.esoft.project.ui.console.utils.*;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class UserUI implements Runnable {
    public UserUI() {
    }

    public void run() {


        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));
        options.add(new MenuItem("Submit a request", new CreateRequestUI()));
        options.add(new MenuItem("Search for property", new SearchUI()));
        options.add(new MenuItem("Check Schedule Visits", new ChechScheduleVisitsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nUser Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
