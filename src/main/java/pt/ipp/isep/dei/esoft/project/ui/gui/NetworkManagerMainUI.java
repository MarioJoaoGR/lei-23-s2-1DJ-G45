
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

import java.net.URL;
import java.util.ResourceBundle;


/**
 * The type Teste controller.
 */
public class NetworkManagerMainUI implements Initializable {

    /**
     * The Mnu account.
     */
    public Menu mnuAccount;
    /**
     * The Mnu logout.
     */
    public MenuItem mnuLogout;

    /**
     * The Mnu close.
     */
    public MenuItem mnuClose;
    /**
     * The Mnu visit request.
     */
    public Menu mnuVisitRequest;
    /**
     * The Mnu list sold announcements.
     */
    public MenuItem mnuListSoldAnnouncements;
    /**
     * The Label email footer.
     */
    public Label labelEmailFooter;

    private static final String MENU_ACCOUNT = "Account";
    private static final String MENU_LOGOUT = "Logout";

    private static final String MENU_FICHEIRO_SAIR = "Close";

    private static final String MENU_DEALS = "Deals";
    private static final String MENU_LIST_VISIT_REQUESTS = "List";
    private static final String MENU_REGISTER_OPINION = "Register Opinion";
    private static final String MENU_DIVIDE_STORES = "Partitions";
    private static final String MENU_DIVIDE_STORES_GET = "Get";

    private static final String CABECALHO_ERRO_FATAL = "Fatal Error";
    private static final String CABECALHO_EMPTY_LIST = "Deals list empty";
    private static final String MESSAGE_EMPTY_LIST = "There aren't any Deals to show";
    private static final String TITULO_LIST_DEALS = "List Deals";

    private static final String CABECALHO_LOGOUT = "Logout success";

    private static final String TITULO_VISIT_REQUESTS = "List visit Requests";
    private static final String TITULO_OPINION = "Register opinion";
    private static final String TITULO_OPEN_DEAL = "Deal";

    private static final String TITULO_MAIN_APP = "MainApp";

    private static final String MESSAGE_LOGOUT = "Logout successfully";
    /**
     * The Mnu divid stores.
     */
    public MenuItem mnuDividStores;
    /**
     * The Mnu divide store.
     */
    public Menu mnuDivideStore;


    private AnnouncementController controller;
    private ControllerDivideStores controllerDivideStores;

    private SearchVisitRequestsUI searchVisitRequestsUI;
    private ListVisitRequestUI listVisitRequestUI;

    private ListDealsUI listDealsUI;
    private MainAppUI mainAppUI;
    private  Stage DivideStoresStage;
    private Stage registerVisitRequestOpinionStage;
    private RegisterVisitRequestOpinionUI registerVisitRequestOpinionUI;
    private Stage searchRequestStage;

    private Stage ListDealsStage;

    private Stage mainAppStage;
    private DivideSetOfAllStoresUI divideSetOfAllStoresUI;
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


    /**
     * Get divide controller controller divide stores.
     *
     * @return the controller divide stores
     */
    public ControllerDivideStores getDivideController(){
        return  divideSetOfAllStoresUI.getControllerDivideStores();
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

            mainAppUI = loader.getController();

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


    @FXML
    private void mnuListDeals(ActionEvent event) {
        try {
            ListDealsStage = new Stage();
            ListDealsStage.initModality(Modality.APPLICATION_MODAL);
            ListDealsStage.setTitle(TITULO_LIST_DEALS);
            ListDealsStage.setResizable(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListDeals.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            ListDealsStage.setScene(scene);
            ListDealsStage.sizeToScene();


            listDealsUI = loader.getController();
            listDealsUI.associarParentUI(this);

            if (controller.getListingSold().isEmpty()){
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION,
                        TITULO_LIST_DEALS, CABECALHO_EMPTY_LIST, MESSAGE_EMPTY_LIST).show();
            }else {
                ListDealsStage.show();

            }

        } catch (Exception ex) {

            AlertaUI.criarAlerta(Alert.AlertType.ERROR,
                    TITULO_LIST_DEALS, CABECALHO_ERRO_FATAL, ex.getLocalizedMessage()).showAndWait();
            Platform.exit();
        }
    }



    private void iniciarControlos() {
        mnuLogout.setText(MENU_LOGOUT);
        mnuAccount.setText(MENU_ACCOUNT);
        mnuClose.setText(MENU_FICHEIRO_SAIR);
        mnuVisitRequest.setText(MENU_DEALS);
        mnuListSoldAnnouncements.setText(MENU_LIST_VISIT_REQUESTS);
        mnuDivideStore.setText(MENU_DIVIDE_STORES);
        mnuDividStores.setText(MENU_DIVIDE_STORES_GET);

    }

    @FXML
    private void mnuDivideStores(){
        try {
            DivideStoresStage = new Stage();
            DivideStoresStage.initModality(Modality.APPLICATION_MODAL);
            DivideStoresStage.setTitle(MENU_DIVIDE_STORES);
            DivideStoresStage.setResizable(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DivideSetOfAllStoresUI.fxml"));
            Parent root = loader.load();



            Scene scene = new Scene(root);
            DivideStoresStage.setScene(scene);
            DivideStoresStage.sizeToScene();
            divideSetOfAllStoresUI= loader.getController();
            divideSetOfAllStoresUI.associarParentUI(this);
            DivideStoresStage.show();
        } catch (Exception ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MENU_DIVIDE_STORES, CABECALHO_ERRO_FATAL, ex.getLocalizedMessage()).showAndWait();
            Platform.exit();
        }
    }


}




