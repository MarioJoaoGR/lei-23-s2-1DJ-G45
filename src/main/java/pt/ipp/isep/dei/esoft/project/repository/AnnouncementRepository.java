package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.mappers.RequestMapper;

import java.io.Serializable;
import java.util.*;

/**
 * The type Listing repository.
 */
public class AnnouncementRepository implements Serializable {


    private final List<AnnouncementAvailable> announcementAvailables = new ArrayList<>();
    private final List<AnnouncementSold> announcementsSold = new ArrayList<>();

    /**
     * Gets listings.
     *
     * @return the listings
     */
//Can't be a clone, need to be changed
    public List<AnnouncementAvailable> getAnnouncementsAvailable() {
        return announcementAvailables;
    }

    /**
     * Gets announcement sorted by time.
     *
     * @return the announcement sorted by time
     */
    public List<AnnouncementAvailable> getAnnouncementSortedByTime() {
        sortByRecent(announcementAvailables);
        return announcementAvailables;
    }

    /**
     * Gets announcements sold.
     *
     * @return the announcements sold
     */
    public List<AnnouncementSold> getAnnouncementsSold() {
        return announcementsSold;
    }


    /**
     * Gets property list.
     *
     * @param business the business
     * @param type     the type
     * @param rooms    the rooms
     * @return the property list
     */
    public List<AnnouncementAvailable> getPropertyList(String business, String type, int rooms) {
        if (business.isEmpty() && type.isEmpty() && rooms == 0) {
            return getAnnouncementsAvailable();
        }
        List<AnnouncementAvailable> listFiltered = new ArrayList<>();
        for (AnnouncementAvailable List : announcementAvailables) {
            if (List.filterProperty(business, type, rooms)) {
                listFiltered.add(List);
            }

        }

        return List.copyOf(listFiltered);
    }

    /**
     * Sort by price asc.
     *
     * @param PropertyListing the property listing
     */
    public void sortByPriceAsc(List<AnnouncementAvailable> PropertyListing) {
        PropertyListing.sort(criterio1);
    }

    /**
     * The Criterio 1.
     */
    transient Comparator<AnnouncementAvailable> criterio1 = new Comparator<AnnouncementAvailable>() {
        public int compare(AnnouncementAvailable L1, AnnouncementAvailable L2) {
            Double price1 = L1.getPrice();
            Double price2 = L2.getPrice();

            return price1.compareTo(price2);
        }
    };


    /**
     * Sort by price desc.
     *
     * @param PropertyListing the property listing
     */
    public void sortByPriceDesc(List<AnnouncementAvailable> PropertyListing) {
        PropertyListing.sort(Collections.reverseOrder(criterio1));
    }

    /**
     * Gets nr properties of an agency.
     *
     * @param agency the agency
     * @return the nr properties of an agency
     */
    public int getNrPropertiesOfAnAgency(Agency agency) {
        int count = 0;

        for (AnnouncementSold announcementSold : announcementsSold) {
            if (announcementSold.getAgency().equals(agency)) {
                count++;
            }
        }
        return count;
    }


    /**
     * Gets schedule visits for client.
     *
     * @param email the email
     * @return the schedule visits for client
     */
    public List<AnnouncementAvailable> getScheduleVisitsForClient(String email) {
        List<AnnouncementAvailable> result = new ArrayList<>();

        for (AnnouncementAvailable announcementAvailable : announcementAvailables) {
            if (announcementAvailable.getProperty().getOwner().getEmail().equalsIgnoreCase(email)) {
                if (checkVisitRequestIsAvailable(announcementAvailable) && thereArentVisitRequestAccepted(announcementAvailable)) {
                    result.add(announcementAvailable);

                }
            }

        }
        return result;
    }

    /**
     * There arent visit request accepted boolean.
     *
     * @param announcementAvailable the announcement available
     * @return the boolean
     */
    public boolean thereArentVisitRequestAccepted(AnnouncementAvailable announcementAvailable) {
        boolean flag = false;
        for (VisitRequest visitRequest : announcementAvailable.getVisitRequests()) {
            if (visitRequest.getStatus().equals("Waiting")) {
                flag = true;
            }
        }
        return flag;
    }


