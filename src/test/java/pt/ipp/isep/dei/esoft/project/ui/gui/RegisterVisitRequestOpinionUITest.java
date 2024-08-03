package pt.ipp.isep.dei.esoft.project.ui.gui;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Adapter.ExternalModuleAdapterCSV;
import pt.ipp.isep.dei.esoft.project.domain.BruteForceAlgorithm;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequestOpinion;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegisterVisitRequestOpinionUITest {
    @Test
    void ensureOpinionLenght(){
        String opinion = "facilis dicat ultrices deserunt dicit nunc ubique equidem deserunt quam sapientem voluptatum natoque delicata saperet mus voluptatibus elaboraret mauris sententiae elaboraret mauris sententiae elabora";
        String opinion1 = "facilis dicat ultrices deserunt dicit nunc ubique equidem deserunt quam sapientem voluptatum natoque delicata saperet mus voluptatibus elaboraret mauris sententiae ";

        System.out.println(opinion.length());
        assertTrue(opinion.length()>=200);
        assertFalse(opinion1.length()>=200);

    }


    @Test
    void ensureClassificationScale(){
        List<String> classification = VisitRequestOpinion.getMyList();

        assertEquals(5,classification.size());

    }

    @Test
    void ensureDuplicateEntriesFail(){
        VisitRequest visitRequest = new VisitRequest("08-06-2023",null,"user","user@this.app",null);

        VisitRequestOpinion opinion = new VisitRequestOpinion("opinion","Likely");
        VisitRequestOpinion opinion1 = new VisitRequestOpinion("opinion1","Likely");

        assertTrue( visitRequest.setVisitRequestOpinion(opinion));
        assertFalse(visitRequest.setVisitRequestOpinion(opinion));
        assertTrue( visitRequest.setVisitRequestOpinion(opinion1));
    }

/*    @Test
    void teste(){
        ExternalModuleAdapterCSV adapterCSV = new ExternalModuleAdapterCSV();

        BruteForceAlgorithm algorithm = new BruteForceAlgorithm();

        String[][] matrix = adapterCSV.readFile1("/Users/miguel/Desktop/teste/6.csv");

        algorithm.getPartitions(matrix);

    }*/



}