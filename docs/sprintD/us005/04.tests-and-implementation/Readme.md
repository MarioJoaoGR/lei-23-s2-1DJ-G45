# US 005 - Register a store 

# 4. Tests 

**Test 1:** Check that store is registered

	@Test
    public void testRegisterStore() {
        RegisterStoreController controller = new RegisterStoreController();
        AgencyRepository agencyRepository = new AgencyRepository();
        controller.setAgencyRepository(agencyRepository);

        String id = "1234";
        String name = "Test Store";
        String location = "Test Location";
        String phone = "123456789";
        String email = "test@store.com";
        controller.registerStore(id, name, location, phone, email);

        List<Agency> agencyList = agencyRepository.getAgencyList();

        assertEquals(1, agencyList.size());
        assertEquals(id, agencyList.get(0).getId());
        assertEquals(name, agencyList.get(0).getName());
        assertEquals(location, agencyList.get(0).getLocation());
        assertEquals(phone, agencyList.get(0).getPhoneNumber());
        assertEquals(email, agencyList.get(0).getEmail());
    }
	


# 5. Construction (Implementation)

## Class RegisterEmployeeController

```java
public class RegisterStoreController {
    private AgencyRepository agencyRepository;

    public void RegisterStore(String id, String name, String location, String phoneNumber, String email) {
        // create Agency object
        // add Agency object to the agencyRepository
    }
```

## Class Agency
```java
public class Agency {

    private int id;


    private String name;
    private String address;
    private int phoneNumber;
    private String email;

    private StoreManager storeManager;

    private List<Agent> agentList = new ArrayList<>();


    public List<Agent> getAgentsList() {
        return agentList;
    }

    public boolean searchAgent(Agent agente) {
        boolean flag = false;
        for (Agent agent : agentList) {
            if (agentList.contains(agente)) {
                return true;
            }

        }


        return flag;
    }

    public boolean checkStoreManagerExists(StoreManager storeManager) {
        return storeManager.equals(this.storeManager);
    }


    private boolean agentListDoNotContain(Agent agent) {
        return !agentList.contains(agent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agency agency = (Agency) o;
        return id == agency.id && phoneNumber == agency.phoneNumber && name.equals(agency.name) && address.equals(agency.address) && email.equals(agency.email) && storeManager.equals(agency.storeManager) && agentList.equals(agency.agentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phoneNumber, email, storeManager, agentList);
    }

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

    public Optional<Agent> removeAgent(Agent agent) {
        Optional<Agent> removedAgent = Optional.empty();
        if (agentList.contains(agent)) {
            removedAgent = Optional.of(agent);
            agentList.remove(agent);
        }
        return removedAgent;
    }


    //need to implement a method to remove an agency for an agency add for other//


    public String getName() {
        return name;
    }


    private boolean validateAgent(Agent agent) {

        return agentListDoNotContain(agent);
    }

    private boolean validateStoreManager(StoreManager storeManager) {

        return ((storeManager != this.storeManager) || !(checkStoreManagerExists(storeManager)));
    }

    //constructor
    public Agency(int id, String name, String address, int phoneNumber, String email, StoreManager storeManager) {

        //do this to verify with try catch the parameters//
        setId(id);
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setStoreManager(storeManager);

    }

    private void setStoreManager(StoreManager storeManager) {
        this.storeManager = storeManager;
    }

    //misses try catches in the sets
    private void setEmail(String email) {
        this.email = email;

    }

    private void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setId(int id) {
        this.id = id;
    }


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
     * @return agente
     */

    public Agent getRandomAgent() {
        Random rand = new Random();
        int index = 0;
        if (agentList.size() > 1) {
            index = rand.nextInt(agentList.size());
        }
        return agentList.get(index);
    }





    /**
     * This methos checks if the agency has an agent with the given email.
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
}
```


## Class AgencyRepository
```java
public class AgencyRepository {


    private List<Agency> agencyList = new ArrayList<>();


    public List<Agency> getAgencyList() {
        return agencyList;
    }


/*
    public boolean checkIfAgencyExists(Agency agency) {
        return agencyList.contains(agency);
    }
*/


    public Optional<Agency> add(Agency agencia) {

        Optional<Agency> newAgency = Optional.empty();

        boolean operationSuccess = false;

        if (validateAgency(agencia)) {
            newAgency = Optional.of(agencia.clone());
            operationSuccess = agencyList.add(newAgency.get());
        }

        if (!operationSuccess) {
            newAgency = Optional.empty();
        }

        return newAgency;

    }


    /**
     * gets a random Agency from agency list
     *
     * @return Agencia object
     */
    public Agency getRandomAgency() {
        Random rand = new Random();
        int index = 0;
        if (agencyList.size() > 1) {
            index = rand.nextInt(agencyList.size());
        }
        return agencyList.get(index);
    }


    public List<Agency> getAgencyListCopy() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(agencyList);
    }


    private boolean validateAgency(Agency agencia) {
        boolean isValid = !agencyList.contains(agencia);
        return isValid;
    }


    public Optional<Agency> getAgencyByAgencyName(String test) {
        Optional<Agency> result = Optional.empty();
        for (Agency agency : agencyList) {
            if (agency.getName().equals(test))
                result= Optional.of(agency);
        }
        return result;
    }
}
```






