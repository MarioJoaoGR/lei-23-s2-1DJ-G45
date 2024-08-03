package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Brute force algorithm.
 */
public class BruteForceAlgorithm implements Serializable {


    /**
     * Gets partitions.
     *
     * @param matrix the matrix
     * @return the partitions
     */
    public AlgorithmInfo getPartitions(String[][] matrix) {

        int n = matrix.length;

        List<String> array1Agencias = new ArrayList<>();
        List<String> array2Agencias = new ArrayList<>();
        List<String> array1NextAgencias = new ArrayList<>();
        List<String> array2NextAgencias = new ArrayList<>();
        List<Integer> arrayNrProperties = new ArrayList<>();
        List<Integer> arrayNrProperties2 = new ArrayList<>();
        List<Integer> arrayNrNextProperties = new ArrayList<>();
        List<Integer> arrayNrNextProperties2 = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        int size = (1 << n) - 1;

        // percorrer todos os números

        for (int i = 0; i < size; i++) {


            String binary = Integer.toBinaryString(i + 1);
            binary = String.format("%" + n + "s", binary).replace(' ', '0');
            int index = 0;

            while (index < n) {

                char digitChar = binary.charAt(n - 1 - index);
                if (digitChar == '1') {
                    array1NextAgencias.add(matrix[index][0]);
                    arrayNrNextProperties.add(Integer.parseInt(matrix[index][1]));
                } else {
                    array2NextAgencias.add(matrix[index][0]);
                    arrayNrNextProperties2.add(Integer.parseInt(matrix[index][1]));
                }
                index++;


            }

            if (Math.abs(somaLista(arrayNrNextProperties)-somaLista(arrayNrNextProperties2)) < Math.abs(somaLista(arrayNrProperties)-somaLista(arrayNrProperties2)) || arrayNrProperties.isEmpty()) {
                array1Agencias = new ArrayList<>(array1NextAgencias);
                array2Agencias = new ArrayList<>(array2NextAgencias);
                arrayNrProperties = new ArrayList<>(arrayNrNextProperties);
                arrayNrProperties2 = new ArrayList<>(arrayNrNextProperties2);
            }

            deleteList(array1NextAgencias);
            deleteList(arrayNrNextProperties);
            deleteList(arrayNrNextProperties2);
            deleteList(array2NextAgencias);
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;

        //printingInformationOfBruteForceAlgorithm(array1Agencias, array2Agencias, arrayNrProperties, arrayNrProperties2);
        return new AlgorithmInfo(arrayNrProperties, arrayNrProperties2, array1Agencias, array2Agencias,time);
    }

    /**
     * Delete list.
     *
     * @param <T>  the type parameter
     * @param list the list
     */
    public static <T> void deleteList(List<T> list) {
        list.clear();
    }

    /**
     * Clear list.
     *
     * @param array the array
     */
    public static void clearList(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = 0; // Set each element to a default value (0 in this example)
        }
    }

    /**
     * Printing information of brute force algorithm.
     *
     * @param array1Agencias     the array 1 agencias
     * @param array2Agencias     the array 2 agencias
     * @param arrayNrProperties  the array nr properties
     * @param arrayNrProperties2 the array nr properties 2
     */
    public static void printingInformationOfBruteForceAlgorithm(List<String> array1Agencias, List<String> array2Agencias, List<Integer> arrayNrProperties, List<Integer> arrayNrProperties2) {
        System.out.println("P1-(Agencies-Nr of employees)");

        for (int i = 0; i < array1Agencias.size(); i++) {
            String agency = array1Agencias.get(i);
            int number = arrayNrProperties.get(i);
            System.out.println(agency + "-" + number);
        }
        System.out.println("P2-(Agencies-Nr of employees)");
        for (int i = 0; i < array2Agencias.size(); i++) {
            String agency = array2Agencias.get(i);
            int number = arrayNrProperties2.get(i);
            System.out.println(agency + "-" + number);
        }

        /*System.out.println("P2-");
        for (int i = 0; i < array2AgenciasEnrProperties.length; i++) {
            if (array2AgenciasEnrProperties[i][0] != null) {
                System.out.println(array2AgenciasEnrProperties[i][0] + "---------" + array2AgenciasEnrProperties[i][1]);
            }
        }*/
        System.out.println();
        System.out.println("soma partiçao1-->" + somaLista(arrayNrProperties));
        System.out.println("soma partiçao2-->" + somaLista(arrayNrProperties2));
        System.out.println("menor diferença = " + (Math.abs(somaLista(arrayNrProperties) - somaLista(arrayNrProperties2))));


    }


    /**
     * Soma lista int.
     *
     * @param list the list
     * @return the int
     */
    public static int somaLista(List<Integer> list) {
        int soma = 0;
        for (Integer i : list) {
            soma += i;
        }
        return soma;
    }


    /**
     * Count digits int.
     *
     * @param number the number
     * @return the int
     */
    public static int countDigits(long number) {
        if (number == 0) {
            return 1;
        }

        int count = 0;
        while (number != 0) {
            number = number / 10;
            count++;
        }

        return count;
    }


}
