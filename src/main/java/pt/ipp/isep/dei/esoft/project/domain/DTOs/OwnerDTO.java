package pt.ipp.isep.dei.esoft.project.domain.DTOs;

/**
 * The type Owner dto.
 */
public class OwnerDTO {


    /**
     * The Name.
     */
    String name;
    /**
     * The Card number.
     */
    long cardNumber;
    /**
     * The Tax number.
     */
    long taxNumber;
    /**
     * The Address.
     */
    String address;
    /**
     * The Email.
     */
    String email;
    /**
     * The Phone number.
     */
    long phoneNumber;


    /**
     * Instantiates a new Owner dto.
     */
    public OwnerDTO() {

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
     * Gets card number.
     *
     * @return the card number
     */
    public long getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets card number.
     *
     * @param cardNumber the card number
     */
    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets tax number.
     *
     * @return the tax number
     */
    public long getTaxNumber() {
        return taxNumber;
    }

    /**
     * Sets tax number.
     *
     * @param taxNumber the tax number
     */
    public void setTaxNumber(long taxNumber) {
        this.taxNumber = taxNumber;
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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
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
}
