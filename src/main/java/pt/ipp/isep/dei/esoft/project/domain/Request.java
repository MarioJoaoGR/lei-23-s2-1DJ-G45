package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.RequestRepository;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The type Request.
 */
public class Request implements Serializable {

    private static int idCount = 1;
    private int requestId;

    private Date date;
    private double area;
    private String location;
    private double distanceFromCityCenter;
    private double requestedPrice;
    private String requestType;
    private List<String> photographs = new ArrayList<>();
    private Agent agente;

    private Owner owner;
    private String typeOfCommercialize;

    /**
     * Instantiates a new Request.
     */
    public Request() {
    }

    /**
     * Instantiates a new Request.
     *
     * @param date                   the date
     * @param typeOfCommercialize    the type of commercialize
     * @param agente                 the agente
     * @param area                   the area
     * @param location               the location
     * @param distanceFromCityCentre the distance from city centre
     * @param requestedPrice         the requested price
     * @param owner                  the owner
     * @param photographs            the photographs
     */
    public Request(Date date, String typeOfCommercialize, Agent agente, double area, String location, double distanceFromCityCentre, double requestedPrice, Owner owner, List<String>photographs ) {
        this.date = new Date();
        this.requestId = idCount++;
        int index = getClass().getName().lastIndexOf(".");
        this.requestType = getClass().getName().substring(index + 1);
        this.typeOfCommercialize = typeOfCommercialize;
        this.agente = agente;
        this.area = area;
        this.location = location;
        this.distanceFromCityCenter = distanceFromCityCentre;
        this.requestedPrice = requestedPrice;
        this.owner = owner;
        this.date = new Date();
        this.photographs = photographs;
    }

    /**
     * Instantiates a new Request.
     *
     * @param typeOfCommercialize    the type of commercialize
     * @param agente                 the agente
     * @param area                   the area
     * @param location               the location
     * @param distanceFromCityCentre the distance from city centre
     * @param requestedPrice         the requested price
     * @param owner                  the owner
     * @param photographs            the photographs
     */
    public Request(String typeOfCommercialize, Agent agente, double area, String location, double distanceFromCityCentre, double requestedPrice, Owner owner, List<String>photographs ) {
        this.requestId = idCount++;
        int index = getClass().getName().lastIndexOf(".");
        this.requestType = getClass().getName().substring(index + 1);
        this.typeOfCommercialize = typeOfCommercialize;
        this.agente = agente;
        this.area = area;
        this.location = location;
        this.distanceFromCityCenter = distanceFromCityCentre;
        this.requestedPrice = requestedPrice;
        this.owner = owner;
        this.date = new Date();
        this.photographs = photographs;
    }

    /**
     * Instantiates a new Request.
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
    public Request(int id, String typeOfCommercialize, Agent agente, double area, String location,
                   double distanceFromCityCentre, double requestedPrice, Owner owner,List<String>photographs) {
        this.requestId = id;
        int index = getClass().getName().lastIndexOf(".");
        this.requestType = getClass().getName().substring(index + 1);
        this.typeOfCommercialize = typeOfCommercialize;
        this.agente = agente;
        this.area = area;
        this.location = location;
        this.distanceFromCityCenter = distanceFromCityCentre;
        this.requestedPrice = requestedPrice;
        this.owner = owner;
        this.date = new Date();
        this.photographs = photographs;
    }

    /*public Request(Request anotherRequest) {
        this.requestId = anotherRequest.requestId;
        this.requestType = anotherRequest.requestType;
        this.typeOfCommercialize = anotherRequest.typeOfCommercialize;
        this.agente = anotherRequest.agente;
        this.area = anotherRequest.area;
        this.location = anotherRequest.location;
        this.distanceFromCityCenter = anotherRequest.distanceFromCityCenter;
        this.photographs = anotherRequest.photographs;
        this.requestedPrice = anotherRequest.requestedPrice;
        this.owner = anotherRequest.owner;
        this.date = anotherRequest.date;
    }*/

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
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
     * Gets photographs.
     *
     * @return the photographs
     */
    public List<String> getPhotographs() {
        return photographs;
    }


