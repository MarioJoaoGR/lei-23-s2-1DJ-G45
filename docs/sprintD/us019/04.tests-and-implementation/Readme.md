# US 019 -  Divide the set of all stores into two subsets


# 4. Tests 
### Tests for RegisterUserController

**Test 1:** Check if it's not possible create orders with value higher than Announcement price


    @Test
    void ensureAddOrderValidateValue(){
        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable(null,10,null,"Sale",p1,null);

        Order order = announcementAvailable.createOrder("user@this.app",120000);
        Order order1 = announcementAvailable.createOrder("user@this.app",10);

        assertTrue(announcementAvailable.addOrder(order).isEmpty());
        assertTrue(!announcementAvailable.addOrder(order1).isEmpty());

    }





**Test 2:** Check if it's not possible create orders with repeated values

    @Test
    void ensureAddOrderValidateValueRepeat(){
        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable(null,10000,null,"Sale",p1,null);

        Order order = announcementAvailable.createOrder("user@this.app",120);
        Order order1 = announcementAvailable.createOrder("user1@this.app",120);
        Order order2 = announcementAvailable.createOrder("user1@this.app",121);

        announcementAvailable.addOrder(order);

        assertTrue(announcementAvailable.addOrder(order1).isEmpty());
        assertFalse(announcementAvailable.addOrder(order2).isEmpty());
    }


**Test 3:** Check if it's not possible create another order for same Client in the same Announcement

    @Test
    void ensureAddOrderValidateRepeatClient(){
        Property p1 = new Apartment("Porto", 70,1000,null,3, 1,1,null,null);
        AnnouncementAvailable announcementAvailable = new AnnouncementAvailable(null,10000,null,"Sale",p1,null);

        Order order = announcementAvailable.createOrder("user@this.app",120);
        Order order1 = announcementAvailable.createOrder("user@this.app",122);
        Order order2 = announcementAvailable.createOrder("user1@this.app",123);

        announcementAvailable.addOrder(order);


        assertTrue(announcementAvailable.addOrder(order1).isEmpty());
        assertFalse(announcementAvailable.addOrder(order2).isEmpty());

    }
	



# 5. Construction (Implementation)


## Class AnnouncementAvailable

```java
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

* A new option on the Network Manager Menu option was added






