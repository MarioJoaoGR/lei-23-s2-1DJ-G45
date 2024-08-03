# US 020 - read responses for appointment requests and accept or reject them

# 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the Task class with null values. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Task instance = new Task(null, null, null, null, null, null, null);
	}
	

**Test 2:** Check that it is not possible to create an instance of the Task class with a reference containing less than five chars - AC2. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureReferenceMeetsAC2() {
		Category cat = new Category(10, "Category 10");
		
		Task instance = new Task("Ab1", "Task Description", "Informal Data", "Technical Data", 3, 3780, cat);
	}


*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)


## Class Repositories

```java
private static final Repositories instance = new Repositories();
AppointmentRequestRepository appointmentRequestRepository= new AppointmentRequestRepository();

public static Repositories getInstance() {
        return instance;
        }

public AppointmentRequestRepository getAppointmentRequestRepository(){
        return appointmentRequestRepository;
        }
```


## Class AnswearAppointmentController

```java
public ArrayList<AppointmentRequestDTO> getAppointmentRequestList(){
        ...
        }
        
public void sendEmail(Agent agent,Client user){
        sendEmail(agent,user)
        }
```

## Class AppointmentRequestRepository

```java
private ArrayList<AppointmentRequest> usersAppointmentRequestList = new ArrayList<>();

public ArrayList<AppointmentRequestDTO> getUserRequests(){
        ...
        getAppointmentRequests();
        ...
        usersAppointmentRequestList.add(appointmentRequest)
        ...
        toDTO(usersAppointmentRequestList);
        }

private ArrayList<AppointmentRequest> getAppointmentRequests(){
        ...
        }
public void sendEmail(Agent agent,Client user)    
        sendEmail(agent,user);
        
```

## Class AppointmentRequestMapper

```java
private ArrayList<AppointmentRequestDTO> usersAppointmentRequestListDTO = new ArrayList<>();

public ArrayList<AppointmentRequestDTO> toDTO(List<AppointmentRequest> usersAppointmentRequestList){
        ...
        userAppointmentRequestDTO = toDTO(userAppointmentRequest)
        ...
        usersAppointmentRequestListDTO.add(userAppointmentRequestDTO);
        }

private AppointmentRequestDTO toDTO(AppointmentRequest userAppointmentRequest){
        ...
        }

```

## Class AppointmentRequestDTO
```java
private final Agent agentDTO;
private final Date dateDTO;
private final Announcement announcementDTO
private final String statusDTO;
private final Client clientDTO;

        
```

## Class AppointmentRequest
```java
private final Agent agent;
private final Date date;
private final Announcement announcement
private final String status;
private final Client client;

        
```

## Class EmailSender
```java

public void sendEmail(Agent agent,Client user){
        ...
        }
        


```

# 6. Integration and Demo 

* A new option on the Employee menu options was added.

* Some demo purposes some tasks are bootstrapped while system starts.


# 7. Observations

Platform and Organization classes are getting too many responsibilities due to IE pattern and, therefore, they are becoming huge and harder to maintain. 

Is there any way to avoid this to happen?





