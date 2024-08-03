package pt.ipp.isep.dei.esoft.project.domain;

import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgentMapper;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The type Visit request opinion.
 */
public class VisitRequestOpinion implements Serializable {


    private static final String CLASSIFICATION1 = "Highly Improbable";
    private static final String CLASSIFICATION2 = "Unlikely";
    private static final String CLASSIFICATION3 = "Moderately Probable";
    private static final String CLASSIFICATION4 = "Likely";
    private static final String CLASSIFICATION5 = "Highly Probable";

    private static List<String> myList = new ArrayList<String>() {{
        add(CLASSIFICATION1);
        add(CLASSIFICATION2);
        add(CLASSIFICATION3);
        add(CLASSIFICATION4);
        add(CLASSIFICATION5);
    }};

    private String opinion;
    private String classification;

    private int state;
    // 1 - done
    // 0 - for writte opinion


    /**
     * Instantiates a new Visit request opinion.
     */
    public VisitRequestOpinion() {
        this.opinion="";
        this.classification="";
    }

    /**
     * Instantiates a new Visit request opinion.
     *
     * @param opinion        the opinion
     * @param classification the classification
     */
    public VisitRequestOpinion(String opinion , String classification) {
        this.opinion=opinion;
        this.classification=classification;
    }


    /**
     * Gets my list.
     *
     * @return the my list
     */
    public static List<String> getMyList() {
        return myList;
    }

    /**
     * Sets my list.
     *
     * @param myList the my list
     */
    public void setMyList(List<String> myList) {
        this.myList = myList;
    }


    /**
     * Gets opinion.
     *
     * @return the opinion
     */
    public String getOpinion() {
        return opinion;
    }

    /**
     * Sets opinion.
     *
     * @param opinion the opinion
     */
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    /**
     * Gets classification.
     *
     * @return the classification
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Sets classification.
     *
     * @param classification the classification
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitRequestOpinion opinion1 = (VisitRequestOpinion) o;
        return state == opinion1.state && Objects.equals(opinion, opinion1.opinion) && Objects.equals(classification, opinion1.classification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opinion, classification, state);
    }
}
