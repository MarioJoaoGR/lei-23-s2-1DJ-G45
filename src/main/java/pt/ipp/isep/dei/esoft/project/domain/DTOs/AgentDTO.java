package pt.ipp.isep.dei.esoft.project.domain.DTOs;

import pt.ipp.isep.dei.esoft.project.domain.Agency;

/**
 * The type Agent dto.
 */
public class AgentDTO {

    /**
     * The Phone number.
     */
    long phoneNumber;
    /**
     * The Tax number.
     */
    int taxNumber;
    /**
     * The Civil number.
     */
    int civilNumber;
    /**
     * The Address.
     */
    String address;
    /**
     * The Mail.
     */
    String mail;
    /**
     * The Name.
     */
    String name;

    /**
     * Instantiates a new Agent dto.
     */
    public AgentDTO() {

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
     * Sets tax number.
     *
     * @param taxNumber the tax number
     */
    public void setTaxNumber(int taxNumber) {
        this.taxNumber = taxNumber;
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
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * Sets mail.
     *
     * @param mail the mail
     */
    public void setMail(String mail) {
        this.mail = mail;
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
     * Gets tax number.
     *
     * @return the tax number
     */
    public int getTaxNumber() {
        return taxNumber;
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
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }






    }
