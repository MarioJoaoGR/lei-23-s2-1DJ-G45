package pt.ipp.isep.dei.esoft.project.domain.Adapter;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;

import java.util.Date;

/**
 * Represents an external module adapter for CSV files.
 * This class provides methods to convert CSV data into domain objects.
 */
public class ExternalModuleCSV extends ExternalModuleAdapterCSV{
    private int sid;
    private String owner_name;
    private long owner_passportNum;
    private long owner_TIN;
    private String owner_email;
    private long owner_phone;
    private String property_type;
    private double property_area;
    private String property_location;
    private double property_distanceFromCenter;
    private int property_numberBedrooms;
    private int property_numberBathrooms;
    private int property_pnumParking;
    private boolean property_centralHeating;
    private boolean property_airconditioned;
    private boolean property_basement;
    private boolean property_loft;
    private String property_sunExposure;
    private double property_requested_sale_rent_price;
    private double property_sale_rent_price;

    private Commission commission;
    private int contract_duration;
    private Date property_dateAnnounceRequest;
    private Date property_dateofSale;
    private String type_business;
    private int store_ID;
    private String store_name;
    private String store_location;
    private long store_phonenumber;
    private String store_emailAddress;


    /**
     * Instantiates a new External module csv.
     *
     * @param sid                                the sid
     * @param owner_name                         the owner name
     * @param owner_passportNum                  the owner passport num
     * @param owner_TIN                          the owner tin
     * @param owner_email                        the owner email
     * @param owner_phone                        the owner phone
     * @param property_type                      the property type
     * @param property_area                      the property area
     * @param property_location                  the property location
     * @param property_distanceFromCenter        the property distance from the center
     * @param property_numberBedrooms            the property number bedrooms
     * @param property_numberBathrooms           the property number bathrooms
     * @param property_pnumParking               the property pnum parking
     * @param property_centralHeating            the property central heating
     * @param property_airconditioned            the property airconditioned
     * @param property_basement                  the property basement
     * @param property_loft                      the property loft
     * @param property_sunExposure               the property sun exposure
     * @param property_requested_sale_rent_price the property requested sale rent price
     * @param property_sale_rent_price           the property sale rent price
     * @param commission                         the commission
     * @param contract_duration                  the contract duration
     * @param property_dateAnnounceRequest       the property date announces request
     * @param property_dateofSale                the property dateof sale
     * @param type_business                      the type business
     * @param store_ID                           the store id
     * @param store_name                         the store name
     * @param store_location                     the store location
     * @param store_phonenumber                  the store phonenumber
     * @param store_emailAddress                 the store email address
     */
    public ExternalModuleCSV(int sid, String owner_name, long owner_passportNum, long owner_TIN, String owner_email, long owner_phone, String property_type, double property_area, String property_location, double property_distanceFromCenter, int property_numberBedrooms, int property_numberBathrooms, int property_pnumParking, boolean property_centralHeating, boolean property_airconditioned, boolean property_basement, boolean property_loft, String property_sunExposure, double property_requested_sale_rent_price, double property_sale_rent_price, Commission commission, int contract_duration, Date property_dateAnnounceRequest, Date property_dateofSale, String type_business, int store_ID, String store_name, String store_location, long store_phonenumber, String store_emailAddress) {
        this.sid = sid;
        this.owner_name = owner_name;
        this.owner_passportNum = owner_passportNum;
        this.owner_TIN = owner_TIN;
        this.owner_email = owner_email;
        this.owner_phone = owner_phone;
        this.property_type = property_type;
        this.property_area = property_area;
        this.property_location = property_location;
        this.property_distanceFromCenter = property_distanceFromCenter;
        this.property_numberBedrooms = property_numberBedrooms;
        this.property_numberBathrooms = property_numberBathrooms;
        this.property_pnumParking = property_pnumParking;
        this.property_centralHeating = property_centralHeating;
        this.property_airconditioned = property_airconditioned;
        this.property_basement = property_basement;
        this.property_loft = property_loft;
        this.property_sunExposure = property_sunExposure;
        this.property_requested_sale_rent_price = property_requested_sale_rent_price;
        this.property_sale_rent_price = property_sale_rent_price;
        this.commission = commission;
        this.contract_duration = contract_duration;
        this.property_dateAnnounceRequest = property_dateAnnounceRequest;
        this.property_dateofSale = property_dateofSale;
        this.type_business = type_business;
        this.store_ID = store_ID;
        this.store_name = store_name;
        this.store_location = store_location;
        this.store_phonenumber = store_phonenumber;
        this.store_emailAddress = store_emailAddress;
    }


