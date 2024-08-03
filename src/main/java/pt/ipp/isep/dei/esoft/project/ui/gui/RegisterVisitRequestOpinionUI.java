package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Request;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequestOpinion;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * The type Register visit request opinion ui.
 */
public class RegisterVisitRequestOpinionUI implements Initializable {

    /**
     * The Btn submit.
     */
    public Button btnSubmit;
    /**
     * The Btn cancel.
     */
    public Button btnCancel;
    /**
     * The Grid.
     */
    public RowConstraints grid;
    private static final String LABEL_CLASSIFICATION = "Classification :";
    private static final String LABEL_OPINION = "Opinion :";
    private static final String LABEL_VISIT_REQUEST = "Visit Request :";
    private static final String BTN_ACCEPT = "Accept";
    private static final String BTN_REFUSE = "Refuse";
    private static final String BTN_CANCEL = "Cancel";

    private static final String TITULO_SEARCH = "Visit Requests";

    private static final String CABECALHO_VALUES_INVALIDOS = "Invalid Values";


    private static final String MESSAGE_ERROR_SEARCH = "Both inputs must be filled";
    private static final String MESSAGE_ERROR_SEARCH_LOGICAL = "Begin date must be lower than End date";

    private static final String MESSAGE_ERROR_EMPTY_LIST = "There aren´t any Visit Requests for the dates that you select";


    private static final String CABECALHO_ERRO_FATAL = "Erro Fatal";

    private static final String CABECALHO_EMPTY_LIST = "Empty Search";

    private static final String TITLE_SEARCH = "List of visit Requests";
    private static final String TITLE_OPINION = "Register opinion";
    private static final String CABECALHO_SUCESSO_OPINION = "Register opinion success";
    private static final String MESSAGE_SUCESSO_OPINION = "Your opinion about the visit request was successfully registered";

    private static final String CABECALHO_ERROR_EMPTY_OPINION = "Register opinion Failed";

    private static final String MESSAGE_ERROR_EMPTY_OPINION = "All the inputs must be filled";

    private static final String MESSAGE_ERROR_LENGHT_OPINION = "The opinion must be 200 characteres at minium";


    /**
     * The Lbl visit request.
     */
    public Label lblVisitRequest;
    /**
     * The Lbl classification.
     */
    public Label lblClassification;
    /**
     * The Combo visit.
     */
    public ComboBox comboVisit;
    /**
     * The Combo classification.
     */
    public ComboBox comboClassification;
    /**
     * The Lbl opinion.
     */
    public Label lblOpinion;
    /**
     * The Txt area opinion.
     */
    public TextArea txtAreaOpinion;


    /**
     * The Controller.
     */
    AnnouncementController controller;

    private AgentMainUI agentMainUI;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            iniciarControlos();
            controller=agentMainUI.getAppControler();
            iniciarControlosController();