    /**
     * Is request type boolean.
     *
     * @param type the type
     * @return the boolean
     */
    public boolean isRequestType(String type) {
        return this.requestType.equalsIgnoreCase(type);
    }

    /**
     * Gets request type.
     *
     * @return the request type
     */
    public String getRequestType() {
        return requestType;
    }


    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", area=" + area +
                ", location='" + location + '\'' +
                ", distanceFromCityCenter=" + distanceFromCityCenter +
                ", requestedPrice=" + requestedPrice +
                ", photographs=" + photographs +
                ", agente=" + agente +
                ", owner=" + owner +
                ", typeOfCommercialize='" + typeOfCommercialize + '\'' +
                '}';
    }


    /**
     * Gets type of commercialize.
     *
     * @return the type of commercialize
     */
    public String getTypeOfCommercialize() {
        return typeOfCommercialize;
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
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return Double.compare(request.area, area) == 0 && Double.compare(request.distanceFromCityCenter, distanceFromCityCenter) == 0 && Double.compare(request.requestedPrice, requestedPrice) == 0 && location.equals(request.location) && photographs.equals(request.photographs) && agente.equals(request.agente) && owner.equals(request.owner)  && typeOfCommercialize.equals(request.typeOfCommercialize);
    }

    /**
     * @param :values – the values to be hashed
     * @return :a hash value of the sequence of input values
     */
    @Override
    public int hashCode() {
        return Objects.hash(area, location, distanceFromCityCenter, requestedPrice, photographs, agente, owner, typeOfCommercialize);
    }

    /**
     * Add photo.
     *
     * @param photo the photo
     */
    public void addPhoto(String photo){
        photographs.add(photo);
    }

    /**
     * @return clone the requestClone
     */

    public Request clone() {
        List<String> clonedPhotographs = new ArrayList<>();
        for (String photograph : this.photographs) {
            clonedPhotographs.add(photograph);
        }
        Request clonedRequest = new Request(this.requestId, this.typeOfCommercialize, this.agente, this.area, this.location, this.distanceFromCityCenter, this.requestedPrice, this.owner,clonedPhotographs);


        return clonedRequest;
    }

    /**
     * Gets request id.
     *
     * @return request id
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * Gets owner.
     *
     * @return request owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Gets location.
     *
     * @return location of the property
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets area.
     *
     * @return area of the property associated to request
     */
    public Double getArea() {
        return area;
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
     * Gets agente.
     *
     * @return see the agent associated to the request
     */
    public Agent getAgente() {
        return this.agente;
    }

    /**
     * Sets requested price.
     *
     * @param requestedPrice change the price associated to the property request
     */
    public void setRequestedPrice(double requestedPrice) {
        this.requestedPrice = requestedPrice;
    }

    /**
     * Get request request.
     *
     * @return the request
     */
    public Request getRequest(){
        return this;
    }

    /**
     * Sets id count.
     *
     * @param idCount the id count
     */
    public static void setIdCount(int idCount) {
        Request.idCount = idCount;
    }

    /**
     * Sets request id.
     *
     * @param requestId the request id
     */
    public void setRequestId(int requestId) {
        this.requestId = requestId;
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
     * Sets distance from city center.
     *
     * @param distanceFromCityCenter the distance from city center
     */
    public void setDistanceFromCityCenter(double distanceFromCityCenter) {
        this.distanceFromCityCenter = distanceFromCityCenter;
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
     * Sets agente.
     *
     * @param agente the agente
     */
    public void setAgente(Agent agente) {
        this.agente = agente;
    }

    /**
     * Sets type of commercialize.
     *
     * @param typeOfCommercialize the type of commercialize
     */
    public void setTypeOfCommercialize(String typeOfCommercialize) {
        this.typeOfCommercialize = typeOfCommercialize;
    }
}
