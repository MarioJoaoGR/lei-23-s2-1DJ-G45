# US 004 - Submit a request for listing a property sale or rent

# 4. Tests

**Test 1:** Check that add Agency Works

    @Test
    void ensureAddAgencyWorks(){

        AgencyRepository agencyRepository = new AgencyRepository();
        StoreManager storeManager = new StoreManager( 1,"aaa", 31, 414, "storepwd", 111, "aaaa","asdas@aa");
        Agency agency = new Agency( 1,"test", "testAddress", 123456789, "teste@mail", storeManager);

        agencyRepository.add(agency);

        assertTrue(agencyRepository.getAgencyList().contains(agency));

    }

**Test 2:** Ensure add two equal agencies fails

	 @Test
    void ensureAddAgencyDuplicateFails() {
        AgencyRepository agencyRepository = new AgencyRepository();
        StoreManager storeManager = new StoreManager( 5,"aaa", 31, 414, "storepwd", 111, "aaaa","asdas@aa");
        Agency agency = new Agency( 6,"test", "testAddress", 123456789, "teste@mail", storeManager);
        agencyRepository.add(agency);

        Optional<Agency> result = agencyRepository.add(agency);

        assertTrue(result.isEmpty());

    }

**Test 3:** Ensure add request works

    @Test
    void ensureAddRequestWorks() {
    RequestRepository requestRepository = new RequestRepository();
    StoreManager storeManager = new StoreManager(321, "aaa", 31, 414, "storepwd", 111, "aaaa","asdas@aa");  
    Agency agency = new Agency(11, "test", "testAddress", 123456789, "teste@mail", storeManager);
    Agent agent = new Agent( 222, "aaa", 123444444, 111111111, "agentpwd", 111, agency,"afaf@");
    Owner owner = new Owner("aaa", 222, 444, "aaa", "sda", 222);
    Request request = new Request("sell", agent, 13123.3, "aaaa", 431241, 13312, "rrr", owner);
    requestRepository.add(request);
        Optional<Request> returnRequest = requestRepository.getRequestByOwnerName("aaa");

        //Assert
        //Make sure both represents the same object
        assertEquals(request, returnRequest.get());
        //Make sure it is a clone (different memory addresses)
        assertNotSame(request, returnRequest.get());
    }

