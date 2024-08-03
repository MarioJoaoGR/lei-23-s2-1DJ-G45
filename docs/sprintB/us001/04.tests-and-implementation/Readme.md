# US 001 - Display listed properties

# 4. Tests 

### Tests for ListingController


* Fill the AnnouncementRepository


	AnnouncementRepository announcementRepository = new AnnouncementRepository();
    ListingController controller = new ListingController(announcementRepository);


    //Fill Announcement Category Repository
    Property p1 = new Apartment(1,"Porto", 70,1000,null,null,null, 3,1,1,null);
    Property p2 = new House(2,"Algarve", 70,1000,null,null,null, 3,1,1,null,false,false,null);
    Property p3 = new House(3,"Lisboa", 70,1000,null,null,null, 3,1,1,null,false,false,null);
    Property p4 = new Apartment(4,"Evora", 70,1000,null,null,null, 3,1,1,null);
    Property p5 = new Land(5,"Leiria", 70,1000,null,null,null);
    Property p6 = new House(6,"Setubal", 70,1000,null,null,null, 2,1,1,null,false,false,null);
    Property p7 = new Land(7,"Bragança", 70,1000,null,null,null);
    Property p8 = new House(6,"Setubal", 70,1000,null,null,null, 3,1,1,null,false,false,null);

    Announcement L1=new Announcement(1,1,200,null,"Lease",p1);
    Announcement L2=new Announcement(2,1,150,null,"Lease",p2);
    Announcement L3=new Announcement(3,1,170,null,"Lease",p3);
    Announcement L4=new Announcement(4,1,90,null,"Sale",p4);
    Announcement L5=new Announcement(5,1,500,null,"Lease",p5);
    Announcement L6=new Announcement(6,1,340,null,"Lease",p6);
    Announcement L7=new Announcement(7,1,370,null,"Sale",p7);
    Announcement L8=new Announcement(8,1,370,null,"Sale",p8);

* **Test 1:** Search announcements by Business, Type and rooms 


    @Test
    void ensureGetListingWorkAllParameters() {

        announcementRepository.add(L1);
        announcementRepository.add(L2);
        announcementRepository.add(L3);
        announcementRepository.add(L4);
        announcementRepository.add(L5);
        announcementRepository.add(L6);
        announcementRepository.add(L7);
        announcementRepository.add(L8);


        //Act

        Integer[] expected = {2,3};
        Integer[] actual = new Integer[expected.length];
        List<Announcement> listfiltered = controller.getFilteredListing("Lease","House",3);

        int position =0;
        for (Announcement lr : listfiltered){
            actual[position] = lr.getId();
            position++;
            System.out.println(lr.getId());
        }

        assertArrayEquals(expected, actual);

    }
* **Test 2:** Search announcements by Type and rooms


    @Test
    void ensureGetListingWorkAllParametersAllBusiness() {

        announcementRepository.add(L1);
        announcementRepository.add(L2);
        announcementRepository.add(L3);
        announcementRepository.add(L4);
        announcementRepository.add(L5);
        announcementRepository.add(L6);
        announcementRepository.add(L7);
        announcementRepository.add(L8);

        Integer[] expected = {2,3,8};
        Integer[] actual = new Integer[expected.length];
        List<Announcement> listfiltered = controller.getFilteredListing("","House",3);

        int position =0;
        for (Announcement lr : listfiltered){
            actual[position] = lr.getId();
            position++;
        }

        assertArrayEquals(expected, actual);

    }

* **Test 3:** Search announcements by Business and rooms


    @Test
    void ensureGetListingWorkAllParametersAllType() {
        announcementRepository.add(L1);
        announcementRepository.add(L2);
        announcementRepository.add(L3);
        announcementRepository.add(L4);
        announcementRepository.add(L5);
        announcementRepository.add(L6);
        announcementRepository.add(L7);
        announcementRepository.add(L8);
        Integer[] expected = {1,2,3};
        Integer[] actual = new Integer[expected.length];
        List<Announcement> listfiltered = controller.getFilteredListing("Lease","",3);

        int position =0;
        for (Announcement lr : listfiltered){
            actual[position] = lr.getId();
            position++;
        }

        assertArrayEquals(expected, actual);

    }

