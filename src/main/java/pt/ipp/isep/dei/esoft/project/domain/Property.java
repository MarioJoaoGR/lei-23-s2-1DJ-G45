package pt.ipp.isep.dei.esoft.project.domain;


import pt.ipp.isep.dei.esoft.project.domain.Serialization.Serialization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Property.
 */
public class Property implements Serializable {
    private static int idCount=1;
    private int id;
    private String propertyType;
    private String location;
    private double area;
    private double distanceCenter;
    private List<String> photographsAnnouncement = new ArrayList<>();
    private Owner owner;

    /**
     * Instantiates a new Property.
     */
    public Property() {
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
        Property.idCount = idCount;
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
     * Gets photographs announcement.
     *
     * @return the photographs announcement
     */
    public List<String> getPhotographsAnnouncement() {
        return photographsAnnouncement;
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
     * Instantiates a new Property.
     *
     * @param id             the id
     * @param location       the location
     * @param area           the area
     * @param distanceCenter the distance center
     * @param photographs    the photographs
     * @param owner          the owner
     */
// Main constructor
    public Property(int id, String location, double area, double distanceCenter,List<String> photographs, Owner owner) {
        this.id = id;
        int index = getClass().getName().lastIndexOf(".");
        this.propertyType = getClass().getName().substring(index+1);
        this.location = location;
        this.area = area;
        this.distanceCenter = distanceCenter;
        this.photographsAnnouncement=photographs;
        this.owner = owner;
    }

    /**
     * Instantiates a new Property.
     *
     * @param location       the location
     * @param area           the area
     * @param distanceCenter the distance center
     * @param photographs    the photographs
     * @param owner          the owner
     */
// Constructor for announcment
    public Property(String location, double area, double distanceCenter, List<String> photographs, Owner owner) {
        this.id=idCount++;
        int index = getClass().getName().lastIndexOf(".");
        this.propertyType = getClass().getName().substring(index+1);
        this.location = location;
        this.area = area;
        this.distanceCenter = distanceCenter;
        this.photographsAnnouncement=photographs;
        this.owner = owner;
    }


    /**
     * Instantiates a new Property.
     *
     * @param property the property
     */
    public Property(Property property) {
        this.id= property.id;
        this.propertyType = property.propertyType;
        this.photographsAnnouncement=property.photographsAnnouncement;
        this.location = property.location;
        this.area =  property.area;
        this.distanceCenter =  property.distanceCenter;
        this.owner = property.owner;

    }

    /**
     * Gets photograph.
     *
     * @return the photograph
     */
    public List<String> getPhotograph() {
        return photographsAnnouncement;
    }

    /**
     * checks the type of property.
     *
     * @param type the type
     * @return the boolean
     */
    public boolean isPropertyType(String type){

        return this.propertyType.equalsIgnoreCase(type);
    }

    /**
     * Check number of rooms of a property.
     *
     * @param rooms the rooms
     * @return the boolean
     */
    public boolean numberRooms(int rooms){
        return true;
    }


    /**
     * do a property clone
     *
     * @return property clone
     */

    public Property clone() {
        return new Property(this.id, this.location, this.area, this.distanceCenter, this.propertyType, this.photographsAnnouncement, this.owner);
    }

    /**
     * Instantiates a new Property.
     *
     * @param id             the id
     * @param location       the location
     * @param area           the area
     * @param distanceCenter the distance center
     * @param propertyType   the property type
     * @param photographs    the photographs
     * @param owner          the owner
     */
    public Property(int id, String location, double area, double distanceCenter,String propertyType,List<String> photographs, Owner owner) {
        this.id = id;
        this.propertyType = propertyType;
        this.location = location;
        this.area = area;
        this.distanceCenter = distanceCenter;
        this.photographsAnnouncement=photographs;
        this.owner = owner;
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
     * compares if the objects are equals(type of object and attributes)
     *
     * @param o object to be compared
     * @return true if the objects are equal false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Property)) return false;
        Property property = (Property) o;
        return  Double.compare(property.area, area) == 0 && Double.compare(property.distanceCenter, distanceCenter) == 0 && propertyType.equals(property.propertyType) && location.equals(property.location) &&  owner.equals(property.owner);
    }

    /**
     * @param :values – the values to be hashed
     * @return :a hash value of the sequence of input values
     */
    @Override
    public int hashCode() {
        return Objects.hash( propertyType, location, area, distanceCenter, photographsAnnouncement, owner);
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
     * Gets area.
     *
     * @return the area
     */
    public Double getArea() {
        return area;
    }


    /**
     * Gets distance center.
     *
     * @return the distance center
     */
    public Double getDistanceCenter() {
        return distanceCenter;
    }


    /**
     * Gets type.
     *
     * @return the type
     */
    public String getPropertyType() {
        return propertyType;
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
     * Gets owner.
     *
     * @return the owner
     */
    public Owner getOwner() {
        return owner;
    }


    /**
     * Gets photograph.
     *
     * @param id the id
     * @return the photograph
     */
    public void setId(int id) {
        this.id = id;
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
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets area.
     *
     * @param area the area
     */
    public void setArea(Double area) {
        this.area = area;
    }

    /**
     * Sets distance center.
     *
     * @param distanceCenter the distance center
     */
    public void setDistanceCenter(double distanceCenter) {
        this.distanceCenter = distanceCenter;
    }

    @Override
    public String toString() {
        return String.format("Area: %.2fm2", getArea());
    }
}