    /**
     * Check visit request is available boolean.
     *
     * @param announcementAvailable the announcement available
     * @return the boolean
     */
    public boolean checkVisitRequestIsAvailable(AnnouncementAvailable announcementAvailable) {

        return !announcementAvailable.getVisitRequests().isEmpty();
    }

    /**
     * Sort by parish.
     *
     * @param propertyAnnouncementAvailable the property listing
     */
    public void sortByParish(List<AnnouncementAvailable> propertyAnnouncementAvailable) {
        propertyAnnouncementAvailable.sort(criterio2);
    }

    /**
     * The Criterio 2.
     */
    transient Comparator<AnnouncementAvailable> criterio2 = new Comparator<AnnouncementAvailable>() {
        public int compare(AnnouncementAvailable L1, AnnouncementAvailable L2) {


            String s1 = L1.getProperty().getLocation();
            String s2 = L2.getProperty().getLocation();

            return s1.compareToIgnoreCase(s2);
        }
    };


    /**
     * Sort by recent.
     *
     * @param propertyAnnouncementAvailable the property listing
     */
    public void sortByRecent(List<AnnouncementAvailable> propertyAnnouncementAvailable) {
        propertyAnnouncementAvailable.sort(criterio3);
    }

    /**
     * The Criterio 3.
     */
    transient Comparator<AnnouncementAvailable> criterio3 = new Comparator<AnnouncementAvailable>() {
        public int compare(AnnouncementAvailable L1, AnnouncementAvailable L2) {
            Date date1 = L1.getDate();
            Date date2 = L2.getDate();

            return Integer.compare(date2.compareTo(date1), 0);
        }
    };


    /**
     * Add sold optional.
     *
     * @param announcementSold the announcement sold
     * @return the optional
     */
    public Optional<AnnouncementSold> addSold(AnnouncementSold announcementSold) {


        Optional<AnnouncementSold> newAnnouncementSold = Optional.empty();

        boolean operationSuccess = false;

        if (validateAnnouncementSold(announcementSold)) {
            newAnnouncementSold = Optional.of(announcementSold.clone());
            operationSuccess = announcementsSold.add(newAnnouncementSold.get());
        }

        if (!operationSuccess) {
            newAnnouncementSold = Optional.empty();
        }

        return newAnnouncementSold;
    }

    /**
     * Add available optional.
     *
     * @param announcementAvailable the announcement available
     * @return the optional
     */
    public Optional<AnnouncementAvailable> addAvailable(AnnouncementAvailable announcementAvailable) {

        Optional<AnnouncementAvailable> newAnnouncement = Optional.empty();

        boolean operationSuccess = false;

        if (validateAnnouncement(announcementAvailable)) {
            newAnnouncement = Optional.of(announcementAvailable.clone());
            operationSuccess = announcementAvailables.add(newAnnouncement.get());
        }

        if (!operationSuccess) {
            newAnnouncement = Optional.empty();
        }

        return newAnnouncement;
    }

    private boolean announcementListDoNotContain(AnnouncementAvailable announcementAvailable) {
        return !announcementAvailables.contains(announcementAvailable);
    }

    private boolean validateAnnouncement(AnnouncementAvailable announcementAvailable) {

        boolean flag = false;
        int count = 0 ;
        if (announcementListDoNotContain(announcementAvailable)){
            for (AnnouncementAvailable obj : announcementAvailables ){
                if (obj.equals(announcementAvailable)) {
                    count++;
                }
            }
            if (count==0){
                return true;
            }
        }
        return flag;
    }

    private boolean announcementListDoNotContainSold(AnnouncementSold announcementSold) {
        return !announcementsSold.contains(announcementSold);
    }

    /**
     * Gets agent announcements.
     *
     * @param email the email
     * @return the agent announcements
     */
    public List<AnnouncementAvailable> getAgentAnnouncements(String email) {
        List<AnnouncementAvailable> AnnouncementAvailableList = this.announcementAvailables;
        List<AnnouncementAvailable> returnList = new ArrayList<>();
        for (AnnouncementAvailable announcementAvailable : AnnouncementAvailableList) {
            if (announcementAvailable.getAgent().getMail().equals(email)) {
                returnList.add(announcementAvailable);
            }
        }
        return returnList;
    }

