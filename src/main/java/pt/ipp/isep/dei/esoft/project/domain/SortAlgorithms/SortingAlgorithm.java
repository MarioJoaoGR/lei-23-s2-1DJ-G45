package pt.ipp.isep.dei.esoft.project.domain.SortAlgorithms;

import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.util.List;

/**
 * SortingAlgorithm interface represents a sorting algorithm for sorting VisitRequest objects.
 */
public interface SortingAlgorithm {

    /**
     * Sorts the list of VisitRequest objects.
     *
     * @param visitRequests the list of VisitRequest objects to be sorted
     */
    void sort(List<VisitRequest> visitRequests);
}
