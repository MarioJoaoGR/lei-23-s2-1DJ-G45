package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ImportControllerTest {

    @Test
    void validateCSV() {
        ImportController controller = new ImportController();
        File file = new File("email.txt");
        File file1 = new File("email.csv");
        assertFalse( controller.validateCSV(file));
        assertTrue( controller.validateCSV(file1));

    }

    @Test
    void validateEmpty() {
        ImportController controller = new ImportController();
        File file = new File("email.txt");
        assertTrue( controller.validateEmpty(file));

    }


}