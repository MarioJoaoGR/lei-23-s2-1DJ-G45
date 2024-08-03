
package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.ControllerDivideStores;
import pt.ipp.isep.dei.esoft.project.domain.AlgorithmInfo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The type Divide set of all stores ui.
 */
public class DivideSetOfAllStoresUI implements Initializable {

    /**
     * The Lbl dif.
     */
    public Label lblDif;
    /**
     * The Lbl time.
     */
    public Label lblTime;
    /**
     * The Lbl p 1.
     */
    public Label lblP1;
    /**
     * The Txt p 1.
     */
    public TextArea txtP1;
    /**
     * The Lbl p 2.
     */
    public Label lblP2;
    /**
     * The Txt p 2.
     */
    public TextArea txtP2;
    /**
     * The Lbl soma 2.
     */
    public Label lblSoma2;
    /**
     * The Lbl soma 1.
     */
    public Label lblSoma1;
    private ControllerDivideStores controllerDivideStores;

    private NetworkManagerMainUI networkManagerMainUI;

    @FXML
    private Label lblSize;


    /**
     * Associar parent ui.
     *
     * @param networkManagerMainUI the network manager main ui
     */
    public void associarParentUI(NetworkManagerMainUI networkManagerMainUI) {
        this.networkManagerMainUI = networkManagerMainUI;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            controllerDivideStores = new ControllerDivideStores();
            getInfo();
        });

    }

    @FXML
    private void getInfo() {


        lblP1.setText("P1");

        AlgorithmInfo algorithmInfo = controllerDivideStores.getNrProperitesForEachAgency();

        //        String str = new String();

        List<String> array1Agencias = algorithmInfo.getList3();
        List<String> array2Agencias = algorithmInfo.getList4();
        List<Integer> arrayNrProperties = algorithmInfo.getList1();
        List<Integer> arrayNrProperties2 = algorithmInfo.getList2();
        long time = algorithmInfo.getTimeAlgorihtm();
        /*
        for (int i = 0; i < array1Agencias.size(); i++) {
            String agency = array1Agencias.get(i);
            int number = arrayNrProperties.get(i);
            str= str.concat(agency + "-" + number+"\n");

        }*/
        txtP1.setText(returnAgenciesAndNrPropertiesForLabel(array1Agencias,arrayNrProperties));


        lblP2.setText("P2");
        //str="";
        /*for (int i = 0; i < array2Agencias.size(); i++) {
            String agency = array2Agencias.get(i);
            int number = arrayNrProperties2.get(i);
            str= str.concat(agency + "-" + number+"\n");

        }*/
        txtP2.setText(returnAgenciesAndNrPropertiesForLabel(array2Agencias,arrayNrProperties2));


        lblSoma1.setText("Sum: " + somaLista(arrayNrProperties));
        lblSoma2.setText("Sum: " + somaLista(arrayNrProperties2));
        lblSize.setText("Input Size: "+(array1Agencias.size()+array2Agencias.size()));
        lblDif.setText("Difference: " + (Math.abs(somaLista(arrayNrProperties) - somaLista(arrayNrProperties2))));
        lblTime.setText("Time: " + time + " milliseconds ");


    }

    private static int somaLista(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }

    /**
     * Get controller divide stores controller divide stores.
     *
     * @return the controller divide stores
     */
    public ControllerDivideStores getControllerDivideStores(){

        return controllerDivideStores;

    }

    /**
     * Return agencies and nr properties for label string.
     *
     * @param list  the list
     * @param list2 the list 2
     * @return the string
     */
    public String returnAgenciesAndNrPropertiesForLabel(List<String>list, List<Integer> list2){
        String str="";
        for (int i = 0; i < list.size(); i++) {
            String agency = list.get(i);
            int number = list2.get(i);
            str= str.concat(agency + " Nr of Properties=" + number+"\n");

        }
    return str;
    }

}