    private boolean validateAnnouncementSold(AnnouncementSold announcementSold) {
        return announcementListDoNotContainSold(announcementSold);
    }

    /**
     * Gets announcement by id.
     *
     * @param id the id
     * @return the announcement by id
     */
    public Optional<AnnouncementAvailable> getAnnouncementById(int id) {
        Optional<AnnouncementAvailable> announcement = Optional.empty();
        for (AnnouncementAvailable o : announcementAvailables) {
            if (o.getId() == id) {
                announcement = Optional.of(o.clone());
            }
        }
        return announcement;
    }

    /**
     * Create property optional.
     *
     * @param requestDTO the request dto
     * @return the optional
     */
    public Optional<Property> createProperty(RequestDTO requestDTO) {
        PropertyDecision propertyDecision = new PropertyDecision();
        Property property = propertyDecision.createPropertyObject(requestDTO);
        Optional<Property> result = Optional.empty();
        if (property != null) {
            result = Optional.of(property);
        }
        return result;
    }

    /**
     * Create apartment apartment.
     *
     * @param requestDTO the request dto
     * @return the apartment
     */
    public Apartment createApartment(RequestDTO requestDTO) {
        return new Apartment(requestDTO.getLocation(), requestDTO.getArea(), requestDTO.getDistanceFromCityCenter(),
                requestDTO.getPhotographs(), requestDTO.getOwner(), requestDTO.getNumberBedrooms(),
                requestDTO.getNumberBathrooms(), requestDTO.getNumberParkingSpaces(), requestDTO.getEquipment());
    }

    /**
     * Create house house.
     *
     * @param requestDTO the request dto
     * @return the house
     */
    public House createHouse(RequestDTO requestDTO) {
        return new House(requestDTO.getLocation(), requestDTO.getArea(), requestDTO.getDistanceFromCityCenter(),
                requestDTO.getPhotographs(), requestDTO.getNumberBedrooms(), requestDTO.getNumberBathrooms(),
                requestDTO.getNumberParkingSpaces(), requestDTO.getEquipment(), requestDTO.getBasement(),
                requestDTO.getInhabitableLoft(), requestDTO.getSunExposure(), requestDTO.getOwner());
    }

    /**
     * Create land land.
     *
     * @param requestDTO the request dto
     * @return the land
     */
    public Land createLand(RequestDTO requestDTO) {
        return new Land(requestDTO.getLocation(), requestDTO.getArea(), requestDTO.getDistanceFromCityCenter(),
                requestDTO.getPhotographs(), requestDTO.getOwner());
    }

    /**
     * Create announcement available announcement available.
     *
     * @param requestDTO the request dto
     * @param commission the commission
     * @param agent      the agent
     * @return the announcement available
     */
    public AnnouncementAvailable createAnnouncementAvailable(RequestDTO requestDTO, Commission commission, Agent agent) {
        if (requestDTO.getRequestType().equalsIgnoreCase(String.valueOf(PropertyType.APARTMENT))) {
            return new AnnouncementAvailable(commission, commission.getCommissionValue(), new Date(), requestDTO.getTypeOfCommercialize(), createApartment(requestDTO), agent);
        } else if (requestDTO.getRequestType().equalsIgnoreCase(String.valueOf(PropertyType.HOUSE))) {
            return new AnnouncementAvailable(commission, commission.getCommissionValue(), new Date(), requestDTO.getTypeOfCommercialize(), createHouse(requestDTO), agent);
        } else {
            return new AnnouncementAvailable(commission, commission.getCommissionValue(), new Date(), requestDTO.getTypeOfCommercialize(), createLand(requestDTO), agent);
        }
    }

    /**
     * Publish sale.
     *
     * @param requestId the request id
     * @param agent     the agent
     * @param comission the comission
     */
    public void publishSale(int requestId, Agent agent, Commission comission) {
        RequestRepository requestRepository = Repositories.getInstance().getRequestRepository();
        List<Request> agentRequests = requestRepository.getAgentRequests(agent);
        RequestMapper requestMapper = new RequestMapper();
        for (Request request : agentRequests) {
            if (request.getRequestId() == requestId) {
                request.setRequestType(request.getRequestType().replaceAll("Request",""));
                RequestDTO requestDTO = requestMapper.toDTO(request);
                AnnouncementAvailable announcementToPublish = createAnnouncementAvailable(requestDTO, comission, agent);
                announcementAvailables.add(announcementToPublish);
                requestRepository.removeRequestById(requestId);
            }
        }
    }


