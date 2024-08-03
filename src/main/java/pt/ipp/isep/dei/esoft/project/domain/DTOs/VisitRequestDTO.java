package pt.ipp.isep.dei.esoft.project.domain.DTOs;

import pt.ipp.isep.dei.esoft.project.domain.VisitRequestOpinion;

import java.util.List;

/**
 * The type Visit request dto.
 */
public class VisitRequestDTO {

    private final String STATUS1 = "Approved";
    private final String STATUS2 = "Not approved";
    private final String STATUS3 = "Waiting";
    private String date;
    private String status;
    private AgentDTO agentDTO;
    private List<String> timeSlot;
    private String userName;
    private String email;
    private VisitRequestOpinion visitRequestOpinion;

    /**
     * Instantiates a new Visit request dto.
     */
    public VisitRequestDTO() {

    }

    /**
     * Gets agent dto.
     *
     * @return the agent dto
     */
    public AgentDTO getAgentDTO() {
        return agentDTO;
    }

    /**
     * Sets agent dto.
     *
     * @param agentDTO the agent dto
     */
    public void setAgentDTO(AgentDTO agentDTO) {
        this.agentDTO = agentDTO;
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
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
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
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets time slot.
     *
     * @return the time slot
     */
    public List<String> getTimeSlot() {
        return this.timeSlot;
    }

    /**
     * Add time slot boolean.
     *
     * @param timeSlot the time slot
     * @return the boolean
     */
    public boolean addTimeSlot(String timeSlot) {
        if (this.timeSlot.contains(timeSlot)) {
            return false;
        } else {
            getTimeSlot().add(timeSlot);
            return true;
        }
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
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns a string representation of the VisitRequestDTO object.
     *
     * @return a string representation of the VisitRequestDTO object
     */
    @Override
    public String toString() {
        return "VisitRequest{" +
                "date='" + date + '\'' +
                ", userName of the owner of the proposal='" + userName + '\'' +
                ", email of the owner of the proposal='" + email + '\'' +
                '}';
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
     */
    public void setVisitRequestOpinion(VisitRequestOpinion visitRequestOpinion) {
        this.visitRequestOpinion = visitRequestOpinion;
    }
}
