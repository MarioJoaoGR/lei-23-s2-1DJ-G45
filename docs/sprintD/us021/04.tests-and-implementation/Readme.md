# US 021 - Register the visit and the opinion about the business


# 4. Tests 
### Tests for RegisterUserController

**Test 1:** Check if the opinion string has 200 Charecters


     @Test
    void ensureOpinionLenght(){
        String opinion = "facilis dicat ultrices deserunt dicit nunc ubique equidem deserunt quam sapientem voluptatum natoque delicata saperet mus voluptatibus elaboraret mauris sententiae elaboraret mauris sententiae elabora";
        String opinion1 = "facilis dicat ultrices deserunt dicit nunc ubique equidem deserunt quam sapientem voluptatum natoque delicata saperet mus voluptatibus elaboraret mauris sententiae ";

        System.out.println(opinion.length());
        assertTrue(opinion.length()>=200);
        assertFalse(opinion1.length()>=200);

    }





**Test 2:** Check if the Classification is a list from 1 to 5

    @Test
    void ensureClassificationScale(){
        List<String> classification = VisitRequestOpinion.getMyList();

        assertEquals(5,classification.size());

    }


**Test 3:** ensure that doesn't exist duplicate entries for VisitRequestOpinion at the same VisitRequest

    @Test
    void ensureDuplicateEntriesFail(){
        VisitRequest visitRequest = new VisitRequest("08-06-2023",null,"user","user@this.app",null);

        VisitRequestOpinion opinion = new VisitRequestOpinion("opinion","Likely");
        VisitRequestOpinion opinion1 = new VisitRequestOpinion("opinion1","Likely");

        assertTrue( visitRequest.setVisitRequestOpinion(opinion));
        assertFalse(visitRequest.setVisitRequestOpinion(opinion));
        assertTrue( visitRequest.setVisitRequestOpinion(opinion1));


    }
	



# 5. Construction (Implementation)


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


## Class AnnouncementController

```java
public List<VisitRequest> getAllVisitRequestListForAgent() {

    String email = getClientFromSession();
    AnnouncementRepository repository = getAnnouncementRepository();
    return repository.getVisitRequestListForAgent(email);

}
```

## Class VisitRequestOpinionUI

```java
private void submitAction(ActionEvent event) {
    String opinion = txtAreaOpinion.getText().trim();
    
    
    if (comboVisit.getValue()!=null && comboClassification.getValue()!=null && !txtAreaOpinion.getText().isEmpty()) {
        if (opinion.length() >= 200) {
            VisitRequest selectedVisitRequest = (VisitRequest) comboVisit.getValue();
            VisitRequestOpinion review = new VisitRequestOpinion(opinion, comboClassification.getValue().toString());
            selectedVisitRequest.setVisitRequestOpinion(review);
         
        }
    }

}
```

## Class VisitRequest

```java
 public boolean setVisitRequestOpinion(VisitRequestOpinion visitRequestOpinion) {
    if (!visitRequestOpinion.equals(this.visitRequestOpinion)){
        this.visitRequestOpinion = visitRequestOpinion;
        return true;
    }
    return false;
}

```
# 6. Integration and Demo 

* A new option on the Agent Menu javafx interface was added.
* A new scene to Register opinion was implemented.






