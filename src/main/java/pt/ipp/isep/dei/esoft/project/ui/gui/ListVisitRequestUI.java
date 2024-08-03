package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementAvailable;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.domain.SortAlgorithms.MergeSort;
import pt.ipp.isep.dei.esoft.project.domain.SortAlgorithms.BubbleSort;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * The type Teste controller.
 */
public class ListVisitRequestUI implements Initializable {
    /**
     * The Lbl start date.
     */
    public Label lblStartDate;
    /**
     * The Lbl end date.
     */
    public Label lblEndDate;
    /**
     * The Label email footer.
     */
    public Label labelEmailFooter;
    @FXML
    private Menu mnuFile;
    @FXML
    private MenuItem mnuFileSaveTxt;
    @FXML
    private MenuItem mnuFileSaveBinary;
    @FXML
    private MenuItem mnuFileClose;

    @FXML
    private ListView<String> lstViewTarefas;

    private static final String MENU_FILE = "File";
    private static final String MENU_FILE_SAVE_TEXT = "Save as text";
    private static final String MENU_FILE_SAVE_BIN = "Save as binary";
    private static final String MENU_FILE_CLOSE = "Close";

    private static final String START_DATE = "Start Date:";
    private static final String END_DATE = "End Date:";

    private static final String CABECALHO_ERRO_FATAL = "Fatal Error";

    private static final String TITLE_ALERT_VISIT_REQUESTS = "List of Visit Request";
    private static final String TITLE_VISIT_REQUESTS = "List of Visit Request";



    //Can be implemented
    private static final String CABECALHO_GUARDAR_FICHEIRO_TEXTO = "Guardar lista num ficheiro de texto";
    private static final String CONTEXTO_CONFIRMAR_GUARDAR_FICHEIRO_TEXTO = "Deseja guardar a lista num ficheiro de texto?";
    private static final String CONTEXTO_SUCESSO_GUARDAR_FICHEIRO_TEXTO = "Ficheiro de texto gravado com sucesso";
    private static final String CONTEXTO_INSUCESSO_GUARDAR_FICHEIRO_TEXTO = "Não foi possível gravar o ficheiro de texto";

    private static final String CABECALHO_GUARDAR_FICHEIRO_BINARIO = "Guardar lista num ficheiro binário";
    private static final String CONTEXTO_CONFIRMAR_GUARDAR_FICHEIRO_BINARIO = "Deseja guardar a lista num ficheiro binário?";
    private static final String CONTEXTO_SUCESSO_GUARDAR_FICHEIRO_BINARIO = "Ficheiro binário gravado com sucesso";
    private static final String CONTEXTO_INSUCESSO_GUARDAR_FICHEIRO_BINARIO = "Não foi possível gravar o ficheiro binário";

    //Controllers
    private AnnouncementController controller;
    private AuthenticationController auth;

    //UI
    private SearchVisitRequestsUI searchVisitRequestsUI;
    private RespondsVisitRequestUI respondsVisitRequestUI;

    private Stage respondsVisitRequestStage;

    //Dates
    private String dateStart;
    private String dateEnd;

    //sortAlgorithm by configfile
    private String sortingAlgorithm;

    private List<VisitRequest> visitRequests = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Load the configuration file
        loadConfigProperties();

        Platform.runLater(() -> {
            iniciarControlos();

            try {
                controller = searchVisitRequestsUI.getAppControler();
                auth = searchVisitRequestsUI.getAuthControler();
                loadAfterControllerSet();
                atualizarListaTarefas();
                lstViewTarefas.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                        openRespondsVisitRequest();
                    }
                });

