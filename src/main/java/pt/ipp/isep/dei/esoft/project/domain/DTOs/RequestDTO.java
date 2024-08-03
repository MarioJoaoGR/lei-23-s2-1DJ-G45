package pt.ipp.isep.dei.esoft.project.domain.DTOs;

import org.apache.poi.util.DelayableLittleEndianOutput;
import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Owner;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgentMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.OwnerMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The type Request dto.
 */
public class RequestDTO {



    private Date date;
    private String equipment;
    private double area;
    private String location;
    private double distanceFromCityCenter;
    private double requestedPrice;

    private String requestType;
    private List<String> photographs = new ArrayList<>();
    private int numberBedrooms;
    private int numberBathrooms;
    private int numberParkingSpaces;
    private Boolean basement;
    private Boolean inhabitableLoft;
    private String sunExposure;
    private int id;
    private AgentDTO agent;
    private OwnerDTO owner;
    private String typeOfCommercialize;

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
     * Gets number bedrooms.
     *
     * @return the number bedrooms
     */
    public int getNumberBedrooms() {
        return numberBedrooms;
    }

    /**
     * Sets number bedrooms.
     *
     * @param numberBedrooms the number bedrooms
     */
    public void setNumberBedrooms(int numberBedrooms) {
        this.numberBedrooms = numberBedrooms;
    }

    /**
     * Gets number parking spaces.
     *
     * @return the number parking spaces
     */
    public int getNumberParkingSpaces() {
        return numberParkingSpaces;
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
     * Sets number parking spaces.
     *
     * @param numberParkingSpaces the number parking spaces
     */
    public void setNumberParkingSpaces(int numberParkingSpaces) {
        this.numberParkingSpaces = numberParkingSpaces;
    }

    /**
     * Gets basement.
     *
     * @return the basement
     */
    public Boolean getBasement() {
        return basement;
    }

    /**
     * Sets basement.
     *
     * @param basement the basement
     */
    public void setBasement(Boolean basement) {
        this.basement = basement;
    }

    /**
     * Gets inhabitable loft.
     *
     * @return the inhabitable loft
     */
    public Boolean getInhabitableLoft() {
        return inhabitableLoft;
    }

    /**
     * Sets inhabitable loft.
     *
     * @param inhabitableLoft the inhabitable loft
     */
    public void setInhabitableLoft(Boolean inhabitableLoft) {
        this.inhabitableLoft = inhabitableLoft;
    }

    /**
     * Instantiates a new Request dto.
     */
    public RequestDTO() {
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
     * Gets area.
     *
     * @return the area
     */
    public double getArea() {
        return area;
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
     * Gets distance from city center.
     *
     * @return the distance from city center
     */
    public double getDistanceFromCityCenter() {
        return distanceFromCityCenter;
    }

    /**
     * Gets requested price.
     *
     * @return the requested price
     */
    public double getRequestedPrice() {
        return requestedPrice;
    }

    /**
     * Gets request type.
     *
     * @return the request type
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * Gets photographs.
     *
     * @return the photographs
     */
    public List<String> getPhotographs() {
        return photographs;
    }

    /**
     * Gets agent dto.
     *
     * @return the agent dto
     */
    public AgentDTO getAgentDTO() {
        return agent;
    }

    /**
     * Gets agent.
     *
     * @return the agent
     */
    public Agent getAgent() {
        AgentMapper agentMapper = new AgentMapper();
        return agentMapper.fromDTO(agent);
    }

    /**
     * Gets owner dto.
     *
     * @return the owner dto
     */
    public OwnerDTO getOwnerDTO() {
        return owner;
    }

    /**
     * Get owner owner.
     *
     * @return the owner
     */
    public Owner getOwner(){
    OwnerMapper ownerMapper = new OwnerMapper();
    return ownerMapper.fromDTO(getOwnerDTO());
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
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Add photograph optional.
     *
     * @param photos the photos
     * @return the optional
     */
    public Optional<List<String>> addPhotograph(List<String> photos) {

        //validate if list is not empty
        if (photos.isEmpty()) {
            return Optional.empty();
        }

        Optional<List<String>> validPhotos = Optional.empty();
        List<String> validPhotoList = new ArrayList<>();
        boolean allPhotosValid = true;

        for (String photo : photos) {
            if (validatePhoto(photo) && isValidPhotoUrl(photo)) {
                photographs.add(photo);
                validPhotoList.add(photo);
            } else {
                allPhotosValid = false;
            }
        }

        if (allPhotosValid) {
            validPhotos = Optional.of(clonePhotographs());
        } else if (!validPhotoList.isEmpty()) {
            validPhotos = Optional.of(validPhotoList);
        }

        return validPhotos;
    }
    private boolean validatePhoto(String newPhoto) {
        if (photographs!=null){
            return !photographs.contains(newPhoto);

        }else {
            return true;
        }

    }

    /**
     * Is valid photo url boolean.
     *
     * @param url the url
     * @return the boolean
     */
    public static boolean isValidPhotoUrl(String url) {

        return url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".png") || url.endsWith(".gif");
    }

    /**
     * Clone photographs list.
     *
     * @return the list
     */
    public List<String> clonePhotographs() {
        return new ArrayList<>(this.photographs);
    }

    /**
     * Sets distance from city center.
     *
     * @param distanceFromCityCenter the distance from city center
     */
    public void setDistanceFromCityCenter(double distanceFromCityCenter) {
        this.distanceFromCityCenter = distanceFromCityCenter;
    }

    /**
     * Sets requested price.
     *
     * @param requestedPrice the requested price
     */
    public void setRequestedPrice(double requestedPrice) {
        this.requestedPrice = requestedPrice;
    }

    /**
     * Sets request type.
     *
     * @param requestType the request type
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * Sets photographs.
     *
     * @param photographs the photographs
     */
    public void setPhotographs(List<String> photographs) {
        this.photographs = photographs;
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
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(OwnerDTO owner) {
        this.owner = owner;
    }

    /**
     * Sets type of commercialize.
     *
     * @param typeOfCommercialize the type of commercialize
     */
    public void setTypeOfCommercialize(String typeOfCommercialize) {
        this.typeOfCommercialize = typeOfCommercialize;
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
     * Gets equipment.
     *
     * @return the equipment
     */
    public String getEquipment() {
        return equipment;
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
     * Gets type of commercialize.
     *
     * @return the type of commercialize
     */
    public String getTypeOfCommercialize() {
        return typeOfCommercialize;
    }
}

