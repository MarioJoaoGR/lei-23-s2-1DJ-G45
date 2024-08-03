package pt.ipp.isep.dei.esoft.project.domain.SortAlgorithms;

import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * BubbleSort class implements the SortingAlgorithm interface and provides a bubble sort algorithm for sorting VisitRequest objects based on their date.
 */
public class BubbleSort implements SortingAlgorithm {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Sorts the list of VisitRequest objects in ascending order based on their date using the bubble sort algorithm.
     *
     * @param visitRequests the list of VisitRequest objects to be sorted
     */
    public void sort(List<VisitRequest> visitRequests) {
        int n = visitRequests.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                VisitRequest visit1 = visitRequests.get(j);
                VisitRequest visit2 = visitRequests.get(j + 1);

                try {
                    Date date1 = dateFormat.parse(visit1.getDate());
                    Date date2 = dateFormat.parse(visit2.getDate());

                    if (date1.compareTo(date2) > 0) {
                        visitRequests.set(j, visit2);
                        visitRequests.set(j + 1, visit1);
                    }
                } catch (ParseException e) {
                    // Handle the parse exception if necessary
                    e.printStackTrace();
                }
            }
        }
    }


}