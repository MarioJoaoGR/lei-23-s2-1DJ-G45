package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.BruteForceAlgorithm;
import pt.ipp.isep.dei.esoft.project.domain.AlgorithmInfo;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

/**
 * The type Controller divide stores.
 */
public class ControllerDivideStores {


    private AnnouncementRepository announcementRepository = null;
    private AgencyRepository agencyRepository = null;

    /**
     * Instantiates a new Controller divide stores.
     */
    public ControllerDivideStores() {
        getAnnouncementRepository();
        getAgencyRepository();
    }


    /**
     * Instantiates a new Controller divide stores.
     *
     * @param announcementRepository the announcement repository
     * @param agencyRepository       the agency repository
     */
//Allows receiving the repositories as parameters for testing purposes
    public ControllerDivideStores(AnnouncementRepository announcementRepository, AgencyRepository agencyRepository) {
        this.announcementRepository = announcementRepository;
        this.agencyRepository = agencyRepository;
    }

    private AnnouncementRepository getAnnouncementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;

    }

    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;

    }


    /**
     * Gets agencies.
     *
     * @return the agencies
     */
    public List<Agency> getAgencies() {
        return agencyRepository.getAgencyList();
    }

    /**
     * Get nr properites for each agency four lists.
     *
     * @return the four lists
     */
    public AlgorithmInfo getNrProperitesForEachAgency(){
        List<Agency> listAgency = getAgencies();

        String[][] array = new String[listAgency.size()][2];

        int i = 0;
        for (Agency ag: listAgency) {
           //testing purposes System.out.println(announcementRepository.getNrPropertiesOfAnAgency(ag));
            array[i][1] = String.valueOf(announcementRepository.getNrPropertiesOfAnAgency(ag));
          //test  System.out.println(ag.getName());
            array[i][0] = ("Id="+ag.getId() + " Name=" + ag.getName());
            i++;
        }
        BruteForceAlgorithm bruteForceAlgorithm = new BruteForceAlgorithm();
        return bruteForceAlgorithm.getPartitions(array);
    }


}