* **Test 4:** Search announcements by Business and type



    @Test
    void ensureGetListingWorkAllParametersAllRooms() {
        announcementRepository.add(L1);
        announcementRepository.add(L2);
        announcementRepository.add(L3);
        announcementRepository.add(L4);
        announcementRepository.add(L5);
        announcementRepository.add(L6);
        announcementRepository.add(L7);
        announcementRepository.add(L8);

        Integer[] expected = {2,3,6};
        Integer[] actual = new Integer[expected.length];
        List<Announcement> listfiltered = controller.getFilteredListing("Lease","House",0);

        int position =0;
        for (Announcement lr : listfiltered){
            actual[position] = lr.getId();
            position++;
        }

        assertArrayEquals(expected, actual);

    }


* **Test 5:** Search all announcements in the System


    @Test
    void ensureGetListingWorkAllParametersAllBusinessAllTypeAllRooms() {
        announcementRepository.add(L1);
        announcementRepository.add(L2);
        announcementRepository.add(L3);
        announcementRepository.add(L4);
        announcementRepository.add(L5);
        announcementRepository.add(L6);
        announcementRepository.add(L7);
        announcementRepository.add(L8);

        Integer[] expected = {1,2,3,4,5,6,7,8};
        Integer[] actual = new Integer[expected.length];
        List<Announcement> listfiltered = controller.getFilteredListing("","",0);

        int position =0;
        for (Announcement lr : listfiltered){
            actual[position] = lr.getId();
            position++;
        }

        assertArrayEquals(expected, actual);

    }

* **Test 6:** Search announcements by rooms


    @Test
    void ensureGetListingWorkAllParametersAllBusinessAllTypeA() {
        announcementRepository.add(L1);
        announcementRepository.add(L2);
        announcementRepository.add(L3);
        announcementRepository.add(L4);
        announcementRepository.add(L5);
        announcementRepository.add(L6);
        announcementRepository.add(L7);
        announcementRepository.add(L8);

        Integer[] expected = {1,2,3,4,8};
        Integer[] actual = new Integer[expected.length];
        List<Announcement> listfiltered = controller.getFilteredListing("","",3);

        int position =0;
        for (Announcement lr : listfiltered){
            actual[position] = lr.getId();
            position++;
        }
        assertArrayEquals(expected, actual);

    }

* **Test 7:** Search announcements by Business


    @Test
    void ensureGetListingWorkAllParametersAllTypeAllRooms() {
        announcementRepository.add(L1);
        announcementRepository.add(L2);
        announcementRepository.add(L3);
        announcementRepository.add(L4);
        announcementRepository.add(L5);
        announcementRepository.add(L6);
        announcementRepository.add(L7);
        announcementRepository.add(L8);

        Integer[] expected = {1,2,3,5,6};
        Integer[] actual = new Integer[expected.length];
        List<Announcement> listfiltered = controller.getFilteredListing("Lease","",0);

        int position =0;
        for (Announcement lr : listfiltered){
            actual[position] = lr.getId();
            position++;
        }

        assertArrayEquals(expected, actual);

    }

* **Test 8:** Search announcements by type


    @Test
    void ensureGetListingWorkAllParametersAllBusinessAllRooms() {
        announcementRepository.add(L1);
        announcementRepository.add(L2);
        announcementRepository.add(L3);
        announcementRepository.add(L4);
        announcementRepository.add(L5);
        announcementRepository.add(L6);
        announcementRepository.add(L7);
        announcementRepository.add(L8);

        Integer[] expected = {2,3,6,8};
        Integer[] actual = new Integer[expected.length];
        List<Announcement> listfiltered = controller.getFilteredListing("","House",0);

        int position =0;
        for (Announcement lr : listfiltered){
            actual[position] = lr.getId();
            position++;
        }


        assertArrayEquals(expected, actual);

    }


