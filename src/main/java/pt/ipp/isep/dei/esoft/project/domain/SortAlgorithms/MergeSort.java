package pt.ipp.isep.dei.esoft.project.domain.SortAlgorithms;

import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * MergeSort class provides a merge sort algorithm for sorting VisitRequest objects based on their date.
 */
public class MergeSort {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Sorts the list of VisitRequest objects in ascending order based on their date using the merge sort algorithm.
     *
     * @param visitRequests the list of VisitRequest objects to be sorted
     */
    public void sort(List<VisitRequest> visitRequests) {
        int n = visitRequests.size();
        VisitRequest[] temp = new VisitRequest[n];
        mergeSort(visitRequests, 0, n - 1, temp);
    }

    /**
     * Recursively applies the merge sort algorithm to the subarray of VisitRequest objects.
     *
     * @param visitRequests the list of VisitRequest objects to be sorted
     * @param left          the index of the leftmost element in the subarray
     * @param right         the index of the rightmost element in the subarray
     * @param temp          an auxiliary array to store temporary values during the merge process
     */
    private void mergeSort(List<VisitRequest> visitRequests, int left, int right, VisitRequest[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(visitRequests, left, mid, temp);
            mergeSort(visitRequests, mid + 1, right, temp);
            merge(visitRequests, left, mid, right, temp);
        }
    }

    /**
     * Merges two sorted subarrays of VisitRequest objects into a single sorted subarray.
     *
     * @param visitRequests the list of VisitRequest objects to be sorted
     * @param left          the index of the leftmost element of the first subarray
     * @param mid           the index of the rightmost element of the first subarray
     * @param right         the index of the rightmost element of the second subarray
     * @param temp          an auxiliary array to store temporary values during the merge process
     */
    private void merge(List<VisitRequest> visitRequests, int left, int mid, int right, VisitRequest[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = visitRequests.get(i);
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            try {
                Date date1 = dateFormat.parse(temp[i].getDate());
                Date date2 = dateFormat.parse(temp[j].getDate());

                if (date1.compareTo(date2) <= 0) {
                    visitRequests.set(k, temp[i]);
                    i++;
                } else {
                    visitRequests.set(k, temp[j]);
                    j++;
                }
                k++;
            } catch (ParseException e) {
                // Handle the parse exception if necessary
                e.printStackTrace();
            }
        }

        while (i <= mid) {
            visitRequests.set(k, temp[i]);
            i++;
            k++;
        }
    }
}
