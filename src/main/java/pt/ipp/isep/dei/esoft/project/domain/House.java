package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * The type House.
 */
public class House extends Property implements Serializable {

    private  int numberRooms;
    private  int numberBathrooms;
    private  int numberParking;
    private  String equipment;
    private  boolean basement;
    private  boolean loft;
    private  String sunExposure;

    /**
     * Instantiates a new House.
     */
    public House() {
    }

    /**
     * Instantiates a new House.
     *
     * @param location        the location
     * @param area            the area
     * @param distanceCenter  the distance center
     * @param photographs     the photographs
     * @param numberRooms     the number rooms
     * @param numberBathrooms the number bathrooms
     * @param numberParking   the number parking
     * @param equipment       the equipment
     * @param basement        the basement
     * @param loft            the loft
     * @param sunExposure     the sun exposure
     * @param owner           the owner
     */
    public House(String location, double area, double distanceCenter, List<String> photographs,
                 int numberRooms, int numberBathrooms,
                 int numberParking, String equipment, boolean basement, boolean loft, String sunExposure, Owner owner) {
        super(location,area,distanceCenter,photographs,owner);
        this.numberRooms = numberRooms;
        this.numberBathrooms = numberBathrooms;
        this.numberParking = numberParking;
        this.equipment = equipment;
        this.basement = basement;
        this.loft = loft;
        this.sunExposure = sunExposure;
    }

    /**
     * Instantiates a new House.
     *
     * @param id              the id
     * @param location        the location
     * @param area            the area
     * @param distanceCenter  the distance center
     * @param photographs     the photographs
     * @param owner           the owner
     * @param numberRooms     the number rooms
     * @param numberBathrooms the number bathrooms
     * @param numberParking   the number parking
     * @param equipment       the equipment
     * @param basement        the basement
     * @param loft            the loft
     * @param sunExposure     the sun exposure
     */
    public House(int id,String location, double area, double distanceCenter, List<String> photographs,
                 Owner owner, int numberRooms, int numberBathrooms,
                 int numberParking, String equipment, boolean basement, boolean loft, String sunExposure) {
        super(id,location,area,distanceCenter,photographs,owner);
        this.numberRooms = numberRooms;
        this.numberBathrooms = numberBathrooms;
        this.numberParking = numberParking;
        this.equipment = equipment;
        this.basement = basement;
        this.loft = loft;
        this.sunExposure = sunExposure;
    }


    /**
     * Instantiates a new House.
     *
     * @param anotherHouse the another house
     */
    public House(House anotherHouse) {
        super(anotherHouse);
        this.numberRooms = anotherHouse.numberRooms;
        this.numberBathrooms = anotherHouse.numberBathrooms;
        this.numberParking = anotherHouse.numberParking;
        this.equipment = anotherHouse.equipment;
        this.basement = anotherHouse.basement;
        this.loft = anotherHouse.loft;
        this.sunExposure = anotherHouse.sunExposure;
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



    /**
     * this method checks if the number of rooms correspond at the parameter inserted
     *
     * @param rooms the number of rooms
     *
     * @return the boolean
     */
    public boolean numberRooms(int rooms){
        return numberRooms == rooms;
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
        if (!(o instanceof House)) return false;
        if (!super.equals(o)) return false;
        House house = (House) o;
        if (this.equipment==null && this.sunExposure==null){
            return  numberRooms == house.numberRooms &&
                    this.getLocation().equals(house.getLocation()) && Objects.equals(this.getDistanceCenter(), house.getDistanceCenter())
                    && Objects.equals(this.getArea(), house.getArea()) && this.getOwner().equals(house.getOwner())  &&
                    numberBathrooms == house.numberBathrooms && numberParking == house.numberParking
                    && basement == house.basement && loft == house.loft;
        }else if (this.equipment==null){
            return  numberRooms == house.numberRooms &&
                    this.getLocation().equals(house.getLocation()) && Objects.equals(this.getDistanceCenter(), house.getDistanceCenter())
                    && Objects.equals(this.getArea(), house.getArea()) && this.getOwner().equals(house.getOwner())  &&
                    numberBathrooms == house.numberBathrooms && numberParking == house.numberParking
                    && basement == house.basement && loft == house.loft
                    && sunExposure.equals(house.sunExposure);
        }else if (this.sunExposure==null){
            return  numberRooms == house.numberRooms &&
                    this.getLocation().equals(house.getLocation()) && Objects.equals(this.getDistanceCenter(), house.getDistanceCenter())
                    && Objects.equals(this.getArea(), house.getArea()) && this.getOwner().equals(house.getOwner())  &&
                    numberBathrooms == house.numberBathrooms && numberParking == house.numberParking
                    && basement == house.basement && loft == house.loft
                    && equipment.equals(house.equipment);
        }else {
                return this.getLocation().equals(house.getLocation()) && Objects.equals(this.getDistanceCenter(), house.getDistanceCenter())
                    && Objects.equals(this.getArea(), house.getArea()) && this.getOwner().equals(house.getOwner())  &&
                    numberBathrooms == house.numberBathrooms && numberParking == house.numberParking
                    && basement == house.basement && loft == house.loft && equipment.equals(house.equipment)
                    && sunExposure.equals(house.sunExposure);
        }

    }



    /**
     * @param :values – the values to be hashed
     * @return :a hash value of the sequence of input values
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberRooms, numberBathrooms, numberParking, equipment, basement, loft, sunExposure);
    }


}
