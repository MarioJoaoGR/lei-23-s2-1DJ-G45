package pt.ipp.isep.dei.esoft.project.domain.DTOs;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * The type Announcement dto.
 */
public class AnnouncementDTO {
    private int id;
    private Commission comission;
    private Date date;
    private double requestPrice;
    private String businessType;
    private PropertyDTO property;
    private List<VisitRequestDTO> visitRequestList = new ArrayList<>();
    private AgentDTO agent;
    private List<OrderDTO> orderList = new ArrayList<>();
    private Integer contractDuration;
    private Double salePrice;
    private Date saleDate;
    private AgencyDTO agency;

    /**
     * Instantiates a new Announcement dto.
     */

    public AnnouncementDTO() {
    }

    /**
     * Instantiates a new Announcement dto.
     *
     * @param id               the id
     * @param comission        the comission
     * @param date             the date
     * @param businessType     the business type
     * @param property         the property
     * @param agent            the agent
     * @param orderList        the order list
     * @param contractDuration the contract duration
     * @param salePrice        the sale price
     * @param saleDate         the sale date
     * @param agency           the agency
     * @param visitRequest     the visit request
     * @param price            the price
     */
    public AnnouncementDTO(int id, Commission comission, Date date, String businessType, PropertyDTO property, AgentDTO agent, List<OrderDTO> orderList, Integer contractDuration, Double salePrice, Date saleDate, AgencyDTO agency, List<VisitRequestDTO> visitRequest, Double price) {
        this.id = id;
        this.comission = comission;
        this.date = date;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.orderList = orderList;
        this.contractDuration = contractDuration;
        this.salePrice = salePrice;
        this.saleDate = saleDate;
        this.agency = agency;
        this.visitRequestList = visitRequest;
        this.requestPrice = price;
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
     * Gets comission.
     *
     * @return the comission
     */
    public Commission getComission() {
        return comission;
    }

    /**
     * Sets comission.
     *
     * @param comission the comission
     */
    public void setComission(Commission comission) {
        this.comission = comission;
    }

    /**
     * Gets visit request list.
     *
     * @return the visit request list
     */
    public List<VisitRequestDTO> getVisitRequestList() {
        return visitRequestList;
    }

    /**
     * Sets visit request list.
     *
     * @param visitRequestList the visit request list
     */
    public void setVisitRequestList(List<VisitRequestDTO> visitRequestList) {
        this.visitRequestList = visitRequestList;
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
     * Gets business type.
     *
     * @return the business type
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * Sets business type.
     *
     * @param businessType the business type
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * Gets property.
     *
     * @return the property
     */
    public PropertyDTO getProperty() {
        return property;
    }

    /**
     * Sets property.
     *
     * @param property the property
     */
    public void setProperty(PropertyDTO property) {
        this.property = property;
    }

    /**
     * Gets agent.
     *
     * @return the agent
     */
    public AgentDTO getAgent() {
        return agent;
    }

    /**
     * Sets agent.
     *
     * @param agent the agent
     */
    public void setAgent(AgentDTO agent) {
        this.agent = agent;
    }

    /**
     * Gets order list.
     *
     * @return the order list
     */
    public List<OrderDTO> getOrderList() {
        return orderList;
    }

    /**
     * Sets order list.
     *
     * @param orderList the order list
     */
    public void setOrderList(List<OrderDTO> orderList) {
        this.orderList = orderList;
    }

    /**
     * Gets contract duration.
     *
     * @return the contract duration
     */
    public Integer getContractDuration() {
        return contractDuration;
    }

    /**
     * Sets contract duration.
     *
     * @param contractDuration the contract duration
     */
    public void setContractDuration(Integer contractDuration) {
        this.contractDuration = contractDuration;
    }

    /**
     * Gets sale price.
     *
     * @return the sale price
     */
    public Double getSalePrice() {
        return salePrice;
    }

    /**
     * Sets sale price.
     *
     * @param salePrice the sale price
     */
    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * Gets sale date.
     *
     * @return the sale date
     */
    public Date getSaleDate() {
        return saleDate;
    }

    /**
     * Sets sale date.
     *
     * @param saleDate the sale date
     */
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    /**
     * Gets agency.
     *
     * @return the agency
     */
    public AgencyDTO getAgency() {
        return agency;
    }

    /**
     * Sets agency.
     *
     * @param agency the agency
     */
    public void setAgency(AgencyDTO agency) {
        this.agency = agency;
    }

    /**
     * Gets request price.
     *
     * @return the request price
     */
    public double getRequestPrice() {
        return requestPrice;
    }

    /**
     * Sets request price.
     *
     * @param requestPrice the request price
     */
    public void setRequestPrice(double requestPrice) {
        this.requestPrice = requestPrice;
    }


    /**
     * Returns a string representation of the AnnouncementDTO object.
     *
     * @return a string representation of the AnnouncementDTO object
     */
    @Override
    public String toString() {
        String str ="id: " + this.getId() +
                "\ndate: " + this.getDate() +
                "\nprice: " + this.getRequestPrice() +
                "\nagent: " + this.getAgent().getName() +
                "\nbusiness Type: " + this.getBusinessType() +
                "\nproperty Type: " + this.getProperty().getPropertyType() +
                "\nlocation: " + getProperty().getLocation()+
                "\narea: " + getProperty().getArea()+
                "\ndistance center: " + getProperty().getDistanceCenter();

        if (property.getPropertyType().equalsIgnoreCase(PropertyType.APARTMENT.getDisplayName())) {
            str = str.concat("\nnumber rooms: "+ getProperty().getNumberRooms());
            str = str.concat("\nnumber bathrooms: "+ getProperty().getNumberBathrooms());
            str = str.concat("\nnumber parking: "+ getProperty().getNumberParking());
            str = str.concat("\nequipment: "+ getProperty().getEquipment());

        }

        if (property.getPropertyType().equalsIgnoreCase(PropertyType.HOUSE.getDisplayName())) {
            str = str.concat("\nnumber rooms: "+ getProperty().getNumberRooms());
            str = str.concat("\nnumber bathrooms: "+ getProperty().getNumberBathrooms());
            str = str.concat("\nnumber parking: "+ getProperty().getNumberParking());
            str = str.concat("\nequipment: "+ getProperty().getEquipment());
            str = str.concat("\nbasement: "+ getProperty().isBasement());
            str = str.concat("\nloft: "+ getProperty().isLoft());
            str = str.concat("\nsun exposure: " +property.getSunExposure());

        }

        return (str);

    }


    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementDTO that = (AnnouncementDTO) o;
        return Double.compare(that.requestPrice, requestPrice) == 0 && Objects.equals(comission, that.comission) && Objects.equals(date, that.date) && Objects.equals(businessType, that.businessType) && Objects.equals(property, that.property) && Objects.equals(visitRequestList, that.visitRequestList) && Objects.equals(agent, that.agent) && Objects.equals(orderList, that.orderList) && Objects.equals(contractDuration, that.contractDuration) && Objects.equals(salePrice, that.salePrice) && Objects.equals(saleDate, that.saleDate) && Objects.equals(agency, that.agency);
    }

    /**
     * Returns a hash code value for the AnnouncementDTO object.
     *
     * @return a hash code value for the AnnouncementDTO object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, comission, date, requestPrice, businessType, property, visitRequestList, agent, orderList, contractDuration, salePrice, saleDate, agency);
    }
}