package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.Serialization.Serialization;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgentMapper;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The type Visit request.
 */
public class VisitRequest implements Serializable {


    private static final String STATUS1 = "Approved";
    private static final String STATUS2 = "Not approved";
    private static final String STATUS3 = "Waiting";
    private static final String STATUS4 = "CounterProposal";
    private String date;
    private Agent agent;
    private String status;
    private List<String> timeSlot;
    private String userName;
    private String email;

    private VisitRequestOpinion visitRequestOpinion=null;

    /**
     * Instantiates a new Visit request.
     */
    public VisitRequest() {
    }

    /**
     * Get status accept string.
     *
     * @return the string
     */
    public static String getStatusAccept(){
        return STATUS1;
    }

    /**
     * Get status refuse string.
     *
     * @return the string
     */
    public static String getStatusRefuse(){
        return STATUS2;
    }

    /**
     * Get status waiting string.
     *
     * @return the string
     */
    public static String getStatusWaiting(){
        return STATUS3;
    }


    /**
     * Instantiates a new Visit request.
     *
     * @param date     the date
     * @param timeSlot the time slot
     * @param userName the user name
     * @param email    the email
     * @param agent    the agent
     */
    public VisitRequest(String date, List<String> timeSlot, String userName, String email, Agent agent) {
        this.status = STATUS3;
        this.date = date;
        this.timeSlot = timeSlot;
        this.userName = userName;
        this.email = email;
        this.agent = agent;
        this.visitRequestOpinion=null;
    }

    /**
     * Instantiates a new Visit request.
     *
     * @param visitRequestDTO the visit request dto
     */
    public VisitRequest(VisitRequestDTO visitRequestDTO) {
        this.status = STATUS3;
        this.date = visitRequestDTO.getDate();
        this.email = visitRequestDTO.getEmail();
        this.userName = visitRequestDTO.getUserName();
        this.timeSlot = visitRequestDTO.getTimeSlot();
        AgentMapper agentMapper = new AgentMapper();
        this.agent = agentMapper.fromDTO(visitRequestDTO.getAgentDTO());
        this.visitRequestOpinion=visitRequestDTO.getVisitRequestOpinion();
    }

    /**
     * Instantiates a new Visit request.
     *
     * @param visitRequest the visit request
     * @param opinion      the opinion
     */
    public VisitRequest(VisitRequest visitRequest, VisitRequestOpinion opinion) {
        this.status = STATUS3;
        this.date = visitRequest.getDate();
        this.email = visitRequest.getEmail();
        this.userName = visitRequest.getUserName();
        this.timeSlot = visitRequest.getTimeSlot();
        this.agent = visitRequest.getAgent();
        this.visitRequestOpinion=opinion;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * message of visit request(for agent to see)
     *
     * @return message
     */
    @Override
    public String toString() {
        String str ="";
        str ="date=" + date +
                ", timeSlot=" + timeSlot  +
                ", userName=" + userName +
                ", userMail=" + getEmail() ;

        if (getAnnouncementForVisitRequest()!=null){
            str=str+ ", Announcement=" + getAnnouncementForVisitRequest().getId();
        }
        return str;

    }


    /**
     * To string list string.
     *
     * @return the string
     */
//change this method to display list correctly
    public String toStringList() {
        Property property = getAnnouncementForVisitRequest().getProperty();
        String str ="date=" + date +
                ", TimeSlot=" + timeSlot  +
                ", Location=" + property.getLocation() + " "+ property.getPropertyType() ;

        return str;

    }

    /**
     * Gets agent.
     *
     * @return the agent
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Sets agent.
     *
     * @param agent the agent
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Change status.
     *
     * @param status the status
     */
    public void changeStatus(String status) {
        this.status = status;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets time slot.
     *
     * @return the time slot
     */
    public List<String> getTimeSlot() {
        return timeSlot;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Clone visit request visit request.
     *
     * @return the visit request
     */
    public VisitRequest cloneVisitRequest() {
        return new VisitRequest(getDate(), getTimeSlot(), getUserName(), getEmail(), getAgent());
    }

    /**
     * Sets time slot.
     *
     * @param timeSlot the time slot
     */
    public void setTimeSlot(List<String> timeSlot) {
        this.timeSlot = timeSlot;
    }

    /**
     * Gets visit request opinion.
     *
     * @return the visit request opinion
     */
    public VisitRequestOpinion getVisitRequestOpinion() {
        return visitRequestOpinion;
    }

    /**
     * Sets visit request opinion.
     *
     * @param visitRequestOpinion the visit request opinion
     * @return the visit request opinion
     */
    public boolean setVisitRequestOpinion(VisitRequestOpinion visitRequestOpinion) {
        if (!visitRequestOpinion.equals(this.visitRequestOpinion)){
            this.visitRequestOpinion = visitRequestOpinion;
            return true;
        }
        return false;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Is state waiting boolean.
     *
     * @return the boolean
     */
    public boolean isStateWaiting(){
        return status.equalsIgnoreCase(STATUS3);
    }

    /**
     * Is state approved boolean.
     *
     * @return the boolean
     */
    public boolean isStateApproved(){
        return status.equalsIgnoreCase(STATUS1);
    }

    /**
     * Is date between boolean.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the boolean
     */
    public boolean isDateBetween(Date startDate,Date endDate){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date data = null;

        startDate = getZeroTimeDate(startDate);
        endDate = getZeroTimeDate(endDate);

        try {
            data = formatter.parse(getDate());
            data=getZeroTimeDate(data);

        }catch (ParseException e) {
            e.printStackTrace();
        }

        return (data.after(startDate) || data.equals(startDate)) &&
                (data.before(endDate) || data.equals(endDate));
    }


    private static Date getZeroTimeDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        date = calendar.getTime();
        return date;
    }

    /**
     * Get announcement for visit request announcement available.
     *
     * @return the announcement available
     */
    public AnnouncementAvailable getAnnouncementForVisitRequest(){
        AnnouncementController controller = new AnnouncementController();
        List<AnnouncementAvailable> list= controller.getListingAvailable();
        for (AnnouncementAvailable announcementAvailable : list){
            if (announcementAvailable.getVisitRequests().contains(this)){
                return announcementAvailable;
            }
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitRequest that = (VisitRequest) o;
        return Objects.equals(date, that.date) && Objects.equals(agent, that.agent) && Objects.equals(status, that.status) && Objects.equals(timeSlot, that.timeSlot) && Objects.equals(userName, that.userName) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, agent, status, timeSlot, userName, email, visitRequestOpinion);
    }
}
