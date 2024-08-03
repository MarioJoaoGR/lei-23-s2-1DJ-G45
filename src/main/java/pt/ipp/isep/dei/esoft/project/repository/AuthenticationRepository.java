package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.EmployeeDTO;
import pt.ipp.isep.dei.esoft.project.domain.Serialization.Serialization;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;
import pt.isep.lei.esoft.auth.domain.model.User;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * The type Authentication repository.
 */
public class AuthenticationRepository implements Serializable {
    private transient final AuthFacade authenticationFacade = new AuthFacade();
    private UserLoginRepository userRepository = new UserLoginRepository();

    /**
     * Gets agent by email.
     *
     * @param agentEmail the agent email
     * @return the agent by email
     */
    public Agent getAgentByEmail(String agentEmail) {
        if (authenticationFacade.existsUser(agentEmail)) {
            return new Agent(agentEmail, authenticationFacade.getUser(agentEmail).get().getName());
        } else {
            System.out.println("Agent is not registered.");
            return null;
        }
    }

    /**
     * Do login boolean.
     *
     * @param email the email
     * @param pwd   the pwd
     * @return the boolean
     */
    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    /**
     * Do logout.
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }

    /**
     * Gets current user session.
     *
     * @return the current user session
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    /**
     * Gets mail from user from session.
     *
     * @return the mail from user from session
     */
    public String getMailFromUserFromSession() {
        return String.valueOf(authenticationFacade.getCurrentUserSession().getUserId());
    }

    /**
     * Add user role boolean.
     *
     * @param id          the id
     * @param description the description
     * @return the boolean
     */
    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public List<UserRoleDTO> getRoles() {
        return authenticationFacade.getUserRoles();
    }

    /**
     * Add user with role boolean.
     *
     * @param name   the name
     * @param email  the email
     * @param pwd    the pwd
     * @param roleId the role id
     * @return the boolean
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        UserLogin user = new UserLogin(name,email,pwd,roleId);
        userRepository.add(user);
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }

    /**
     * Create employee employee.
     *
     * @param employeeDTO the employee dto
     * @return the employee
     */
    public Employee createEmployee(EmployeeDTO employeeDTO){
        EmployeeDecision employeeDecision = new EmployeeDecision();
        return employeeDecision.createEmployeeObject(employeeDTO);
    }

    /**
     * Get users with role list.
     *
     * @param role the role
     * @return the list
     */
    public List<UserDTO> getUsersWithRole(String role){
        return authenticationFacade.getUsersWithRole(role);
    }

    /**
     * Get users list.
     *
     * @return the list
     */
    public List<UserDTO> getUsers(){
        return authenticationFacade.getUsers();
    }


    /**
     * Remove user boolean.
     *
     * @param mail the mail
     * @return the boolean
     */
    public boolean removeUser(String mail) {
        return authenticationFacade.removeUser(mail);
    }

    /**
     * Gets user repository.
     *
     * @return the user repository
     */
    public UserLoginRepository getUserRepository() {
        return userRepository;
    }
}
