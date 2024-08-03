package pt.ipp.isep.dei.esoft.project.ui.console.menu;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.PublishAnnouncementPhoneCallUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.ListAcceptOrDeclineOffersUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.PublishAnnouncementUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class AgentUI implements Runnable {
    public AgentUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Publish an Announcement through request", new PublishAnnouncementUI()));
        options.add(new MenuItem("Publish an Announcement through phone call", new PublishAnnouncementPhoneCallUI()));
        options.add(new MenuItem("List an offer for an Announcement", new ListAcceptOrDeclineOffersUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAgent Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
