# US 017 - List all deals made


# 4. Tests 
### Tests for Announcement Controller

**Test 1:** Check if Announcements are getting listed correctly


     @Test
    public void testGetListingSoldnull() {

        AnnouncementRepository announcementRepo = new AnnouncementRepository();



        List<AnnouncementSold> result = announcementRepo.getAnnouncementsSold();


        assertNotNull(result);

    }

    public void testGetListingSold() {
        AnnouncementRepository announcementRepo = new AnnouncementRepository();

        List<AnnouncementSold> result = announcementRepo.getAnnouncementsSold();

        assertNotNull(result, "Result must not be null");
    }





**Test 2:** Ensure get Deals list works

            @Test
    public void testGetDealsList() {
        
        List<AnnouncementSold> announcementSoldList = new ArrayList<>();

        
        AnnouncementSold announcementSold1 = new AnnouncementSold();
        announcementSoldList.add(announcementSold1);

        AnnouncementSold announcementSold2 = new AnnouncementSold();
        announcementSoldList.add(announcementSold2);

        
        AnnouncementRepository yourClass = new AnnouncementRepository();

        
        announcementRepository.ge(announcementSoldList);

        
        String[] result = announcementRepository.getAnnouncementsSold();

      
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(announcementSold1.toString(), result[0]);
        assertEquals(announcementSold2.toString(), result[1]);
    }
    }


    }
	



# 5. Construction (Implementation)



## Class AnnouncementController
```java
    public String[] getDealsList() {
    String[] array = new String[getListingSold().size()];
    for (int i = 0; i < getListingSold().size(); i++) {
    AnnouncementSold announcement = getListingSold().get(i);
    String dealString = announcement.toString();
    array[i] = dealString;
    }

        return array;
    }
```

## Class Comparators
```java

    public class BubbleSortAlgorithm implements SortingAnnouncementAlgorithm {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


    /**
     * Sorts the list of announcementSold objects in ascending order based on the sale date.
     *
     * @param announcementSold The list of announcementSold objects to be sorted.
     */
    public void sort(List<AnnouncementSold> announcementSold) {
        int n = announcementSold.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                AnnouncementSold announcementSold1 = announcementSold.get(j);
                AnnouncementSold announcementSold2 = announcementSold.get(j + 1);

                Date date1 = (announcementSold1.getSaleDate());
                Date date2 = (announcementSold2.getSaleDate());

                if (date1.compareTo(date2) > 0) {
                    announcementSold.set(j, announcementSold2);
                    announcementSold.set(j + 1, announcementSold1);
                }
            }
        }
    }


    /**
     * Sorts the list of announcementSold objects in descending order based on the sale date.
     *
     * @param announcementSold The list of announcementSold objects to be sorted.
     */
    public void sortDescending(List<AnnouncementSold> announcementSold) {
        int n = announcementSold.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                AnnouncementSold announcementSold1 = announcementSold.get(j);
                AnnouncementSold announcementSold2 = announcementSold.get(j + 1);

                Date date1 = (announcementSold1.getSaleDate());
                Date date2 = (announcementSold2.getSaleDate());

                if (date1.compareTo(date2) < 0) {
                    announcementSold.set(j, announcementSold2);
                    announcementSold.set(j + 1, announcementSold1);
                }
            }
        }
    }


    public class InsertionSortAlgorithm implements SortingAnnouncementAlgorithm {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Sorts the list of announcementSold objects in ascending order based on the sale date.
     *
     * @param announcementSold The list of announcementSold objects to be sorted.
     */
    public void sort(List<AnnouncementSold> announcementSold) {
        int n = announcementSold.size();

        for (int i = 1; i < n; ++i) {
            AnnouncementSold key = announcementSold.get(i);
            int j = i - 1;

            while (j >= 0 && announcementSold.get(j).getSaleDate().compareTo(key.getSaleDate()) > 0) {
                announcementSold.set(j + 1, announcementSold.get(j));
                j = j - 1;
            }

            announcementSold.set(j + 1, key);
        }
    }

    /**
     * Sorts the list of announcementSold objects in descending order based on the sale date.
     *
     * @param announcementSold The list of announcementSold objects to be sorted.
     */
    public void sortDescending(List<AnnouncementSold> announcementSold) {
        int n = announcementSold.size();

        for (int i = 1; i < n; ++i) {
            AnnouncementSold key = announcementSold.get(i);
            int j = i - 1;

            while (j >= 0 && announcementSold.get(j).getSaleDate().compareTo(key.getSaleDate()) < 0) {
                announcementSold.set(j + 1, announcementSold.get(j));
                j = j - 1;
            }

            announcementSold.set(j + 1, key);
        }
    }

    public interface SortingAnnouncementAlgorithm {
    /**
    *    Sorts the list of announcementSold objects in ascending order.
    *
     * @param announcementsold The list of announcementSold objects to be sorted.
    */
     void sort(List<AnnouncementSold> announcementsold);

      /**
       * Sorts the list of announcementSold objects in descending order.
       *
       * @param announcementsold The list of announcementSold objects to be sorted.
       */
      void sortDescending(List<AnnouncementSold> announcementsold);
  }



}


```


# 6. Integration and Demo 

* A new option on the Agent Menu in javafx was added






