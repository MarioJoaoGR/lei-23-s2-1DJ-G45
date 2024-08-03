package pt.ipp.isep.dei.esoft.project.domain.DTOs;

import pt.ipp.isep.dei.esoft.project.domain.Owner;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Property dto.
 */
public class PropertyDTO {

    private int id;
    private String propertyType;
    private String location;
    private double area;
    private double distanceCenter;
    private List<String> photographsAnnouncement = new ArrayList<>();
    private Owner owner;
    private  int numberRooms;
    private  int numberBathrooms;
    private  int numberParking;
    private  String equipment;
    private  boolean basement;
    private  boolean loft;
    private  String sunExposure;


    /**
     * Instantiates a new Property dto.
     */
//Constructor
    public PropertyDTO() {
    }

    /**
     * Instantiates a new Property dto.
     *
     * @param id                      the id
     * @param propertyType            the property type
     * @param location                the location
     * @param area                    the area
     * @param distanceCenter          the distance center
     * @param photographsAnnouncement the photographs announcement
     * @param owner                   the owner
     * @param numberRooms             the number rooms
     * @param numberBathrooms         the number bathrooms
     * @param numberParking           the number parking
     * @param equipment               the equipment
     * @param basement                the basement
     * @param loft                    the loft
     * @param sunExposure             the sun exposure
     */
    public PropertyDTO(int id, String propertyType, String location, double area, double distanceCenter, List<String> photographsAnnouncement, Owner owner, int numberRooms, int numberBathrooms, int numberParking, String equipment, boolean basement, boolean loft, String sunExposure) {
        this.id = id;
        this.propertyType = propertyType;
        this.location = location;
        this.area = area;
        this.distanceCenter = distanceCenter;
        this.photographsAnnouncement = photographsAnnouncement;
        this.owner = owner;
        this.numberRooms = numberRooms;
        this.numberBathrooms = numberBathrooms;
        this.numberParking = numberParking;
        this.equipment = equipment;
        this.basement = basement;
        this.loft = loft;
        this.sunExposure = sunExposure;
    }

    //Gets and sets

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
     * Gets property type.
     *
     * @return the property type
     */
    public String getPropertyType() {
        return propertyType;
    }

    /**
     * Sets property type.
     *
     * @param propertyType the property type
     */
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets area.
     *
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * Sets area.
     *
     * @param area the area
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * Gets distance center.
     *
     * @return the distance center
     */
    public double getDistanceCenter() {
        return distanceCenter;
    }

    /**
     * Sets distance center.
     *
     * @param distanceCenter the distance center
     */
    public void setDistanceCenter(double distanceCenter) {
        this.distanceCenter = distanceCenter;
    }

    /**
     * Gets photographs announcement.
     *
     * @return the photographs announcement
     */
    public List<String> getPhotographsAnnouncement() {
        return photographsAnnouncement;
    }

    /**
     * Sets photographs announcement.
     *
     * @param photographsAnnouncement the photographs announcement
     */
    public void setPhotographsAnnouncement(List<String> photographsAnnouncement) {
        this.photographsAnnouncement = photographsAnnouncement;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * Gets number rooms.
     *
     * @return the number rooms
     */
    public int getNumberRooms() {
        return numberRooms;
    }

    /**
     * Sets number rooms.
     *
     * @param numberRooms the number rooms
     */
    public void setNumberRooms(int numberRooms) {
        this.numberRooms = numberRooms;
    }

    /**
     * Gets number bathrooms.
     *
     * @return the number bathrooms
     */
    public int getNumberBathrooms() {
        return numberBathrooms;
    }

    /**
     * Sets number bathrooms.
     *
     * @param numberBathrooms the number bathrooms
     */
    public void setNumberBathrooms(int numberBathrooms) {
        this.numberBathrooms = numberBathrooms;
    }

    /**
     * Gets number parking.
     *
     * @return the number parking
     */
    public int getNumberParking() {
        return numberParking;
    }

    /**
     * Sets number parking.
     *
     * @param numberParking the number parking
     */
    public void setNumberParking(int numberParking) {
        this.numberParking = numberParking;
    }

    /**
     * Gets equipment.
     *
     * @return the equipment
     */
    public String getEquipment() {
        return equipment;
    }

    /**
     * Sets equipment.
     *
     * @param equipment the equipment
     */
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    /**
     * Is basement boolean.
     *
     * @return the boolean
     */
    public boolean isBasement() {
        return basement;
    }

    /**
     * Sets basement.
     *
     * @param basement the basement
     */
    public void setBasement(boolean basement) {
        this.basement = basement;
    }

    /**
     * Is loft boolean.
     *
     * @return the boolean
     */
    public boolean isLoft() {
        return loft;
    }

    /**
     * Sets loft.
     *
     * @param loft the loft
     */
    public void setLoft(boolean loft) {
        this.loft = loft;
    }

    /**
     * Gets sun exposure.
     *
     * @return the sun exposure
     */
    public String getSunExposure() {
        return sunExposure;
    }

    /**
     * Sets sun exposure.
     *
     * @param sunExposure the sun exposure
     */
    public void setSunExposure(String sunExposure) {
        this.sunExposure = sunExposure;
    }
}
