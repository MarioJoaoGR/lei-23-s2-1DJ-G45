package pt.ipp.isep.dei.esoft.project.domain.DTOs;

import pt.ipp.isep.dei.esoft.project.domain.Agency;

import java.util.Date;

/**
 * The type Order dto.
 */
public class OrderDTO {

    private int id;
    private String clientEmail;
    private double value;
    private Date date;
    private int state;

    /**
     * Instantiates a new Order dto.
     */
//Contructor
    public OrderDTO() {
    }

    /**
     * Instantiates a new Order dto.
     *
     * @param id          the id
     * @param clientEmail the client email
     * @param value       the value
     * @param date        the date
     * @param state       the state
     */
    public OrderDTO(int id, String clientEmail, double value, Date date, int state) {
        this.id = id;
        this.clientEmail = clientEmail;
        this.value = value;
        this.date = date;
        this.state = state;
    }


    /**
     * Gets id.
     *
     * @return the id
     */
//gets e sets
    public int getId() {
        return id;
    }

    /**
     * Gets client email.
     *
     * @return the client email
     */
    public String getClientEmail() {
        return clientEmail;
    }

    /**
     * Sets client email.
     *
     * @param clientEmail the client email
     */
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public int getState() {
        return state;
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
     * Sets state.
     *
     * @param state the state
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * Returns a string representation of the OrderDTO object.
     *
     * @return a string representation of the OrderDTO object
     */
    @Override
    public String toString(){
        return String.format("Order %d with the value of %.2f$", getId(), getValue());
    }
}



