# US 011 - List real estate purchase orders to accept or decline a purchase order for a property


# 4. Tests

# Tests for announcement controller


**Test 1:** Ensures order list is being correctly brougth from the announcement.

    @Test
    public void testGetOrderListDTO() {
    // Create a mock AnnouncementDTO
    AnnouncementDTO announcementDTO = new AnnouncementDTO();

        // Create a list of OrderDTOs
        List<OrderDTO> orderList = new ArrayList<>();
        orderList.add(new OrderDTO(clientmail@mail.com));
        // Add the order list to the announcementDTO
        announcementDTO.setOrderList(orderList);

        // Call the method
        List<OrderDTO> result = getOrderListDTO(announcementDTO);

        // Assert that the result is the same as the orderList
        assertEquals(orderList, result);
    }
    @Test
    public void testGetListAnnouncementAvailableDTOForAgent() {
    // Create a mock AnnouncementAvailable list
    List<AnnouncementAvailable> announcementAvailableList = new ArrayList<>();
    announcementAvailableList.add(new AnnouncementAvailable());

    // Mock the announcementRepository
    when(announcementRepository.getAgentAnnouncements(any())).thenReturn(announcementAvailableList);

    // Mock the mapper
    when(mapper.toAnnouncementDTO(any())).thenReturn(new AnnouncementDTO(clientmail@mail.com));

    // Call the method
    List<AnnouncementDTO> result = getListAnnouncementAvailableDTOForAgent();

    // Assert that the result size is the same as the announcementAvailableList size
    assertEquals(announcementAvailableList.size(), result.size());

    // Assert that each AnnouncementDTO in the result is not null
    for (AnnouncementDTO announcementDTO : result) {
        assertNotNull(announcementDTO);
    }
}



     




**Test 2:** Check if clients are getting correctly notificated about the results of the contest

    @Test
    public void testSendOrderResultsViaEmailAcceptedOrder() {
    // Create a mock OrderDTO
    OrderDTO order = new OrderDTO();
    order.setId(1);
    order.setValue(100.00);
    // Set the order state to accepted (state 2)
    order.setState(2);

    // Create a mock AnnouncementDTO
    AnnouncementDTO announcement = new AnnouncementDTO();
    announcement.setBusinessType("Business Type");
    // Set the property for the announcement
    announcement.setProperty(new Property(a2, a1, p0));

    // Create a list with the order
    List<OrderDTO> orders = Collections.singletonList(order);

    // Call the method
    boolean isSuccess = sendOrderResultsViaEmail(orders, announcement);

    // Assert that the email was sent successfully
    assertTrue(isSuccess);
}

    @Test
    public void testSendOrderResultsViaEmailDeclinedOrder() {
    // Create a mock OrderDTO
    OrderDTO order = new OrderDTO();
    order.setId(2);
    // Set the order state to declined (any state other than 2)
    order.setState(1);

    // Create a mock AnnouncementDTO
    AnnouncementDTO announcement = new AnnouncementDTO();

    // Create a list with the order
    List<OrderDTO> orders = Collections.singletonList(order);

    // Call the method
    boolean isSuccess = sendOrderResultsViaEmail(orders, announcement);

    // Assert that the email was sent successfully
    assertTrue(isSuccess);
}

# Tests for Announcement Repository

**Test 3:** Ensures the logged agent is receiving the right announcements

    @Test
    public void testGetAgentAnnouncements() {
    // Create a mock email
    String email = "agent@example.com";

    // Create a mock list of AnnouncementAvailable objects
    List<AnnouncementAvailable> announcementAvailableList = new ArrayList<>();
    announcementAvailableList.add(new AnnouncementAvailable(a2, p3, p4));

    // Set the agent's email for some AnnouncementAvailable objects to match the mock email
    for (AnnouncementAvailable announcementAvailable : announcementAvailableList) {
        announcementAvailable.getAgent().setMail(email);
    }

    // Mock the announcementAvailables list in the class being tested
    MyClassUnderTest classUnderTest = new MyClassUnderTest();
    classUnderTest.setAnnouncementAvailables(announcementAvailableList);

    // Call the method
    List<AnnouncementAvailable> result = classUnderTest.getAgentAnnouncements(email);

    // Assert that the result contains AnnouncementAvailable objects with the agent's email matching the mock email
    for (AnnouncementAvailable announcementAvailable : result) {
        assertEquals(email, announcementAvailable.getAgent().getMail());
    }
    }

    @Test
    public void testGetAgentAnnouncementsNoMatchingEmail() {
    // Create a mock email
    String email = "agent@example.com";

    // Create a mock list of AnnouncementAvailable objects
    List<AnnouncementAvailable> announcementAvailableList = new ArrayList<>();
    announcementAvailableList.add(new AnnouncementAvailable(a2, p3, p4));

    // Set the agent's email for all AnnouncementAvailable objects to a different email
    for (AnnouncementAvailable announcementAvailable : announcementAvailableList) {
        announcementAvailable.getAgent().setMail("other@example.com");
    }

    // Mock the announcementAvailables list in the class being tested
    MyClassUnderTest classUnderTest = new MyClassUnderTest();
    classUnderTest.setAnnouncementAvailables(announcementAvailableList);

    // Call the method
    List<AnnouncementAvailable> result = classUnderTest.getAgentAnnouncements(email);

    // Assert that the result is an empty list
    assertTrue(result.isEmpty());
}




