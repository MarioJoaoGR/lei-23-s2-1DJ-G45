package pt.ipp.isep.dei.esoft.project.domain.DTOs;

import pt.ipp.isep.dei.esoft.project.domain.StoreManager;

/**
 * The type Store manager dto.
 */
public class StoreManagerDTO {
    private int id;
    private String name;
    private long phoneNumber;
    private int taxNumber;
    private String password;
    private int civilNumber;
    private String address;
    private String mail;


    /**
     * Instantiates a new Store manager dto.
     */
    public StoreManagerDTO() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets tax number.
     *
     * @return the tax number
     */
    public int getTaxNumber() {
        return taxNumber;
    }

    /**
     * Sets tax number.
     *
     * @param taxNumber the tax number
     */
    public void setTaxNumber(int taxNumber) {
        this.taxNumber = taxNumber;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets civil number.
     *
     * @return the civil number
     */
    public int getCivilNumber() {
        return civilNumber;
    }

    /**
     * Sets civil number.
     *
     * @param civilNumber the civil number
     */
    public void setCivilNumber(int civilNumber) {
        this.civilNumber = civilNumber;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets mail.
     *
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets mail.
     *
     * @param mail the mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
}
