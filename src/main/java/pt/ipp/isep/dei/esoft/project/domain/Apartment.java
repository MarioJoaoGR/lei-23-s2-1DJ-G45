package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.DTOs.PropertyDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * The type Apartment.
 */
public class Apartment extends Property implements Serializable {
    private int numberRooms;
    private int numberBathrooms;
    private int numberParking;
    private String equipment;

    /**
     * Instantiates a new Apartment.
     */
    public Apartment() {
    }

    /**
     * Instantiates a new Apartment.
     *
     * @param location        the location
     * @param area            the area
     * @param distanceCenter  the distance center
     * @param photographs     the photographs
     * @param owner           the owner
     * @param numberRooms     the number rooms
     * @param numberBathrooms the number bathrooms
     * @param numberParking   the number parking
     * @param equipment       the equipment
     */
    public Apartment(String location, double area, double distanceCenter, List<String> photographs, Owner owner,
                     int numberRooms, int numberBathrooms, int numberParking, String equipment) {
        super(location,area,distanceCenter,photographs,owner);
        this.numberRooms = numberRooms;
        this.numberBathrooms = numberBathrooms;
        this.numberParking = numberParking;
        this.equipment = equipment;
    }

    /**
     * Instantiates a new Apartment.
     *
     * @param location        the location
     * @param area            the area
     * @param distanceCenter  the distance center
     * @param photos          the photos
     * @param numberRooms     the number rooms
     * @param numberBathrooms the number bathrooms
     * @param numberParking   the number parking
     * @param equipment       the equipment
     * @param owner           the owner
     */
    public Apartment(String location, double area, double distanceCenter, List<String> photos, int numberRooms, int numberBathrooms, int numberParking, String equipment, Owner owner) {
        super(location, area, distanceCenter, photos, owner);
        this.numberRooms = numberRooms;
        this.numberBathrooms = numberBathrooms;
        this.numberParking = numberParking;
        this.equipment = equipment;
    }

    /**
     * Instantiates a new Apartment.
     *
     * @param id              the id
     * @param location        the location
     * @param area            the area
     * @param distanceCenter  the distance center
     * @param photograph      the photograph
     * @param numberRooms     the number rooms
     * @param numberBathrooms the number bathrooms
     * @param numberParking   the number parking
     * @param equipment       the equipment
     * @param owner           the owner
     */
    public Apartment(int id,String location, double area, double distanceCenter,
                     List<String> photograph,int numberRooms, int numberBathrooms, int numberParking,
                     String equipment , Owner owner) {
        super(id,location, area, distanceCenter,photograph,owner);
        this.numberRooms = numberRooms;
        this.numberBathrooms = numberBathrooms;
        this.numberParking = numberParking;
        this.equipment = equipment;
    }


    /**
     * Instantiates a new Apartment receiving another apartment object.
     *
     * @param anotherApartment the another apartment
     */
    public Apartment(Apartment anotherApartment) {
        super(anotherApartment);
        this.numberRooms = anotherApartment.numberRooms;
        this.numberBathrooms = anotherApartment.numberBathrooms;
        this.numberParking = anotherApartment.numberParking;
        this.equipment = anotherApartment.equipment;

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
     * Sets number bathrooms.
     *
     * @param numberBathrooms the number bathrooms
     */
    public void setNumberBathrooms(int numberBathrooms) {
        this.numberBathrooms = numberBathrooms;
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
     * Sets equipment.
     *
     * @param equipment the equipment
     */
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }



    /**
     * this method checks if the number of rooms correspond at the parameter inserted
     *
     * @param rooms the number of rooms
     * @return the boolean
     */
    public boolean numberRooms(int rooms) {
        return numberRooms == rooms;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), numberRooms, numberBathrooms, numberParking, equipment);
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
     * Gets number bathrooms.
     *
     * @return the number bathrooms
     */
    public int getNumberBathrooms() {
        return numberBathrooms;
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
     * Gets equipment.
     *
     * @return the equipment
     */
    public String getEquipment() {
        return equipment;
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
        if (!(o instanceof Apartment)) return false;
        if (!super.equals(o)) return false;
        Apartment apartment = (Apartment) o;
        if (equipment!=null){
            return  numberRooms==apartment.numberRooms &&
                    this.getLocation().equals(apartment.getLocation()) && Objects.equals(this.getDistanceCenter(), apartment.getDistanceCenter())
                    && Objects.equals(this.getArea(), apartment.getArea()) && this.getOwner().equals(apartment.getOwner()) &&
                    numberBathrooms == apartment.numberBathrooms && numberParking == apartment.numberParking
                    && equipment.equals(apartment.equipment);
        }else {
            return  numberRooms==apartment.numberRooms &&
                    this.getLocation().equals(apartment.getLocation()) && Objects.equals(this.getDistanceCenter(), apartment.getDistanceCenter())
                    && Objects.equals(this.getArea(), apartment.getArea()) && this.getOwner().equals(apartment.getOwner()) &&
                    numberBathrooms == apartment.numberBathrooms && numberParking == apartment.numberParking;
        }

    }
}