**Test 4:** Ensure can`t add two equal requests

    @Test
    void ensureCantAddDuplicate() {

        RequestRepository requestRepository = new RequestRepository();
        StoreManager storeManager = new StoreManager(321, "aaa", 31, 414, "storepwd", 111, "aaaa","aasfa");
        Agency agency = new Agency(11, "test", "testAddress", 123456789, "teste@mail", storeManager);
        Agent agent = new Agent( 222, "aaa", 123444444, 111111111, "agentpwd", 111, agency,"aga");
        Owner owner = new Owner("aaa", 222, 444, "aaa", "sda", 222);
        Request request = new Request("sell", agent, 13123.3, "aaaa", 431241, 13312, "rrr", owner);
        requestRepository.add(request);
        Optional<Request> result = requestRepository.add(request);
        assertTrue(result.isEmpty());

    }

    }

**Test 5:** Ensure get requests for agent is working

    @Test
    void ensureGetRequestsForAgentIsWorking(){
        RequestRepository requestRepository = new RequestRepository();
        StoreManager storeManager = new StoreManager(321, "aaa", 31, 414, "storepwd", 111, "aaaa","fafa@");
        Agency agency = new Agency(11, "test", "testAddress", 123456789, "teste@mail", storeManager);
        Agent agent = new Agent( 222, "aaa", 123444444, 111111111, "agentpwd", 111, agency,"dsadas@");
        Agent agent2 = new Agent(21122, "adaa", 123444444, 111111111, "agentpwd", 111, agency,"dsadas@");
        Owner owner = new Owner("aaa", 222, 444, "aaa", "sda", 222);
        Agent agent3 = new Agent(222, "aaBBa", 123444444, 111111111, "agentpwd", 111, agency,"dsadas@");
        Request request = new Request("sell", agent, 13123.3, "aaaa", 431241, 13312, "rrr", owner);
        requestRepository.add(request);
        Request request3 = new Request("sell", agent3, 13123.3, "aaaa", 431241, 13334112, "rrr", owner);
        Request request2 = new Request("rent", agent, 13123.3, "aaaa", 431241, 13343212, "rrr", owner);
        requestRepository.add(request2);
        requestRepository.add(request3);
        // Test the method for an agent with multiple requests
        Optional<List<Request>> matchingRequests1 = requestRepository.getRequestsForAgent(agent);
        assertTrue(matchingRequests1.isPresent());
        assertTrue(requestRepository.checkIfRequestExists(request2));
        List<Request> requestsForAgent1 = matchingRequests1.get();
        assertEquals(2, requestsForAgent1.size());
        assertTrue(requestsForAgent1.contains(request));
        assertTrue(requestsForAgent1.contains(request2));

        // Test the method for an agent with no requests
        Optional<List<Request>> matchingRequests2 = requestRepository.getRequestsForAgent(agent2);
        assertFalse(matchingRequests2.isPresent());

        // Test the method for an agent with one request
        Optional<List<Request>> matchingRequests3 = requestRepository.getRequestsForAgent(agent3);
        assertTrue(matchingRequests3.isPresent());
        List<Request> requestsForAgent3 = matchingRequests3.get();
        assertEquals(1, requestsForAgent3.size());
        assertTrue(requestsForAgent3.contains(request3));
    }




**Test 6:** Ensure different objects are different

    @Test
        public void testNotEquals() {
            Owner owner1 = new Owner("John", 11121, 423432,"aaa","dada",4141414);
            Owner owner2 = new Owner("Josshn", 1111, 423432,"aaa","dada",4141414);
            Agency agency1 = new Agency(1, "aaab", "bbab", 123456155, "acaca", storeManager1);
            Agent agent1 = new Agent( 3, "testeAdd", 111111111, 11111, "teste", 11111, agency1,"afa@mail.com");
            Agency agency2 = new Agency( 4,"aaab", "bbab", 123456155, "acaca", storeManager1);
            Agent agent2 = new Agent(  5,"testeAdd", 111111111, 11111, "teste", 11111, agency1,"afa@mail.com");

            Request request1 = new Request("buy", agent1,100.0, "location", 5.0, 100000.0, "photographs", owner1);
            Request request2 = new Request("rent", agent1, 100.0, "location", 5.0, 100000.0, "photographs", owner1);
            Request request3 = new Request("buy", agent2,100.0, "location", 5.0, 100000.0, "photographs", owner1);
            Request request4 = new Request("buy", agent1, 100.0, "location", 5.0, 100000.0, "photographs", owner1);
            Request request5 = new Request("buy", agent1,200.0, "location", 5.0, 100000.0, "photographs", owner1);
            Request request6 = new Request("buy", agent1,100.0, "location2", 5.0, 100000.0, "photographs", owner1);
            Request request7 = new Request("buy", agent1,100.0, "location", 10.0, 100000.0, "photographs", owner1);
            Request request8 = new Request("buy", agent1,100.0, "location", 5.0, 200000.0, "photographs", owner1);
            Request request9 = new Request("buy", agent1,100.0, "location", 5.0, 100000.0, "photographs2", owner1);
            Request request10 = new Request("buy", agent2, 100.0, "location", 5.0, 100000.0, "photographs", owner2);

            assertNotEquals(request1, request2);
            assertNotEquals(request1, request3);
            assertEquals(request1, request4);
            assertNotEquals(request1, request5);
            assertNotEquals(request1, request6);
            assertNotEquals(request1, request7);
            assertNotEquals(request1,request8);
            assertNotEquals(request1,request9);
            assertNotEquals(request1,request10);


**Test 7:** Ensure add Agent works
    
    @Test
    void testThatAddAgentWorks() {


        Agent agentExpected = new Agent( 111, "testeAdd", 111111111, 11111, "teste", 11111, agency1,"afa@mail.com");
        Optional<Agent> agent = agency1.addAgent(agentExpected);

        assertNotNull(agent1);
        assertTrue(agent.isPresent());
        assertEquals(agentExpected, agent.get());
    }


**Test 8:** Ensure add two equal agents fails

    @Test
    void ensureAddingDuplicateAgentFails() {

        Agent agent1 = new Agent( 111, "testeAdd", 111111111, 11111, "teste", 11111, agency1,"afa@mail.com");
        Optional<Agent> agent = agency1.addAgent(agent1);

        Optional<Agent> agent2 =agency1.addAgent(agent1);


        //Assert
        assertTrue(agent2.isEmpty());
    }

**Test 9:** Ensure the remove of agent
    
    @Test
    void verifyRemoveOfAgent() {
        Agent agent1 = new Agent( 111, "testeAdd", 111111111, 11111, "teste", 11111, agency1,"afa@mail.com");


        // Add the test Agent to the list
        agency1.addAgent(agent1);

        // Call the removeAgent() method
        Optional<Agent> removedAgent = agency1.removeAgent(agent1);

        // Check that the removed Agent is the same as the test Agent
        assertTrue(removedAgent.isPresent());
        assertEquals(agent1, removedAgent.get());

        // Check that the Agent list is now empty
        assertTrue(agency1.getAgentsList().isEmpty());

    }

**Test 10:** Ensure create request works


     @Test
    void ensureCreateRequestWorks() {

        //Arrange
        StoreManager storeManager1 = new StoreManager(111, "saa", 423, 222, "pwd", 13123, "Aaa", "gsdgsdg@mail");
        Owner owner1 = new Owner("vsvs", 141, 11, "vsdvs", "ffsfs", 1232112421);
        Agency agency1 = new Agency(111, "aaa", "bbb", 123456755, "acaca", storeManager1);
        Agency agency2 = new Agency(1121, "aaab", "bbab", 123456155, "acaca", storeManager1);
        Agent agent1 = new Agent(111, "testeAdd", 111111111, 11111, "teste", 11111, agency1, "afa@mail.com");
        Request request = new Request("sell", agent1, 103123.12, "aaa", 31231.24, 121.14, "aaaphoto", owner1);
        Request request2 = new Request("rent", agent1, 10312312123.12, "aaaaaf", 3124521.24, 121.14, "aaaphoto", owner1);
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        RequestRepository requestRepository = new RequestRepository();
        AgencyRepository agencyRepository = new AgencyRepository();


        //Fill Agency Repository
        agencyRepository.add(agency1);
        agencyRepository.add(agency2);


        CreateRequestController controller = new CreateRequestController(agencyRepository, requestRepository);

        //Act
        Request newRequest = controller.createAndSaveRequestForLand("sell", agent1, 312321.312, "aaaa", 3131.312, 41341.31, "aaaa", owner1);
        Request newRequest2 = controller.createAndSaveRequestForApartment("rent",agent1,41241.42,"aa",40241.42,424142.4,"fafa",3213,3123,4,"aaa",owner1);
        assertTrue(requestRepository.checkIfRequestExists(newRequest2));
        assertTrue(requestRepository.checkIfRequestExists(newRequest));
    }


*It is also recommended to organize this content by subsections.*

# 5. Construction (Implementation)

## Class Request


    public class Request {

    private int requestId;
    private double area;
    private String location;
    private double distanceFromCityCenter;
    private double requestedPrice;
    private String photographs;
    private Agent agente;

    private Owner owner;
    private String typeOfCommercialize;

    private static int requestID = 0;


    public Request(String typeOfCommercialize, Agent agente, double area, String location, double distanceFromCityCentre, double requestedPrice, String photographs, Owner owner) {
        this.typeOfCommercialize = typeOfCommercialize;
        this.agente = agente;
        this.area = area;
        this.location = location;
        this.distanceFromCityCenter = distanceFromCityCentre;
        this.requestedPrice = requestedPrice;
        this.photographs = photographs;
        this.owner = owner;
        requestId++;
    }



    public static int getRequestID() {
        return requestID;
    }

    public double getDistanceFromCityCenter() {
        return distanceFromCityCenter;
    }

    public double getRequestedPrice() {
        return requestedPrice;
    }

    public String getPhotographs() {
        return photographs;
    }

    public String getTypeOfCommercialize() {
        return typeOfCommercialize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return requestId == request.getRequestId() && Double.compare(request.area, area) == 0 && Double.compare(request.distanceFromCityCenter, distanceFromCityCenter) == 0 && Double.compare(request.requestedPrice, requestedPrice) == 0 && location.equals(request.location) && photographs.equals(request.photographs) && agente == request.agente && owner == request.owner && typeOfCommercialize.equals(request.typeOfCommercialize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequestId(),area, location, distanceFromCityCenter, requestedPrice, photographs, agente, owner, typeOfCommercialize);
    }

    public Request clone() {
        return new Request(this.typeOfCommercialize, this.agente, this.area, this.location, this.distanceFromCityCenter, this.requestedPrice, this.photographs, this.owner);
    }

    public int getRequestId() {
        return requestId;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getLocation() {
        return location;
    }

    public Double getArea() {
        return area;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Agent getAgente() {
        return this.agente;
    }

    public void setRequestedPrice(double requestedPrice) {
        this.requestedPrice = requestedPrice;
    }
    }
        }


## Class Agency


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



## Class AgencyRepository

    public class AgencyRepository {


    private List<Agency> agencyList = new ArrayList<>();


    public List<Agency> getAgencyList() {
        return agencyList;
    }

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


## Class RequestRepository

    public class RequestRepository {

    private final List<Request> requestsList = new ArrayList<>();


    public List<Request> getRequestsList() {
        return requestsList;
    }

    public Optional<Request> add(Request requestToAdd) {
        Optional<Request> newRequests = Optional.empty();

        boolean operationSuccess = false;

        if (validateRequest(requestToAdd)) {

            newRequests = Optional.of(requestToAdd.clone());
            operationSuccess = requestsList.add(newRequests.get());
        }
        if (!operationSuccess) {
            newRequests = Optional.empty();
        }
        return newRequests;
    }

    private boolean validateRequest(Request requestToAdd) {
        boolean isValid = !requestsList.contains(requestToAdd);
        return isValid;
    }

    public boolean checkIfRequestExists(Request requestAdded) {

        return requestsList.contains(requestAdded);

    }

    public List<Request> getRequests() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(requestsList);
    }

    public Optional<Request> getRequestByOwnerName(String name) {
        Optional<Request> returnRequest = Optional.empty();
        for (Request request : requestsList) {
            if (request.getOwner().getName().equals(name)) {
                returnRequest = Optional.of(request);
            }
        }

        return returnRequest;
    }

    public Optional<List<Request>> getRequestsForAgent(Agent agent) {
        List<Request> matchingRequests = new ArrayList<>();
        for (Request request : requestsList) {
            if (request.getAgente().equals(agent)) {
                matchingRequests.add(request);
            }
        }

        if (matchingRequests.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(matchingRequests);
        }

    }

    }

## Class RequestHouse

    public class RequestHouse extends Request{

    private int numberBedrooms;
    private int numberBathrooms;
    private int numberParkingSpaces;
    private String availableEquipment;
    private boolean basement;
    private boolean inhabitableLoft;

    private boolean sunExposure;
    private String LAND_TYPE = "house";
    public RequestHouse(String typeOfCommercialize, Agent agente, double area, String location, Double distanceFromCityCentre, Double requestedPrice, String photographs, int numberBedrooms, int numberBathrooms, int numberParkingSpaces, String availableEquipment, boolean basement, boolean inhabitableLoft, boolean sunExposure, Owner owner) {
        super(typeOfCommercialize, agente, area, location, distanceFromCityCentre, requestedPrice, photographs, owner);
        this.numberBathrooms = numberBathrooms;
        this.numberBedrooms = numberBedrooms;
        this.availableEquipment = availableEquipment;
        this.numberParkingSpaces=numberParkingSpaces;
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
        this.sunExposure = sunExposure;
    }

    public String getLandType() {
        return LAND_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestHouse)) return false;
        if (!super.equals(o)) return false;
        RequestHouse that = (RequestHouse) o;
        return numberBedrooms == that.numberBedrooms && numberBathrooms == that.numberBathrooms && numberParkingSpaces == that.numberParkingSpaces && basement == that.basement && inhabitableLoft == that.inhabitableLoft && sunExposure == that.sunExposure && availableEquipment.equals(that.availableEquipment) && LAND_TYPE.equals(that.LAND_TYPE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberBedrooms, numberBathrooms, numberParkingSpaces, availableEquipment, basement, inhabitableLoft, sunExposure, LAND_TYPE);
    }

    public int getNumberBedrooms() {
        return numberBedrooms;
    }

    public void setNumberBedrooms(int numberBedrooms) {
        this.numberBedrooms = numberBedrooms;
    }

    public int getNumberBathrooms() {
        return numberBathrooms;
    }

    public void setNumberBathrooms(int numberBathrooms) {
        this.numberBathrooms = numberBathrooms;
    }

    public int getNumberParkingSpaces() {
        return numberParkingSpaces;
    }

    public void setNumberParkingSpaces(int numberParkingSpaces) {
        this.numberParkingSpaces = numberParkingSpaces;
    }

    public String getAvailableEquipment() {
        return availableEquipment;
    }

    public void setAvailableEquipment(String availableEquipment) {
        this.availableEquipment = availableEquipment;
    }

    public boolean getBasement() {
        return basement;
    }

    public void setBasement(boolean basement) {
        this.basement = basement;
    }

    public boolean getInhabitableLoft() {
        return inhabitableLoft;
    }

    public void setInhabitableLoft(boolean inhabitableLoft) {
        this.inhabitableLoft = inhabitableLoft;
    }

    public boolean getSunExposure() {
        return sunExposure;
    }

    public void setSunExposure(boolean sunExposure) {
        this.sunExposure = sunExposure;
    }

    public String getLAND_TYPE() {
        return LAND_TYPE;
    }

    public void setLAND_TYPE(String LAND_TYPE) {
        this.LAND_TYPE = LAND_TYPE;
    }
    }


## Class RequestApartment

    public class RequestApartment extends Request {

    private int numberBedrooms;
    private int numberBathrooms;
    private int numberParkingSpaces;
    private String availableEquipment;
    private final String LAND_TYPE ="apartment";

    public int getNumberBedrooms() {
        return numberBedrooms;
    }

    public int getNumberBathrooms() {
        return numberBathrooms;
    }

    public int getNumberParkingSpaces() {
        return numberParkingSpaces;
    }

    public String getAvailableEquipment() {
        return availableEquipment;
    }

    public String getLAND_TYPE() {
        return LAND_TYPE;
    }

    public RequestApartment(String typeOfCommercialize, Agent agente, double area, String location, double distanceFromCityCenter, Double requestedPrice, String photographs, int numberBedrooms, int numberBathrooms, int numberParkingSpaces, String availableEquipment, Owner owner) {
        super(typeOfCommercialize, agente, area, location, distanceFromCityCenter, requestedPrice, photographs, owner);
        this.numberBedrooms = numberBedrooms;
        this.numberBathrooms = numberBathrooms;
        this.numberParkingSpaces = numberParkingSpaces;
        this.availableEquipment = availableEquipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestApartment that = (RequestApartment) o;
        return numberBedrooms == that.numberBedrooms && numberBathrooms == that.numberBathrooms && numberParkingSpaces == that.numberParkingSpaces && availableEquipment.equals(that.availableEquipment);
    }

    public String getLandType() {
        return LAND_TYPE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),numberBedrooms, numberBathrooms, numberParkingSpaces, availableEquipment);
    }





## Class RequestLand


    public class RequestLand extends Request{

    String LAND_TYPE = "land";
    public RequestLand(String typeOfCommercialize, Agent agente, double area, String location, Double distanceFromCityCentre, Double requestedPrice, String photographs, Owner owner) {
        super(typeOfCommercialize, agente, area, location, distanceFromCityCentre, requestedPrice, photographs, owner);
    }

    public String getLAND_TYPE() {
        return LAND_TYPE;
    }

    public void setLAND_TYPE(String LAND_TYPE) {
        this.LAND_TYPE = LAND_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestLand)) return false;
        if (!super.equals(o)) return false;
        RequestLand that = (RequestLand) o;
        return LAND_TYPE.equals(that.LAND_TYPE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), LAND_TYPE);
    }

    public String getLandType() {
        return LAND_TYPE;
    }
    }



## Create RequestUI
    public class CreateRequestUI implements Runnable {


    private final CreateRequestController controller = new CreateRequestController();
    private double area;
    private String location;
    private double distanceFromCityCenter;
    private double requestedPrice;
    private String photographs;
    private String requestType;
    private int numberBedrooms;
    private int numberBathrooms;
    private int numberParkingSpaces;
    private String availableEquipment;
    private boolean basement;
    private boolean inhabitableLoft;
    private boolean sunExposure;

    //owner attributes
    private String ownerName;
    private int ownerCardNumber;
    private int ownerPhoneNumber;
    private int ownerTaxNumber;
    private String ownerAddress;
    private String ownerEmail;
    //can be rent or sell//

    private String typeOfComercial;


    //type of choice, choose agent or not
    private boolean choiceOrNoAgent;

    private CreateRequestController getController() {
        return controller;
    }

    public void run() {
        System.out.println("Create Request");


        requestData();


        Agency agency;
        //----------------
        Agent agent;
        if (choiceOrNoAgent) {
            agency = displayAndSelectAgency();
            agent = displayAndSelectAgent(agency);
            //  submitData(agentChosed, requestType);
        } else {
            agency = controller.getSortedAgency();
            agent = controller.getSortedAgent(agency);
            //  submitData(agent, requestType);
        }
        submitData(agent, requestType);

    }

    private void submitData(Agent agente, String requestType) {


        Owner newOwner = getController().createOwner(ownerName, ownerCardNumber, ownerTaxNumber, ownerAddress, ownerEmail, ownerPhoneNumber);

        Request newRequest = null;
        switch (requestType) {
            case "land":
                newRequest = getController().createAndSaveRequestForLand(typeOfComercial, agente, area, location, distanceFromCityCenter, requestedPrice, photographs, newOwner);
                break;
            case "house":
                newRequest = getController().createAndSaveRequestForHouse(typeOfComercial, agente, area, location, distanceFromCityCenter, requestedPrice, photographs, numberBedrooms, numberBathrooms, numberParkingSpaces, availableEquipment, basement, inhabitableLoft, sunExposure, newOwner);
                break;
            case "apartment":
                newRequest = getController().createAndSaveRequestForApartment(typeOfComercial, agente, area, location, distanceFromCityCenter, requestedPrice, photographs, numberBedrooms, numberBathrooms, numberParkingSpaces, availableEquipment, newOwner);
                break;
        }

        if (getController().checkIfRequestIsCreated(newRequest)) {
            System.out.println("Request successfully created and saved!");

        } else {
            System.out.println("Request not created!");
        }

    }

    private void requestData() {
        requestOwnerData();
        typeOfComercial = requestTypeOfComercial();
        requestType = requestLandType();
        switch (requestType) {
            case "land":
                area = requestProperyArea();
                location = requestLocation();
                distanceFromCityCenter = requestDistanceFromCityCenter();
                requestedPrice = requestPropertyPrice();
                photographs = requestStringPhotographs();
                break;

            case "house":
                area = requestProperyArea();
                location = requestLocation();
                distanceFromCityCenter = requestDistanceFromCityCenter();
                requestedPrice = requestPropertyPrice();
                photographs = requestStringPhotographs();
                numberBedrooms = requestNumberBedrooms();
                numberBathrooms = requestBathrooms();
                numberParkingSpaces = requestParkingSpaces();
                basement = requestBasement();
                inhabitableLoft = requestInhabitableLoft();
                sunExposure = requestSunExposure();
                availableEquipment = requestAvailableEquipment();
                break;
            case "apartment":
                area = requestProperyArea();
                location = requestLocation();
                distanceFromCityCenter = requestDistanceFromCityCenter();
                requestedPrice = requestPropertyPrice();
                photographs = requestStringPhotographs();
                numberBedrooms = requestNumberBedrooms();
                numberBathrooms = requestBathrooms();
                numberParkingSpaces = requestParkingSpaces();
                availableEquipment = requestAvailableEquipment();
                break;
        }
        choiceOrNoAgent = requestsChooseOrNoAgent();
    }

    private void requestOwnerData() {
        ownerName = requestOwnerName();
        ownerCardNumber = requestOwnerCardNumber();
        ownerPhoneNumber = requestOwnerPhoneNumber();
        ownerTaxNumber = requestOwnerTaxNumber();
        ownerEmail = requestOwnerEmail();
        ownerAddress = requestOwnerAddress();
    }

    private int requestOwnerCardNumber() {
        System.out.println("Type your citizen`s card number");
        return verificationMethodInt();
    }

    public double verificationMethodDouble() {
        Scanner input = new Scanner(System.in);
        double num = 0;
        String option;
        boolean validInput = false;
        while (!validInput) {
            option = input.nextLine();

            try {
                num = Double.parseDouble(option);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }

        return num;


    }

    public static int verificationMethodInt() {
        Scanner input = new Scanner(System.in);
        int num = 0;
        String option;
        boolean validInput = false;
        while (!validInput) {
            option = input.nextLine();

            try {
                num = Integer.parseInt(option);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }

        return num;
    }

    private String requestOwnerName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Type your name");
        do {
            ownerName = input.nextLine().trim();
        } while (ownerName.isEmpty());
        return ownerName;

    }

    public static boolean isPhoneNumber(String input) {
        // Remove all non-digit characters from the input string
        String digitsOnly = input.replaceAll("\\D", "");

        // Check if the resulting string has exactly 10 digits
        return digitsOnly.length() == 10 && digitsOnly.matches("\\d{10}");
    }


    public int requestOwnerPhoneNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a phone number:");
        String phoneNumber;
        int result = 0;
        boolean flag = false;
        do {
            phoneNumber = input.nextLine();
            if (isPhoneNumber(phoneNumber)) {
                flag = true;
                result = parseInt(phoneNumber);
            } else {
                System.out.println("The phone number is invalid.");
            }
        } while (!flag);

        return result;
    }

    public static boolean isValidTaxNumber(int taxNumber) {
        // US tax number should be a positive 9-digit integer
        if (taxNumber < 0 || taxNumber > 999999999) {
            return false;
        }

        int digits = 0;
        int temp = taxNumber;
        while (temp > 0) {
            digits++;
            temp /= 10;
        }

        return digits == 9;
    }


    private int requestOwnerTaxNumber() {
        System.out.println("Type your tax number");
        boolean flag;
        int number = 0;
        do {
            flag = false;
            number = verificationMethodInt();
            if (isValidTaxNumber(number)) {
                flag = true;
            } else {
                System.out.println("Invalid Tax Number");
            }

        } while (!flag);


        return number;
    }


    //TODO:see mail verification

    public static boolean isValidEmail(String email) {
        int atSymbolIndex = email.indexOf('@');
        boolean hasAtSymbol = atSymbolIndex >= 0;
        boolean hasValidDomain = email.endsWith(".com") || email.endsWith(".org") || email.endsWith(".net");

        return hasAtSymbol && hasValidDomain;
    }

    private String requestOwnerEmail() {
        Scanner input = new Scanner(System.in);
        System.out.println("Type your email");
        boolean flag = false;
        String mail;
        do {
            mail = input.nextLine();
            if (isValidEmail(mail)) {
                flag = true;
            } else {
                System.out.println("Invalid mail");
            }
        } while (!flag);

        return mail;
    }

    private String requestOwnerAddress() {
        Scanner input = new Scanner(System.in);
        System.out.println("Type your address");
        do {
            ownerAddress = input.nextLine().trim();
        } while (ownerAddress.isEmpty());
        return ownerAddress;

    }

    private boolean requestsChooseOrNoAgent() {
        Scanner input = new Scanner(System.in);
        int choice;
        boolean flag;
        System.out.println("Do you want to choose the Agent for you Request?");
        System.out.println("1-Yes");
        System.out.println("2-No");
        do {
            choice = input.nextInt();
        } while (choice > 2 || choice <= 0);
        return booleanMethodsReturn(choice);

    }

    private double requestProperyArea() {

        System.out.println("Property area:");
        return verificationMethodDouble();
    }

    private String requestTypeOfComercial() {

        Scanner input = new Scanner(System.in);
        int choice;
        String typeOfComercial;
        System.out.println("Do you want to rent or sell the property?");
        System.out.println("1-Sell");
        System.out.println("2-Rent");
        do {
            choice = input.nextInt();
        } while (choice > 2 || choice <= 0);
        switch (choice) {
            case 1:
                typeOfComercial = "sell";
                break;
            default:
                typeOfComercial = "rent";
                break;
        }
        return typeOfComercial;


    }

    private String requestLocation() {
        Scanner input = new Scanner(System.in);
        System.out.println("Location:");
        String result;
        do {
            result = input.nextLine().trim();
        } while (result.isEmpty());
        return result;
    }

    private double requestDistanceFromCityCenter() {

        System.out.println("Distance from city centre:");
        return verificationMethodDouble();
    }
    private String requestStringPhotographs() {
        Scanner input = new Scanner(System.in);
        System.out.println("Photo URL/URL`s:");
        String result;
        do {
            result = input.nextLine().trim();
        } while (result.isEmpty());
        return result;
    }
    private double requestPropertyPrice() {

        System.out.println("Price:");
        return verificationMethodDouble();
    }
    private String requestLandType() {
        Scanner input = new Scanner(System.in);
        int choice;
        String requestType;
        System.out.println("What type is your property");
        System.out.println("1-House");
        System.out.println("2-Land");
        System.out.println("3-Apartment");
        do {
            choice = input.nextInt();
        } while (choice > 3 || choice <= 0);
        switch (choice) {
            case 1:
                requestType = "house";
                break;
            case 2:
                requestType = "land";
                break;
            case 3:
                requestType = "apartment";
                break;
            default:
                requestType = "Error";
        }
        return requestType;
    }
    private int requestNumberBedrooms() {
        System.out.println("Number of bedrooms:");
        return verificationMethodInt();
    }
    private int requestBathrooms() {
        System.out.println("Number of bathrooms");
        return verificationMethodInt();
    }
    private int requestParkingSpaces() {

        System.out.println("Number of parking spaces");
        return verificationMethodInt();
    }
    private String requestAvailableEquipment() {
        Scanner input = new Scanner(System.in);
        System.out.println("Available equipment:");
        String result;
        do {
            result = input.nextLine().trim();
        } while (result.isEmpty());
        return result;
    }
    private boolean requestBasement() {
        Scanner input = new Scanner(System.in);
        System.out.println("Basement(Yes/No):");
        System.out.println("1-Yes");
        System.out.println("2-No");
        int option;
        do {
            option = input.nextInt();
        } while (option <= 0 || option > 2);
        return booleanMethodsReturn(option);
    }
    private boolean requestInhabitableLoft() {
        int option;
        Scanner input = new Scanner(System.in);
        System.out.println("Inhabitable loft(Yes/No):");
        System.out.println("1-Yes");
        System.out.println("2-No");
        do {
            option = input.nextInt();
        } while (option > 2 || option <= 0);
        return booleanMethodsReturn(option);
    }
    private boolean booleanMethodsReturn(int option) {
        boolean returnBoolean;
        switch (option) {
            case 1:
                returnBoolean = true;
                break;
            default:
                returnBoolean = false;
                break;

        }
        return returnBoolean;
    }
    private boolean requestSunExposure() {

        Scanner input = new Scanner(System.in);
        int option;
        System.out.println("Sun Exposure");
        System.out.println("1-Yes");
        System.out.println("2-No");
        do {
            option = input.nextInt();
        } while (option <= 0 || option > 2);
        return booleanMethodsReturn(option);
    }
    private Agency displayAndSelectAgency() {
        //Display the list of agencies
        List<Agency> agencias = controller.getAgencysFromController();

        int listSize = agencias.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            System.out.println("Select an agency:");
            displayAgencyOptions(agencias);
            answer = input.nextInt();

            if (answer < 1 || answer > listSize) {
                System.out.println("Choose a valid Agency Number");
            }
        }

        return agencias.get(answer - 1);

    }
    private void displayAgentOptions(Agency agency) {
        //display the Agent options as a menu with number options to select
        List<Agent> agentesList = controller.getAgentsFromController(agency);
        int i = 1;
        for (Agent a : agentesList) {
            System.out.println(i + " - " + a.getName());
            i++;
        }

    }
    public static void displayAgencyOptions(List<Agency> agents) {
        //display the Agency options as a menu with number options to select
        int i = 1;
        for (Agency a : agents) {
            System.out.println(i + " - " + a.getName());
            i++;
        }
    }
    private Agent displayAndSelectAgent(Agency agencia) {

        List<Agent> agentes = controller.getAgentsFromController(agencia);

        int listSize = agentes.size();
        int answer = -1;
        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > 200) {

            displayAgentOptions(agencia);
            answer = input.nextInt();
        }

        return agentes.get(answer - 1);
    }


    }













