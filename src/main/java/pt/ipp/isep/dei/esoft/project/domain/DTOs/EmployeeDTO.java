package pt.ipp.isep.dei.esoft.project.domain.DTOs;

import pt.ipp.isep.dei.esoft.project.domain.Employee;

import java.util.Objects;

/**
 * The type Employee dto.
 */
public class EmployeeDTO {
    private int id;
    private static int idCount = 1;
    private String name;
    private long phoneNumber;
    private int taxNumber;
    private String password;
    private int civilNumber;
    private String address;
    private String mail;
    private String role;
    private final String ADDRESS_BY_OMISSION = "No address";

    /**
     * Instantiates a new Employee dto.
     */
    public EmployeeDTO() {
    }


    /**
     * Gets id.
     *
     * @return Employye 's id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets mail.
     *
     * @return Empoloyee 's email
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * Sets id.
     *
     * @param id Employee's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets name.
     *
     * @return Employee 's name
     */
    public String getName() {
        return name;
    }

    /**
     * changes Employee's name
     *
     * @param name Employee's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets phone number.
     *
     * @return Employee 's phone number
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * changes Employee's phone number
     *
     * @param phoneNumber Employee's phone number
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets tax number.
     *
     * @return Employee 's taxnumber
     */
    public int getTaxNumber() {
        return taxNumber;
    }

    /**
     * * changes Employee's taxnumber
     *
     * @param taxNumber Employee's taxnumber
     */
    public void setTaxNumber(int taxNumber) {
        this.taxNumber = taxNumber;
    }

    /**
     * Gets password.
     *
     * @return Employee 's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * * changes Employee's password
     *
     * @param password Employee's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets civil number.
     *
     * @return Employee 's civil number
     */
    public int getCivilNumber() {
        return civilNumber;
    }

    /**
     * * changes Employee's civil number
     *
     * @param civilNumber Employee's civil number
     */
    public void setCivilNumber(int civilNumber) {
        this.civilNumber = civilNumber;
    }

    /**
     * Gets address.
     *
     * @return EMployee 's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * * changes Employee's address
     *
     * @param address Employee's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * changes Employee´s email
     *
     * @param mail the mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }


}
