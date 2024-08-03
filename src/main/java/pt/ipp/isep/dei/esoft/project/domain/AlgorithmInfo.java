package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The type Four lists.
 */
public class AlgorithmInfo implements Serializable {

    private List<Integer> list1;
    private List<Integer> list2;
    private List<String> list3;
    private List<String> list4;
    private  long timeAlgorihtm;
    /**
     * Instantiates a new Four lists.
     *
     * @param list1 the list 1
     * @param list2 the list 2
     * @param list3 the list 3
     * @param list4 the list 4
     */
    public AlgorithmInfo(List<Integer> list1, List<Integer> list2, List<String> list3, List<String> list4, long timeAlgorithm) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        this.timeAlgorihtm = timeAlgorithm;
    }

    /**
     * Gets list 1.
     *
     * @return the list 1
     */
    public List<Integer> getList1() {
        return list1;
    }

    /**
     * Gets list 3.
     *
     * @return the list 3
     */
    public List<String> getList3() {
        return list3;
    }

    /**
     * Gets list 4.
     *
     * @return the list 4
     */
    public List<String> getList4() {
        return list4;
    }

    /**
     * Gets algorithm time
     * @return algorithm time
     */
    public long getTimeAlgorihtm() {
        return timeAlgorihtm;
    }

    /**
     * Gets list 2.
     *
     * @return the list 2
     */
    public List<Integer> getList2() {
        return list2;
    }
}


