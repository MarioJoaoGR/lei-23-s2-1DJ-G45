package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgentDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.OrderDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgentMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AnnouncementMapper;
import pt.ipp.isep.dei.esoft.project.domain.mappers.VisitRequestMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The type Announcement controller.
 */
public class AnnouncementController {

    private AnnouncementRepository announcementRepository = null;

    private AgencyRepository agencyRepository = null;

    private final AnnouncementMapper mapper = new AnnouncementMapper();
    private AuthenticationRepository authenticationRepository = null;
    private List<VisitRequest> visitRequestList;

    /**
     * Instantiates a new Announcement controller.
     */
    public AnnouncementController() {
        visitRequestList = new ArrayList<>();
        getAnnouncementRepository();
        getAuthenticationRepository();
        getAgencyRepository();
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    /**
     * Instantiates a new Announcement controller.
     *
     * @param announcementRepository   the announcement repository
     * @param authenticationRepository the authentication repository
     */
//Allows receiving the repositories as parameters for testing purposes
    public AnnouncementController(AnnouncementRepository announcementRepository, AuthenticationRepository authenticationRepository) {
        this.announcementRepository = announcementRepository;
        this.authenticationRepository = authenticationRepository;
    }

    private AnnouncementRepository getAnnouncementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;

    }

    /**
     * Gets listing available.
     *
     * @return the listing available
     */
    public List<AnnouncementAvailable> getListingAvailable() {

        AnnouncementRepository announcementRepo = getAnnouncementRepository();

        return announcementRepo.getAnnouncementsAvailable();
    }


    /**
     * Gets listing sold.
     *
     * @return the listing sold
     */
    public List<AnnouncementSold> getListingSold() {

        AnnouncementRepository announcementRepo = getAnnouncementRepository();

        return announcementRepo.getAnnouncementsSold();
    }

    /**
     * Gets filtered listing.
     *
     * @param business the business
     * @param type     the type
     * @param rooms    the rooms
     * @return the filtered listing
     */
    public List<AnnouncementAvailable> getFilteredListing(String business, String type, int rooms) {

        AnnouncementRepository announcementRepo = getAnnouncementRepository();

        return announcementRepo.getPropertyList(business, type, rooms);
    }


    /**
     * Add announcement available.
     *
     * @param announcementAvailable the announcement available
     */
    public void addAnnouncementAvailable(AnnouncementAvailable announcementAvailable) {

        AnnouncementRepository announcementRepo = getAnnouncementRepository();

        announcementRepo.addAvailable(announcementAvailable);
    }

    /**
     * Add announcement sold.
     *
     * @param announcementSold the announcement sold
     */
    public void addAnnouncementSold(AnnouncementSold announcementSold) {

        AnnouncementRepository announcementRepo = getAnnouncementRepository();

        announcementRepo.addSold(announcementSold);
    }

    /**
     * Gets client from session.
     *
     * @return the client from session
     */
    public String getClientFromSession() {

        return ApplicationSession.getInstance().getCurrentSession().getUserEmail();
    }

    /**
     * Is login boolean.
     *
     * @return the boolean
     */
    public boolean isLogin() {
        return ApplicationSession.getInstance().getCurrentSession().isLoggedIn();
    }


    /**
     * Check announcement exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean checkAnnouncementExists(int id) {
        boolean flag = false;
        Optional<AnnouncementAvailable> result = announcementRepository.getAnnouncementById(id);

        if (result.isPresent()) {
            flag = true;
        }
        return flag;

    }

    /**
     * Gets announcement by id.
     *
     * @param id the id
     * @return the announcement by id
     */
    public Optional<AnnouncementDTO> getAnnouncementById(int id) {
        AnnouncementAvailable announcementAvailable = announcementRepository.getAnnouncementById(id).get();
        return Optional.ofNullable(mapper.toAnnouncementDTO(announcementAvailable));
    }

    /**
     * Create order optional.
     *
     * @param announcementAvailableDTO the announcement available dto
     * @param email                    the email
     * @param value                    the value
     * @return the optional
     */
    public Optional<Order> createOrder(AnnouncementDTO announcementAvailableDTO, String email, double value) {
        AnnouncementAvailable announcementAvailable = mapper.toAnnouncementAvailable(announcementAvailableDTO);
        Optional<Order> order = announcementAvailable.addOrder(announcementAvailable.createOrder(email, value));
        if (!order.isEmpty()) {
            saveOrder(announcementAvailable.getId(), order.get());

        }
        return order;
    }

