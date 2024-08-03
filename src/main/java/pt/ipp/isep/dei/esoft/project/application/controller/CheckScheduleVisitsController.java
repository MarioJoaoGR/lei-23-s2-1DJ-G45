
package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AnnouncementAvailable;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AnnouncementMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.VisitRequestMapper;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Check schedule visits controller.
 */
public class CheckScheduleVisitsController {


    private AnnouncementRepository announcementRepository = null;
    private AuthenticationRepository authenticationRepository = null;

    private AnnouncementController controller = new AnnouncementController();

    /**
     * Instantiates a new Check schedule visits controller.
     */
    public CheckScheduleVisitsController() {
        getAnnouncementRepository();
        getAuthenticationRepository();
    }


    private AnnouncementRepository getAnnouncementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;

    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;

    }


    /**
     * Gets schedule visits for client.
     *
     * @return the schedule visits for client
     */
    public List<AnnouncementDTO> getScheduleVisitsForClient() {
        AnnouncementMapper announcementMapper = new AnnouncementMapper();
        List<AnnouncementAvailable> list = announcementRepository.getScheduleVisitsForClient(controller.getClientFromSession());
        List<AnnouncementDTO> nullList = new ArrayList<>();
        if (!list.isEmpty()) {
            return announcementMapper.toDTO(list);
        }
        return nullList;
    }

    /**
     * Accepted schedule visit.
     *
     * @param announcementDTO the announcement dto
     * @param visitRequestDTO the visit request dto
     * @param s               the s
     */
    public void acceptedScheduleVisit(AnnouncementDTO announcementDTO, VisitRequestDTO visitRequestDTO, String s) {
    AnnouncementMapper announcementMapper = new AnnouncementMapper();
    AnnouncementAvailable announcementAvailable = announcementMapper.toAnnouncementAvailable(announcementDTO);
        VisitRequestMapper visitRequestMapper = new VisitRequestMapper();
        VisitRequest visitRequest = visitRequestMapper.fromDTO(visitRequestDTO);
        announcementAvailable.acceptVisitRequest(visitRequest,s);
        controller.saveVisitRequest(announcementAvailable,visitRequest);
    }


    /**
     * Counter schedule.
     *
     * @param announcementDTO      the announcement dto
     * @param s                    the s
     * @param listCounterTimeSlots the list counter time slots
     */
    public void counterSchedule(AnnouncementDTO announcementDTO, VisitRequestDTO s, List<String> listCounterTimeSlots) {
        AnnouncementMapper announcementMapper = new AnnouncementMapper();
        AnnouncementAvailable announcementAvailable = announcementMapper.toAnnouncementAvailable(announcementDTO);
        VisitRequestMapper visitRequestMapper = new VisitRequestMapper();
        VisitRequest visitRequest = visitRequestMapper.fromDTO(s);
        announcementAvailable.counterPurpose(visitRequest,listCounterTimeSlots);
        controller.saveVisitRequest(announcementAvailable,visitRequest);

    }

    /**
     * Remove schedule.
     *
     * @param announcementDTO the announcement dto
     * @param s               the s
     */
    public void removeSchedule(AnnouncementDTO announcementDTO, VisitRequestDTO s) {
        AnnouncementMapper announcementMapper = new AnnouncementMapper();
        AnnouncementAvailable announcementAvailable = announcementMapper.toAnnouncementAvailable(announcementDTO);
        VisitRequestMapper visitRequestMapper = new VisitRequestMapper();
        VisitRequest visitRequest = visitRequestMapper.fromDTO(s);
        announcementAvailable.rejectTimeRequest(visitRequest);
        controller.saveVisitRequest(announcementAvailable,visitRequest);


    }







}
