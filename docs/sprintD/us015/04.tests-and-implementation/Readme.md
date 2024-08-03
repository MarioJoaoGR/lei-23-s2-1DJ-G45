# US 015 - List all booking requests for properties managed by me.


# 4. Tests 
### Tests for RegisterUserController

**Test 1:** Get the list of requests for a specific period (begin date, end date).


    @Test
    void getVisitRequestsForPeriod() throws ParseException {
        AnnouncementRepository announcementRepository = new AnnouncementRepository();

        String dateString = "31-05-2023";
        String endString = "02-06-2023";

        Agent agent = new Agent("agent",11111,1111,"pwd",111,"agent@this.app");
        Property p1 = new Apartment("Porto", 70,1000,null,3, 3,1,null,null);

        List<VisitRequest> visitList = new ArrayList<>();
        visitList.add(new VisitRequest("01-06-2023", null, "teste", "agent@this.app", null));
        visitList.add(new VisitRequest("02-06-2023",null, "teste", "agent@this.app", null));
        visitList.add(new VisitRequest("13-06-2023",null, "teste", "agent@this.app", null));

        AnnouncementAvailable A1=new AnnouncementAvailable(null,2,null,visitList,"Sale",p1,agent);

        announcementRepository.addAvailable(A1);

        List<VisitRequest> actual = announcementRepository.getVisitRequestListForAgent(agent.getMail(),start,end);

        List<VisitRequest> expected = new ArrayList<>();
        expected.add(visitRequest);
        expected.add(visitRequest1);

        assertEquals(expected,actual);
    }





**Test 2:** Check if the list of requests is sorted by date in ascending order.

    @Test
    void getVisitRequestsSorted(){
        AnnouncementRepository announcementRepository = new AnnouncementRepository();

        String sortingAlgorithm = null;
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            sortingAlgorithm = prop.getProperty("sorting_algorithm");
        } catch (IOException ex) {
            ex.printStackTrace();
        }



        Agent agent = new Agent("agent", 11111, 1111, "pwd", 111, "agent@this.app");
        Property p1 = new Apartment("Porto", 70, 1000, null, 3, 3, 1, null, null);

        VisitRequest visitRequest = new VisitRequest("13-06-2023", null, "teste", "agent@this.app", null);
        VisitRequest visitRequest1 = new VisitRequest("07-06-2023", null, "teste", "agent@this.app", null);
        VisitRequest visitRequest2 = new VisitRequest("09-06-2023", null, "teste", "agent@this.app", null);

        List<VisitRequest> visitList = new ArrayList<>();
        visitList.add(visitRequest);
        visitList.add(visitRequest1);
        visitList.add(visitRequest2);
        AnnouncementAvailable A1 = new AnnouncementAvailable(null, 2, null, visitList, "Sale", p1, agent);

        A1.addVisitRequests(visitRequest);

        announcementRepository.addAvailable(A1);


        List<VisitRequest> actual = announcementRepository.getVisitRequest();
        if (sortingAlgorithm.equalsIgnoreCase("bubble_sort")) {
            BubbleSort bubbleSort = new BubbleSort();
            bubbleSort.sort(actual);
        } else if (sortingAlgorithm.equalsIgnoreCase("merge_sort")) {
            MergeSort mergeSort = new MergeSort();
            mergeSort.sort(actual);
        }

        List<VisitRequest> expected = new ArrayList<>();
        expected.add(visitRequest1);
        expected.add(visitRequest2);
        expected.add(visitRequest);
        
        assertEquals(expected, actual);
    }





# 5. Construction (Implementation)


## Class AnnouncementController

```java
public List<VisitRequest> getVisitRequestListForAgent() {
    String email = getClientFromSession();
    return announcementRepository.getVisitRequestListForAgent(email);

}

```


## Class AnnouncementRepository

```java
public List<VisitRequest> getVisitRequestListForAgent(String email) {
    List<VisitRequest> aux;
    List<VisitRequest> result = new ArrayList<>();

    for (AnnouncementAvailable announcementAvailable : announcementAvailables) {
        aux = announcementAvailable.getAllVisitRequestForAgent(email);
        result.addAll(aux);
    }

    return result;
}
```

## Class AnnouncementAvailable

```java
public List<VisitRequest> getVisitRequestForAgent (String email,Date startDate,Date endDate){
    List<VisitRequest> result= new ArrayList<>();
    
    if(this.agent.isAgent(email)){
        for (VisitRequest req : visitRequests){
            if (req.isDateBetween(startDate,endDate) && req.isStateWaiting()){
                result.add(req);
            }
        }
    }
    
    return result;
}
```


## Class VisitRequest

```java
public boolean isDateBetween(Date startDate,Date endDate){
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    Date data = null;
    
    startDate = getZeroTimeDate(startDate);
    endDate = getZeroTimeDate(endDate);
    
    try {
        data = formatter.parse(getDate());
        data=getZeroTimeDate(data);
        
    }catch (ParseException e) {
        e.printStackTrace();
    }
    
    return (data.after(startDate) || data.equals(startDate)) &&
    (data.before(endDate) || data.equals(endDate));
}



public boolean isStateWaiting(){
    return status.equalsIgnoreCase(STATUS3);
}
```



## Class Agent

```java
public boolean isAgent(String email){
    return  email.equalsIgnoreCase(getMail());
}
```



## Class VisitRequestMapper

```java
public List<VisitRequestDTO> toDTO(List<VisitRequest> visitRequests) {
    List<VisitRequestDTO> visitRequestDTOS = new ArrayList<>();
    for (VisitRequest visitRequest : visitRequests) {
        visitRequestDTOS.add(this.toDTO(visitRequest));
    }
    return visitRequestDTOS;
}
```




# 6. Integration and Demo 

* A new option, List Visit Request, was added to Agent menu in javafx
* A new option, Agent menu, was added to Main menu in javafx`
* A new option, Login, was added to Main menu in javafx`