    /**
     * Gets list announcement available dto.
     *
     * @param business the business
     * @param type     the type
     * @param rooms    the rooms
     * @return the list announcement available dto
     */
    public List<AnnouncementDTO> getListAnnouncementAvailableDTO(String business, String type, int rooms) {
        List<AnnouncementAvailable> announcementAvailables = getFilteredListing(business, type, rooms);
        List<AnnouncementDTO> announcementDTOs = new ArrayList<>();

        for (AnnouncementAvailable announcement : announcementAvailables) {
            AnnouncementDTO announcementDTO = announcement.toDTO();
            announcementDTOs.add(announcementDTO);
        }
        return announcementDTOs;
    }

    /**
     * Gets list announcement available dt ofor visit request.
     *
     * @return the list announcement available dt ofor visit request
     */
    public List<AnnouncementDTO> getListAnnouncementAvailableDTOforVisitRequest() {
        AnnouncementMapper announcementMapper = new AnnouncementMapper();
        List<AnnouncementAvailable> list = announcementRepository.getAnnouncementSortedByTime();

        return announcementMapper.toDTO(list);


    }

    /**
     * Gets order list dto.
     *
     * @param announcementDTO the announcement dto
     * @return the order list dto
     */
    public List<OrderDTO> getOrderListDTO(AnnouncementDTO announcementDTO) {
        return announcementDTO.getOrderList();
    }

    /**
     * Gets list announcement available dto for agent.
     *
     * @return the list announcement available dto for agent
     */
    public List<AnnouncementDTO> getListAnnouncementAvailableDTOForAgent() {
        List<AnnouncementAvailable> list = announcementRepository.getAgentAnnouncements(getClientFromSession());
        List<AnnouncementDTO> result = new ArrayList<>();

        for (AnnouncementAvailable announcementAvailable : list) {
            result.add(mapper.toAnnouncementDTO(announcementAvailable));
        }

        return result;
    }

    /**
     * Send order results via email boolean.
     *
     * @param orders       the orders
     * @param announcement the announcement
     * @return the boolean
     */
    public boolean sendOrderResultsViaEmail(List<OrderDTO> orders, AnnouncementDTO announcement) {
        boolean isSuccess = false;

        for (OrderDTO order : orders) {
            UUID id = UUID.randomUUID();
            String email = order.getClientEmail();
            String fileName = email + id + ".txt";
            String announcementStr = announcement.getBusinessType() + " " +announcement.getProperty().getPropertyType() + " in " + announcement.getProperty().getLocation();


            try {
                Formatter formatter = new Formatter(fileName);
                // Set isSuccess to true if the email message is created and stored successfully.
                if (order.getState() == 2) {
                    formatter.format("We are glad to inform you that your purchase order has been accepted.%nThank you for choosing us and we hope you are happy with our work.%nWe hope to see you soon%n%nThis are some of the details corresponding your most recent purchase:%nOrder id: %d%nOrder value: %.2f%nBusiness type: %s%nProperty type: %s%nLocation: %s", order.getId(), order.getValue(), announcement.getBusinessType(), announcement.getProperty().getPropertyType(), announcement.getProperty().getLocation());
                } else if (order.getState() == 1) {
                    formatter.format("We are sorry to inform you that your purchase order with the id %d to the announcement %s has been declined.%nThank you for choosing us and we hope you are happy with our work.%nWe hope to see you soon", order.getId(),announcementStr);
                }
                formatter.close();
                isSuccess = true;
            } catch (FileNotFoundException e) {
                System.out.println("Emails were not delivered!");
                e.printStackTrace();
            }
        }
        return isSuccess;
    }