* **Test 9:** Check if search is empty


    @Test
    void ensureGetListingWorkEmptyProperty() {

        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        AnnouncementRepository announcementRepository = new AnnouncementRepository();


        //Fill Announcement Category Repository
        Property p1 = new Apartment(1,"Porto", 70,1000,null,null,null, 3,1,1,null);
        Property p2 = new House(4,"Lisboa", 70,1000,null,null,null, 3,1,1,null,false,false,null);
        Property p3 = new House(5,"Lisboa", 70,1000,null,null,null, 3,1,1,null,false,false,null);

        Announcement L1=new Announcement(1,1,2,null,"Sale",p1);
        Announcement L2=new Announcement(2,1,1,null,"Sale",p2);
        Announcement L3=new Announcement(3,1,1,null,"Lease",p3);

        announcementRepository.add(L1);
        announcementRepository.add(L2);
        announcementRepository.add(L3);


        ListingController controller =
                new ListingController(announcementRepository);

        //Act
        List<Announcement> listfiltered = controller.getFilteredListing("Lease","Apartment",3);


        assertTrue(listfiltered.isEmpty());
    }


* **Test 10:** Check if it gets all Listings and order


    @Test
    void ensureGetAllListingWorkOrdered() {

        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        AnnouncementRepository announcementRepository = new AnnouncementRepository();


        //Fill Announcement Category Repository
        Property p1 = new Apartment(1,"Aveiro", 70,1000,null,null,null, 3,1,1,null);
        Property p2 = new House(4,"Porto", 70,1000,null,null,null, 3,1,1,null,false,false,null);
        Property p3 = new House(5,"Lisboa", 70,1000,null,null,null, 3,1,1,null,false,false,null);

        Announcement L1=new Announcement(1,1,3,null,"Sale",p1);
        Announcement L2=new Announcement(2,1,2,null,"Sale",p2);
        Announcement L3=new Announcement(3,1,1,null,"Lease",p3);

        announcementRepository.add(L1);
        announcementRepository.add(L2);
        announcementRepository.add(L3);

        Integer[] expected = {3,2,1};

        ListingController controller =
                new ListingController(announcementRepository);


        //Act

        List<Announcement> listfiltered = controller.getFilteredListing("","",0);
        announcementRepository.sortByPriceAsc(listfiltered);

        Integer[] actual = new Integer[expected.length];
        int position =0;
        for (Announcement lr : listfiltered){
            actual[position] = lr.getId();

            position++;
        }


        assertArrayEquals(expected, actual);
    }

### Tests for Announcements

* **Test 11:** Check if different objects with same values are the same


    @Test
    void EqualDifObjectsSameValues() {
        Announcement a1 = new Announcement(1,0,0,null,"Sale",null);
        Announcement a2 = new Announcement(1,0,0,null,"Sale",null);

        Announcement expect = a1;
        Announcement result = a2;

        assertEquals(expect, result);

    }


* **Test 12:** Check if filter by business, type and rooms works for a individual announcement works


    @Test
    void TestFoundFilteredProperty() {
        String business="Sale";
        String type="Apartment";
        int rooms=1;

        Property p1 = new Apartment(1,"Porto", 70,1000,null,null, null,1,1,1,null);
        Announcement l1 = new Announcement(1,0,0,null,"Sale",p1);

        assertTrue(l1.filterProperty(business,type,rooms));

    }



* **Test 13:** Check if same business return true

    
    @Test
    void testEqualsSameBusiness() {

        Property p1 = new Apartment(1,"Porto", 70,1000,null,null, null,1,1,1,null);
        Announcement l1 = new Announcement(1,0,0,null,"Sale",p1);

        assertTrue(l1.isBusiness("sale"));
    }

* **Test 14:** Check if diferent business return false


        @Test
        void testEqualsDifferentBusiness() {
            Property p1 = new Land(1,"Porto",70,1000,null,null,null);
            Announcement l1 = new Announcement(1,1,10,null,"Sale",p1);

            assertFalse(l1.isBusiness("Lease"));
        }


