package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementAvailable;
import pt.ipp.isep.dei.esoft.project.domain.Owner;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.UserSession;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;

import java.util.List;

/**
 * The type Publish announcement phone call controller.
 */
public class PublishAnnouncementPhoneCallController {

    private AuthenticationRepository authenticationRepository = null;
    private AnnouncementRepository announcementRepository = null;
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }
    private AnnouncementRepository getAnnouncementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }

    /**
     * Gets announcement list.
     *
     * @return the announcement list
     */
    public List<AnnouncementAvailable> getAnnouncementList() {
        return getAnnouncementRepository().getAnnouncementsAvailable();
    }

    /**
     * Gets agent from session.
     *
     * @return the agent from session
     */
    public Agent getAgentFromSession() {
        AuthenticationRepository authenticationRepository = getAuthenticationRepository();
        UserSession currentUserSession = authenticationRepository.getCurrentUserSession();

        return authenticationRepository.getAgentByEmail(String.valueOf(currentUserSession.getUserId()));
    }

    /**
     * Add text message to owner textbox.
     *
     * @param owner   the owner
     * @param message the message
     */
    public void addTextMessageToOwnerTextbox(Owner owner, String message) {
        List<UserDTO> owners = getAuthenticationRepository().getUsersWithRole(AuthenticationController.ROLE_USER);
        for (UserDTO ownerDTO: owners) {
            if (ownerDTO.getId().equals(owner.getEmail())) {
                owner.getTextMessages().add(message);
            }
        }
    }

}