    public boolean sendOrderResultsRefuseViaEmail(OrderDTO order, AnnouncementDTO announcement) {
        boolean isSuccess = false;
        String announcementStr = announcement.getBusinessType() + " " +announcement.getProperty().getPropertyType() + " in " + announcement.getProperty().getLocation();

        UUID id = UUID.randomUUID();
        String email = order.getClientEmail();
        String fileName = email + id + ".txt";

        try {
            Formatter formatter = new Formatter(fileName);
            // Set isSuccess to true if the email message is created and stored successfully.
            if (order.getState() == 1) {
                formatter.format("We are sorry to inform you that your purchase order with the id %d to the announcement %s has been declined.%nThank you for choosing us and we hope you are happy with our work.%nWe hope to see you soon", order.getId(),announcementStr);
            }
            formatter.close();
            isSuccess = true;
        } catch (FileNotFoundException e) {
            System.out.println("Emails were not delivered!");
            e.printStackTrace();
        }

        return isSuccess;
    }

    //SCHEDULE VISIT PART//

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        for (UserDTO user : authenticationRepository.getUsers()){
            if (Objects.equals(getEmail(), user.getId())){
                return user.getName();
            }
        }
        return "";
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return authenticationRepository.getCurrentUserSession().getUserId().getEmail();

    }

    /**
     * Create visit request boolean.
     *
     * @param visitRequestDTO the visit request dto
     * @param announcementDTO the announcement dto
     * @return the boolean
     */
    public boolean createVisitRequest(VisitRequestDTO visitRequestDTO, AnnouncementDTO announcementDTO) {
        boolean flag = false;

        AnnouncementMapper announcementMapper = new AnnouncementMapper();
        AnnouncementAvailable announcement = announcementMapper.toAnnouncementAvailable(announcementDTO);
        VisitRequest newVisitRequest = announcement.createVisitRequest(visitRequestDTO);;
        Optional<VisitRequest> result = announcement.addVisitRequests(newVisitRequest);
        if (result.isPresent()) {
            announcementRepository.saveNewVisitRequest(announcement,result.get());
            flag = true;
        }

        return flag;
    }

    /**
     * Save order.
     *
     * @param id    the id
     * @param order the order
     */
    public void saveOrder(int id, Order order) {
        announcementRepository.saveOrder(id, order);
    }


    /**
     * Save order state.
     *
     * @param id    the id
     * @param order the order
     */
    public void saveOrderState(int id, Order order) {
        announcementRepository.saveOrderState(id, order);
    }


    /**
     * Save visit request.
     *
     * @param announcementAvailable the announcement available
     * @param visitRequest          the visit request
     */
    public void saveVisitRequest(AnnouncementAvailable announcementAvailable, VisitRequest visitRequest) {
        announcementRepository.saveVisitRequest(announcementAvailable, visitRequest);
    }


    /**
     * Gets visit request list for agent.
     *
     * @param startDate the start date
     * @param endDate   the end date
     */
    public void getVisitRequestListForAgent(Date startDate, Date endDate) {

        String email = getClientFromSession();
        AnnouncementRepository repository = getAnnouncementRepository();
        visitRequestList = repository.getVisitRequestListForAgent(email, startDate, endDate);

    }

    /**
     * Gets visit request list for agent.
     *
     * @param email     the email
     * @param startDate the start date
     * @param endDate   the end date
     * @return the visit request list for agent
     */
    public List<VisitRequest> getVisitRequestListForAgent(String email,Date startDate, Date endDate) {
        return announcementRepository.getVisitRequestListForAgent(email, startDate, endDate);

    }

    /**
     * Gets all visit request list for agent.
     *
     * @return the all visit request list for agent
     */
    public List<VisitRequest> getAllVisitRequestListForAgent() {

        String email = getClientFromSession();
        AnnouncementRepository repository = getAnnouncementRepository();
        return repository.getVisitRequestListForAgent(email);

    }


    /**
     * Gets visit request lists.
     *
     * @return the visit request lists
     */
    public List<VisitRequest> getVisitRequestLists() {
        return announcementRepository.getVisitRequest();
    }

    /**
     * Gets visit request lists waiting.
     *
     * @return the visit request lists waiting
     */
    public List<VisitRequest> getVisitRequestListsWaiting() {
        List<VisitRequest> aux=new ArrayList<>();

        for (VisitRequest v : getVisitRequestLists()){
            if (v.isStateWaiting()){
                aux.add(v);
            }
        }
        return aux;

    }

    /**
     * Sets sort by date.
     *
     * @param visitRequests the visit requests
     */
    public void setSortByDate(List<VisitRequest> visitRequests) {
        visitRequestList = visitRequests;
    }

    /**
     * Is visit request list empty boolean.
     *
     * @return the boolean
     */
    public boolean isVisitRequestListEmpty() {
        return visitRequestList.isEmpty();
    }

    /**
     * Get visit request list string [ ].
     *
     * @return the string [ ]
     */
    public String[] getVisitRequestList() {
        String[] array = new String[visitRequestList.size()];
        for (int i = 0; i < visitRequestList.size(); i++) {
            VisitRequest appointment = visitRequestList.get(i);
            String appointmentString = appointment.toStringList();
            array[i] = appointmentString;
        }

        return array;
    }

    /**
     * Get deals list string [ ].
     *
     * @return the string [ ]
     */
    public String[] getDealsList() {
        String[] array = new String[getListingSold().size()];
        for (int i = 0; i < getListingSold().size(); i++) {
            AnnouncementSold announcement = getListingSold().get(i);
            String dealString = announcement.toString() + " "+ announcement.getProperty().toString();
            array[i] = dealString;
        }

        return array;
    }



    /**
     * Send email boolean.
     *
     * @param announcementAvailable the announcement available
     * @param visitRequest          the visit request
     * @param flag                  the flag
     * @return the boolean
     */
    public boolean sendEmail(AnnouncementAvailable announcementAvailable, VisitRequest visitRequest,boolean flag) {
        String name = visitRequest.getUserName();
        //String phoneNumber = String.valueOf(announcementAvailable.getAgent().getPhoneNumber());
        String toMail = visitRequest.getEmail();
        String info = "default";
        if (announcementAvailable.getProperty() instanceof Land) {
            info = "a "+announcementAvailable.getProperty().getPropertyType() + " in " + announcementAvailable.getProperty().getLocation();
        }
        if (announcementAvailable.getProperty() instanceof House) {
            info = "a "+((House) announcementAvailable.getProperty()).getNumberRooms() + "-" + announcementAvailable.getProperty().getPropertyType() + " in " + announcementAvailable.getProperty().getLocation();
        }
        if (announcementAvailable.getProperty() instanceof Apartment) {
            info = "an "+((Apartment) announcementAvailable.getProperty()).getNumberRooms() + "-" + announcementAvailable.getProperty().getPropertyType() + " in " + announcementAvailable.getProperty().getLocation();
        }

        String message ="";
        if (flag){
            message= name + ", your visit request for announcement about " + info + " to day " + visitRequest.getDate() +" at "+ visitRequest.getTimeSlot() + " was accepted \n\n"+announcementAvailable.getAgent().getName() +"\n"+announcementAvailable.getAgent().getPhoneNumber();
        }else {
            message= name + ", your visit request for announcement about " + info + " to day " + visitRequest.getDate() +" at "+ visitRequest.getTimeSlot() + " was rejected \n\n"+announcementAvailable.getAgent().getName() +"\n"+announcementAvailable.getAgent().getPhoneNumber();

        }
       return SendEmail.emailSend(toMail, message);
    }


    /**
     * Accept visit request.
     *
     * @param visitRequest the visit request
     */
    public void acceptVisitRequest(VisitRequest visitRequest){
        visitRequest.setStatus(VisitRequest.getStatusAccept());
        announcementRepository.saveVisitRequestState(visitRequest);
        updateVisitRequestList();
    }

    /**
     * Refuse visit request.
     *
     * @param visitRequest the visit request
     */
    public void refuseVisitRequest(VisitRequest visitRequest){
        visitRequest.setStatus(VisitRequest.getStatusRefuse());
        updateVisitRequestList();
    }

    /**
     * Update visit request list.
     */
    public void updateVisitRequestList(){
        visitRequestList.removeIf(visit -> !visit.getStatus().equalsIgnoreCase(VisitRequest.getStatusWaiting()));
    }

    /**
     * Get agency list list.
     *
     * @return the list
     */
    public List<Agency> getAgencyList(){
        return agencyRepository.getAgencyList();
    }

    /**
     * Add agency list.
     *
     * @param agency the agency
     */
    public void addAgencyList(Agency agency){
        int nextId;
        if (agencyRepository.getAgencyList().isEmpty()){
            nextId=1;
        }else {
            nextId = agencyRepository.getAgencyList().size()+1;
        }
        agency.setId(nextId);
        agencyRepository.add(agency);
    }
}



