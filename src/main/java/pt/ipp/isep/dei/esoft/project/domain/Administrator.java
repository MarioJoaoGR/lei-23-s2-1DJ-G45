package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.EmployeeDTO;

import java.io.Serializable;

/**
 * The type System administrator.
 */
public class Administrator extends Employee implements Serializable {
    private final String role = AuthenticationController.ROLE_ADMIN;// coloca a role como system administrator


    /**
     * Instantiates a new System administrator.
     *
     * @param id          the id
     * @param name        the name
     * @param phoneNumber the phone number
     * @param taxNumber   the tax number
     * @param password    the password
     * @param civilNumber the civil number
     * @param address     the address
     * @param mail        the mail
     */
    public Administrator(int id, String name, long phoneNumber, int taxNumber, String password, int civilNumber, String address, String mail) {
        super(id,name, phoneNumber, taxNumber, password, civilNumber, address, mail);

    }

    public Administrator(EmployeeDTO employeeDTO) {
        super(employeeDTO.getName(),employeeDTO.getPhoneNumber(), employeeDTO.getTaxNumber(), employeeDTO.getPassword(), employeeDTO.getCivilNumber(), employeeDTO.getAddress(), employeeDTO.getMail());
    }
    /**
     * Instantiates a new System administrator.
     *
     * @param id          the id
     * @param name        the name
     * @param phoneNumber the phone number
     * @param taxNumber   the tax number
     * @param password    the password
     * @param civilNumber the civil number
     * @param mail        the mail
     */
    public Administrator(int id, String name, long phoneNumber, int taxNumber, String password, int civilNumber, String mail) {
        super(id,name, phoneNumber, taxNumber, password, civilNumber, mail);
    }

    /**
     * Instantiates a new System administrator.
     *
     * @param name        the name
     * @param phoneNumber the phone number
     * @param taxNumber   the tax number
     * @param password    the password
     * @param civilNumber the civil number
     * @param address     the address
     * @param mail        the mail
     */
    public Administrator(String name, long phoneNumber, int taxNumber, String password, int civilNumber, String address, String mail) {
        super(name, phoneNumber, taxNumber, password, civilNumber, address, mail);

    }

    /**
     * Instantiates a new System administrator.
     *
     * @param name        the name
     * @param phoneNumber the phone number
     * @param taxNumber   the tax number
     * @param password    the password
     * @param civilNumber the civil number
     * @param mail        the mail
     */
    public Administrator(String name, long phoneNumber, int taxNumber, String password, int civilNumber, String mail) {
        super(name, phoneNumber, taxNumber, password, civilNumber, mail);
    }

    public Administrator clone() {

        return new Administrator(getId(),getName(), getPhoneNumber(), getTaxNumber(), getPassword(), getCivilNumber(), getAddress(), getMail());
    }
}

