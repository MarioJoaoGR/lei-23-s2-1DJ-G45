package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * The type Order.
 */
public class Order implements Serializable {
    private static int idCount =1;
    private int id;
    private String clientEmail;
    private double value;
    private Date date;
    private int state;
    //0-without response
    //1-decline
    //2-accepted


    /**
     * Instantiates a new Order.
     */
    public Order() {
    }

    /**
     * Instantiates a new Order.
     *
     * @param clientEmail the client email
     * @param value       the value
     * @param date        the date
     */
    public Order(String clientEmail, double value, Date date){
        this.id= idCount++;
        this.clientEmail =clientEmail;
        this.value =value;
        this.date = date;
        this.state=0;
    }


    /**
     * Instantiates a new Order.
     *
     * @param id          the id
     * @param clientEmail the client email
     * @param value       the value
     * @param date        the date
     * @param state       the state
     */
    public Order(int id, String clientEmail, double value, Date date, int state) {
        this.id = id;
        this.clientEmail = clientEmail;
        this.value = value;
        this.date = date;
        this.state = state;
    }


    public Order clone(){
        return new Order(this.id, this.clientEmail, this.value, this.date, this.state);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && value == order.value && date==order.date && clientEmail.equals(order.clientEmail) && state==order.state;

    }

    public int hashCode() {
        return Objects.hash(id, clientEmail, value, date, state);
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
     * Gets id count.
     *
     * @return the id count
     */
    public static int getIdCount() {
        return idCount;
    }

    /**
     * Sets id count.
     *
     * @param idCount the id count
     */
    public static void setIdCount(int idCount) {
        Order.idCount = idCount;
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

    public String toString(){
        return String.format("Order %d with the value of %.2f$", getId(), getValue());
    }


}
