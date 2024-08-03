package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgentDTO;
import pt.ipp.isep.dei.esoft.project.domain.mappers.AgentMapper;

import java.io.Serializable;
import java.util.*;

/**
 * The type Agency.
 */
public class Agency implements Serializable {

    private static int idCount = 1;
    private int id;

    private String name;
    private String address;
    private long phoneNumber;
    private String email;

    private StoreManager storeManager;

    private final List<Agent> agentList = new ArrayList<>();


    /**
     * Gets agents list.
     *
     * @return agentsList agents list
     */
    public List<Agent> getAgentsList() {
        return agentList;
    }

    /**
     * Search agent boolean.
     *
     * @param agente agent to be searched
     * @return true if agent exists
     */
    public boolean searchAgent(Agent agente) {
        if (agentList.contains(agente)) {
            return true;

        }
    return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agency)) return false;
        Agency agency = (Agency) o;
        if (((Agency) o).getStoreManager() != null){
            return phoneNumber == agency.phoneNumber && name.equals(agency.name) && Objects.equals(address, agency.address) && email.equals(agency.email) && storeManager.equals(agency.storeManager) && Objects.equals(agentList, agency.agentList);

        }else {
            return phoneNumber == agency.phoneNumber && name.equals(agency.name) && Objects.equals(address, agency.address) && email.equals(agency.email) && Objects.equals(agentList, agency.agentList);

        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, phoneNumber, email, storeManager, agentList);
    }

    /**
     * Check store manager exists boolean.
     *
     * @param storeManager the store manager
     * @return the boolean
     */
    public boolean checkStoreManagerExists(StoreManager storeManager) {
        return storeManager.equals(this.storeManager);
    }


    private boolean agentListDoNotContain(Agent agent) {
        return !agentList.contains(agent);
    }

    /**
     * Optional will be empty if storeManager was not added and will have the storeManager is was added correctly
     *
     * @param storeManager the store manager
     * @return Optional value of the storeManager to be added
     */
    public Optional<StoreManager> addStoreManager(StoreManager storeManager) {

        Optional<StoreManager> newStoreManager = Optional.empty();

        boolean operationSuccess = false;

        if (validateStoreManager(storeManager)) {
            newStoreManager = Optional.of(storeManager.clone());
        }

        if (!operationSuccess) {
            newStoreManager = Optional.empty();
        }

        return newStoreManager;

    }

    /**
     * Optional will be empty if agent was not added and will have the agent is was added correctly
     *
     * @param agent the agent
     * @return Optional value of the agent to be added
     */
    public Optional<Agent> addAgent(Agent agent) {

        Optional<Agent> newAgents = Optional.empty();

        boolean operationSuccess = false;

        if (validateAgent(agent)) {
            newAgents = Optional.of(agent.clone());
            operationSuccess = agentList.add(newAgents.get());
        }

        if (!operationSuccess) {
            newAgents = Optional.empty();
        }

        return newAgents;

    }


    /**
     * removes the agent if founds it
     *
     * @param agent the agent
     * @return Optional value of the removed agent
     */
    public Optional<Agent> removeAgent(Agent agent) {
        Optional<Agent> removedAgent = Optional.empty();
        if (agentList.contains(agent)) {
            removedAgent = Optional.of(agent);
            agentList.remove(agent);
        }
        return removedAgent;
    }

    /**
     * Gets name.
     *
     * @return agency name
     */
    public String getName() {
        return name;
    }

    /**
     * See if the agent list contains the agent
     *
     * @param agent
     * @return true(not founds the agent)/false(founds the agent)
     */
    private boolean validateAgent(Agent agent) {

        return agentListDoNotContain(agent);
    }

    /**
     * See if the storeManager list contains the storeManager
     *
     * @param storeManager
     * @return true(not founds the storeManager)/false(founds the storeManager)
     */

    private boolean validateStoreManager(StoreManager storeManager) {

        return ((storeManager != this.storeManager) || !(checkStoreManagerExists(storeManager)));
    }

    /**
     * constructor for doing clone with id
     *
     * @param id           the id
     * @param name         the name
     * @param address      the address
     * @param phoneNumber  the phone number
     * @param email        the email
     * @param storeManager the store manager
     */
    public Agency(int id, String name, String address, long phoneNumber, String email, StoreManager storeManager) {


        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.storeManager = storeManager;

    }

    /**
     * constructor
     *
     * @param name         the name
     * @param address      the address
     * @param phoneNumber  the phone number
     * @param email        the email
     * @param storeManager the store manager
     */
    public Agency(String name, String address, long phoneNumber, String email, StoreManager storeManager) {

        id = idCount++;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.storeManager = storeManager;

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
     * Gets phone number.
     *
     * @return the phone number
     */
    public long getPhoneNumber() {
        return phoneNumber;
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
     * Gets store manager.
     *
     * @return the store manager
     */
    public StoreManager getStoreManager() {
        return storeManager;
    }

    /**
     * set the store manager
     *
     * @param storeManager
     */
    private void setStoreManager(StoreManager storeManager) {
        this.storeManager = storeManager;
    }

    //misses try catches in the sets

    /**
     * set the email
     *
     * @param email
     */

    private void setEmail(String email) {
        this.email = email;

    }

    /**
     * set the phone number
     *
     * @param phoneNumber
     */
    private void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * set the address
     *
     * @param address
     */
    private void setAddress(String address) {
        this.address = address;
    }

    /**
     * set the name
     *
     * @param name
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * set the id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * agency clone
     *
     * @return
     */

    public Agency clone() {
        Agency clone = new Agency(this.id, this.name, this.address, this.phoneNumber, this.email, this.storeManager);

        for (Agent in : this.agentList) {

            clone.agentList.add(in.clone());
        }

        return clone;
    }


    /**
     * gets a random agent from an Agency
     *
     * @return agente random agent
     */
    public Optional<Agent> getRandomAgent() {
        Random rand = new Random();
        Optional<Agent> result = Optional.empty();
        int index;
        if (agentList.size() > 1) {
            index = rand.nextInt(agentList.size());
            result = Optional.of(agentList.get(index));
        }
        return result;
    }


    /**
     * This methos checks if the agency has an agent with the given name.
     *
     * @param name The name to be checked.
     * @return True if the agency has an employee with the given name.
     */
    public boolean anyAgentHasName(String name) {
        boolean result = false;
        for (Agent agent : agentList) {
            if (agent.hasName(name)) {
                result = true;
            }
        }
        return result;

    }

    /**
     * Any agent has mail.
     *
     * @param email the email
     * @return the optional
     */
    public Optional<Agent> anyAgentHasMail(String email) {
        ;
        Optional<Agent> resultAgent = Optional.empty();
        for (Agent agent : agentList) {
            if (agent.getMail().equals(email)) {
                Optional.of(agent);
            }
        }
        return resultAgent;

    }
}
