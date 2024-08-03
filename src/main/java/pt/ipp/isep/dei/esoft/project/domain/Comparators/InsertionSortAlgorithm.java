package pt.ipp.isep.dei.esoft.project.domain.Comparators;

import pt.ipp.isep.dei.esoft.project.domain.AnnouncementSold;


import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Represents an Insertion Sort algorithm implementation for sorting announcement data.
 */
public class InsertionSortAlgorithm implements SortingAnnouncementAlgorithm {



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

            while (j >= 0 && announcementSold.get(j).getProperty().getArea().compareTo(key.getProperty().getArea()) > 0) {
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

            while (j >= 0 && announcementSold.get(j).getProperty().getArea().compareTo(key.getProperty().getArea()) < 0) {
                announcementSold.set(j + 1, announcementSold.get(j));
                j = j - 1;
            }

            announcementSold.set(j + 1, key);
        }
    }
}