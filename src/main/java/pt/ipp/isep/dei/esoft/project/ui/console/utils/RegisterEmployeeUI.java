package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgencyDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.EmployeeDTO;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgencyMapper;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;


import java.util.List;

import static java.lang.Integer.parseInt;
import static pt.ipp.isep.dei.esoft.project.ui.console.utils.CreateRequestUI.*;


/**
 * The type Register employee ui.
 */
public class RegisterEmployeeUI implements Runnable {

    private final RegisterEmployeeController controller = new RegisterEmployeeController();


    private String password = "default password";
    private String address;
    private String role;
    /**
     * The Employee dto.
     */
    EmployeeDTO employeeDTO = new EmployeeDTO();
    private final String ADDRESS_BY_OMISSION = "No address";


    private final int pwdLength = 8;//perguntar---------------------------//

    //--------

    /**
     * method to access controller
     *
     * @return controller
     */
    private RegisterEmployeeController getController() {
        return controller;
    }


    /**
     * run
     */
    public void run() {


        System.out.println(String.format("Register an Employee%n"));

        requestEmployeeData();
        AgencyDTO agencyChosed = null;
        if (role.equals("Agent") || role.equals("Store Manager")) {
            agencyChosed = displayAndSelectAgency();
        }
        submitData(agencyChosed);
    }

    /**
     * sumbimiton of the data to register the employee
     *
     * @param agencyChosed agency that was chosed to register the employee
     */
    private void submitData(AgencyDTO agencyChosed) {

        Employee newEmployee;
        employeeDTO.setPassword(getController().generatePassword());

        newEmployee = controller.createEmployeeAgent(employeeDTO);



        if (getController().addEmployeeToSystem(employeeDTO) && getController().sendEmployeesCredentialsViaEmail(employeeDTO)) {
            System.out.println(String.format("Employee with name " + newEmployee.getName() + " and id " + newEmployee.getId() + " was created%n"));
            System.out.println(String.format("Employee registered with success%n"));
            System.out.println(String.format("email sent correctly%n"));

        } else {
            System.out.println(String.format("Fail with register%n"));
            System.out.println(String.format("email not sent%n"));

        }


    }

    /**
     * Requested data to register new Employee
     */

    private void requestEmployeeData() {

        employeeDTO.setName(Utils.requestName("Type the employee's name:"));
        role = requestEmployeesRole();
        employeeDTO.setRole(role);
        employeeDTO.setCivilNumber(Utils.requestCardNumber("Type the employee's citizen`s card number:"));
        employeeDTO.setPhoneNumber(Utils.requestPhoneNumber("Please enter a phone number:"));
        employeeDTO.setTaxNumber(Utils.requestTaxNumber("Type the employee's tax number:"));
        employeeDTO.setMail(Utils.requestEmail("Type the employee's email:"));
        address = Utils.requestAddress();
        if (!address.equals(ADDRESS_BY_OMISSION)) {
            employeeDTO.setAddress(address);
        }

    }


    /**
     * request employee's role to be registered
     *
     * @return Employee's role
     */

    private String requestEmployeesRole() {

        List<UserRoleDTO> lista = controller.getRoles();
        int option = Utils.showRolesAndSelectIndex(controller.getRoles(),"Choose the Employee's Role:");
        return lista.get(option).getDescription();
    }


    /**
     * displayAgencyOptions(agencias) displays list of agencies for System admin to choose
     *
     * @return agency chosen by system admin to register the employee
     */


    private AgencyDTO displayAndSelectAgency() {
        //Display the list of agencies
        List<AgencyDTO> agencias = controller.getAgencysFromController();

        int listSize = agencias.size();
        int answer = -1;

        while (answer < 1 || answer > listSize) {
            System.out.println("Select an agency:");
            displayAgencyOptions(agencias);
            answer = Utils.readIntegerFromConsole("Choice:");

            if (answer < 1 || answer > listSize) {
                System.out.println("Choose a valid Agency Number");
            }
        }

        return agencias.get(answer - 1);

    }
}