### Tests for AnnouncementRepository
* **Test 15:** Check if Annoucement List is sorted by upward price


        @Test
        void sortByPriceAsc() {
            double price1=1.0;
            double price2=4.0;
            double price3=10.0;
            double price4=7.0;
            double price5=4.0;
    
            Announcement L1=new Announcement(1,1,price1,null,null,null);
            Announcement L2=new Announcement(2,1,price2,null,null,null);
            Announcement L3=new Announcement(3,1,price3,null,null,null);
            Announcement L4=new Announcement(4,1,price4,null,null,null);
            Announcement L5=new Announcement(5,1,price5,null,null,null);
    
            AnnouncementRepository lr = new AnnouncementRepository();
    
            List<Announcement> listings = lr.getAnnouncements();
            listings.add(L1);
            listings.add(L2);
            listings.add(L3);
            listings.add(L4);
            listings.add(L5);
    
            lr.sortByPriceAsc(listings);
    
    
            Integer[] expected = {1,2,5,4,3};
            Integer[] actual = new Integer[5];
    
            int position=0;
            for(Announcement l : listings ){
                actual[position]=l.getId();
                position++;
            }

        assertArrayEquals(expected,actual);
        }

* **Test 16:** check if announcement list is sorted by descendent price


    @Test
    void sortByPriceDesc() {
        double price1=1.0;
        double price2=4.0;
        double price3=10.0;
        double price4=7.0;
        double price5=4.0;

        Announcement L1=new Announcement(1,1,price1,null,null,null);
        Announcement L2=new Announcement(2,1,price2,null,null,null);
        Announcement L3=new Announcement(3,1,price3,null,null,null);
        Announcement L4=new Announcement(4,1,price4,null,null,null);
        Announcement L5=new Announcement(5,1,price5,null,null,null);

        AnnouncementRepository lr = new AnnouncementRepository();

        List<Announcement> listings = lr.getAnnouncements();
        listings.add(L1);
        listings.add(L2);
        listings.add(L3);
        listings.add(L4);
        listings.add(L5);

        lr.sortByPriceDesc(listings);


        Double[] expected = {10.0,7.0,4.0,4.0,1.0};
        Double[] actual = new Double[5];

        int position=0;
        for(Announcement l : listings ){
            actual[position]=l.getPrice();
            position++;
        }

        assertArrayEquals(expected,actual);
    }

* **Test 17:** check if the announcement list is sorted by parish name alphabetically


        @Test
        void sortByParish() {
            double price1=1.0;
            double price2=2.0;

            Property p1 = new Apartment(1,"Porto",
                70,1000,null,null,null,
                1,1,1,null);

            Property p2 = new Apartment(1,"Lisboa",
                70,1000,null,null,null,
                1,1,1,null);

            Property p3 = new Apartment(1,"Evora",
                70,1000,null,null,null,
                1,1,1,null);

            Property p4 = new Apartment(1,"Algarve",
                70,1000,null,null,null,
                1,1,1,null);

            Announcement L1=new Announcement(1,1,price1,null,null,p1);
            Announcement L2=new Announcement(1,1,price2,null,null,p2);
            Announcement L3=new Announcement(1,1,price2,null,null,p3);
            Announcement L4=new Announcement(1,1,price2,null,null,p4);
    
            AnnouncementRepository lr = new AnnouncementRepository();
    
            List<Announcement> listings = lr.getAnnouncements();
            listings.add(L1);
            listings.add(L2);
            listings.add(L3);
            listings.add(L4);
    
    
            lr.sortByParish(listings);
    
    
            String[] expected = {"Algarve","Evora","Lisboa","Porto"};
            String[] actual = new String[expected.length];
    
            int position=0;
            for(Announcement l : listings ){
                actual[position]=l.getProperty().getLocation();
                position++;
            }
    
            assertArrayEquals(expected,actual);
        }


