package pt.ipp.isep.dei.esoft.project.domain.Comparators;

import pt.ipp.isep.dei.esoft.project.domain.AnnouncementSold;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Represents a Bubble Sort algorithm implementation for sorting announcement data.
 */
public class BubbleSortAlgorithm implements SortingAnnouncementAlgorithm {



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

                Double area1 = (announcementSold1.getProperty().getArea());
                Double area2 = (announcementSold2.getProperty().getArea());

                if (area1.compareTo(area2) > 0) {
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

                Double area1 = (announcementSold1.getProperty().getArea());
                Double area2 = (announcementSold2.getProperty().getArea());

                if (area1.compareTo(area2) < 0) {
                    announcementSold.set(j, announcementSold2);
                    announcementSold.set(j + 1, announcementSold1);
                }
            }
        }
    }


}