package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.gui.DivideSetOfAllStoresUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NetworkManagerUI implements Runnable {



    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
   //     options.add(new MenuItem("Divide the set of all stores into two subsets", new DivideSetOfAllStoresUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nNetwork Manager Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }








}
