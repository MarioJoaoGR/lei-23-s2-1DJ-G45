# US 018 - Analyze the deals made


# 4. Tests 
### 





# 5. Construction (Implementation)


## Class AnnouncementAvailable

```java

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






