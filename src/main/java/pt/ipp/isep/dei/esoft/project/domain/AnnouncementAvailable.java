package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgentDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.OrderDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgentMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AnnouncementMapper;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.io.Serializable;
import java.util.*;


/**
 * The type Announcement.
 */
public class AnnouncementAvailable implements Serializable {

    /**
     * The constant idCount.
     */
    public static int idCount = 0;
    private int id;
    /**
     * The Commission.
     */
    Commission commission;
    private Date date;

    private double price;
    /**
     * The Visit requests.
     */
    List<VisitRequest> visitRequests = new ArrayList<>();
    private String businessType;
    private Property property;
    private Agent agent;
    private List<Order> orderList = new ArrayList<>();

    /**
     * Instantiates a new Announcement available.
     *
     * @param commission    the commission
     * @param date          the date
     * @param price         the price
     * @param visitRequests the visit requests
     * @param businessType  the business type
     * @param property      the property
     * @param agent         the agent
     * @param orderList     the order list
     */
    public AnnouncementAvailable(Commission commission, Date date, double price, List<VisitRequest> visitRequests, String businessType, Property property, Agent agent, List<Order> orderList) {
        this.id = idCount++;
        this.commission = commission;
        this.date = date;
        this.price = price;
        this.visitRequests = visitRequests;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.orderList = orderList;
    }

    /**
     * Instantiates a new Announcement available.
     *
     * @param id            the id
     * @param commission    the commission
     * @param date          the date
     * @param visitRequests the visit requests
     * @param businessType  the business type
     * @param property      the property
     * @param agent         the agent
     * @param orderList     the order list
     * @param price         the price
     */
    public AnnouncementAvailable(int id, Commission commission, Date date, List<VisitRequest> visitRequests, String businessType, Property property, Agent agent, List<Order> orderList, double price) {
        this.id = id;
        this.commission = commission;
        this.date = date;
        this.visitRequests = visitRequests;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.orderList = orderList;
        this.price = price;
    }

    /**
     * Instantiates a new Announcement available.
     *
     * @param commission   the commission
     * @param price        the price
     * @param date         the date
     * @param businessType the business type
     * @param property     the property
     * @param agent        the agent
     */
    public AnnouncementAvailable(Commission commission, double price, Date date, String businessType, Property property, Agent agent) {
        this.id = idCount++;
        this.commission = commission;
        this.date = date;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.price = price;
    }


    /**
     * Instantiates a new Announcement available.
     *
     * @param commission    the commission
     * @param price         the price
     * @param date          the date
     * @param visitRequests the visit requests
     * @param businessType  the business type
     * @param property      the property
     * @param agent         the agent
     */
    public AnnouncementAvailable(Commission commission, double price, Date date, List<VisitRequest> visitRequests, String businessType, Property property, Agent agent) {
        this.id = idCount++;
        this.commission = commission;
        this.date = date;
        this.visitRequests = visitRequests;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.orderList = orderList;
        this.price = price;
    }


    /**
     * Instantiates a new Announcement available.
     *
     * @param commission    the commission
     * @param price         the price
     * @param date          the date
     * @param visitRequests the visit requests
     * @param businessType  the business type
     * @param property      the property
     * @param agent         the agent
     * @param orderList     the order list
     */
    public AnnouncementAvailable(Commission commission, double price, Date date, List<VisitRequest> visitRequests, String businessType, Property property, Agent agent, List<Order> orderList) {
        this.id = idCount++;
        this.commission = commission;
        this.date = date;
        this.visitRequests = visitRequests;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.orderList = orderList;
        this.price = price;
    }

    /**
     * Instantiates a new Announcement available.
     */
    public AnnouncementAvailable() {

    }

    public AnnouncementAvailable clone() {
        return new AnnouncementAvailable(this.id, this.commission, this.date, this.visitRequests, this.businessType, this.property, this.agent, this.orderList, this.price);
    }


    /**
     * Gets commission value.
     *
     * @return the commission value
     */
    public double getCommissionValue() {
        return this.commission.getCommissionValue();
    }

