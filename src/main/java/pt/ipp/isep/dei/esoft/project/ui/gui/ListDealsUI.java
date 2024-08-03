
package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementSold;
import pt.ipp.isep.dei.esoft.project.domain.Comparators.BubbleSortAlgorithm;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.io.IOException;
import java.net.URL;
import java.util.*;


/**
 * The type Teste controller.
 */
public class ListDealsUI implements Initializable {
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
    private Button btnSort;
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
    private static final String TITULO_LIST_DEALS = "List Deals";
    private static final String TITULO_OPEN_DEAL = "Deal";

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
    private NetworkManagerMainUI networkManagerMainUI;
    private OpenDealsUI openDealsUI;

    private Stage openDealsStage;


    //sortAlgorithm by configfile
    private String sortingAlgorithm;

    private List<VisitRequest> visitRequests = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Load the configuration file

        Platform.runLater(() -> {
            iniciarControlos();

            try {
                controller = networkManagerMainUI.getAppControler();
                auth = networkManagerMainUI.getAuthControler();
                loadAfterControllerSet();
                atualizarListaTarefas();
                lstViewTarefas.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                        openDeal();
                    }
                });

                lstViewTarefas.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        openDeal();
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
        return networkManagerMainUI.getAuthControler();
    }

    /**
     * Gets app controller.
     *
     * @return the app controller
     */
    public AnnouncementController getAppController() {
        return networkManagerMainUI.getAppControler();
    }


    /**
     * Associar parent ui.
     *
     * @param networkManagerMainUI the network manager main ui
     */
    public void associarParentUI(NetworkManagerMainUI networkManagerMainUI) {
        this.networkManagerMainUI = networkManagerMainUI;

    }


    /**
     * Atualizar lista tarefas.
     */
    public void atualizarListaTarefas() {
        lstViewTarefas.getItems().setAll(controller.getDealsList());
    }




    @FXML
    private void mnuFileCloseAction(ActionEvent event) {
        Window janela = mnuFileClose.getParentPopup().getOwnerWindow();
        janela.fireEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSE_REQUEST));
    }


    private void iniciarControlos() {
        mnuFile.setText(MENU_FILE);
        mnuFileClose.setText(MENU_FILE_CLOSE);


    }


    private void loadAfterControllerSet() {
        visitRequests = controller.getVisitRequestListsWaiting();
        sortAnnouncements();
        labelEmailFooter.setText(auth.getSessionEmail());

    }

    /**
     * Sort announcements.
     */
    public void sortAnnouncements() {
        BubbleSortAlgorithm bubbleSortAlgorithm = new BubbleSortAlgorithm();
        bubbleSortAlgorithm.sort(controller.getListingSold());

    }


    @FXML
    private void openDeal() {
        // Get the selected item from the list view
        String selectedItem = lstViewTarefas.getSelectionModel().getSelectedItem();

        // Check if an item is selected
        if (selectedItem != null) {
            try {
                openDealsStage = new Stage();
                openDealsStage.initModality(Modality.APPLICATION_MODAL);

                openDealsStage.setTitle(TITULO_OPEN_DEAL);
                openDealsStage.setResizable(false);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OpenDeals.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                openDealsStage.setScene(scene);
                openDealsStage.sizeToScene();

                openDealsStage.setOnHiding(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        openDealsUI.limparGUI();
                    }
                });

                openDealsUI = loader.getController();

                AnnouncementSold announcementSold = getSelectedAnnouncementSold();

                openDealsUI.setAnouncementSold(announcementSold);
                openDealsUI.associarParentUI(this);

                openDealsStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private AnnouncementSold getSelectedAnnouncementSold() {
        // Get the selected item index from the list view
        int selectedIndex = lstViewTarefas.getSelectionModel().getSelectedIndex();

        // Check if a valid item is selected
        if (selectedIndex >= 0 && selectedIndex < controller.getListingSold().size()) {
            return controller.getListingSold().get(selectedIndex);
        }

        return null;
    }

    /**
     * Mnu sort.
     *
     * @param actionEvent the action event
     */
    public void mnuSort(ActionEvent actionEvent) {
        Stage chooseSortAlgorithmStage;
        ChooseSortAlgorithmUI chooseSortAlgorithm;
        try {
            chooseSortAlgorithmStage = new Stage();
            chooseSortAlgorithmStage.initModality(Modality.APPLICATION_MODAL);

            chooseSortAlgorithmStage.setTitle(TITULO_LIST_DEALS);
            chooseSortAlgorithmStage.setResizable(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChooseSortAlgorithm.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            chooseSortAlgorithmStage.setScene(scene);
            chooseSortAlgorithmStage.sizeToScene();

            chooseSortAlgorithm = loader.getController();


            chooseSortAlgorithm.associarParentUI(this);

            chooseSortAlgorithmStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




