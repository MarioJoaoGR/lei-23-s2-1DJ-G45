package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.DTOs.EmployeeDTO;

import java.io.Serializable;

/**
 * The type Store network manager.
 */
public class NetworkManager extends Employee implements Serializable {

    private final String role = "Store Network Manager";// coloca a role como storenetworkmanager


    /**
     * Instantiates a new Store network manager.
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
    public NetworkManager(int id, String name, long phoneNumber, int taxNumber, String password, int civilNumber, String address, String mail) {
        super(id, name, phoneNumber, taxNumber, password, civilNumber, address, mail);

    }

    /**
     * Instantiates a new Store network manager.
     *
     * @param id          the id
     * @param name        the name
     * @param phoneNumber the phone number
     * @param taxNumber   the tax number
     * @param password    the password
     * @param civilNumber the civil number
     * @param mail        the mail
     */
    public NetworkManager(int id, String name, long phoneNumber, int taxNumber, String password, int civilNumber, String mail) {
        super(id, name, phoneNumber, taxNumber, password, civilNumber, mail);
    }

    /**
     * Instantiates a new Network manager.
     *
     * @param employeeDTO the employee dto
     */
    public NetworkManager(EmployeeDTO employeeDTO) {
        super(employeeDTO.getName(), employeeDTO.getPhoneNumber(), employeeDTO.getTaxNumber(), employeeDTO.getPassword(), employeeDTO.getCivilNumber(), employeeDTO.getAddress(), employeeDTO.getMail());
    }


    /**
     * Instantiates a new Store network manager.
     *
     * @param name        the name
     * @param phoneNumber the phone number
     * @param taxNumber   the tax number
     * @param password    the password
     * @param civilNumber the civil number
     * @param address     the address
     * @param mail        the mail
     */
    public NetworkManager(String name, long phoneNumber, int taxNumber, String password, int civilNumber, String address, String mail) {
        super(name, phoneNumber, taxNumber, password, civilNumber, address, mail);

    }

    /**
     * Instantiates a new Store network manager.
     *
     * @param name        the name
     * @param phoneNumber the phone number
     * @param taxNumber   the tax number
     * @param password    the password
     * @param civilNumber the civil number
     * @param mail        the mail
     */
    public NetworkManager(String name, long phoneNumber, int taxNumber, String password, int civilNumber, String mail) {
        super(name, phoneNumber, taxNumber, password, civilNumber, mail);
    }

    public NetworkManager clone() {

        return new NetworkManager(getId(), getName(), getPhoneNumber(), getTaxNumber(), getPassword(), getCivilNumber(), getAddress(), getMail());
    }
}