    /**
     * Save order.
     *
     * @param id    the id
     * @param order the order
     */
    public void saveOrder(int id, Order order) {
        for (AnnouncementAvailable a : announcementAvailables) {
            if (a.getId() == id) {
                a.addOrder(order);
            }
        }
    }

    /**
     * Save order state.
     *
     * @param id    the id
     * @param order the order
     */
    public void saveOrderState(int id, Order order) {
        for (AnnouncementAvailable a : announcementAvailables) {
            if (a.getId() == id) {
                for (Order ord : a.getOrderList()) {
                    if (ord.getId() == order.getId()) {
                        ord.setState(order.getState());
                    }
                }
            }
        }
    }


    /**
     * Save visit request.
     *
     * @param announcementAvailable the announcement available
     * @param visitRequest          the visit request
     */
    public void saveVisitRequest(AnnouncementAvailable announcementAvailable, VisitRequest visitRequest) {
        int postion = 0;
        for (AnnouncementAvailable a : announcementAvailables) {
            if (a.getId() == announcementAvailable.getId()) {
                for (VisitRequest visit : a.getVisitRequests()) {
                    if (visit.equals(visitRequest)) {
                        visit.setStatus(announcementAvailable.getVisitRequests().get(postion).getStatus());

                    }
                }
            }
            postion++;
        }
    }

    /**
     * Save new visit request.
     *
     * @param announcementAvailable the announcement available
     * @param visitRequest          the visit request
     */
    public void saveNewVisitRequest(AnnouncementAvailable announcementAvailable, VisitRequest visitRequest) {
        for (AnnouncementAvailable a : announcementAvailables) {
            if (a.getId() == announcementAvailable.getId()) {
                a.getVisitRequests().add(visitRequest);
            }
        }
    }

    /**
     * Gets visit request list for agent.
     *
     * @param email     the email
     * @param startDate the start date
     * @param endDate   the end date
     * @return the visit request list for agent
     */
    public List<VisitRequest> getVisitRequestListForAgent(String email, Date startDate, Date endDate) {
        List<VisitRequest> aux;
        List<VisitRequest> result = new ArrayList<>();

        for (AnnouncementAvailable announcementAvailable : announcementAvailables) {
            aux = announcementAvailable.getVisitRequestForAgent(email, startDate, endDate);
            result.addAll(aux);
        }

        return result;
    }

    /**
     * Gets visit request list for agent.
     *
     * @param email the email
     * @return the visit request list for agent
     */
    public List<VisitRequest> getVisitRequestListForAgent(String email) {
        List<VisitRequest> aux;
        List<VisitRequest> result = new ArrayList<>();

        for (AnnouncementAvailable announcementAvailable : announcementAvailables) {
            aux = announcementAvailable.getAllVisitRequestForAgent(email);
            result.addAll(aux);
        }

        return result;
    }

    /**
     * List to array announcement sold [ ].
     *
     * @param announcementsSold the announcements sold
     * @return the announcement sold [ ]
     */
    public AnnouncementSold[] ListToArray(List<AnnouncementSold> announcementsSold) {
        AnnouncementSold[] array = announcementsSold.toArray(new AnnouncementSold[0]);
        return array;
    }


    /**
     * Get visit request list.
     *
     * @return the list
     */
    public List<VisitRequest> getVisitRequest(){
        List<VisitRequest> aux = new ArrayList<>();
        for ( AnnouncementAvailable a : announcementAvailables){
            aux.addAll(a.getVisitRequests());
        }
        return  aux;
    }

    /**
     * Save visit request state.
     *
     * @param visitRequest the visit request
     */
    public void saveVisitRequestState(VisitRequest visitRequest) {
        for (AnnouncementAvailable a : announcementAvailables) {
            if (a.getVisitRequests().contains(visitRequest)){
                for (VisitRequest v : a.getVisitRequests()){
                    if (v.equals(visitRequest)){
                        v.setStatus(visitRequest.getStatus());
                    }
                }
            }
        }
    }

}