## Class CreateRequestController

    public class CreateRequestController {

    private AgencyRepository agencyRepository = null;
    private RequestRepository requestRepository = null;

    public CreateRequestController() {
        getAgencyRepository();
        getRequestRepository();
    }


    //created for test purposes
    public CreateRequestController(AgencyRepository agencyRepository, RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
        this.agencyRepository = agencyRepository;
    }

    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    private RequestRepository getRequestRepository() {
        if (requestRepository == null) {
            Repositories repositories = Repositories.getInstance();

            requestRepository = repositories.getRequestRepository();
        }
        return requestRepository;
    }


    public Agent getSortedAgent(Agency agencyChosed) {

        return agencyChosed.getRandomAgent();
    }

    public Agency getSortedAgency() {

        return agencyRepository.getRandomAgency();

    }


    /*public Request createAndSaveRequest(String typeOfComercial,Agent agente, String requestType, String area, String location, Double distanceFromCityCentre, Double requestedPrice,
                                 String photographs, int numberBedrooms, int numberBathrooms, int numberParkingSpaces, String availableEquipment,
                                 String basement, String inhabitableLoft, String sunExposure) {
        Request newRequest = new Request(typeOfComercial,agente, requestType, area, location, distanceFromCityCentre, requestedPrice, photographs, numberBedrooms, numberBathrooms, numberParkingSpaces, availableEquipment, basement, inhabitableLoft, sunExposure);
        saveRequestInRepository(newRequest);
        return newRequest;

    }*/


    public boolean checkIfRequestIsCreated(Request request) {
        boolean result = requestRepository.checkIfRequestExists(request);
        return result;
    }


    public List<Agency> getAgencysFromController() {

        List<Agency> list = agencyRepository.getAgencyList();

        return list;
    }


    public List<Agent> getAgentsFromController(Agency agencyChosed) {

        return agencyChosed.getAgentsList();


    }

    public Request createAndSaveRequestForLand(String typeOfCommercial, Agent agente, Double area, String location, Double distanceFromCityCentre, Double requestedPrice, String photographs, Owner owner) {
        RequestLand newRequest = new RequestLand(typeOfCommercial, agente, area, location, distanceFromCityCentre, requestedPrice, photographs, owner);
        Optional<Request> addedRequest = requestRepository.add(newRequest);


            if (addedRequest.isPresent()) {
                return addedRequest.get();
            } else {
                // Handle the case where the request was not added to the repository
                throw new RuntimeException("Failed to add request to repository.");
            }
        }

    public Request createAndSaveRequestForApartment(String typeOfCommercialize, Agent agente, double area, String location, double distanceFromCityCentre, double requestedPrice, String photographs, int numberBedrooms, int numberBathrooms,
                                                    int numberParkingSpaces, String availableEquipment, Owner owner) {

        RequestApartment newRequest = new RequestApartment(typeOfCommercialize, agente, area, location, distanceFromCityCentre, requestedPrice, photographs, numberBedrooms, numberBathrooms, numberParkingSpaces, availableEquipment, owner);
        //addRequestInRepository(newRequest);

        Optional<Request> addedRequest = requestRepository.add(newRequest);

        if (addedRequest.isPresent()) {
            return addedRequest.get();
        } else {
            // Handle the case where the request was not added to the repository
            throw new RuntimeException("Failed to add request to repository.");
        }
    }

    public Owner createOwner(String name, int cardNumber, int taxNumber, String address, String email, int phoneNumber) {
        Owner newOwner = new Owner(name, cardNumber, taxNumber, address, email, phoneNumber);
        return newOwner;

    }

    public List<Request> getRequest() {

        RequestRepository requestRepository = getRequestRepository();

        return requestRepository.getRequests();
    }


    public Request createAndSaveRequestForHouse(String typeOfCommercialize, Agent agente, double area, String location, Double distanceFromCityCentre, Double requestedPrice,
                                                String photographs, int numberBedrooms, int numberBathrooms, int numberParkingSpaces, String availableEquipment,
                                                boolean basement, boolean inhabitableLoft, boolean sunExposure, Owner owner) {

        RequestHouse newRequest = new RequestHouse(typeOfCommercialize, agente, area, location, distanceFromCityCentre, requestedPrice, photographs, numberBedrooms, numberBathrooms, numberParkingSpaces, availableEquipment, basement, inhabitableLoft, sunExposure, owner);

        Optional<Request> addedRequest = requestRepository.add(newRequest);

            if (addedRequest.isPresent()) {
                return addedRequest.get();
            } else {
                // Handle the case where the request was not added to the repository
                throw new RuntimeException("Failed to add request to repository.");
            }

        }

    }











# 6. Integration and Demo

* A new option on the Main menu options was added.







