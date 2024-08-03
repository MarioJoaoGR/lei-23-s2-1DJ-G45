package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

public class BruteForceAlgorithmTests {



    @Test
    void testlength3 (){
            String[][] m = {{"a","9"},{"a","28"}, {"a","32"},{"d","11"}, {"e","5"},{"e","2"},{"e","69"},{"e","20"},{"e","12"},{"e","35"},{"e", "3"},{"e", "7"},{"e","11"},{"e","2"},{"e", "5"},{"e", "12"},{"e", "6"},{"e", "6"},{"e", "6"},{"e", "13"},{"e", "12"}};
             BruteForceAlgorithm bruteForceAlgorithm = new BruteForceAlgorithm();
               AlgorithmInfo b =   bruteForceAlgorithm.getPartitions(m);
            System.out.println( b.getTimeAlgorihtm());
            BruteForceAlgorithm.printingInformationOfBruteForceAlgorithm(b.getList3(),b.getList4(),b.getList1(),b.getList2());
    }


    /*
    @Test
    void testlength6 (){
        BruteForceAlgorithm bruteForceAlgorithm = new BruteForceAlgorithm();
        String[][] m= {{"a","1321" },{"b","112" },{"c","31" },{"a","1" },{"b","2" },{"c","3" },{"a","1321" },{"b","112" },{"c","31" },{"a","1" },{"b","2" },{"c","3" },{"a","1321" },{"b","112" },{"c","31" },{"a","1" },{"b","2" },{"c","3" },{"a","1321" },{"b","112" },{"c","31" },{"a","1" },{"b","2" },{"c","3" },{"a","1321" },{"b","112" },{"c","31" },{"a","1" },{"b","2" },{"c","3" } };

        bruteForceAlgorithm.getPartitions(m);
        AlgorithmInfo b =   bruteForceAlgorithm.getPartitions(m);
        System.out.println( b.getTimeAlgorihtm());
        BruteForceAlgorithm.printingInformationOfBruteForceAlgorithm(b.getList3(),b.getList4(),b.getList1(),b.getList2());

    }

    */

    /*@Test
    void testlength9 (){
        String[][] m= {{"a","1" }};
        BruteForceAlgorithm bruteForceAlgorithm = new BruteForceAlgorithm();
        bruteForceAlgorithm.getPartitions(m);
        String[][] a= {{"a","0" }};
        bruteForceAlgorithm.getPartitions(a);

    }*/


    @Test
    void testlength30 (){
        String[][] m= {{"a","1" }};
        BruteForceAlgorithm bruteForceAlgorithm = new BruteForceAlgorithm();
        bruteForceAlgorithm.getPartitions(m);
        String[][] a= {{"a","0" }};
        bruteForceAlgorithm.getPartitions(a);

    }







}