# 5. Construction (Implementation)


## Class Announcement Available

```java
public class AnnouncementAvailable {

    public  static  int idCount = 0;
    private int id;
    Commission commission;
    private Date date;

    private double price;
    List<VisitRequest> visitRequests = new ArrayList<>();
    private String businessType;
    private Property property;
    private Agent agent;
    private List<Order> orderList = new ArrayList<>();

    public AnnouncementAvailable(int id, Commission commission, Date date, List<VisitRequest> visitRequests, String businessType, Property property, Agent agent, List<Order> orderList, double price) {
        this.id = id;
        this.commission = commission;
        this.date = date;
        this.visitRequests = visitRequests;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.orderList = orderList;
        this.price = price;
    }

    public AnnouncementAvailable(Commission commission, double price, Date date, String businessType, Property property, Agent agent) {
        this.id = idCount++;
        this.commission = commission;
        this.date = date;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.price = price;
    }

    public AnnouncementAvailable(Commission commission, double price, Date date, List<VisitRequest> visitRequests, String businessType, Property property, Agent agent, List<Order> orderList) {
        this.id = idCount++;
        this.commission = commission;
        this.date = date;
        this.visitRequests = visitRequests;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.orderList = orderList;
        this.price = price;
    }

    public AnnouncementAvailable() {

    }

    public AnnouncementAvailable clone() {
        return new AnnouncementAvailable(this.id, this.commission, this.date, this.visitRequests, this.businessType, this.property, this.agent, this.orderList,this.price);
    }



 

    public static int getIdCount() {
        return idCount;
    }

    public int getId() {
        return id;
    }

   

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementAvailable announcementAvailable = (AnnouncementAvailable) o;
        return id == announcementAvailable.id && commission == announcementAvailable.getCommission() && date== announcementAvailable.date && businessType.equals(announcementAvailable.businessType) && property== announcementAvailable.property;

    }

    public int hashCode() {
        return Objects.hash(id, commission, date, businessType, property);
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public Order createOrder(String email,double value){
        return new Order(email,value,new Date());
    }

    public Optional<Order> addOrder(Order order) {

        Optional<Order> newOrder = Optional.empty();

        if (validateOrderValue(order) && validateOrderValueRepeat(order) && validateOrderRepeatClient(order)){
            boolean operationSuccess = false;
            if (validateOrder(order)) {
                newOrder = Optional.of(order.clone());
                operationSuccess = orderList.add(newOrder.get());
            }
            if (!operationSuccess) {
                newOrder  = Optional.empty();
            }
        }

        return newOrder;
    }

    private boolean validateOrderValue(Order order){
        return !(order.getValue() > getPrice());
    }

    private boolean validateOrderValueRepeat(Order order){
        for (Order o : orderList){
            if (o.getValue()==order.getValue()){
                return false;
            }
        }

        return true;
    }

    private boolean validateOrderRepeatClient(Order order){
        Order aux = new Order();
        int position=0;
        for (Order o : orderList){
            if (o.getClientEmail().equalsIgnoreCase(order.getClientEmail())){
                aux = orderList.get(position);
                if (aux.getState()==0){
                    return false;
                }
            }
            position++;
        }

        return  true;
    }

    private boolean validateOrder(Order order) {
        return orderListDoNotContain(order);
    }

    private boolean orderListDoNotContain(Order order) {
        return !orderList.contains(order);
    }

    public AnnouncementDTO toDTO() {
        AnnouncementMapper announcementMapper = new AnnouncementMapper();
        return announcementMapper.toAnnouncementDTO(this);
    }

    @Override
    public String toString() {
        return "AnnouncementAvailable{" +
                "id=" + id +
                ", price=" + this.commission.getCommissionValue() +
                ", date=" + date +
                ", businessType='" + businessType + '\'' +
                ", property=" + property +
                ", agent=" + agent +
                '}';
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    
    

}
```


## Class Order

```java
public class Order {
    public class Order {
        private static int idCount = 1;
        private int id;
        private String clientEmail;
        private double value;
        private Date date;
        private int state;
        //0-without response
        //1-decline
        //2-accepted


        public Order() {
        }

        public Order(String clientEmail, double value, Date date) {
            this.id = idCount++;
            this.clientEmail = clientEmail;
            this.value = value;
            this.date = date;
            this.state = 0;
        }
    }
}
```

# 6. Integration and Demo

* A new option on the Announcement Menu option was added




