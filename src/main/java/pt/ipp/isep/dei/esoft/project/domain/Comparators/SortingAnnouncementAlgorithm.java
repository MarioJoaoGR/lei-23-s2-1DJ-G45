package pt.ipp.isep.dei.esoft.project.domain.Comparators;

import pt.ipp.isep.dei.esoft.project.domain.AnnouncementSold;

import java.util.List;

/**
 * Represents a sorting algorithm for announcementSold objects.
 */
public interface SortingAnnouncementAlgorithm {
    /**
     * Sorts the list of announcementSold objects in ascending order.
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
