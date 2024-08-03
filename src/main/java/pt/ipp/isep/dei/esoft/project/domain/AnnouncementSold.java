package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * The type Announcement.
 */
public class AnnouncementSold implements Serializable {

    /**
     * The constant idCount.
     */
    public  static  int idCount=1;
    private int id;
    private Commission comission;
    private double price;
    private Date date;

    private String businessType;

    private Property property;

    private Agent agent;

    private List<Order> orderList = new ArrayList<>();

    private int ContractDuraction=0;

    private double salePrice;
    private Date saleDate;

    private Agency agency;


    /**
     * Instantiates a new Announcement sold.
     */
    public AnnouncementSold() {
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
        AnnouncementSold.idCount = idCount;
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
     * Gets agent.
     *
     * @return the agent
     */
    public Agent getAgent() {
        return agent;
    }


    /**
     * Instantiates a new Announcement.
     *
     * @param comission         the comission
     * @param date              the date
     * @param business          the business
     * @param property          the property
     * @param contractDuraction the contract duraction
     * @param saleDate          the sale date
     * @param salePrice         the sale price
     * @param agency            the agency
     */
    /*public AnnouncementSold(double comission, double price, Date date, String business, Property property, Agent agent, int contractDuraction) {
        this.id = idCount++;
        this.comission = comission;
        this.price = price;
        this.date = date;
        this.businessType = business;
        this.property = property;
        this.agent=agent;
        this.agency=agent.getAgencia();
        this.ContractDuraction=contractDuraction;
        this.saleDate=null;
        this.salePrice=0;
    }*/
    public AnnouncementSold(Commission comission,double requestPrice ,Date date, String business, Property property,  int contractDuraction, Date saleDate, double salePrice,  Agency agency) {
        this.id = idCount++;
        this.comission = comission;
        this.price = requestPrice;
        this.date = date;
        this.businessType = business;
        this.property = property;
        this.agent=null;
        this.ContractDuraction=contractDuraction;
        this.saleDate=saleDate;
        this.salePrice=salePrice;
        this.agency=agency;
    }


   //TODO: metodo comentado para poder testar outro us
    /* public AnnouncementSold( double comission, double price, Date date, String business, Property property, Agent agent, List<Order> orderList, int contractDuraction, Date saleDate, double salePrice){
        this.id=idCount++;
        this.comission=comission;
        this.price=price;
        this.date=date;
        this.businessType= business;
        this.property=property;
        this.agent=agent;
        this.agency=agent.getAgencia();
        this.orderList = orderList;
        this.ContractDuraction=contractDuraction;
        this.saleDate=saleDate;
        this.salePrice=salePrice;
    }*/


    /**
     * Instantiates a new Announcement.
     *
     * @param id                the id from announcement
     * @param comission         the comission from agent
     * @param price             the price from announcement
     * @param date              the date from announcement
     * @param business          the businessType
     * @param property          the property object
     * @param agent             the agent
     * @param orderList         the order list
     * @param contractDuraction the contract duraction
     * @param saleDate          the sale date
     * @param salePrice         the sale price
     * @param agency            the agency
     */
    public AnnouncementSold(int id, Commission comission, double price, Date date, String business, Property property, Agent agent, List<Order> orderList, int contractDuraction, Date saleDate, double salePrice, Agency agency){
        this.id=id;
        this.comission=comission;
        this.price=price;
        this.date=date;
        this.businessType= business;
        this.property=property;
        this.agent=agent;
        this.agency=agency;
        this.orderList = orderList;
        this.ContractDuraction=contractDuraction;
        this.saleDate=saleDate;
        this.salePrice=salePrice;
    }

    /**
     * Instantiates a new Announcement sold.
     *
     * @param id                the id
     * @param comission         the comission
     * @param price             the price
     * @param date              the date
     * @param businessType      the business type
     * @param property          the property
     * @param agent             the agent
     * @param orderList         the order list
     * @param contractDuraction the contract duraction
     * @param salePrice         the sale price
     * @param saleDate          the sale date
     * @param agency            the agency
     */
    public AnnouncementSold(int id, Commission comission, double price, Date date, String businessType, Property property, Agent agent, List<Order> orderList, int contractDuraction, double salePrice, Date saleDate, Agency agency) {
        this.id = id;
        this.comission = comission;
        this.price = price;
        this.date = date;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.orderList = orderList;
        ContractDuraction = contractDuraction;
        this.salePrice = salePrice;
        this.saleDate = saleDate;
        this.agency = agency;
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
     * Gets id.
     *
     * @return the id
     */
    public int getId() {return id;}

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {this.id = id;}

    /**
     * Gets comission.
     *
     * @return the comission
     */
    public Commission getComission() {return comission;}

    /**
     * Sets comission.
     *
     * @param comission the comission
     */
    public void setComission(Commission comission) {this.comission = comission;}

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {return price;}

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {this.price = price;}

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {return date;}

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {this.date = date;}


    /**
     * Gets property.
     *
     * @return the property
     */
    public Property getProperty() {return property;}

    /**
     * Sets property.
     *
     * @param property the property
     */
    public void setProperty(Property property) {this.property = property;}

    public AnnouncementSold clone() {
        return new AnnouncementSold(this.id,this.comission,this.price,this.date,this.businessType,this.property,this.agent,this.orderList,this.ContractDuraction,this.saleDate,this.salePrice,this.agency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementSold that = (AnnouncementSold) o;
        return Double.compare(that.price, price) == 0 && ContractDuraction == that.ContractDuraction && Double.compare(that.salePrice, salePrice) == 0 && Objects.equals(comission, that.comission) && Objects.equals(date, that.date) && Objects.equals(businessType, that.businessType) && Objects.equals(property, that.property) && Objects.equals(agent, that.agent) && Objects.equals(orderList, that.orderList) && Objects.equals(saleDate, that.saleDate) && Objects.equals(agency, that.agency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comission, price, date, businessType, property, agent, orderList, ContractDuraction, salePrice, saleDate, agency);
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
    public Order createOrder(String email,double value){
        return new Order(email,value,new Date());
    }

    /**
     * Add order optional.
     *
     * @param order the order
     * @return the optional
     */
    public Optional<Order> addOrder(Order order) {

        Optional<Order> newOrder = Optional.empty();
        boolean operationSuccess = false;
        if (validateOrder(order)) {
            newOrder = Optional.of(order.clone());
            operationSuccess = orderList.add(newOrder.get());
        }
        if (!operationSuccess) {
            newOrder  = Optional.empty();
        }
        return newOrder;
    }


    private boolean validateOrder(Order order) {
        return orderListDoNotContain(order);
    }


    private boolean orderListDoNotContain(Order order) {
        return !orderList.contains(order);
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
     * Gets contract duraction.
     *
     * @return the contract duraction
     */
    public int getContractDuraction() {
        return ContractDuraction;
    }

    /**
     * Sets contract duraction.
     *
     * @param contractDuraction the contract duraction
     */
    public void setContractDuraction(int contractDuraction) {
        ContractDuraction = contractDuraction;
    }

    /**
     * Gets sale price.
     *
     * @return the sale price
     */
    public double getSalePrice() {
        return salePrice;
    }

    /**
     * Sets sale price.
     *
     * @param salePrice the sale price
     */
    public void setSalePrice(double salePrice) {
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
    public Agency getAgency() {
        return agency;
    }

    public Agency getAgencyFromRepository() {
        AgencyRepository repository = Repositories.getInstance().getAgencyRepository();
        for (Agency agency : repository.getAgencyList()){
            if (agency.equals(this.getAgency())){
                return agency;
            }
        }
        return null;
    }
    /**
     * Sets agency.
     *
     * @param agency the agency
     */
    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        return dateFormat.format(saleDate) + ", price=" + salePrice +", "+  businessType + " " + property.getPropertyType() + " in " + property.getLocation();

    }


}
