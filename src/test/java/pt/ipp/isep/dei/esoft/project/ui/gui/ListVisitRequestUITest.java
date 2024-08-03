package pt.ipp.isep.dei.esoft.project.ui.gui;

import com.sun.javafx.binding.StringFormatter;
import jdk.jshell.execution.Util;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.SortAlgorithms.BubbleSort;
import pt.ipp.isep.dei.esoft.project.domain.SortAlgorithms.MergeSort;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ListVisitRequestUITest {
    Agent a1 = new Agent("a1",1111,1,"pwd",1,"agent@this.app");
    Agent a2 = new Agent("a2@this.app","pwd");


    VisitRequest visitRequest1 = new VisitRequest("08-06-2023",null,"a1","agent@this.app",a1);
    VisitRequest visitRequest2 = new VisitRequest("09-06-2023",null,"user2","user2@this.app",a2);
    VisitRequest visitRequest3 = new VisitRequest("12-06-2023",null,"user3","user3@this.app",a2);
    VisitRequest visitRequest4 = new VisitRequest("11-06-2023",null,"a1","agent@this.app",a1);

    Land l1 = new Land("porto",11,1,null ,new Owner());

    Commission commission= new Commission(1,"percentage");

    Date date = new Date();
    AnnouncementAvailable aV1 = new AnnouncementAvailable(commission,1,date,"sale",l1,a1);
    AnnouncementAvailable aV2 = new AnnouncementAvailable(commission,2,date,"lease",l1,a2);
    AnnouncementAvailable aV3 = new AnnouncementAvailable(commission,3,date,"sale",l1,a1);
    List<VisitRequest> v1=new ArrayList<>();
    List<VisitRequest> v2=new ArrayList<>();
    List<VisitRequest> v3=new ArrayList<>();


    @Test
    void ensureGetVisitRequestListForAgentWorksForDate() throws ParseException {
        AnnouncementRepository repository = new AnnouncementRepository();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        v1.add(visitRequest1);
        v2.add(visitRequest2);
        v2.add(visitRequest3);
        v3.add(visitRequest4);

        aV1.setVisitRequests(v1);
        aV2.setVisitRequests(v2);
        aV3.setVisitRequests(v3);

        repository.addAvailable(aV1);
        repository.addAvailable(aV2);
        repository.addAvailable(aV3);


        List<VisitRequest> expected = new ArrayList<>();
        expected.add(visitRequest1);
        expected.add(visitRequest4);

        List<VisitRequest> list = repository.getVisitRequestListForAgent(a1.getMail(),dateFormat.parse("05-06-2023"),dateFormat.parse("11-06-2023"));


        assertTrue(expected.equals(list));



    }

    @Test
    void ensureSortAlgoithmWorks(){
        AnnouncementRepository repository = new AnnouncementRepository();

        v1.add(visitRequest1);
        v2.add(visitRequest2);
        v2.add(visitRequest3);
        v3.add(visitRequest4);

        aV1.setVisitRequests(v1);
        aV2.setVisitRequests(v2);
        aV3.setVisitRequests(v3);

        repository.addAvailable(aV1);
        repository.addAvailable(aV2);
        repository.addAvailable(aV3);


        List<VisitRequest> expected1 = new ArrayList<>();
        expected1.add(visitRequest1);
        expected1.add(visitRequest2);
        expected1.add(visitRequest4);
        expected1.add(visitRequest3);


        List<VisitRequest> list = repository.getVisitRequest();


        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(list);

        assertEquals(list,expected1);

        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(list);
        assertEquals(list,expected1);



    }

}