    /**
     * Instantiates a new External module csv.
     */
    public ExternalModuleCSV() {

    }


    /**
     * Get owner.
     *
     * @return the owner
     */
    public Owner getOwner(){
        return new Owner(owner_name,owner_passportNum,owner_TIN,null,owner_email,owner_phone);
    }

    /**
     * Get property.
     *
     * @return the property
     */
    public Property getProperty(){
        if (property_type.equalsIgnoreCase(PropertyType.LAND.getDisplayName())){
            return getLand();
        }else if (property_type.equalsIgnoreCase(PropertyType.APARTMENT.getDisplayName())){
            return getApartment();
        }else if (property_type.equalsIgnoreCase(PropertyType.HOUSE.getDisplayName())){
            return getHouse();
        }
        return null;
    }

    /**
     * Get Land.
     *
     * @return the Land
     */
    private Property getLand(){
        return new Land(property_location,property_area,property_distanceFromCenter,null,getOwner());
    }

    /**
     * Get House.
     *
     * @return the House
     */
    private Property getHouse(){
        return new House(property_location,property_area,property_distanceFromCenter,null,property_numberBedrooms,property_numberBathrooms,property_pnumParking,getEquipment(),property_basement,property_loft,property_sunExposure,getOwner());
    }

    /**
     * Get an Apartment.
     *
     * @return the Apartment
     */
    private Property getApartment(){
        return new Apartment(property_location,property_area,property_distanceFromCenter,null,property_numberBedrooms,property_numberBathrooms,property_pnumParking,getEquipment(),getOwner());
    }


    /**
     * Get agency.
     *
     * @return the agency
     */
    public Agency getAgency(){
        return new Agency(store_name,store_location,store_phonenumber,store_emailAddress,null);

    }


    /**
     * Gets an announcement sold.
     *
     * @return the announcement sold
     */
    public AnnouncementSold getAnnouncementSold() {
        return new AnnouncementSold(commission,property_requested_sale_rent_price, property_dateAnnounceRequest, type_business, getProperty(), contract_duration, property_dateofSale, property_sale_rent_price, getAgency());

    }

    /**
     * Validate owner.
     *
     * @return the boolean
     */
    public boolean validateOwner(){
        return sid != 0 && !owner_name.isEmpty() && owner_passportNum != 0 && owner_TIN != 0 && !owner_email.isEmpty() && owner_phone != 0;
    }

    /**
     * Validate agency.
     *
     * @return the boolean
     */
    public boolean validateAgency(){
        return !store_name.isEmpty() && !store_location.isEmpty() && store_phonenumber != 0 && !store_emailAddress.isEmpty();
    }

    /**
     * Get equipment string.
     *
     * @return the string
     */
    public String getEquipment(){
        String result="";
        if (property_centralHeating){
            result = result + "central heating ";
        }

        if (property_airconditioned){
            result = result + "air conditioned ";
        }
        return result;
    }


    /**
     * Gets sid.
     *
     * @return the sid
     */
    public int getSid() {
        return sid;
    }

    /**
     * Sets sid.
     *
     * @param sid the sid
     */
    public void setSid(int sid) {
        this.sid = sid;
    }

    /**
     * Gets owner name.
     *
     * @return the owner name
     */
    public String getOwner_name() {
        return owner_name;
    }

    /**
     * Sets owner name.
     *
     * @param owner_name the owner name
     */
    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    /**
     * Gets owner passport num.
     *
     * @return the owner passport num
     */
    public long getOwner_passportNum() {
        return owner_passportNum;
    }

    /**
     * Sets owner passport num.
     *
     * @param owner_passportNum the owner passport num
     */
    public void setOwner_passportNum(long owner_passportNum) {
        this.owner_passportNum = owner_passportNum;
    }

    /**
     * Gets owner tin.
     *
     * @return the owner tin
     */
    public long getOwner_TIN() {
        return owner_TIN;
    }

    /**
     * Sets owner tin.
     *
     * @param owner_TIN the owner tin
     */
    public void setOwner_TIN(long owner_TIN) {
        this.owner_TIN = owner_TIN;
    }

