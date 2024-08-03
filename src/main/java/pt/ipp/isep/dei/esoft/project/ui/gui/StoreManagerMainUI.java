package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.ControllerDivideStores;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Teste controller.
 */
public class StoreManagerMainUI implements Initializable {

    /**
     * The Mnu account.
     */
    @FXML
    public Menu mnuAccount;
    /**
     * The Mnu logout.
     */
    @FXML
    public MenuItem mnuLogout;
    /**
     * The Mnu close.
     */
    @FXML
    public MenuItem mnuClose;
    /**
     * The Mnu visit request.
     */
    @FXML
    public Menu mnuVisitRequest;
    /**
     * The Mnu list sold announcements.
     */
    @FXML
    public MenuItem mnuListSoldAnnouncements;
    /**
     * The Label email footer.
     */
    @FXML
    public Label labelEmailFooter;

    private static final String MENU_ACCOUNT = "Account";
    private static final String MENU_LOGOUT = "Logout";

    private static final String MENU_FICHEIRO_SAIR = "Close";

    private static final String MENU_DEALS = "Deals";
    private static final String MENU_LIST_VISIT_REQUESTS = "List";
    private static final String MENU_DIVIDE_STORES = "Get Partitions by minimum difference";

    private static final String CABECALHO_ERRO_FATAL = "Fatal Error";
    
    private static final String CABECALHO_LOGOUT = "Logout success";

    private static final String TITULO_VISIT_REQUESTS = "List visit Requests";

    private static final String TITULO_MAIN_APP = "Titulo";

    private static final String MESSAGE_LOGOUT = "Logout successfully";
    /**
     * The Mnu divid stores.
     */
    public MenuItem mnuDividStores;
    /**
     * The Mnu divide store.
     */
    public Menu mnuDivideStore;
    /**
     * The Mnu linear.
     */
    public Menu mnuLinear;
    /**
     * The Mnu linear app.
     */
    public MenuItem mnuLinearApp;


    private AnnouncementController controller;


    private Stage mainAppStage;

    private LoginUserUI loginUserUI;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarControlos();

        Platform.runLater(() -> {
            controller = loginUserUI.getAppControler();
            labelEmailFooter.setText(controller.getClientFromSession());
        });
    }

    /**
     * Associar parent ui.
     *
     * @param loginUserUI the login user ui
     */
    public void associarParentUI(LoginUserUI loginUserUI) {
        this.loginUserUI = loginUserUI;
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
        return loginUserUI.getAuthControler();
    }


    @FXML
    private void mnuClose(ActionEvent event) {
        Window janela = mnuClose.getParentPopup().getOwnerWindow();

        janela.fireEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSE_REQUEST));
    }


    /**
     * Limpar gui.
     */
    public void limparGUI() {


    }


    @FXML
    private void mnuLogout(ActionEvent event) {

        try {
            AuthenticationController auth = loginUserUI.getAuthControler();
            auth.doLogout();

            mainAppStage = new Stage();
            mainAppStage.initModality(Modality.APPLICATION_MODAL);
            mainAppStage.setTitle(TITULO_MAIN_APP);
            mainAppStage.setResizable(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainApp.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            mainAppStage.setScene(scene);
            mainAppStage.sizeToScene();

            MainAppUI mainAppUI = loader.getController();

            // ESCREVER CORRETAMENETE ESTE ALERTA
            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MENU_LOGOUT,
                    CABECALHO_LOGOUT, MESSAGE_LOGOUT).showAndWait();
            mnuClose(event);

            mainAppStage.show();

        } catch (Exception ex) {

            AlertaUI.criarAlerta(Alert.AlertType.ERROR,
                    TITULO_VISIT_REQUESTS, CABECALHO_ERRO_FATAL, ex.getLocalizedMessage()).showAndWait();
            Platform.exit();
        }


    }




    private void iniciarControlos() {
        mnuLogout.setText(MENU_LOGOUT);
        mnuAccount.setText(MENU_ACCOUNT);
        mnuClose.setText(MENU_FICHEIRO_SAIR);
        mnuLinear.setText("Linear Regression");
        mnuLinearApp.setText("Open");

    }



    @FXML
    private void mnuLinearAppAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainAppUI.class.getResource("/fxml/LinearRegressionApp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Linear Regression App");
        stage.setScene(scene);
        stage.show();
    }
}



