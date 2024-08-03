package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.Adapter.ExternalModuleAdapterCSV;
import pt.ipp.isep.dei.esoft.project.domain.Adapter.ExternalModuleCSV;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementAvailable;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementSold;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.OrderDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AnnouncementMapper;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;

/**
 * The type Import controller.
 */
public class ImportController {
    /**
     * The Adapter csv.
     */
    ExternalModuleAdapterCSV adapterCSV = new ExternalModuleCSV();

    /**
     * Add legacy file boolean.
     *
     * @param file     the file
     * @param filePath the file path
     * @return the boolean
     */
    public boolean addLegacyFile(File file,String filePath){
        if(validateCSV(file) && validateEmpty(file)){
            return adapterCSV.readFile(String.valueOf(filePath));

        }
        if (!validateCSV(file)){
            System.out.println("file is not in csv format");
        }

        if (!validateEmpty(file)){
            System.out.println("file is empty");
        }
        return false;
    }

    /**
     * Validate csv boolean.
     *
     * @param file the file
     * @return the boolean
     */
    public boolean validateCSV(File file) {
        return file.getName().toLowerCase().endsWith(".csv");

    }

    /**
     * Validate empty boolean.
     *
     * @param file the file
     * @return the boolean
     */
    public boolean validateEmpty(File file){
        return file != null;
    }
}