* **Test 18:** check if the announcement list is sorted by recent date


        @Test
        void sortByRecent() {

    
            Date d=new Date();
    
            Date date1 = new Date(d.getYear(), Calendar.AUGUST, 5);
            Date date2= new Date(d.getYear(), Calendar.JANUARY, 20);
            Date date3 = new Date(d.getYear(), Calendar.DECEMBER, 14);
            Date date4= new Date(d.getYear(), Calendar.MARCH, 13);
            Date date5 = new Date(d.getYear(), Calendar.AUGUST, 17);
    
    
            Announcement L1=new Announcement(1,1,1,date1,null,null);
            Announcement L2=new Announcement(2,1,0,date2,null,null);
            Announcement L3=new Announcement(3,1,0,date3,null,null);
            Announcement L4=new Announcement(4,1,0,date5,null,null);
            Announcement L5=new Announcement(5,1,0,date4,null,null);
    
            AnnouncementRepository lr = new AnnouncementRepository();
    
            List<Announcement> listings = lr.getAnnouncements();
            listings.add(L1);
            listings.add(L2);
            listings.add(L3);
            listings.add(L4);
            listings.add(L5);
    
            lr.sortByRecent(listings);
    
    
            Integer[] expected = {3,4,1,5,2};
            Integer[] actual = new Integer[5];
    
            int position=0;
            for(Announcement l : listings ){
                actual[position]=l.getId();
    
                position++;
            }
    
            assertArrayEquals(expected,actual);
    
    
        }








# 5. Construction (Implementation)

## Class LintingController
* Method to get all announcements available.
```java
public class ListingController {

    private AnnouncementRepository announcementRepository = null;

    public ListingController() {
        getAnnouncementRepository();

    }

    //Allows receiving the repositories as parameters for testing purposes
    public ListingController(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;

    }

    private AnnouncementRepository getAnnouncementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;

    }

    public List<Announcement> getListing() {

        AnnouncementRepository announcementRepo = getAnnouncementRepository();

        return announcementRepo.getAnnouncements();
    }

    public List<Announcement> getFilteredListing(String business,String type,int rooms) {

        AnnouncementRepository announcementRepo = getAnnouncementRepository();

        return announcementRepo.getPropertyList(business,type,rooms);
    }


    public void addAnnouncement(Announcement announcement) {

        AnnouncementRepository announcementRepo = getAnnouncementRepository();

        announcementRepo.add(announcement);
    }




}
```


### Class AnnouncementRepository
* Search throw repository and filter all announcements by business, type and rooms.
```java
public class AnnouncementRepository {

    private final List<Announcement> announcements = new ArrayList<>();

    
    public List<Announcement> getAnnouncements() {return announcements;}


    
    public List<Announcement> getPropertyList(String business, String type, int rooms) {
        if (business.isEmpty() && type.isEmpty() && rooms==0){
            return getAnnouncements();
        }
        List<Announcement> listfiltered = new ArrayList<>();
        for(Announcement List : announcements){
            if(List.filterProperty(business, type,  rooms)) {
                listfiltered.add(List);
            }
        }
        return List.copyOf(listfiltered);
    }

    
    
    public void sortByPriceAsc(List<Announcement> PropertyListing){
        PropertyListing.sort(criterio1);
    }

    
    Comparator<Announcement> criterio1 = new Comparator<Announcement>() {
        public int compare(Announcement L1, Announcement L2) {
            Double price1 = L1.getPrice();
            Double price2 = L2.getPrice();

            return price1.compareTo(price2);
        }
    };


    
    public void sortByPriceDesc(List<Announcement> PropertyListing){
        PropertyListing.sort(Collections.reverseOrder(criterio1));
    }

    
    public void sortByParish(List<Announcement> propertyAnnouncement){
        propertyAnnouncement.sort(criterio2);
    }

    Comparator<Announcement> criterio2 = new Comparator<Announcement>() {
        public int compare(Announcement L1, Announcement L2) {


            String s1= L1.getProperty().getLocation();
            String s2= L2.getProperty().getLocation();

            return s1.compareToIgnoreCase(s2);
        }
    };


    public void sortByRecent(List<Announcement> propertyAnnouncement){
        propertyAnnouncement.sort(criterio3);
    }
    Comparator<Announcement> criterio3 = new Comparator<Announcement>() {
        public int compare(Announcement L1, Announcement L2) {
            Date date1 = L1.getDate();
            Date date2 = L2.getDate();

            return Integer.compare(date2.compareTo(date1), 0);
        }
    };


    public Optional<Announcement> add(Announcement announcement) {


        Optional<Announcement> newAnnouncement = Optional.empty();

        boolean operationSuccess = false;

        if (validateAnnouncement(announcement)) {
            newAnnouncement = Optional.of(announcement.clone());
            operationSuccess = announcements.add(newAnnouncement.get());
        }

        if (!operationSuccess) {
            newAnnouncement= Optional.empty();
        }

        return  newAnnouncement;
    }

    private boolean announcementListDoNotContain(Announcement announcement) {
        return !announcements.contains(announcement);
    }

    private boolean validateAnnouncement(Announcement announcement) {

        return announcementListDoNotContain(announcement);
    }


}
```

