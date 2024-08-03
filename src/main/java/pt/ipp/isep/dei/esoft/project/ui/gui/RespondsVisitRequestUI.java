package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.SendEmail;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * The type Responds visit request ui.
 */
public class RespondsVisitRequestUI implements Initializable {

    /**
     * The Btn accept.
     */
    @FXML
    public Button btnAccept;
    /**
     * The Btn refuse.
     */
    @FXML
    public Button btnRefuse;
    /**
     * The Lbl client name.
     */
    @FXML
    public Label lblClientName;
    /**
     * The Lbl date.
     */
    @FXML
    public Label lblDate;
    /**
     * The Lbl time slot.
     */
    @FXML
    public Label lblTimeSlot;
    /**
     * The Lbl announcement.
     */
    @FXML
    public Label lblAnnouncement;
    /**
     * The Lbl client name result.
     */
    @FXML
    public Label lblClientNameResult;

    /**
     * The Lbl date result.
     */
    @FXML
    public Label lblDateResult;
    /**
     * The Lbl time slot result.
     */
    @FXML
    public Label lblTimeSlotResult;
    /**
     * The Lbl announcement result.
     */
    @FXML
    public Label lblAnnouncementResult;
    /**
     * The Btn cancel.
     */
    @FXML
    public Button btnCancel;
    /**
     * The Grid.
     */
    @FXML
    public RowConstraints grid;
    private static final String LABEL_DATE = "Date :";
    private static final String LABEL_TIMESLOT = "Timeslot :";

    private static final String LABEL_CLIENT_NAME = "Client Name :";
    private static final String LABEL_ANNOUNCEMENT = "Announcement :";
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

    /**
     * The Controller.
     */
    AnnouncementController controller;

    private ListVisitRequestUI listVisitRequestUI;

    private VisitRequest visitRequest;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarControlos();
        Platform.runLater(() -> {
            iniciarVisitRequest();

            controller=listVisitRequestUI.getAppController();
        });


    }


    /**
     * Associar parent ui.
     *
     * @param listVisitRequestUI the list visit request ui
     */
    public void associarParentUI(ListVisitRequestUI listVisitRequestUI) {
        this.listVisitRequestUI = listVisitRequestUI;
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
        return listVisitRequestUI.getAuthControler();
    }



    private void iniciarControlos() {
        lblAnnouncement.setText(LABEL_ANNOUNCEMENT);
        lblDate.setText(LABEL_DATE);
        lblClientName.setText(LABEL_CLIENT_NAME);
        lblTimeSlot.setText(LABEL_TIMESLOT);
        btnAccept.setText(BTN_ACCEPT);
        btnRefuse.setText(BTN_REFUSE);
        btnCancel.setText(BTN_CANCEL);
    }

    private void iniciarVisitRequest() {
        lblDateResult.setText(visitRequest.getDate());
        lblAnnouncementResult.setText(visitRequest.getAnnouncementForVisitRequest().toStringfx());
        lblClientNameResult.setText(visitRequest.getUserName() );

        String str = "";
        for (String s :visitRequest.getTimeSlot()){
            str = str.concat(s + "\n");
        }
        lblTimeSlotResult.setText(visitRequest.getTimeSlot().toString());
    }


    /**
     * Refuse action.
     *
     * @param event the event
     */
    public void refuseAction(ActionEvent event) {
        controller.refuseVisitRequest(visitRequest);
        cancelAction(event);
        listVisitRequestUI.atualizarListaTarefas();
        controller.sendEmail(visitRequest.getAnnouncementForVisitRequest(),visitRequest,false);
    }


    @FXML
    private void acceptAction(ActionEvent event) {
        controller.acceptVisitRequest(visitRequest);
        cancelAction(event);
        listVisitRequestUI.atualizarListaTarefas();
        controller.sendEmail(visitRequest.getAnnouncementForVisitRequest(),visitRequest,true);
    }

    /**
     * Sets visit request.
     *
     * @param selectedVisitRequest the selected visit request
     */
    public void setVisitRequest(VisitRequest selectedVisitRequest) {
        visitRequest=selectedVisitRequest;
    }
}
