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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * The type Teste controller.
 */
public class AgentMainUI implements Initializable {
    /**
     * The Mnu logout.
     */
    public MenuItem mnuLogout;
    /**
     * The Label email footer.
     */
    public Label labelEmailFooter;
    /**
     * The Mnu register opinion.
     */
    public MenuItem mnuRegisterOpinion;
    @FXML
    private Menu mnuAccount;
    @FXML
    private MenuItem mnuClose;
    @FXML
    private Menu mnuVisitRequest;

    @FXML
    private MenuItem mnuVisitRequestSearch;
    @FXML

    private static final String MENU_ACCOUNT = "Account";
    private static final String MENU_LOGOUT = "Logout";

    private static final String MENU_FICHEIRO_SAIR = "Close";

    private static final String MENU_VISIT_REQUESTS = "Visit Requests";
    private static final String MENU_LIST_VISIT_REQUESTS = "List";
    private static final String MENU_REGISTER_OPINION = "Register Opinion";

    private static final String CABECALHO_ERRO_FATAL = "Fatal Error";
    private static final String CABECALHO_EMPTY_LIST = "Visit requests empty";
    private static final String MESSAGE_EMPTY_LIST = "There aren't any Visit request to register an opinion";

    private static final String CABECALHO_LOGOUT = "Logout success";

    private static final String TITULO_VISIT_REQUESTS = "List visit Requests";
    private static final String TITULO_OPINION = "Register opinion";

    private static final String TITULO_MAIN_APP = "Main";

    private static final String MESSAGE_LOGOUT = "Logout successfully";



    private AnnouncementController controller;

    private SearchVisitRequestsUI searchVisitRequestsUI;

    private RegisterVisitRequestOpinionUI registerVisitRequestOpinionUI;
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

            Stage mainAppStage = new Stage();
            mainAppStage.initModality(Modality.APPLICATION_MODAL);
            mainAppStage.setTitle(TITULO_MAIN_APP);
            mainAppStage.setResizable(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainApp.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            mainAppStage.setScene(scene);
            mainAppStage.sizeToScene();


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
    private void mnuSearchVisitRequests(ActionEvent event) {

        try {
            Stage searchRequestStage = new Stage();
        searchRequestStage.initModality(Modality.APPLICATION_MODAL);
        searchRequestStage.setTitle(TITULO_VISIT_REQUESTS);
        searchRequestStage.setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SearchVisitRequest.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        searchRequestStage.setScene(scene);
        searchRequestStage.sizeToScene();

        searchRequestStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                searchVisitRequestsUI.limparGUI();
            }
        });

        searchVisitRequestsUI = loader.getController();
        searchVisitRequestsUI.associarParentUI(this);
        searchRequestStage.show();

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
        mnuVisitRequest.setText(MENU_VISIT_REQUESTS);
        mnuVisitRequestSearch.setText(MENU_LIST_VISIT_REQUESTS);
        mnuRegisterOpinion.setText(MENU_REGISTER_OPINION);

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
     * Mnu register opinion.
     *
     * @param actionEvent the action event
     */
    public void mnuRegisterOpinion(ActionEvent actionEvent) {
        int count = 0;

        for (VisitRequest visitRequest : controller.getAllVisitRequestListForAgent()){
            if (visitRequest.getVisitRequestOpinion()==null && visitRequest.isStateApproved() && dateConverter(visitRequest.getDate()).before(new Date()) ){
                count++;
            }
        }

        if (count>0){
            try {
                Stage registerVisitRequestOpinionStage = new Stage();
                registerVisitRequestOpinionStage.initModality(Modality.APPLICATION_MODAL);
                registerVisitRequestOpinionStage.setTitle(TITULO_VISIT_REQUESTS);
                registerVisitRequestOpinionStage.setResizable(false);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterVisitRequestOpinion.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                registerVisitRequestOpinionStage.setScene(scene);
                registerVisitRequestOpinionStage.sizeToScene();

                registerVisitRequestOpinionStage.setOnHiding(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        registerVisitRequestOpinionUI.limparGUI();
                    }
                });

                registerVisitRequestOpinionUI = loader.getController();

                registerVisitRequestOpinionUI.associarParentUI(this);

                registerVisitRequestOpinionStage.show();


            } catch (Exception ex) {

                AlertaUI.criarAlerta(Alert.AlertType.ERROR,
                        TITULO_VISIT_REQUESTS, CABECALHO_ERRO_FATAL, ex.getLocalizedMessage()).showAndWait();
                Platform.exit();
            }

        }else {

            //FAZER TEXTO ALERTA
            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION,
                    TITULO_OPINION, CABECALHO_EMPTY_LIST, MESSAGE_EMPTY_LIST).showAndWait();


        }


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
}



