package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgencyDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.EmployeeDTO;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgencyMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.UserLoginRepository;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;


import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.List;


/**
 * The type Register employee controller.
 */
public class RegisterEmployeeController {


    private AuthenticationRepository authenticationRepository = null;

    private AgencyRepository agencyRepository = null;

    /**
     * method that grants access to repositories
     */
    public RegisterEmployeeController() {
        getAuthenticationRepository();
        getAgencyRepository();
    }

    /**
     * created for test purposes
     *
     * @param agencyRepository         the agency repository
     * @param authenticationRepository the authentication repository
     */
    public RegisterEmployeeController(AgencyRepository agencyRepository, AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
        this.agencyRepository = agencyRepository;
    }

    /**
     * method that grants access to authentication repository
     *
     * @return authenticationrepository data
     */
    private AuthenticationRepository getAuthenticationRepository() {

        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * method that grants access to agencyrepository
     *
     * @return aagencyrepository data
     */

    private AgencyRepository getAgencyRepository() {

        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    /**
     * Gets agencys from controller.
     *
     * @return list of agencys from agencyrepository
     */
    public List<AgencyDTO> getAgencysFromController() {
        List<Agency> list = agencyRepository.getAgencyList();
        AgencyMapper agencyMapper = new AgencyMapper();
        return agencyMapper.toDTO(list);
    }


    /**
     * Create employee agent employee.
     *
     * @param employeeDTO the employee dto
     * @return the employee
     */
    public Employee createEmployeeAgent(EmployeeDTO employeeDTO) {
        return authenticationRepository.createEmployee(employeeDTO);
    }

    /**
     * Get roles list.
     *
     * @return the list
     */
    public List<UserRoleDTO> getRoles(){
       return authenticationRepository.getRoles();
    }

    /**
     * Add employee to system boolean.
     *
     * @param employeeDTO the employee dto
     * @return the boolean
     */
    public boolean addEmployeeToSystem(EmployeeDTO employeeDTO) {
        UserLoginRepository userRepository = Repositories.getInstance().getUserRepository();
        String name = employeeDTO.getName();
        String mail = employeeDTO.getMail();
        String password = employeeDTO.getPassword();
        String role = employeeDTO.getRole();
        authenticationRepository.addUserRole(role, role);

        UserLogin user = new UserLogin(name,mail,password,role);
        userRepository.add(user);

        return authenticationRepository.addUserWithRole(name, mail, password, role);
    }

    /**
     * metodo que gera password
     *
     * @return password gerada
     */
    public String generatePassword() {
        return Security.generateRandomPassword();
    }

    /**
     * Send employees credentials via email boolean.
     *
     * @param employeeDTO the employee dto
     * @return the boolean
     */
    public boolean sendEmployeesCredentialsViaEmail(EmployeeDTO employeeDTO) {
        String name = employeeDTO.getName();
        String mail = employeeDTO.getMail();
        String password = employeeDTO.getPassword();
        String file = "email.txt";
        boolean isSuccess = false;

        try {
            Formatter formatter = new Formatter(file);
            formatter.format("Credentials for Employee %s to login in the application.%nEmail : %s%nPassword : %s ", name, mail, password);
            formatter.close();
            isSuccess = true; // Set isSuccess to true if the email message is created and stored successfully.
        } catch (FileNotFoundException e) {
            System.out.println("Email wasn't delivered!");
            e.printStackTrace();
        }

        return isSuccess;
    }

}
