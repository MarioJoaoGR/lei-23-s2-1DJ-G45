package pt.ipp.isep.dei.esoft.project.domain;

import com.sun.security.auth.login.ConfigFile;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.EmployeeDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.Interface.ConfigFileWritter;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.User;

import java.io.*;
import java.util.*;

/**
 * The type Agent.
 */
public class Agent extends Employee implements ConfigFileWritter, Serializable{


    private final String ROLE = AuthenticationController.ROLE_AGENT;


    /**
     * Instantiates a new Agent.
     */
    public Agent() {
    }

    /**
     * Instantiates a new Agent.
     *
     * @param email the email
     * @param name  the name
     */
    public Agent(String email, String name) {
        super(email, name);
    }


    /**
     * constructor
     *
     * @param id          agent id
     * @param name        agent name
     * @param phoneNumber agent phone number
     * @param taxNumber   agent tax number
     * @param password    agent password
     * @param civilNumber agent civil number
     * @param address     agent address
     * @param mail        agent email
     */
    public Agent(int id, String name, long phoneNumber, int taxNumber, String password, int civilNumber, String address, String mail) {
        super(id, name, phoneNumber, taxNumber, password, civilNumber, address, mail);
    }

    /**
     * constructor
     *
     * @param name        agent name
     * @param phoneNumber agent phone number
     * @param taxNumber   agent tax number
     * @param password    agent password
     * @param civilNumber agent civil number
     * @param address     agent address
     * @param mail        the mail
     */
    public Agent(String name, long phoneNumber, int taxNumber, String password, int civilNumber, String address, String mail) {
        super(name, phoneNumber, taxNumber, password, civilNumber, address, mail);
    }


    public Agent(EmployeeDTO employeeDTO) {
        super(employeeDTO.getName(), employeeDTO.getPhoneNumber(), employeeDTO.getTaxNumber(), employeeDTO.getPassword(), employeeDTO.getCivilNumber(), employeeDTO.getAddress(), employeeDTO.getMail());
    }

    /**
     * constructor
     *
     * @param id          agent id
     * @param name        agent name
     * @param phoneNumber agent phone number
     * @param taxNumber   agent tax number
     * @param password    agent password
     * @param civilNumber agent civil number
     * @param mail        the mail
     */
    public Agent(int id, String name, int phoneNumber, int taxNumber, String password, int civilNumber, String mail) {
        super(id, name, phoneNumber, taxNumber, password, civilNumber, mail);

    }

    /**
     * Instantiates a new Agent.
     *
     * @param name        the name
     * @param phoneNumber the phone number
     * @param taxNumber   the tax number
     * @param password    the password
     * @param civilNumber the civil number
     * @param mail        the mail
     */
    public Agent(String name, int phoneNumber, int taxNumber, String password, int civilNumber, String mail) {
        super(name, phoneNumber, taxNumber, password, civilNumber, mail);
    }


    /**
     * get role
     *
     * @return agent role
     */

    @Override
    public int getId() {
        return super.getId();
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getROLE() {
        return ROLE;
    }


    /**
     * do an agent clone
     *
     * @return agent clone
     */


    public Agent clone() {

        return new Agent(getId(), getName(), getPhoneNumber(), getTaxNumber(), getPassword(), getCivilNumber(), getAddress(), getMail());

    }

    /**
     * Is agent boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean isAgent(String email){
        return  email.equalsIgnoreCase(getMail());
    }


    @Override
    public void configFileWritter(String fileType, long phoneNumber, String agentName, int idProperty, String locationProperty, String response) {
        Properties props = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream("config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        props.setProperty("File type", fileType);
        props.setProperty("Agent phone number", String.valueOf(phoneNumber));
        props.setProperty("Agent name", String.valueOf(agentName));
        props.setProperty("Property id", String.valueOf(idProperty));
        props.setProperty("Property location", locationProperty);
        props.setProperty("Response", response);
        OutputStream out = null;
        try {
            out = new FileOutputStream("config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            props.store(out, "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}

