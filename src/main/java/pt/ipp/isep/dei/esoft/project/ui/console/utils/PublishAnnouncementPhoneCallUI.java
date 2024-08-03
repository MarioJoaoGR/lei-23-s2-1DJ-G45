package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementPhoneCallController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;

import java.sql.SQLOutput;
import java.util.*;

/**
 * The type Publish announcement phone call ui.
 */
public class PublishAnnouncementPhoneCallUI implements Runnable {

    /**
     * The Publish announcement phone call controller.
     */
    PublishAnnouncementPhoneCallController publishAnnouncementPhoneCallController = new PublishAnnouncementPhoneCallController();

    @Override
    public void run() {
        AnnouncementAvailable announcementAvailable = requestData();
        if (announcementAvailable!=null){
            publishAnnouncementPhoneCallController.getAnnouncementList().add(announcementAvailable);
        }
    }


    private AnnouncementAvailable requestData() {
        Agent announcementAgent = publishAnnouncementPhoneCallController.getAgentFromSession();
        String businessType = inputBusinessType();
        if (businessType!= null) {

            double price = inputAnnouncementPrice();
            String propertyLocation = inputPropertyLocation();
            double propertyArea = inputArea();
            double distanceCenter = inputDistanceCenter();
            List<String> photos = inputPhotos();
            String propertyType = inputPropertyType();
            Owner propertyOwner = inputOwnerInfo();
            String messageToSend;
            if (propertyType.equals("Apartment")) {
                int numberOfBedrooms = inputNumberOf("Number of bedrooms: ");
                int numberOfBathrooms = inputNumberOf("Number of bathrooms: ");
                int numberOfParkingSpaces = inputNumberOf("Number of parking spaces: ");
                String equipment = inputEquipment();
                Apartment apartmentToAnnounce = new Apartment(propertyLocation, propertyArea, distanceCenter, photos, propertyOwner, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, equipment);
                Commission commission = getAgentComission();
                return new AnnouncementAvailable(commission, new Date(), price, null, businessType, apartmentToAnnounce, announcementAgent, null);
            } else if (propertyType.equals("House")) {
                int numberOfBedrooms = inputNumberOf("Number of bedrooms: ");
                int numberOfBathrooms = inputNumberOf("Number of bathrooms: ");
                int numberOfParkingSpaces = inputNumberOf("Number of parking spaces: ");
                String equipment = inputEquipment();
                boolean basement = inputBasement();
                boolean loft = inputLoft();
                String sunExposure = inputSunExposure();
                House houseToAnnounce = new House(propertyLocation, propertyArea, distanceCenter, photos, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, equipment, basement, loft, sunExposure, propertyOwner);
                Commission commission = getAgentComission();
                return new AnnouncementAvailable(commission, new Date(), price, null, businessType, houseToAnnounce, announcementAgent, null);

            } else {
                Commission commission = getAgentComission();
                Land landToAnnounce = new Land(propertyLocation, propertyArea, distanceCenter, photos, propertyOwner);
                return new AnnouncementAvailable(commission, price,new Date(), null, businessType, landToAnnounce, announcementAgent, null);
            }

        }
        return null;
    }

    private double inputAnnouncementPrice() {
        Scanner keyboardScanner = new Scanner(System.in);
        double announcementPrice = 0;
        boolean passed;
        System.out.println("Introduce the announcement price: ");
        do {
            passed = true;
            try {
                announcementPrice = keyboardScanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("That input is not valid, try again.");
                passed = false;
                keyboardScanner.next();
            }
        } while (!passed);
        return announcementPrice;
    }

    private Commission getAgentComission() {
        Commission commission = new Commission();
        Scanner scn = new Scanner(System.in);
        int comissionType = 0;
        System.out.println("Select the type of commission you want: ");
        do {
            System.out.println("1 - Fix comission");
            System.out.println("2 - Percentage comission");
            comissionType = scn.nextInt();
        } while (comissionType < 1 || comissionType > 2);
        if (comissionType == 1) {
            commission.setCommissionTypeFixed();
            commission.setCommissionValue(getFixComission());
        } else {
            commission.setCommissionTypePercentage();
            commission.setCommissionValue(getPercentComission());
        }
        return commission;
    }

    private double getFixComission() {
        Scanner scn = new Scanner(System.in);
        double comission = 0;
        boolean passed;
        System.out.println("Type the value for your comission");
        do {
            passed = true;
            try {
                comission = scn.nextDouble();
                if (comission <= 0) {
                    passed = false;
                    System.out.println("Commission should be positive, type again.");
                }
            } catch (InputMismatchException exception) {
                System.out.println("That is not a valid commission, type again.");
                passed = false;
                scn.next();
            }
        } while (!passed);
        return comission;
    }

    private double getPercentComission() {
        Scanner scn = new Scanner(System.in);
        double comission = 0;
        boolean passed;
        System.out.println("Type the value for your comission: ");
        do {
            passed = true;
            try {
                comission = scn.nextDouble();
                if (comission < 0 || comission > 100) {
                    System.out.println("Comission should be between 0% and 100%");
                    passed = false;
                }
            } catch (InputMismatchException exception) {
                System.out.println("That is not a valid commission, type again.");
                passed = false;
                scn.next();
            }
        } while (!passed);
        return comission;
    }