    /**
     * Gets owner email.
     *
     * @return the owner email
     */
    public String getOwner_email() {
        return owner_email;
    }

    /**
     * Sets owner email.
     *
     * @param owner_email the owner email
     */
    public void setOwner_email(String owner_email) {
        this.owner_email = owner_email;
    }

    /**
     * Gets owner phone.
     *
     * @return the owner phone
     */
    public long getOwner_phone() {
        return owner_phone;
    }

    /**
     * Sets owner phone.
     *
     * @param owner_phone the owner phone
     */
    public void setOwner_phone(long owner_phone) {
        this.owner_phone = owner_phone;
    }

    /**
     * Gets property type.
     *
     * @return the property type
     */
    public String getProperty_type() {
        return property_type;
    }

    /**
     * Sets property type.
     *
     * @param property_type the property type
     */
    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    /**
     * Gets property area.
     *
     * @return the property area
     */
    public double getProperty_area() {
        return property_area;
    }

    /**
     * Sets property area.
     *
     * @param property_area the property area
     */
    public void setProperty_area(double property_area) {
        this.property_area = property_area;
    }

    /**
     * Gets property location.
     *
     * @return the property location
     */
    public String getProperty_location() {
        return property_location;
    }

    /**
     * Sets property location.
     *
     * @param property_location the property location
     */
    public void setProperty_location(String property_location) {
        this.property_location = property_location;
    }

    /**
     * Gets property distance from center.
     *
     * @return the property distance from center
     */
    public double getProperty_distanceFromCenter() {
        return property_distanceFromCenter;
    }

    /**
     * Sets property distance from center.
     *
     * @param property_distanceFromCenter the property distance from center
     */
    public void setProperty_distanceFromCenter(double property_distanceFromCenter) {
        this.property_distanceFromCenter = property_distanceFromCenter;
    }

    /**
     * Gets property number bedrooms.
     *
     * @return the property number bedrooms
     */
    public int getProperty_numberBedrooms() {
        return property_numberBedrooms;
    }

    /**
     * Sets property number bedrooms.
     *
     * @param property_numberBedrooms the property number bedrooms
     */
    public void setProperty_numberBedrooms(int property_numberBedrooms) {
        this.property_numberBedrooms = property_numberBedrooms;
    }

    /**
     * Gets property number bathrooms.
     *
     * @return the property number bathrooms
     */
    public int getProperty_numberBathrooms() {
        return property_numberBathrooms;
    }

    /**
     * Sets property number bathrooms.
     *
     * @param property_numberBathrooms the property number bathrooms
     */
    public void setProperty_numberBathrooms(int property_numberBathrooms) {
        this.property_numberBathrooms = property_numberBathrooms;
    }

    /**
     * Gets property pnum parking.
     *
     * @return the property pnum parking
     */
    public int getProperty_pnumParking() {
        return property_pnumParking;
    }

    /**
     * Sets property pnum parking.
     *
     * @param property_pnumParking the property pnum parking
     */
    public void setProperty_pnumParking(int property_pnumParking) {
        this.property_pnumParking = property_pnumParking;
    }

    /**
     * Is property central heating boolean.
     *
     * @return the boolean
     */
    public boolean isProperty_centralHeating() {
        return property_centralHeating;
    }

    /**
     * Sets property central heating.
     *
     * @param property_centralHeating the property central heating
     */
    public void setProperty_centralHeating(boolean property_centralHeating) {
        this.property_centralHeating = property_centralHeating;
    }

    /**
     * Is property airconditioned boolean.
     *
     * @return the boolean
     */
    public boolean isProperty_airconditioned() {
        return property_airconditioned;
    }

    /**
     * Sets property airconditioned.
     *
     * @param property_airconditioned the property airconditioned
     */
    public void setProperty_airconditioned(boolean property_airconditioned) {
        this.property_airconditioned = property_airconditioned;
    }

    /**
     * Is property basement boolean.
     *
     * @return the boolean
     */
    public boolean isProperty_basement() {
        return property_basement;
    }

    /**
     * Sets property basement.
     *
     * @param property_basement the property basement
     */
    public void setProperty_basement(boolean property_basement) {
        this.property_basement = property_basement;
    }

    /**
     * Is property loft boolean.
     *
     * @return the boolean
     */
    public boolean isProperty_loft() {
        return property_loft;
    }

