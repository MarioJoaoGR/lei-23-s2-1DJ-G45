package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgentMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.OwnerMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.RequestMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Request house.
 */
public class RequestHouse extends Request implements Serializable {

    private int numberBedrooms;
    private int numberBathrooms;
    private int numberParkingSpaces;
    private String availableEquipment;
    private boolean basement;
    private boolean inhabitableLoft;

    private String sunExposure;
    private final String LAND_TYPE = PropertyType.HOUSE.getDisplayName();


    /**
     * Instantiates a new Request house.
     *
     * @param typeOfCommercialize    rent/sell
     * @param agente                 the agent responsible
     * @param area                   the area
     * @param location               the location
     * @param distanceFromCityCenter the distance from city center
     * @param requestedPrice         the requested price
     * @param numberBedrooms         number of bedrooms
     * @param numberBathrooms        number of bathrooms
     * @param numberParkingSpaces    number of parking spaces
     * @param availableEquipment     the available equipment
     * @param basement               existance of a basement
     * @param inhabitableLoft        existance of a inhabitable loft
     * @param sunExposure            the type of sun exposure
     * @param owner                  the owner associated
     * @param photographs            the photographs
     */
    public RequestHouse(String typeOfCommercialize, Agent agente, double area, String location,
                        Double distanceFromCityCenter, Double requestedPrice, int numberBedrooms, int numberBathrooms,
                        int numberParkingSpaces, String availableEquipment, boolean basement, boolean inhabitableLoft,
                        String sunExposure, Owner owner, List<String> photographs) {
        super(typeOfCommercialize, agente, area, location, distanceFromCityCenter, requestedPrice, owner, photographs);
        this.numberBathrooms = numberBathrooms;
        this.numberBedrooms = numberBedrooms;
        this.availableEquipment = availableEquipment;
        this.numberParkingSpaces = numberParkingSpaces;
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
        this.sunExposure = sunExposure;
    }

    /**
     * Instantiates a new Request house.
     *
     * @param requestDTO the request dto
     */
    public RequestHouse(RequestDTO requestDTO) {

        super(requestDTO.getTypeOfCommercialize(), requestDTO.getAgent(), requestDTO.getArea(), requestDTO.getLocation(), requestDTO.getDistanceFromCityCenter(), requestDTO.getRequestedPrice(), requestDTO.getOwner(), requestDTO.getPhotographs());
        this.numberBathrooms = requestDTO.getNumberBathrooms();
        this.numberBedrooms = requestDTO.getNumberBedrooms();
        this.availableEquipment = requestDTO.getEquipment();
        this.numberParkingSpaces = requestDTO.getNumberParkingSpaces();
        this.basement = requestDTO.getBasement();
        this.inhabitableLoft = requestDTO.getInhabitableLoft();
        this.sunExposure = requestDTO.getSunExposure();


    }


    /**
     * Instantiates a new Request house.
     *
     * @param id                     the id
     * @param typeOfCommercialize    the type of commercialize
     * @param agente                 the agente
     * @param area                   the area
     * @param location               the location
     * @param distanceFromCityCentre the distance from city centre
     * @param requestedPrice         the requested price
     * @param owner                  the owner
     * @param numberBedrooms         the number bedrooms
     * @param numberBathrooms        the number bathrooms
     * @param numberParkingSpaces    the number parking spaces
     * @param availableEquipment     the available equipment
     * @param basement               the basement
     * @param inhabitableLoft        the inhabitable loft
     * @param sunExposure            the sun exposure
     * @param photographs            the photographs
     */
    public RequestHouse(int id, String typeOfCommercialize, Agent agente, double area,
                        String location, double distanceFromCityCentre, double requestedPrice,
                        Owner owner, int numberBedrooms, int numberBathrooms, int numberParkingSpaces,
                        String availableEquipment, boolean basement, boolean inhabitableLoft, String sunExposure, List<String> photographs) {
        super(id, typeOfCommercialize, agente, area, location, distanceFromCityCentre, requestedPrice, owner, photographs);
        this.numberBedrooms = numberBedrooms;
        this.numberBathrooms = numberBathrooms;
        this.numberParkingSpaces = numberParkingSpaces;
        this.availableEquipment = availableEquipment;
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
        this.sunExposure = sunExposure;

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
        if (!(o instanceof RequestHouse)) return false;
        if (!super.equals(o)) return false;
        RequestHouse that = (RequestHouse) o;
        return numberBedrooms == that.numberBedrooms && numberBathrooms == that.numberBathrooms && numberParkingSpaces == that.numberParkingSpaces && basement == that.basement && inhabitableLoft == that.inhabitableLoft && sunExposure.equals(that.sunExposure) && availableEquipment.equals(that.availableEquipment) && LAND_TYPE.equals(that.LAND_TYPE);
    }