### Class Announcement
* Checks if an Announcement respect the data that user chosed
```java


public class Announcement {

    private int id;
    private double comission;
    private double price;
    private Date date;

    private String businessType;

    private Property property;

    //implement agent
    private Agent agent;



    
    public Announcement(int id, double comission, double price, Date date, String business, Property property ){
        this.id = id;
        this.comission=comission;
        this.price=price;
        this.date=date;
        this.businessType= business;
        this.property=property;

    }


    
    public boolean filterProperty(String business, String type, int room){
        if (Objects.equals(business, "") && !Objects.equals(type, "") && room!=0){
            return property.isPropertyType(type) && property.numberRooms(room)& !property.isPropertyType("land");
        }else if (Objects.equals(business, "") && Objects.equals(type, "") && room!=0){
            return property.numberRooms(room) && !property.isPropertyType("land");
        }else if (!Objects.equals(business, "") && Objects.equals(type, "") && room!=0){
            return property.numberRooms(room)&& isBusiness(business)& !property.isPropertyType("land");
        }else if (!Objects.equals(business, "") && Objects.equals(type, "") && room==0){
            return isBusiness(business);
        }else if (Objects.equals(business, "") && !Objects.equals(type, "") && room==0){
            return property.isPropertyType(type);
        } else if (!Objects.equals(business, "") && !Objects.equals(type, "") && room==0){
            return property.isPropertyType(type)&&isBusiness(business);
        }else if (!Objects.equals(business, "") && !Objects.equals(type, "") && room!=0){
            if (type.equalsIgnoreCase("land")) {
                return isBusiness(business) && property.isPropertyType(type);
            } else {
                return isBusiness(business) && property.isPropertyType(type) && property.numberRooms(room);
            }
        }else {
            return false;
        }

    }

    
    public boolean isBusiness(String business){
        return this.businessType.equalsIgnoreCase(business);
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

  
    public int getId() {return id;}

    
    public void setId(int id) {this.id = id;}

    
    public double getComission() {return comission;}

    
    public void setComission(double comission) {this.comission = comission;}

    
    public double getPrice() {return price;}

    
    public void setPrice(double price) {this.price = price;}

    
    public Date getDate() {return date;}

    
    public void setDate(Date date) {this.date = date;}

    
    public Property getProperty() {return property;}

    
    public void setProperty(Property property) {this.property = property;}

    public Announcement clone() {
        return new Announcement(this.id,this.comission,this.price,this.date,this.businessType,property);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement announcement = (Announcement) o;
        return id == announcement.id && comission == announcement.comission && date==announcement.date && businessType.equals(announcement.businessType) && property==announcement.property;

    }



    public int hashCode() {
        return Objects.hash(id, comission, date, businessType, property);
    }
}
```




# 6. Integration and Demo 

* A new option on the Main menu options was added.
* Some announcements are bootstrapped while system starts.


# 7. Observations

Some classes as Announcement and Announcement Repository are becaming huge and harder to maintain.
There are some classes that repeat some actions of another classes.
Some classes can be simplifyed





