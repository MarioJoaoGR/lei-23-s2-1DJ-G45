package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateRequestController;
import pt.ipp.isep.dei.esoft.project.application.controller.ImportController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Adapter.ExternalModuleAdapterCSV;
import pt.ipp.isep.dei.esoft.project.domain.Adapter.ExternalModuleCSV;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgencyDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgencyMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AnnouncementMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;


/**
 * The type Import file ui.
 */
public class ImportFileUI implements Runnable {
    /**
     * The Controller.
     */
    ImportController controller = new ImportController();
    /**
     * run
     */
    public void run() {

        System.out.println("Import from Legacy file:");
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);


        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile());

            File selectedFile = chooser.getSelectedFile();
                try {
                    boolean flag = controller.addLegacyFile(selectedFile,String.valueOf(chooser.getSelectedFile()));
                    if (flag){
                        System.out.println("The file was successfuly imported");
                    }else {
                        System.out.println("the file is not in the requested format");
                    }


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

        }
    }
}

