package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.DTOs.OwnerDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Owner.
 */
public class Owner implements Serializable {

    private static int idCount=1;
    private int id;
    private String name;
    private long cardNumber;
    private long taxNumber;
    private String address;
    private String email;
    private long phoneNumber;
    private List<String> textMessages = new ArrayList<>();

    /**
     * Instantiates a new Owner.
     */
    public Owner() {

    }

    /**
     * Add message.
     *
     * @param sms the sms
     */
    public void addMessage(String sms) {
        textMessages.add(sms);
    }

    /**
     * Gets text messages.
     *
     * @return the text messages
     */
    public List<String> getTextMessages() {
        return textMessages;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner)) return false;
        Owner owner = (Owner) o;
        if (email == null || owner.email == null) return false; // Check if email is null
        if (name == null && address == null) {
            return cardNumber == owner.cardNumber && taxNumber == owner.taxNumber && phoneNumber == owner.phoneNumber && email.equals(owner.email);
        } else if (name == null) {
            return cardNumber == owner.cardNumber && taxNumber == owner.taxNumber && phoneNumber == owner.phoneNumber && address.equals(owner.address) && email.equals(owner.email);
        } else if (address == null) {
            return cardNumber == owner.cardNumber && taxNumber == owner.taxNumber && phoneNumber == owner.phoneNumber && name.equals(owner.name) && email.equals(owner.email);
        } else {
            return cardNumber == owner.cardNumber && taxNumber == owner.taxNumber && phoneNumber == owner.phoneNumber && name.equals(owner.name) && address.equals(owner.address) && email.equals(owner.email);
        }
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, cardNumber, taxNumber, address, email, phoneNumber);
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
    public void setCardNumber(int cardNumber) {
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
    public void setTaxNumber(int taxNumber) {
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

    /**
     * Instantiates a new Owner.
     *
     * @param name        the name
     * @param cardNumber  the card number
     * @param taxNumber   the tax number
     * @param address     the address
     * @param email       the email
     * @param phoneNumber the phone number
     */
    public Owner(String name, long cardNumber, long taxNumber, String address, String email, long phoneNumber) {
        this.id=idCount++;
        this.name = name;
        this.cardNumber = cardNumber;
        this.taxNumber = taxNumber;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Instantiates a new Owner.
     *
     * @param ownerDTO the owner dto
     */
    public Owner(OwnerDTO ownerDTO){
        this.name = ownerDTO.getName();
        this.cardNumber = ownerDTO.getCardNumber();
        this.taxNumber = ownerDTO.getTaxNumber();
        this.email = ownerDTO.getEmail();
        // need to verify if it is null and moreover this.address = getAddress();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }
}