            comboVisit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    comboClassification.setDisable(false);
                    txtAreaOpinion.setDisable(false);
                    btnSubmit.setDisable(false);
                } else {
                    comboClassification.setDisable(true);
                    txtAreaOpinion.setDisable(true);
                    btnSubmit.setDisable(false);
                }
            });
        });

    }


    /**
     * Associar parent ui.
     *
     * @param agentMainUI the agent main ui
     */
    public void associarParentUI(AgentMainUI agentMainUI) {
        this.agentMainUI = agentMainUI;
    }


    /**
     * Limpar gui.
     */
    public void limparGUI() {
        //dateStart.setValue(null);
        //dateEnd.setValue(null);

    }

    @FXML
    private void cancelAction(ActionEvent event) {
            ((Node) event.getSource()).getScene().getWindow().hide();
            ((Stage) btnCancel.getScene().getWindow()).hide();
    }


    /**
     * Gets app controler.
     *
     * @return the app controler
     */
    public AnnouncementController getAppControler() {
        return controller;
    }

    /**
     * Gets auth controler.
     *
     * @return the auth controler
     */
    public AuthenticationController getAuthControler() {
        return agentMainUI.getAuthControler();
    }



    private void iniciarControlos() {

        lblVisitRequest.setText(LABEL_VISIT_REQUEST);
        lblClassification.setText(LABEL_CLASSIFICATION);
        lblOpinion.setText(LABEL_OPINION);
        btnSubmit.setText(BTN_ACCEPT);
        btnCancel.setText(BTN_CANCEL);
        comboVisit.getItems().clear();
        comboClassification.getItems().clear();
        txtAreaOpinion.setScrollLeft(Double.MAX_VALUE);
        if (comboVisit.getValue()==null){
            comboClassification.setDisable(true);
            txtAreaOpinion.setDisable(true);
            btnSubmit.setDisable(true);
        }

        int index=0;
        for (String str : VisitRequestOpinion.getMyList()){
            index++;
            comboClassification.getItems().add(index + " - " + str);
        }
    }


    private void iniciarControlosController() {
        for (VisitRequest visitRequest : controller.getAllVisitRequestListForAgent()) {
            if (visitRequest.getVisitRequestOpinion() == null && visitRequest.isStateApproved() &&
                    dateConverter(visitRequest.getDate()).before(new Date())) {
                comboVisit.getItems().add(visitRequest);
            }
        }
        sortComboVisitItemsByDate();

        comboVisit.setCellFactory(param -> new ListCell<VisitRequest>() {
            @Override
            protected void updateItem(VisitRequest item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String dateText = dateFormat.format(dateConverter(item.getDate()));
                    setText(dateText.substring(0, 10)); // Display only the first 10 characters (date portion)
                }
            }
        });

        comboVisit.setConverter(new StringConverter<VisitRequest>() {
            @Override
            public String toString(VisitRequest visitRequest) {
                if (visitRequest != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String dateText = dateFormat.format(dateConverter(visitRequest.getDate()));
                    return dateText.substring(0, 10); // Display only the first 10 characters (date portion)
                }
                return null;
            }

            @Override
            public VisitRequest fromString(String string) {
                return null; // Not needed for display purposes
            }
        });


    }

    private void sortComboVisitItemsByDate() {
        Collections.sort(comboVisit.getItems(), new Comparator<VisitRequest>() {
            @Override
            public int compare(VisitRequest o1, VisitRequest o2) {
                Date date1 = dateConverter(o1.getDate());
                Date date2 = dateConverter(o2.getDate());
                return date1.compareTo(date2);
            }
        });
    }

    /**
     * Date converter date.
     *
     * @param dateString the date string
     * @return the date
     */
    public Date dateConverter(String dateString) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            try {
                return dateFormat.parse(dateString);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
    }


    @FXML
    private void submitAction(ActionEvent event) {
        String opinion = txtAreaOpinion.getText().trim();


        if (comboVisit.getValue()!=null && comboClassification.getValue()!=null && !txtAreaOpinion.getText().isEmpty()) {
            if (opinion.length() >= 200) {
                VisitRequest selectedVisitRequest = (VisitRequest) comboVisit.getValue();
                VisitRequestOpinion review = new VisitRequestOpinion(opinion, comboClassification.getValue().toString());
                selectedVisitRequest.setVisitRequestOpinion(review);

                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION,
                        TITLE_OPINION, CABECALHO_SUCESSO_OPINION, MESSAGE_SUCESSO_OPINION).showAndWait();

                cancelAction(event);
            } else {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR,
                        TITLE_OPINION, CABECALHO_ERROR_EMPTY_OPINION, MESSAGE_ERROR_LENGHT_OPINION).show();

            }
        }else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR,
                    TITLE_OPINION, CABECALHO_ERROR_EMPTY_OPINION, MESSAGE_ERROR_EMPTY_OPINION).show();

        }

    }

    /**
     * Sets visit request.
     *
     * @param selectedVisitRequest the selected visit request
     */
    public void setVisitRequest(VisitRequest selectedVisitRequest) {
        //visitRequest=selectedVisitRequest;
    }
}
