package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;


import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * The type Search visit requests ui.
 */
public class SearchVisitRequestsUI implements Initializable {

    /**
     * The Order.
     */
    @FXML
    public ToggleGroup Order;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnCancel;
    @FXML
    private Label lblStartDate;
    @FXML
    private Label lblEndDate;
    @FXML
    private DatePicker dateStart;
    @FXML
    private DatePicker dateEnd;

    private static final String LABEL_START_DATE = "Begin date";
    private static final String LABEL_END_DATE = "End date";

    private static final String BOTAO_SEARCH = "Search Visit Requests";
    private static final String BOTAO_CANCEL = "Cancelar";

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
    private Stage ListRequestStage;

    private AgentMainUI agentMainUI;



    @Override
    public void initialize(URL url, ResourceBundle rb) {iniciarControlos();}


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
        dateStart.setValue(null);
        dateEnd.setValue(null);

    }

    @FXML
    private void cancelAction(ActionEvent event) {

            ((Node) event.getSource()).getScene().getWindow().hide();
            (btnCancel.getScene().getWindow()).hide();
    }

    @FXML
    private void searchAction(ActionEvent event) {
        controller = agentMainUI.getAppControler();


        if (dateStart.getValue()==null || dateEnd.getValue()==null ){
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITULO_SEARCH,
                    CABECALHO_VALUES_INVALIDOS, MESSAGE_ERROR_SEARCH).show();
        }else if (dateStart.getValue().isAfter(dateEnd.getValue())){
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITULO_SEARCH,
                    CABECALHO_VALUES_INVALIDOS, MESSAGE_ERROR_SEARCH_LOGICAL).show();
        }else {
            try {

                ListRequestStage = new Stage();
                ListRequestStage.initModality(Modality.APPLICATION_MODAL);

                ListRequestStage.setTitle(TITLE_SEARCH);
                ListRequestStage.setResizable(false);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListVisitRequests.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                ListRequestStage.setScene(scene);
                ListRequestStage.sizeToScene();


                ListVisitRequestUI listVisitRequestUI = loader.getController();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                listVisitRequestUI.getData(dateStart.getValue().format(formatter),dateEnd.getValue().format(formatter));
                listVisitRequestUI.associarParentUI(this);

            } catch (Exception ex) {

                AlertaUI.criarAlerta(Alert.AlertType.ERROR,
                        TITLE_SEARCH, CABECALHO_ERRO_FATAL, ex.getLocalizedMessage()).showAndWait();
                Platform.exit();
            }

            controller.getVisitRequestListForAgent(asDate(dateStart.getValue()),asDate(dateEnd.getValue()));

            if (controller.isVisitRequestListEmpty()){
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION,
                        TITLE_SEARCH, CABECALHO_EMPTY_LIST, MESSAGE_ERROR_EMPTY_LIST).show();
            }else {
                ListRequestStage.show();
                cancelAction(event);
            }

        }

    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return dateStart.getValue();
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return dateEnd.getValue();
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


    private Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    private void iniciarControlos() {

        formatter(dateStart);
        formatter(dateEnd);
        dateStart.setShowWeekNumbers(false);
        dateEnd.setShowWeekNumbers(false);
        dateEnd.setEditable(false);
        dateStart.setEditable(false);
        lblEndDate.setText(LABEL_END_DATE);
        lblStartDate.setText(LABEL_START_DATE);
        btnSearch.setText(BOTAO_SEARCH);
        btnCancel.setText(BOTAO_CANCEL);
    }



    private void formatter(DatePicker data){
        String pattern = "dd-MM-yyyy";

        data.setConverter(new StringConverter<>() {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }



}
