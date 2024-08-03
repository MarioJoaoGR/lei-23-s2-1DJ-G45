# US 003 - Register a new employee

# 4. Tests 

**Test 1:** Ensure add Agent works

    @Test
    void testThatAddAgentWorks() {


        Agent agentExpected = new Agent( 111, "testeAdd", 111111111, 11111, "teste", 11111, agency1,"afa@mail.com");
        Optional<Agent> agent = agency1.addAgent(agentExpected);

        assertNotNull(agent1);
        assertTrue(agent.isPresent());
        assertEquals(agentExpected, agent.get());
    }


**Test 2:** Ensure add StoreManager works

    @Test
    void testThatAddStoreManagerWorks() {


       StoreManager StoreManagerExpected = new StoreManager( 111, "testeAdd", 111111111, 11111, "teste", 11111, agency1,"afa@mail.com");
        Optional<StoreManager> storeManager = agency1.addStoreManager(StoreManagerExpected);

        assertNotNull(StoreManager1);
        assertTrue(storeManager.isPresent());
        assertEquals(StoreManagerExpected, storeManager.get());
    }

**Test 3:** Ensure add StoreNetworkManager works

    @Test
    void testThatAddStoreNetworkManagerWorks() {


       StoreNetworkManager StoreNetworkManagerExpected = new StoreNetworkManager( 111, "testeAdd", 111111111, 11111, "teste", 11111, agency1,"afa@mail.com");
        Optional<StoreManager> storeNetworkManager = agency1.addStoreNetworkManager(StoreNetworkManagerExpected);

        assertNotNull(StoreManager1);
        assertTrue(storeNetworkManager.isPresent());
        assertEquals(StoreNetworkManagerExpected, storeNetworkManager.get());
    }

**Test 4:** Ensure add System Administrator works

    @Test
    void testThatAddSystemAdministratorWorks() {


       SystemAdministrator SystemAdministratorExpected = new SystemAdministrator( 111, "testeAdd", 111111111, 11111, "teste", 11111, agency1,"afa@mail.com");
        Optional<SystemAdministrator> systemAdministrator = systemAdministrator1.addSSystemAdministrator(SystemAdministratorExpected);

        assertNotNull(systemAdministrator1);
        assertTrue(systemAdministrator.isPresent());
        assertEquals(SystemAdministratorExpected, systemAdministrator.get());
    }


*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)


## Class RegisterEmployeeController

```java
public class RegisterEmployeeController {

    private AuthenticationRepository authenticationRepository = null;

    private AgencyRepository agencyRepository = null;

    public RegisterEmployeeController() {
        getAuthenticationRepository();
        getAgencyRepository();
    }
    //created for test purposes

    public RegisterEmployeeController(AgencyRepository agencyRepository, AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
        this.agencyRepository = agencyRepository;
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

    public List<Agency> getAgencysFromController() {

        List<Agency> list = agencyRepository.getAgencyList();

        return list;
    }


    public Agent createEmployeeAgent(int id, String name, int phoneNumber, int taxNumber, String password, int civilNumber, String address, Agency agencia, String mail) {
        Agent newAgent = new Agent(id, name, phoneNumber, taxNumber, password, civilNumber, address, agencia, mail);
        return newAgent;
    }

    public StoreManager createEmployeeStoreManager(int id, String name, int phoneNumber, int taxNumber, String password, int civilNumber, String address, String mail) {
        StoreManager newStoreManager = new StoreManager(id, name, phoneNumber, taxNumber, password, civilNumber, address, mail);
        return newStoreManager;
    }

    public StoreNetworkManager createEmployeeStoreNetworkManager(int id, String name, int phoneNumber, int taxNumber, String password, int civilNumber, String address, String mail) {
        StoreNetworkManager newStoreNetworkManager = new StoreNetworkManager(id, name, phoneNumber, taxNumber, password, civilNumber, address, mail);
        return newStoreNetworkManager;
    }

    public SystemAdministrator createEmployeeSystemAdministrator(int id, String name, int phoneNumber, int taxNumber, String password, int civilNumber, String address, String mail) {
        SystemAdministrator newSystemAdministrator = new SystemAdministrator(id, name, phoneNumber, taxNumber, password, civilNumber, address, mail);
        return newSystemAdministrator;
    }


    public boolean addEmployeeToSystem(String name, String mail, String password, String role) {

        return authenticationRepository.addUserWithRole(name, mail, password, role);
    }
    public String generatePassword(){
        return Password.generateRandomPassword();
    }

}
```


## Class Employe

```java
public class Employe {

    private int id;

    private String name;
    private int phoneNumber;
    private int taxNumber;
    private String password;
    private int civilNumber;


    private String address;
    private String mail;
    private final String ADDRESS_BY_OMISSION = "No address";

    public Employe(int id, String name, int phoneNumber, int taxNumber, String password, int civilNumber, String address, String mail) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.taxNumber = taxNumber;
        this.password = password;
        this.civilNumber = civilNumber;
        this.address = address;
        this.mail = mail;
    }

    public Employe(int id, String name, int phoneNumber, int taxNumber, String password, int civilNumber, String mail) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.taxNumber = taxNumber;
        this.password = password;
        this.civilNumber = civilNumber;
        this.address = ADDRESS_BY_OMISSION;
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employe)) return false;
        Employe employe = (Employe) o;
        return id == employe.id && phoneNumber == employe.phoneNumber && taxNumber == employe.taxNumber && civilNumber == employe.civilNumber && name.equals(employe.name) && password.equals(employe.password) && Objects.equals(address, employe.address) && mail.equals(employe.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, taxNumber, password, civilNumber, address, mail);
    }

    public int getId() {
        return id;
    }

    public String getMail() {
        return this.mail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(int taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCivilNumber() {
        return civilNumber;
    }

    public void setCivilNumber(int civilNumber) {
        this.civilNumber = civilNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }
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



# 6. Integration and Demo 

* A new option on the Admnin menu options was added.









