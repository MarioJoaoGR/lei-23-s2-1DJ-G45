package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LandTest {

    @Test
    void ensureLandIsCreatedSuccessfully() {
        Land l1 = new Land("poto",1,1,null,null);
    }

}