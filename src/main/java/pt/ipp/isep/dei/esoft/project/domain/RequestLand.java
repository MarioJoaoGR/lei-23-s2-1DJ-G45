package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgentMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.OwnerMapper;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * The type Request land.
 */
public class RequestLand extends Request implements Serializable {

    /**
     * The Land type.
     */
    String LAND_TYPE = PropertyType.LAND.getDisplayName();

    /**
     * Instantiates a new Request land.
     *
     * @param id                     the id
     * @param typeOfCommercialize    the type of commercialize
     * @param agente                 the agente
     * @param area                   the area
     * @param location               the location
     * @param distanceFromCityCentre the distance from city centre
     * @param requestedPrice         the requested price
     * @param owner                  the owner
     * @param photographs            the photographs
     */
    public RequestLand(int id, String typeOfCommercialize, Agent agente,
                       double area, String location, double distanceFromCityCentre,
                       double requestedPrice, Owner owner , List<String>photographs) {
        super(id, typeOfCommercialize, agente, area, location, distanceFromCityCentre, requestedPrice, owner,photographs);
    }

    /**
     * Instantiates a new Request land.
     *
     * @param requestDTO the request dto
     */
    public RequestLand(RequestDTO requestDTO) {

        super(requestDTO.getTypeOfCommercialize(), requestDTO.getAgent(), requestDTO.getArea(), requestDTO.getLocation(), requestDTO.getDistanceFromCityCenter(), requestDTO.getRequestedPrice(), requestDTO.getOwner(), requestDTO.getPhotographs());

    }

    /**
     * Instantiates a new Request land.
     *
     * @param typeOfCommercialize    sale/rent
     * @param agente                 chosen agent
     * @param area                   land area
     * @param location               land location
     * @param distanceFromCityCenter land distance from city center
     * @param requestedPrice         price of the request
     * @param owner                  owner of the request
     * @param photohraphs            the photohraphs
     */
    public RequestLand(String typeOfCommercialize, Agent agente, double area, String location, Double distanceFromCityCenter, Double requestedPrice, Owner owner,List<String> photohraphs) {
        super(typeOfCommercialize, agente, area, location, distanceFromCityCenter, requestedPrice,owner,photohraphs);
    }

    /**
     * Gets land type.
     *
     * @return land land type
     */
    public String getLAND_TYPE() {
        return LAND_TYPE;
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
        if (!(o instanceof RequestLand)) return false;
        if (!super.equals(o)) return false;
        RequestLand that = (RequestLand) o;
        return LAND_TYPE.equals(that.LAND_TYPE);
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
    return dto;
    }

    /**
     * @param :values – the values to be hashed
     * @return :a hash value of the sequence of input values
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), LAND_TYPE);
    }

    /**
     * @return Request Land clone
     */
    public RequestLand clone() {
        return new RequestLand(this.getTypeOfCommercialize(), this.getAgente(), this.getArea(), this.getLocation(), this.getDistanceFromCityCenter(), this.getRequestedPrice(), this.getOwner(),this.getPhotographs());
    }

    @Override
    public String toString() {
        return super.toString() +
                "LAND_TYPE='" + LAND_TYPE + '\'' +
                '}';
    }

    /**
     * Gets land type.
     *
     * @return String land
     */
    public String getLandType() {
        return LAND_TYPE;
    }
}
