package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.UserLogin;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;

/**
 * The type Repositories.
 */
public class Repositories implements Serializable, Cloneable {

    private static Repositories instance = new Repositories();
    /**
     * The Task category repository.
     */
    TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
    /**
     * The Organization repository.
     */
    OrganizationRepository organizationRepository = new OrganizationRepository();
    /**
     * The Authentication repository.
     */
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    /**
     * The Agency repository.
     */
    AgencyRepository agencyRepository = new AgencyRepository();
    /**
     * The Request repository.
     */
    RequestRepository requestRepository = new RequestRepository();
    /**
     * The Announcement repository.
     */
    AnnouncementRepository announcementRepository = new AnnouncementRepository();
    /**
     * The User repository.
     */
    UserLoginRepository userRepository = authenticationRepository.getUserRepository();

    /**
     * Instantiates a new Repositories.
     */
    public Repositories() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Repositories getInstance() {
        return instance;
    }

    /**
     * Sets instance.
     *
     * @param instance the instance
     */
    public static void setInstance(Repositories instance) {
        Repositories.instance = instance;
        instance.authenticationRepository= new AuthenticationRepository();

        for (UserLogin user : instance.userRepository.getUserList()){
            instance.authenticationRepository.addUserRole(user.getRole(),user.getRole());
            instance.authenticationRepository.addUserWithRole(user.getName(), user.getEmail(), user.getPwd(), user.getRole());
        }
    }


    /**
     * Gets organization repository.
     *
     * @return the organization repository
     */
    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    /**
     * Gets task category repository.
     *
     * @return the task category repository
     */
    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
    }

    /**
     * Gets authentication repository.
     *
     * @return the authentication repository
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    /**
     * Get agency repository agency repository.
     *
     * @return the agency repository
     */
    public AgencyRepository getAgencyRepository(){
        return agencyRepository;
    }

    /**
     * Get request repository request repository.
     *
     * @return the request repository
     */
    public RequestRepository getRequestRepository(){
        return requestRepository;
    }

    /**
     * Get announcement repository announcement repository.
     *
     * @return the announcement repository
     */
    public AnnouncementRepository getAnnouncementRepository(){
        return announcementRepository;
    }


    /**
     * Copy object.
     *
     * @param data the data
     * @return the object
     * @throws CloneNotSupportedException the clone not supported exception
     */
    public Object copy(Repositories data) throws CloneNotSupportedException {
        return instance.clone();
    }


    @Override
    public Repositories clone() {
        try {
            // Copy additional instance variables here if needed
            return (Repositories) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should not happen since the class implements Cloneable
            throw new AssertionError();
        }
    }


    /**
     * Sets task category repository.
     *
     * @param taskCategoryRepository the task category repository
     */
    public void setTaskCategoryRepository(TaskCategoryRepository taskCategoryRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
    }

    /**
     * Sets organization repository.
     *
     * @param organizationRepository the organization repository
     */
    public void setOrganizationRepository(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    /**
     * Sets authentication repository.
     *
     * @param authenticationRepository the authentication repository
     */
    public void setAuthenticationRepository(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Sets agency repository.
     *
     * @param agencyRepository the agency repository
     */
    public void setAgencyRepository(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    /**
     * Sets request repository.
     *
     * @param requestRepository the request repository
     */
    public void setRequestRepository(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    /**
     * Sets announcement repository.
     *
     * @param announcementRepository the announcement repository
     */
    public void setAnnouncementRepository(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    /**
     * Gets user repository.
     *
     * @return the user repository
     */
    public UserLoginRepository getUserRepository() {
        return userRepository;
    }

    /**
     * Sets user repository.
     *
     * @param userRepository the user repository
     */
    public void setUserRepository(UserLoginRepository userRepository) {
        this.userRepository = userRepository;
    }
}