    /**
     * Gets commission type.
     *
     * @return the commission type
     */
    public String getCommissionType() {
        return this.commission.getCommissionType();
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
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets commission.
     *
     * @return the commission
     */
    public Commission getCommission() {
        return commission;
    }

    /**
     * Sets commission.
     *
     * @param commission the commission
     */
    public void setCommission(Commission commission) {
        this.commission = commission;
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
    public Property getProperty() {
        return property;
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
     * Sets property.
     *
     * @param property the property
     */
    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * Gets agent.
     *
     * @return the agent
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Sets agent.
     *
     * @param agent the agent
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    /**
     * Gets visit requests.
     *
     * @return the visit requests
     */
    public List<VisitRequest> getVisitRequests() {
        return visitRequests;
    }

    /**
     * Sets visit requests.
     *
     * @param visitRequests the visit requests
     */
    public void setVisitRequests(List<VisitRequest> visitRequests) {
        this.visitRequests = visitRequests;
    }

    /**
     * this method checks announcement if correspond at parametres of search.
     *
     * @param business the business
     * @param type     the type
     * @param room     the room
     * @return the boolean
     */
    public boolean filterProperty(String business, String type, int room) {
        if (Objects.equals(business, "") && !Objects.equals(type, "") && room != 0) {
            return property.isPropertyType(type) && property.numberRooms(room) & !property.isPropertyType(PropertyType.LAND.getDisplayName());
        } else if (Objects.equals(business, "") && Objects.equals(type, "") && room != 0) {
            return property.numberRooms(room) && !property.isPropertyType(PropertyType.LAND.getDisplayName());
        } else if (!Objects.equals(business, "") && Objects.equals(type, "") && room != 0) {
            return property.numberRooms(room) && isBusiness(business) & !property.isPropertyType(PropertyType.LAND.getDisplayName());
        } else if (!Objects.equals(business, "") && Objects.equals(type, "") && room == 0) {
            return isBusiness(business);
        } else if (Objects.equals(business, "") && !Objects.equals(type, "") && room == 0) {
            return property.isPropertyType(type);
        } else if (!Objects.equals(business, "") && !Objects.equals(type, "") && room == 0) {
            return property.isPropertyType(type) && isBusiness(business);
        } else if (!Objects.equals(business, "") && !Objects.equals(type, "") && room != 0) {
            if (type.equalsIgnoreCase(PropertyType.LAND.getDisplayName())) {
                return isBusiness(business) && property.isPropertyType(type);
            } else {
                return isBusiness(business) && property.isPropertyType(type) && property.numberRooms(room);
            }
        } else {
            return false;
        }

    }

    /**
     * this method check if business correspond at the parametre value
     *
     * @param business the business
     * @return the boolean
     */
    public boolean isBusiness(String business) {
        return this.businessType.equalsIgnoreCase(business);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementAvailable announcementAvailable = (AnnouncementAvailable) o;
        return commission.equals(announcementAvailable.getCommission()) && date.equals(announcementAvailable.getDate()) && businessType.equals(announcementAvailable.getBusinessType()) && property.equals(announcementAvailable.getProperty()) && price==announcementAvailable.getPrice() && agent.equals(announcementAvailable.getAgent());

    }

    public int hashCode() {
        return Objects.hash(id, commission, date, businessType, property);
    }

    /**
     * Gets order list.
     *
     * @return the order list
     */
    public List<Order> getOrderList() {
        return orderList;
    }

    /**
     * Create order order.
     *
     * @param email the email
     * @param value the value
     * @return the order
     */
    public Order createOrder(String email, double value) {
        return new Order(email, value, new Date());
    }

    /**
     * Add order optional.
     *
     * @param order the order
     * @return the optional
     */
    public Optional<Order> addOrder(Order order) {

        AnnouncementController controller = new AnnouncementController();

        Optional<Order> newOrder = Optional.empty();

        if (validateOrderValue(order) && validateOrderValueRepeat(order) && validateOrderRepeatClient(order)) {
            orderList.add(order);
            newOrder = Optional.of(order);

        }

        return newOrder;
    }



    /**
     * Validates if the order value is lower or equal to the requested price.
     *
     * @param order the order to be validated
     * @return true if the order value is valid, false otherwise
     */
    private boolean validateOrderValue(Order order) {
        if (order.getValue() > price) {
            System.out.println("The order price must be lower or equals to resquested price");

            return false;
        }
        return true;
    }


    /**
     * Validates if there is no other order with the same value.
     *
     * @param order the order to be validated
     * @return true if the order value is unique, false otherwise
     */
    private boolean validateOrderValueRepeat(Order order) {
        for (Order o : orderList) {
            if (o.getValue() == order.getValue()) {

                System.out.println("There is another order with the same value.");

                return false;
            }
        }

        return true;
    }

    /**
     * Validates if the client has another pending order in this business.
     *
     * @param order the order to be validated
     * @return true if the client's order is unique, false otherwise
     */
    private boolean validateOrderRepeatClient(Order order) {
        Order aux = new Order();
        int position = 0;
        for (Order o : orderList) {
            if (o.getClientEmail().equalsIgnoreCase(order.getClientEmail())) {
                aux = orderList.get(position);
                if (aux.getState() == 0) {
                    System.out.println("The user has another order pending in this business");

                    return false;
                }
            }
            position++;
        }

        return true;
    }

    /**
     * Checks if the order list does not contain the specified order.
     *
     * @param order the order to be checked
     * @return true if the order list does not contain the order, false otherwise
     */
    private boolean orderListDoNotContain(Order order) {
        return !orderList.contains(order);
    }

    /**
     * To dto announcement dto.
     *
     * @return the announcement dto
     */
    public AnnouncementDTO toDTO() {
        AnnouncementMapper announcementMapper = new AnnouncementMapper();
        return announcementMapper.toAnnouncementDTO(this);
    }

    @Override
    public String toString() {
        return "AnnouncementAvailable{" +
                "id=" + id +
                ", price=" + this.commission.getCommissionValue() +
                ", date=" + date +
                ", businessType='" + businessType + '\'' +
                ", property=" + property +
                ", agent=" + agent +
                '}';
    }

    /**
     * To string to show in javafx.
     *
     * @return the string
     */
    public String toStringfx() {
        if (this.getProperty() instanceof Land){
            Land land= (Land) this.getProperty();
            return PropertyType.LAND.getDisplayName() + " in " + land.getLocation();
        } else if (this.getProperty() instanceof Apartment) {
            Apartment apartment= (Apartment) this.getProperty();
            return PropertyType.APARTMENT.getDisplayName() + " T"+ apartment.getNumberRooms() + " in " + apartment.getLocation();
        }else if (this.getProperty() instanceof House) {
            House house= (House) this.getProperty();
            return PropertyType.HOUSE.getDisplayName() + " T"+ house.getNumberRooms() + " in " + house.getLocation();
        }
        return null;
    }

    /**
     * Sets order list.
     *
     * @param orderList the order list
     */
    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    /**
     * Create visit request visit request.
     *
     * @param visitRequestDTO the visit request dto
     * @return the visit request
     */
    public VisitRequest createVisitRequest(VisitRequestDTO visitRequestDTO) {
        AgentMapper agentMapper = new AgentMapper();
        Agent agent1 = agentMapper.fromDTO(visitRequestDTO.getAgentDTO());
        return new VisitRequest(visitRequestDTO.getDate(), visitRequestDTO.getTimeSlot(), visitRequestDTO.getUserName(), visitRequestDTO.getEmail(), agent1);

    }

    /**
     * Add visit requests optional.
     *
     * @param visitRequest the visit request
     * @return the optional
     */
    public Optional<VisitRequest> addVisitRequests(VisitRequest visitRequest) {

        Optional<VisitRequest> newMessage = Optional.empty();

        boolean operationSuccess = false;

        if (validVisitRequest(visitRequest)) {
            newMessage = Optional.of(visitRequest.cloneVisitRequest());
            operationSuccess = visitRequests.add(newMessage.get());
        }

        if (!operationSuccess) {
            newMessage = Optional.empty();
        }

        return newMessage;

    }

    /**
     * Valid visit request boolean.
     *
     * @param newVisitRequest the new visit request
     * @return the boolean
     */
    public boolean validVisitRequest(VisitRequest newVisitRequest) {
        boolean flag = true;
        for (VisitRequest a : visitRequests) {

            if (a.equals(newVisitRequest)) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }


    /**
     * Accept visit request.
     *
     * @param vr the vr
     * @param s  the s
     */
    public void acceptVisitRequest(VisitRequest vr, String s) {

        for (VisitRequest visitRequest : visitRequests) {
            if (visitRequest.equals(vr)) {
                visitRequest.setStatus("Approved");
                for (String t : visitRequest.getTimeSlot()) {
                    if (!t.equals(s)) {
                        visitRequest.getTimeSlot().remove(t);
                    }
                }
            } else {
                visitRequests.remove(visitRequest);
            }
        }
    }

    /**
     * Reject time request.
     *
     * @param vr the vr
     */
    public void rejectTimeRequest(VisitRequest vr) {
        for (VisitRequest visitRequest : visitRequests) {
            if (visitRequest.equals(vr)) {
                visitRequest.setStatus("Not approved");

            } else {
                visitRequests.remove(visitRequest);
            }
        }

    }


    /**
     * Counter purpose.
     *
     * @param visitRequest         the visit request
     * @param listCounterTimeSlots the list counter time slots
     */
    public void counterPurpose(VisitRequest visitRequest, List<String> listCounterTimeSlots) {

        for (VisitRequest v : visitRequests) {
            if (visitRequest.equals(v)) {
                visitRequest.setStatus("CounterProposal");
                v.setTimeSlot(listCounterTimeSlots);
            } else {
                visitRequests.remove(visitRequest);
            }
        }
    }

    /**
     * Get visit request for agent.
     *
     * @param email     the email
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     */
    public List<VisitRequest> getVisitRequestForAgent (String email,Date startDate,Date endDate){
        List<VisitRequest> result= new ArrayList<>();

        if(this.agent.isAgent(email)){
            for (VisitRequest req : visitRequests){
                if (req.isDateBetween(startDate,endDate) && req.isStateWaiting()){
                    result.add(req);
                }
            }
        }

        return result;
    }

    /**
     * Get all visit requests for agent.
     *
     * @param email the email
     * @return the list
     */
    public List<VisitRequest> getAllVisitRequestForAgent (String email){
        List<VisitRequest> result= new ArrayList<>();

        if(this.agent.isAgent(email)){
            for (VisitRequest req : visitRequests){
                    result.add(req);

            }
        }

        return result;
    }




}
