package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.EmployeeDTO;

import java.io.Serializable;

/**
 * The type Store manager.
 */
public class StoreManager extends Employee implements Serializable {


    private final String role = AuthenticationController.ROLE_STORE_MANAGER;


    /**
     * Gets role name.
     *
     * @return the role name
     */
    public String getRoleName() {
        return role;
    }


    /**
     * Instantiates a new Store manager.
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
    public StoreManager(int id, String name, long phoneNumber, int taxNumber,
                        String password, int civilNumber, String address,String mail) {
        super(id, name, phoneNumber, taxNumber, password, civilNumber,address,mail);

    }

    /**
     * Instantiates a new Store manager.
     *
     * @param name        the name
     * @param phoneNumber the phone number
     * @param taxNumber   the tax number
     * @param password    the password
     * @param civilNumber the civil number
     * @param mail        the mail
     */
    public StoreManager( String name, long phoneNumber, int taxNumber, String password, int civilNumber,String mail) {
        super(name, phoneNumber, taxNumber, password, civilNumber,mail);
    }


    public StoreManager(EmployeeDTO employeeDTO) {
        super(employeeDTO.getName(), employeeDTO.getPhoneNumber(), employeeDTO.getTaxNumber(), employeeDTO.getPassword(), employeeDTO.getCivilNumber(), employeeDTO.getAddress(), employeeDTO.getMail());
    }

    /**
     * Instantiates a new Store manager.
     *
     * @param name        the name
     * @param phoneNumber the phone number
     * @param taxNumber   the tax number
     * @param password    the password
     * @param civilNumber the civil number
     * @param address     the address
     * @param mail        the mail
     */
    public StoreManager( String name, long phoneNumber, int taxNumber,
                        String password, int civilNumber, String address,String mail) {
        super(name, phoneNumber, taxNumber, password, civilNumber,address,mail);

    }

    /**
     * Instantiates a new Store manager.
     *
     * @param id          the id
     * @param name        the name
     * @param phoneNumber the phone number
     * @param taxNumber   the tax number
     * @param password    the password
     * @param civilNumber the civil number
     * @param mail        the mail
     */
    public StoreManager(int id, String name, long phoneNumber, int taxNumber, String password, int civilNumber,String mail) {
        super(id, name, phoneNumber, taxNumber, password, civilNumber,mail);
    }

    public StoreManager clone()  {

        return new StoreManager(getId(),getName(),getPhoneNumber(),getTaxNumber(),getPassword(),getCivilNumber(),getAddress(),getMail());

    }

}