    /**
     * Sets property loft.
     *
     * @param property_loft the property loft
     */
    public void setProperty_loft(boolean property_loft) {
        this.property_loft = property_loft;
    }

    /**
     * Gets property sun exposure.
     *
     * @return the property sun exposure
     */
    public String getProperty_sunExposure() {
        return property_sunExposure;
    }

    /**
     * Sets property sun exposure.
     *
     * @param property_sunExposure the property sun exposure
     */
    public void setProperty_sunExposure(String property_sunExposure) {
        this.property_sunExposure = property_sunExposure;
    }

    /**
     * Gets property requested sale rent price.
     *
     * @return the property requested sale rent price
     */
    public double getProperty_requested_sale_rent_price() {
        return property_requested_sale_rent_price;
    }

    /**
     * Sets property requested sale rent price.
     *
     * @param property_requested_sale_rent_price the property requested sale rent price
     */
    public void setProperty_requested_sale_rent_price(double property_requested_sale_rent_price) {
        this.property_requested_sale_rent_price = property_requested_sale_rent_price;
    }

    /**
     * Gets property sale rent price.
     *
     * @return the property sale rent price
     */
    public double getProperty_sale_rent_price() {
        return property_sale_rent_price;
    }

    /**
     * Sets property sale rent price.
     *
     * @param property_sale_rent_price the property sale rent price
     */
    public void setProperty_sale_rent_price(double property_sale_rent_price) {
        this.property_sale_rent_price = property_sale_rent_price;
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
     * Gets contract duration.
     *
     * @return the contract duration
     */
    public int getContract_duration() {
        return contract_duration;
    }

    /**
     * Sets contract duration.
     *
     * @param contract_duration the contract duration
     */
    public void setContract_duration(int contract_duration) {
        this.contract_duration = contract_duration;
    }

    /**
     * Gets property date announce request.
     *
     * @return the property date announcement request
     */
    public Date getProperty_dateAnnounceRequest() {
        return property_dateAnnounceRequest;
    }

    /**
     * Sets property date announce request.
     *
     * @param property_dateAnnounceRequest the property date announce request
     */
    public void setProperty_dateAnnounceRequest(Date property_dateAnnounceRequest) {
        this.property_dateAnnounceRequest = property_dateAnnounceRequest;
    }

    /**
     * Gets property dateof sale.
     *
     * @return the property dateof sale
     */
    public Date getProperty_dateofSale() {
        return property_dateofSale;
    }

    /**
     * Sets property dateof sale.
     *
     * @param property_dateofSale the property dateof sale
     */
    public void setProperty_dateofSale(Date property_dateofSale) {
        this.property_dateofSale = property_dateofSale;
    }

    /**
     * Gets type business.
     *
     * @return the type business
     */
    public String getType_business() {
        return type_business;
    }

    /**
     * Sets type business.
     *
     * @param type_business the type business
     */
    public void setType_business(String type_business) {
        this.type_business = type_business;
    }

    /**
     * Gets store id.
     *
     * @return the store id
     */
    public int getStore_ID() {
        return store_ID;
    }

    /**
     * Sets store id.
     *
     * @param store_ID the store id
     */
    public void setStore_ID(int store_ID) {
        this.store_ID = store_ID;
    }

    /**
     * Gets store name.
     *
     * @return the store name
     */
    public String getStore_name() {
        return store_name;
    }

    /**
     * Sets store name.
     *
     * @param store_name the store name
     */
    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    /**
     * Gets store location.
     *
     * @return the store location
     */
    public String getStore_location() {
        return store_location;
    }

    /**
     * Sets store location.
     *
     * @param store_location the store location
     */
    public void setStore_location(String store_location) {
        this.store_location = store_location;
    }

    /**
     * Gets store phonenumber.
     *
     * @return the store phonenumber
     */
    public long getStore_phonenumber() {
        return store_phonenumber;
    }

    /**
     * Sets store phonenumber.
     *
     * @param store_phonenumber the store phonenumber
     */
    public void setStore_phonenumber(long store_phonenumber) {
        this.store_phonenumber = store_phonenumber;
    }

    /**
     * Gets store email address.
     *
     * @return the store email address
     */
    public String getStore_emailAddress() {
        return store_emailAddress;
    }

    /**
     * Sets store email address.
     *
     * @param store_emailAddress the store email address
     */
    public void setStore_emailAddress(String store_emailAddress) {
        this.store_emailAddress = store_emailAddress;
    }
}

