package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgentMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.OwnerMapper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The type Request apartment.
 */
public class RequestApartment extends Request implements Serializable {

    private int numberBedrooms;
    private int numberBathrooms;
    private int numberParkingSpaces;
    private String availableEquipment;
    private final String LAND_TYPE = PropertyType.APARTMENT.getDisplayName();


    /**
     * Gets land type.
     *
     * @return the land type
     */
    public String getLAND_TYPE() {
        return LAND_TYPE;
    }

    /**
     * Instantiates a new Request apartment.
     *
     * @param date                   the date
     * @param typeOfCommercialize    the type of commercialize
     * @param agente                 the agente
     * @param area                   the area
     * @param location               the location
     * @param distanceFromCityCenter the distance from city center
     * @param requestedPrice         the requested price
     * @param numberBedrooms         the number bedrooms
     * @param numberBathrooms        the number bathrooms
     * @param numberParkingSpaces    the number parking spaces
     * @param availableEquipment     the available equipment
     * @param owner                  the owner
     * @param photographs            the photographs
     */
    public RequestApartment(Date date, String typeOfCommercialize, Agent agente, double area, String location,
                            double distanceFromCityCenter, Double requestedPrice, int numberBedrooms, int numberBathrooms,
                            int numberParkingSpaces, String availableEquipment, Owner owner,List<String>photographs) {
        super(date, typeOfCommercialize, agente, area, location, distanceFromCityCenter, requestedPrice, owner,photographs);
        this.numberBedrooms = numberBedrooms;
        this.numberBathrooms = numberBathrooms;
        this.numberParkingSpaces = numberParkingSpaces;
        this.availableEquipment = availableEquipment;
    }

    /**
     * Instantiates a new Request apartment.
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
     * @param photographs            the photographs
     */
    public RequestApartment(int id, String typeOfCommercialize, Agent agente, double area,
                            String location, double distanceFromCityCentre, double requestedPrice,
                            Owner owner, int numberBedrooms, int numberBathrooms, int numberParkingSpaces,
                            String availableEquipment, List<String>photographs) {
        super(id, typeOfCommercialize, agente, area, location, distanceFromCityCentre, requestedPrice, owner,photographs);
        this.numberBedrooms = numberBedrooms;
        this.numberBathrooms = numberBathrooms;
        this.numberParkingSpaces = numberParkingSpaces;
        this.availableEquipment = availableEquipment;
    }

    /**
     * Instantiates a new Request apartment.
     *
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
     * @param photographs            the photographs
     */
    public RequestApartment(String typeOfCommercialize, Agent agente, double area,
                            String location, double distanceFromCityCentre, double requestedPrice,
                            Owner owner, int numberBedrooms, int numberBathrooms, int numberParkingSpaces,
                            String availableEquipment, List<String>photographs) {
        super(typeOfCommercialize, agente, area, location, distanceFromCityCentre, requestedPrice, owner,photographs);
        this.numberBedrooms = numberBedrooms;
        this.numberBathrooms = numberBathrooms;
        this.numberParkingSpaces = numberParkingSpaces;
        this.availableEquipment = availableEquipment;
    }


    /**
     * Instantiates a new Request apartment.
     *
     * @param typeOfCommercialize    the type of commercialize
     * @param agente                 the agente
     * @param area                   the area
     * @param location               the location
     * @param distanceFromCityCenter the distance from city center
     * @param requestedPrice         the requested price
     * @param numberBedrooms         the number bedrooms
     * @param numberBathrooms        the number bathrooms
     * @param numberParkingSpaces    the number parking spaces
     * @param availableEquipment     the available equipment
     * @param owner                  the owner
     * @param photographs            the photographs
     */
    public RequestApartment(String typeOfCommercialize, Agent agente, double area, String location,
                            double distanceFromCityCenter, Double requestedPrice, int numberBedrooms, int numberBathrooms,
                            int numberParkingSpaces, String availableEquipment, Owner owner,List<String>photographs) {
        super(typeOfCommercialize, agente, area, location, distanceFromCityCenter, requestedPrice, owner,photographs);
        this.numberBedrooms = numberBedrooms;
        this.numberBathrooms = numberBathrooms;
        this.numberParkingSpaces = numberParkingSpaces;
        this.availableEquipment = availableEquipment;
    }


    /**
     * Gets land type.
     *
     * @return apartment land type
     */
    public String getLandType() {
        return LAND_TYPE;
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
        dto.setId(getRequestId());
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
        dto.setNumberParkingSpaces(getNumberParkingSpaces());
        dto.setEquipment(getAvailableEquipment());
    return dto;
    }

    /**
     * Gets number bedrooms.
     *
     * @return number of bedrooms
     */
    public int getNumberBedrooms() {
        return numberBedrooms;
    }

    /**
     * Gets number bathrooms.
     *
     * @return number of bathrooms
     */
    public int getNumberBathrooms() {
        return numberBathrooms;
    }

    @Override
    public String toString() {
        return super.toString()+
                "numberBedrooms=" + numberBedrooms +
                ", numberBathrooms=" + numberBathrooms +
                ", numberParkingSpaces=" + numberParkingSpaces +
                ", availableEquipment='" + availableEquipment + '\'' +
                ", LAND_TYPE='" + LAND_TYPE + '\'' +
                '}';
    }

    /**
     * Instantiates a new Request apartment.
     *
     * @param requestDTO the request dto
     * @return number of parking spaces
     */
    public RequestApartment(RequestDTO requestDTO) {

        super(requestDTO.getTypeOfCommercialize(), requestDTO.getAgent(), requestDTO.getArea(), requestDTO.getLocation(), requestDTO.getDistanceFromCityCenter(), requestDTO.getRequestedPrice(), requestDTO.getOwner(), requestDTO.getPhotographs());
        this.numberBathrooms = requestDTO.getNumberBathrooms();
        this.numberBedrooms = requestDTO.getNumberBedrooms();
        this.availableEquipment = requestDTO.getEquipment();
        this.numberParkingSpaces = requestDTO.getNumberParkingSpaces();

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
     * Gets available equipment.
     *
     * @return the available equipment
     */
    public String getAvailableEquipment() {
        return availableEquipment;
    }



    /**
     *
     * @return clone of RequestApartment
     */

    public RequestApartment clone(){
        return new RequestApartment(this.getDate(),this.getTypeOfCommercialize(),this.getAgente(),this.getArea(),this.getLocation(),
                this.getDistanceFromCityCenter(),this.getRequestedPrice(),this.numberBedrooms,this.numberBathrooms,this.numberParkingSpaces,this.availableEquipment,this.getOwner(),this.getPhotographs());

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RequestApartment that = (RequestApartment) o;
        return numberBedrooms == that.numberBedrooms && numberBathrooms == that.numberBathrooms && numberParkingSpaces == that.numberParkingSpaces && Objects.equals(availableEquipment, that.availableEquipment) && Objects.equals(LAND_TYPE, that.LAND_TYPE);
    }

}