    /**
     * @param :values – the values to be hashed
     * @return :a hash value of the sequence of input values
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberBedrooms, numberBathrooms, numberParkingSpaces, availableEquipment, basement, inhabitableLoft, sunExposure, LAND_TYPE);
    }

    @Override
    public String toString() {
        return super.toString() +
                "numberBedrooms=" + numberBedrooms +
                ", numberBathrooms=" + numberBathrooms +
                ", numberParkingSpaces=" + numberParkingSpaces +
                ", availableEquipment='" + availableEquipment + '\'' +
                ", basement=" + basement +
                ", inhabitableLoft=" + inhabitableLoft +
                ", sunExposure='" + sunExposure + '\'' +
                ", LAND_TYPE='" + LAND_TYPE + '\'' +
                '}';
    }

    /**
     * Gets land type.
     *
     * @return String house
     */
    public String getLandType() {
        return LAND_TYPE;
    }

    /**
     * Gets number bedrooms.
     *
     * @return the number of bedrooms
     */
    public int getNumberBedrooms() {
        return numberBedrooms;
    }

    /**
     * set the number of bedrooms
     *
     * @param numberBedrooms the number bedrooms
     */
    public void setNumberBedrooms(int numberBedrooms) {
        this.numberBedrooms = numberBedrooms;
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
     * changes the number of bathrooms
     *
     * @param numberBathrooms number of bathrooms
     */
    public void setNumberBathrooms(int numberBathrooms) {
        this.numberBathrooms = numberBathrooms;
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
     * changes the number of parking spaces
     *
     * @param numberParkingSpaces the number parking spaces
     */
    public void setNumberParkingSpaces(int numberParkingSpaces) {
        this.numberParkingSpaces = numberParkingSpaces;
    }

    /**
     * Gets available equipment.
     *
     * @return available equipment of the request
     */
    public String getAvailableEquipment() {
        return availableEquipment;
    }

    /**
     * Sets available equipment.
     *
     * @param availableEquipment changes the available equipment
     */
    public void setAvailableEquipment(String availableEquipment) {
        this.availableEquipment = availableEquipment;
    }

    /**
     * Gets basement.
     *
     * @return existance of basement
     */
    public boolean getBasement() {
        return basement;
    }

    /**
     * Sets basement.
     *
     * @param basement existance of basement
     */
    public void setBasement(boolean basement) {
        this.basement = basement;
    }

    /**
     * true if there is inhabitable loft and false if not
     *
     * @return boolean inhabitable loft
     */
    public boolean getInhabitableLoft() {
        return inhabitableLoft;
    }

    /**
     * changes the inhabitable loft existance
     *
     * @param inhabitableLoft the inhabitable loft
     */
    public void setInhabitableLoft(boolean inhabitableLoft) {
        this.inhabitableLoft = inhabitableLoft;
    }

    /**
     * Gets sun exposure.
     *
     * @return sun exposure
     */
    public String getSunExposure() {
        return sunExposure;
    }

    /**
     * To dto request dto.
     *
     * @return the request dto
     */
    public RequestDTO toDTO() {
        RequestDTO dto = new RequestDTO();
        dto.setTypeOfCommercialize(getTypeOfCommercialize());
        dto.setRequestType(LAND_TYPE);
        dto.setArea(getArea());
        dto.setLocation(getLocation());
        dto.setDistanceFromCityCenter(getDistanceFromCityCenter());
        dto.setRequestedPrice(getRequestedPrice());
        dto.setPhotographs(getPhotographs());
        AgentMapper agentMapper = new AgentMapper();
        dto.setAgent(agentMapper.toDTO(getAgente()));
        OwnerMapper ownerMapper = new OwnerMapper();
        dto.setOwner(ownerMapper.toDTO(getOwner()));
        dto.setNumberBedrooms(getNumberBedrooms());
        dto.setNumberBathrooms(getNumberBathrooms());
        dto.setId(getRequestId());
        dto.setNumberParkingSpaces(getNumberParkingSpaces());
        dto.setEquipment(getAvailableEquipment());
        dto.setBasement(getBasement());
        dto.setRequestType(getRequestType());
        dto.setSunExposure(getSunExposure());
        dto.setInhabitableLoft(getInhabitableLoft());
        return dto;

    }


    /**
     * changes the sun exposure
     *
     * @param sunExposure the sun exposure
     */
    public void setSunExposure(String sunExposure) {
        this.sunExposure = sunExposure;
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
     * Is inhabitable loft boolean.
     *
     * @return the boolean
     */
    public boolean isInhabitableLoft() {
        return inhabitableLoft;
    }

    /**
     * @return RequestHouse clone
     */


    public RequestHouse clone() {
        return new RequestHouse(this.getTypeOfCommercialize(), this.getAgente(), this.getArea(), this.getLocation(), this.getDistanceFromCityCenter(), this.getRequestedPrice(), this.getNumberBedrooms(), this.getNumberBathrooms(), this.getNumberParkingSpaces(), this.getAvailableEquipment(), this.getBasement(), this.getInhabitableLoft(), this.getSunExposure(), this.getOwner(), this.getPhotographs());


    }


}
