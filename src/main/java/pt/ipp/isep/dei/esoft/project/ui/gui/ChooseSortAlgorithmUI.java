package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.Comparators.BubbleSortAlgorithm;
import pt.ipp.isep.dei.esoft.project.domain.Comparators.InsertionSortAlgorithm;

import java.net.URL;

import java.util.ResourceBundle;

/**
 * The type Choose sort algorithm ui.
 */
public class ChooseSortAlgorithmUI implements Initializable {

    /**
     * The Btn sort.
     */
    @FXML
    public Button btnSort;
    /**
     * The Lbl algorithm.
     */
    public Label lblAlgorithm;
    /**
     * The Lbl order.
     */
    public Label lblOrder;
    /**
     * The Radio bubble.
     */
    public RadioButton radioBubble;
    /**
     * The Order.
     */
    public ToggleGroup Order;
    /**
     * The Radio asc.
     */
    public RadioButton radioAsc;
    /**
     * The Radio merge.
     */
    public RadioButton radioMerge;
    /**
     * The Algorithm.
     */
    public ToggleGroup Algorithm;
    /**
     * The Radio desc.
     */
    public RadioButton radioDesc;
    @FXML
    private Button btnCancel;


    private static final String LABEL_ALGORITHM = "Algorithm:";
    private static final String LABEL_ORDER = "Order:";

    private static final String BOTAO_SEARCH = "Sort";
    private static final String BOTAO_CANCEL = "Cancelar";

    private static final String MERGE = "InsertionSort";
    private static final String BUBBLE = "BubbleSort";
    private static final String ASCENDING = "Ascending";
    private static final String DESCENDING = "Descending";

    private static final String TITULO_ERROR = "Sort Error";

    private static final String CABECALHO_SORT_ERROR = "Sort Error";
    private static final String MESSAGE_SORT_ERROR = "A type of sort and Algorithm must be choose";


    /**
     * The Controller.
     */
    AnnouncementController controller;

    private ListDealsUI listDealsUI;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarControlos();
        Platform.runLater(() -> controller = listDealsUI.getAppController());

    }


    /**
     * Associar parent ui.
     *
     * @param listDealsUI the list deals ui
     */
    public void associarParentUI(ListDealsUI listDealsUI) {
        this.listDealsUI = listDealsUI;
    }


    @FXML
    private void cancelAction(ActionEvent event) {

            ((Node) event.getSource()).getScene().getWindow().hide();
            (btnCancel.getScene().getWindow()).hide();
    }


    /**
     * Gets app controler.
     *
     * @return the app controler
     */
    public AnnouncementController getAppControler() {
        return controller;
    }

    private void iniciarControlos() {

        lblAlgorithm.setText(LABEL_ALGORITHM);
        lblOrder.setText(LABEL_ORDER);
        btnSort.setText(BOTAO_SEARCH);
        btnCancel.setText(BOTAO_CANCEL);
        radioDesc.setText(DESCENDING);
        radioAsc.setText(ASCENDING);
        radioBubble.setText(BUBBLE);
        radioMerge.setText(MERGE);
        if (algorithm.equalsIgnoreCase(BUBBLE)){
            radioBubble.setSelected(true);
        }
        if (algorithm.equalsIgnoreCase(MERGE)){
            radioMerge.setSelected(true);
        }
        if (order.equalsIgnoreCase(ASCENDING)){
            radioAsc.setSelected(true);
        }
        if (order.equalsIgnoreCase(DESCENDING)){
            radioDesc.setSelected(true);
        }
    }



    private static  String algorithm="";
    private static String order="";

    /**
     * Sort action.
     *
     * @param actionEvent the action event
     */
    public void sortAction(ActionEvent actionEvent) {
        BubbleSortAlgorithm bubble = new BubbleSortAlgorithm();
        InsertionSortAlgorithm insert = new InsertionSortAlgorithm();


        if (Algorithm.getSelectedToggle()==null || Order.getSelectedToggle()==null){
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITULO_ERROR,
                    CABECALHO_SORT_ERROR, MESSAGE_SORT_ERROR).showAndWait();
        }else {
            if (radioBubble.isSelected()){
                algorithm=BUBBLE;
            }else {
                algorithm=MERGE;
            }

            if (radioAsc.isSelected()){
                order=ASCENDING;
            }else {
                order=DESCENDING;
            }
            switch (algorithm){
                case BUBBLE:
                    if (order.equalsIgnoreCase(ASCENDING)){
                        bubble.sort(controller.getListingSold());

                    }else {
                        bubble.sortDescending(controller.getListingSold());
                    }
                case MERGE:
                    if (order.equalsIgnoreCase(ASCENDING)){
                        insert.sort(controller.getListingSold());

                    }else {
                        insert.sortDescending(controller.getListingSold());
                    }
            }
            listDealsUI.atualizarListaTarefas();
            cancelAction(actionEvent);
        }

    }
}
