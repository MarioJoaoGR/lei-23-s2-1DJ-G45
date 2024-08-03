# US 006 - To create a Task 

# 4. Tests 

**Test 1:** TBD

**Test 2:** TBD 



# 5. Construction (Implementation)


## Class PublishSaleController 

```java
public void publishSale(int requestId, Agent agent, double comission, Date date) {
        List<Request> requests = (List<Request>) getRequestRepository();
        AnnouncementRepository announcementRepository = getSaleRepository();
        for (Request request : requests) {
        if (request.getRequestId() == requestId && request.getAgente() == agent && request.getTypeOfCommercialize() == "Apartment") {
        RequestApartment apartmentRequest = (RequestApartment) request;
        Apartment apartmentToPublish = new Apartment(apartmentRequest.getLocation(), apartmentRequest.getArea(), apartmentRequest.getDistanceFromCityCenter(),
        apartmentRequest.getPhotographs(), apartmentRequest.getNumberBedrooms(), apartmentRequest.getNumberBathrooms(), apartmentRequest.getNumberParkingSpaces(),
        apartmentRequest.getAvailableEquipment());
        Announcement sale = new Announcement(comission, apartmentRequest.getRequestedPrice(), date, request.getTypeOfCommercialize(), apartmentToPublish);
        announcementRepository.add(sale);
        } else if (request.getRequestId() == requestId && request.getAgente() == agent && request.getTypeOfCommercialize() == "House") {
        RequestHouse houseRequest = (RequestHouse) request;
        House houseToPublish = new House(houseRequest.getLocation(), houseRequest.getArea(), houseRequest.getDistanceFromCityCenter(), houseRequest.getPhotographs(),
        houseRequest.getNumberBedrooms(), houseRequest.getNumberBathrooms(), houseRequest.getNumberParkingSpaces(), houseRequest.getAvailableEquipment(),
        houseRequest.getBasement(), houseRequest.getInhabitableLoft(), houseRequest.getSunExposure());
        Announcement sale = new Announcement(comission, houseRequest.getRequestedPrice(), date, houseRequest.getTypeOfCommercialize(), houseToPublish);
        announcementRepository.add(sale);

        } else if (request.getRequestId() == requestId && request.getAgente() == agent && request.getTypeOfCommercialize() == "Land") {
        RequestLand landRequest = (RequestLand) request;
        Land landToPublish = new Land(landRequest.getLocation(), landRequest.getArea(), landRequest.getDistanceFromCityCenter(), landRequest.getPhotographs());
        Announcement sale = new Announcement(comission, landRequest.getRequestedPrice(), date, landRequest.getTypeOfCommercialize(), landToPublish);
        announcementRepository.add(sale);

        }
            }
        }
```


## Class PublishSaleUI

```java
package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.PublishSaleController;

public class PublishSaleUI implements Runnable {

    private PublishAnnouncementController controller = new PublishAnnouncementController();

    @Override
    public void run() {

    }

    /**
     * Prompts the agent to type the requestId necessary for the sale publish
     * @return requestId
     */

    public int getRequestId() {
        boolean isValid = false;
        do {

        } while (!isValid);
        return 0;
    }

    /**
     * this method checks if the requestId or in other others the request itself, is present in the requests repository
     * @return
     */

    public boolean checkRequestIdIsPresent(int idToBeChecked) {
        boolean isPresent = false;
        return isPresent;
    }

    /**
     * Prompts the agent to set the commission for the sale publish
     * @return comission
     */

    public double getAgentComission() {
        return 0;
    }

    /**
     * Displays the all the agent Requests id this is useful for the agent to choose the request he wants to work on
     */

    public void viewAgentRequests() {

    }

}

```

# 6. Integration and Demo 

* A new option on the Employee menu options was added.

* Some demo purposes some tasks are bootstrapped while system starts.


# 7. Observations

The id's will be updated so they can have a static id counter
and removed from the constructor since the id's are predefined
and cannot be altered by anyone. The id's are a form to identify 
and quickly access each class object so there could be changes 
made to the implementation that certain methods request only the id
and not so many data related to the object, having the id
they can access the object data trough getter methods.





