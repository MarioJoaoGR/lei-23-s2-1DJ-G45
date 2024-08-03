package pt.ipp.isep.dei.esoft.project.domain.Serialization;

import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Land;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Serialization class for reading and saving Repositories instance to/from a file.
 */
public class Serialization {

    /**
     * Reads data from a file and initializes the Repositories instance.
     *
     * @param filename the name of the file to read from
     * @throws IOException if an I/O error occurs
     */
    public static void readFromFile(String filename) throws IOException {
        File file = new File(filename);
        file.createNewFile();
        if (file.length() == 0) {

        }else {
            try {
                FileInputStream fileIn =new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Repositories rep = (Repositories) in.readObject();
                Repositories.setInstance(rep);
                in.close(); fileIn.close();
            } catch(IOException | ClassNotFoundException i) {
                i.printStackTrace(); return;
            }
        }
    }

    /**
     * Saves the Repositories instance to a file.
     *
     * @param fileName the name of the file to save to
     */
    public static void saveToFile(String fileName) {
        try
                (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            Repositories instance = Repositories.getInstance();
            objectOut.writeObject(instance.clone());

        } catch (IOException ignored) {
        }finally {
            System.exit(0);
        }
    }

}
