package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.UserSession;

import java.util.List;

/**
 * The type Publish announcement controller.
 */
public class PublishAnnouncementController {

    private RequestRepository requestRepository = null;
    private AnnouncementRepository announcementRepository = null;
    private AuthenticationRepository authenticationRepository = null;

    /**
     * Instantiates a new Publish announcement controller.
     */
    public PublishAnnouncementController() {
        getRequestRepository();
        getAnnouncementRepository();
        getAuthenticationRepository();
    }

    /**
     * Instantiates a new Publish announcement controller.
     *
     * @param requestRepository      the request repository
     * @param announcementRepository the announcement repository
     */
    public PublishAnnouncementController(RequestRepository requestRepository, AnnouncementRepository announcementRepository) {
        this.requestRepository = requestRepository;
        this.announcementRepository = announcementRepository;
    }

    private RequestRepository getRequestRepository() {
        if (requestRepository == null) {
            Repositories repositories = Repositories.getInstance();
            requestRepository = repositories.getRequestRepository();
        }
        return requestRepository;
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
     * Gets agent from session.
     *
     * @return the agent from session
     */
    public Agent getAgentFromSession() {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        return authenticationRepository.getAgentByEmail(String.valueOf(authenticationRepository.getCurrentUserSession().getUserId()));
    }

    /**
     * Check request id boolean.
     *
     * @param requestId the request id
     * @return the boolean
     */
    public boolean checkRequestId(int requestId) {
        return requestRepository.checkRequestId(requestId);
    }

    /**
     * Gets agent requests.
     *
     * @return the agent requests
     */
    public List<Request> getAgentRequests() {
        Agent agent = getAgentFromSession();
        RequestRepository requestRepository = getRequestRepository();
        return requestRepository.getAgentRequests(agent);
    }

    /**
     * Publish sale.
     *
     * @param requestId  the request id
     * @param commission the commission
     */
    public void publishSale(int requestId, Commission commission) {
        Agent agent = getAgentFromSession();
        AnnouncementRepository announcementRepository = getAnnouncementRepository();
        announcementRepository.publishSale(requestId, agent, commission);
    }

    /**
     * Gets request by id.
     *
     * @param requestId the request id
     * @return the request by id
     */
    public Request getRequestById(int requestId) {
        return requestRepository.getRequestByIdNoOptional(requestId);
    }

}