                lstViewTarefas.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        openRespondsVisitRequest();
                    }
                });

            } catch (Exception ex) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR,
                        TITLE_ALERT_VISIT_REQUESTS, CABECALHO_ERRO_FATAL, ex.getLocalizedMessage()).showAndWait();
                Platform.exit();
            }
        });



    }


    /**
     * Gets auth controler.
     *
     * @return the auth controler
     */
    public AuthenticationController getAuthControler() {
        return searchVisitRequestsUI.getAuthControler();
    }

    /**
     * Gets app controller.
     *
     * @return the app controller
     */
    public AnnouncementController getAppController() {
        return searchVisitRequestsUI.getAppControler();
    }


    /**
     * Associar parent ui.
     *
     * @param searchVisitRequestsUI the search visit requests ui
     */
    public void associarParentUI(SearchVisitRequestsUI searchVisitRequestsUI) {
        this.searchVisitRequestsUI = searchVisitRequestsUI;

    }

    /**
     * Gets data.
     *
     * @param dateStart the date start
     * @param dateEnd   the date end
     */
    public void getData(String dateStart,String dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }


    /**
     * Atualizar lista tarefas.
     */
    public void atualizarListaTarefas() {
        lstViewTarefas.getItems().setAll(controller.getVisitRequestList());
        if (controller.getListingAvailable().isEmpty()) {
            lstViewTarefas.getItems().clear();
        }
    }


    /**
     * On showing file.
     */
    public void onShowingFile() {
        if (lstViewTarefas.getItems().isEmpty()){
            mnuFileSaveBinary.setDisable(true);
            mnuFileSaveTxt.setDisable(true);
        }
    }



    @FXML
    private void mnuFileCloseAction(ActionEvent event) {
        Window janela = mnuFileClose.getParentPopup().getOwnerWindow();
        janela.fireEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSE_REQUEST));
    }


    private void iniciarControlos() {
        lblStartDate.setText(START_DATE+" "+dateStart);
        lblEndDate.setText(END_DATE+" "+dateEnd);
        mnuFile.setText(MENU_FILE);
        mnuFileClose.setText(MENU_FILE_CLOSE);


    }


    /**
     * Load config properties.
     */
    public void loadConfigProperties() {
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            sortingAlgorithm = prop.getProperty("sorting_algorithm");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void loadAfterControllerSet() {
        visitRequests = controller.getVisitRequestListsWaiting();
        sortVisitRequests();
        controller.setSortByDate(visitRequests);
        labelEmailFooter.setText(auth.getSessionEmail());

    }

    /**
     * Sort visit requests.
     */
    public void sortVisitRequests() {
        if (sortingAlgorithm.equalsIgnoreCase("bubble_sort")) {
            BubbleSort bubbleSort = new BubbleSort();
            bubbleSort.sort(visitRequests);
        } else if (sortingAlgorithm.equalsIgnoreCase("merge_sort")) {
            MergeSort mergeSort = new MergeSort();
            mergeSort.sort(visitRequests);
        }
    }


    @FXML
    private void openRespondsVisitRequest() {
        // Get the selected item from the list view
        String selectedItem = lstViewTarefas.getSelectionModel().getSelectedItem();

        // Check if an item is selected
        if (selectedItem != null) {
            try {
                respondsVisitRequestStage = new Stage();
                respondsVisitRequestStage.initModality(Modality.APPLICATION_MODAL);

                respondsVisitRequestStage.setTitle(TITLE_VISIT_REQUESTS);
                respondsVisitRequestStage.setResizable(false);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RespondsVisitRequest.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                respondsVisitRequestStage.setScene(scene);
                respondsVisitRequestStage.sizeToScene();

                respondsVisitRequestStage.setOnHiding(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        respondsVisitRequestUI.limparGUI();
                    }
                });

                respondsVisitRequestUI = loader.getController();

                VisitRequest selectedVisitRequest = getSelectedVisitRequest(); // Implement this method to get the VisitRequest object

                respondsVisitRequestUI.setVisitRequest(selectedVisitRequest);
                respondsVisitRequestUI.associarParentUI(this);

                respondsVisitRequestStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private VisitRequest getSelectedVisitRequest() {
        // Get the selected item index from the list view
        int selectedIndex = lstViewTarefas.getSelectionModel().getSelectedIndex();

        // Check if a valid item is selected
        if (selectedIndex >= 0 && selectedIndex < visitRequests.size()) {
            return visitRequests.get(selectedIndex);
        }

        return null;
    }

}



