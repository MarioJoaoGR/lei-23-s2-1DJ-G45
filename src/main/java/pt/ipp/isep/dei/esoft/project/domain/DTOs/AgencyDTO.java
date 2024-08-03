package pt.ipp.isep.dei.esoft.project.domain.DTOs;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.domain.StoreManager;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * The type Agency dto.
 */
public class AgencyDTO {

    private String name;
    private String address;
    private long phoneNumber;
    private String email;
    private StoreManagerDTO storeManager;

    private List<AgentDTO> agentDTOList = new ArrayList<>();

    /**
     * Instantiates a new Agency dto.
     */
    public AgencyDTO() {
    }

    /**
     * Sets agent dto list.
     *
     * @param agentDTOList the agent dto list
     */
    public void setAgentDTOList(List<AgentDTO> agentDTOList) {
        this.agentDTOList = agentDTOList;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
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
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets store manager.
     *
     * @return the store manager
     */
    public StoreManagerDTO getStoreManager() {
        return storeManager;
    }

    /**
     * Sets store manager.
     *
     * @param storeManager the store manager
     */
    public void setStoreManager(StoreManagerDTO storeManager) {
        this.storeManager = storeManager;
    }

    /**
     * Gets agent dto list.
     *
     * @return the agent dto list
     */
    public List<AgentDTO> getAgentDTOList() {
        return agentDTOList;
    }

    /**
     * Gets random agent.
     *
     * @return the random agent
     */
    public Optional<AgentDTO> getRandomAgent() {
        Random rand = new Random();
        Optional<AgentDTO> result = Optional.empty();
        int index=0;
        if (agentDTOList.size() > 1) {
            index = rand.nextInt(agentDTOList.size());
            result = Optional.of(agentDTOList.get(index));
        }else {
            result =Optional.of(agentDTOList.get(index));
        }

        return result;
    }

}