    private String inputBusinessType() {
        List<String> options = new ArrayList<>();
        options.add("Sale");
        options.add("Lease");
        String businessType = (String) Utils.showAndSelectOne(options, "Select the business type:");
        return businessType;
    }

    private String inputPropertyType() {
        List<String> options = new ArrayList<>();
        options.add("Apartment");
        options.add("House");
        options.add("Land");
        String propertyType = (String) Utils.showAndSelectOne(options, "Select the property type:");
        return propertyType;
    }

    private String inputPropertyLocation() {
        Scanner keyboardScanner = new Scanner(System.in);
        String propertyLocation = null;
        boolean passed;
        System.out.println("Property location: ");
        do {
            passed = true;
            try {
                propertyLocation = keyboardScanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("That is not a valid location");
                keyboardScanner.next();
                passed = false;
            }
        } while (!passed);
        return propertyLocation;
    }

    private double inputArea() {
        Scanner keyboardScanner = new Scanner(System.in);
        double area = 0;
        boolean passed;
        System.out.println("Introduce the property area: ");
        do {
            passed = true;
            try {
                area = keyboardScanner.nextDouble();
                if (area <= 0) {
                    System.out.println("Area must be positive.");
                }
            } catch (InputMismatchException e) {
                System.out.println("That input is not valid, try again.");
                passed = false;
                keyboardScanner.next();
            }
        } while (!passed);
        return area;
    }

    private double inputDistanceCenter() {
        Scanner keyboardScanner = new Scanner(System.in);
        double distanceCenter = 0;
        boolean passed;
        System.out.println("Introduce the distance center: ");
        do {
            passed = true;
            try {
                distanceCenter = keyboardScanner.nextDouble();
                if (distanceCenter <= 0) {
                    System.out.println("Distance center must be positive.");
                }
            } catch (InputMismatchException e) {
                System.out.println("That input is not valid, try again.");
                passed = false;
                keyboardScanner.next();
            }
        } while (!passed);
        return distanceCenter;
    }

    private List<String> inputPhotos() {
        Scanner keyboardScanner = new Scanner(System.in);
        List<String> photos = new ArrayList<>();
        int numberOfPhotos = Utils.readIntegerFromConsole("How many photos does the property have: ");
        int counter = 1;
        for (String photoURL : photos) {
            boolean passed;
            System.out.printf("Photo url %d: ", counter);
            counter++;
            do {
                passed = true;
                try {
                    photoURL = keyboardScanner.nextLine();
                    photos.add(photoURL);
                    System.out.println();
                } catch (InputMismatchException e) {
                    System.out.println("That is not a valid location");
                    keyboardScanner.next();
                    passed = false;
                }
            } while (!passed);

        }
        return photos;
    }

    private Owner inputOwnerInfo() {
        Scanner keyboardScanner = new Scanner(System.in);
        Owner owner = new Owner();
        owner.setName(Utils.requestName());
        owner.setCardNumber(Utils.requestCardNumber());
        owner.setTaxNumber(Utils.requestTaxNumber());
        owner.setAddress(Utils.requestAddress());
        owner.setEmail(Utils.requestEmail());
        owner.setPhoneNumber(Utils.requestPhoneNumber());
        return owner;
    }

    private int inputNumberOf(String message) {
        Scanner keyboardScanner = new Scanner(System.in);
        int numberOfBedrooms = 0;
        boolean passed;
        System.out.println(message);
        do {
            passed = true;
            try {
                numberOfBedrooms = keyboardScanner.nextInt();
                if (numberOfBedrooms < 0) {
                    System.out.println("That is not a valid number of bedrooms.");
                }
            } catch (InputMismatchException e) {
                System.out.println("That input is not valid, try again.");
                passed = false;
                keyboardScanner.next();
            }
        } while (!passed);
        return numberOfBedrooms;
    }

    private String inputEquipment() {
        Scanner keyboardScanner = new Scanner(System.in);
        String equipment = null;
        boolean passed;
        System.out.println("Equipment: ");
        do {
            passed = true;
            try {
                equipment = keyboardScanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("That is not valid");
                keyboardScanner.next();
                passed = false;
            }
        } while (!passed);
        return equipment;
    }

    private boolean inputBasement() {
        List<String> options = new ArrayList<>();
        options.add("Yes");
        options.add("No");
        String answer = (String) Utils.showAndSelectOne(options, "Does the property have a basement?");
        if (answer.equals("Yes")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean inputLoft() {
        List<String> options = new ArrayList<>();
        options.add("Yes");
        options.add("No");
        String answer = (String) Utils.showAndSelectOne(options, "Does the property have a inhabitable loft?");
        if (answer.equals("Yes")) {
            return true;
        } else {
            return false;
        }
    }

    private String inputSunExposure() {
        List<String> options = new ArrayList<>();
        options.add("North");
        options.add("South");
        options.add("West");
        options.add("East");
        String sunExposure = (String) Utils.showAndSelectOne(options, "Select the sun exposure of the house:");
        return sunExposure;

    }


}
