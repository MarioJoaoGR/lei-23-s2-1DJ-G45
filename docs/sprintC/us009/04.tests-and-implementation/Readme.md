# US 006 - To create a Task

# 4. Tests

**Test 1:** Check that the of announcement is showed the most recent
entries to the oldest.

        // Iterate through the list starting from the second element
        for (int i = 1; i < announcementList.size(); i++) {
            AnnouncementDTO currentAnnouncement = announcementList.get(i);
            AnnouncementDTO previousAnnouncement = announcementList.get(i - 1);

            // Compare the dates
            assertTrue(previousAnnouncement.getDate().compareTo(currentAnnouncement.getDate()) > 0);
        }


    }

**Test 2** check add visit request is working

    @Test
     List<String> timeSlot = new ArrayList<>();
        timeSlot.add("10:00 AM - 11:00 AM");
        visitRequestDTO.setTimeSlot(timeSlot);
        //ensure adding double timeSlots for visit property fails
        assertFalse(visitRequestDTO.addTimeSlot("10:00 AM - 11:00 AM"));
        assertTrue(visitRequestDTO.addTimeSlot("aa"));


# 5. Construction (Implementation)

## Class RegisterEmployeeController

```java
public class ScheduleVisitController {


    private AnnouncementRepository announcementRepository = null;
    private AuthenticationRepository authenticationRepository = null;

    public ScheduleVisitController() {
        getAnnouncementRepository();
    }


    public ScheduleVisitController(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;

    }


    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }


    private AnnouncementRepository getAnnouncementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }

        return announcementRepository;


    }

    public Optional<Announcement> getAnnouncementById(int id) {

        return announcementRepository.getAnnouncementById(id);
    }

    public String getUserName() {
        return authenticationRepository.getCurrentUserSession().getUserName();
    }

    public String getEmail() {
        return authenticationRepository.getCurrentUserSession().getUserId().getEmail();
    }

    public boolean createVisitRequest(String date, String timeSlot, String userName, Agent agent, String email) {
        boolean flag = false;
        VisitRequest newVisitRequest = agent.createVisitRequest(date, timeSlot, userName, email);
        Optional<VisitRequest> result = agent.addVisitRequests(newVisitRequest);
        if (result.isPresent()) {
            flag = true;
        }
        return flag;
    }

    public boolean checkAnnouncementExists(int id) {
        boolean flag = false;
        Optional<Announcement> result = announcementRepository.getAnnouncementById(id);
        if (result.isPresent()) {
            flag = true;
        }
        return flag;

    }
}
````

## Class VisitRequest

```java
public class VisitRequest {


    private final String STATUS1 = "Approved";
    private final String STATUS2 = "Not approved";
    private final String STATUS3 = "Waiting";
    private String date;
    private String status;
    private String timeSlot;
    private String userName;
    private String email;

    public VisitRequest(String date, String timeSlot, String userName, String email) {
        this.status = STATUS3;
        this.date = date;
        this.timeSlot = timeSlot;
        this.userName = userName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    /**
     * message of visit request(for agent to see)
     *
     * @return message
     */
    @Override
    public String toString() {
        return "VisitRequest{" +
                "date='" + date + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }


    public String getStatus() {
        return status;
    }

    public void changeStatusForApproved() {
        this.status = STATUS1;
    }

    public void changeStatusForRejected() {
        this.status = STATUS2;
    }

    public String getDate() {
        return date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitRequest)) return false;
        VisitRequest that = (VisitRequest) o;
        return date.equals(that.date) && status.equals(that.status) && timeSlot.equals(that.timeSlot) && userName.equals(that.userName) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, status, timeSlot, userName, email);
    }

    public VisitRequest cloneVisitRequest() {
        return new VisitRequest(getDate(), getTimeSlot(), getUserName(), getEmail());
    }


}
````

## Class Agent

```java
public class Agent extends Employee {
    

    //Agent messages received//
    List<VisitRequest> visitRequests = new ArrayList<>();
    
    public Optional<VisitRequest> addVisitRequests(VisitRequest visitRequest) {

        Optional<VisitRequest> newMessage = Optional.empty();

        boolean operationSuccess = false;

        if (validVisitRequest(visitRequest)) {
            newMessage = Optional.of(visitRequest.cloneVisitRequest());
            operationSuccess = visitRequests.add(newMessage.get());
        }

        if (!operationSuccess) {
            newMessage = Optional.empty();
        }

        return newMessage;

    }


    public VisitRequest createVisitRequest(String date, String timeSlot, String userName, String mail) {
        return new VisitRequest(date, timeSlot, userName, mail);
    }

    public boolean validVisitRequest(VisitRequest newVisitRequest) {
        boolean flag = true;
        for (VisitRequest a : visitRequests) {

            if (a.equals(newVisitRequest)) {
                flag = false;
            }
        }
        return flag;
    }
}
````