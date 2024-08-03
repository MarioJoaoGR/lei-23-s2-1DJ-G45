package pt.ipp.isep.dei.esoft.project.application.controller.authorization;


import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;


/**
 * The type Authentication controller.
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class AuthenticationController {

    /**
     * The constant ROLE_ADMIN.
     */
    public static final String ROLE_ADMIN = "Administrator";
    /**
     * The constant ROLE_STORE_MANAGER.
     */
    public static final String ROLE_STORE_MANAGER = "StoreManager";
    /**
     * The constant ROLE_AGENT.
     */
    public static final String ROLE_AGENT = "Agent";
    /**
     * The constant ROLE_USER.
     */
    public static final String ROLE_USER = "User";
    /**
     * The constant ROLE_NETWORK_MANAGER.
     */
    public static final String ROLE_NETWORK_MANAGER = "NetworkManager";

    /**
     * The Display name.
     */

    //private final ApplicationSession applicationSession;
    private final AuthenticationRepository authenticationRepository;

    /**
     * Instantiates a new Authentication controller.
     */
    public AuthenticationController() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    /**
     * Do log in boolean.
     *
     * @param email the email
     * @param pwd   the pwd
     * @return the boolean
     */
    public boolean doLogin(String email, String pwd) {
        try {
            return authenticationRepository.doLogin(email, pwd);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Gets user roles.
     *
     * @return the user roles
     */
    public List<UserRoleDTO> getUserRoles() {
        if (authenticationRepository.getCurrentUserSession().isLoggedIn()) {
            return authenticationRepository.getCurrentUserSession().getUserRoles();
        }
        return null;
    }

    /**
     * Get session email string.
     *
     * @return the string
     */
    public String getSessionEmail(){
        return authenticationRepository.getCurrentUserSession().getUserId().getEmail();

    }

    /**
     * Is login boolean.
     *
     * @return the boolean
     */
    public boolean isLogin(){
        return authenticationRepository.getCurrentUserSession().isLoggedIn();

    }

    /**
     * Add user role.
     */
    public void addUserRole(){}


    /**
     * Do logout.
     */
    public void doLogout() {
        authenticationRepository.doLogout();
    }
}
