package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.utils.*;

import java.util.ArrayList;
import java.util.List;

public class StoreManagerUI implements Runnable {
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nStore manager menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
