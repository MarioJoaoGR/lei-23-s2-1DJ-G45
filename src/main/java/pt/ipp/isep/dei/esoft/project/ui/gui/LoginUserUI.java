package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Login user ui.
 */
public class LoginUserUI implements Initializable {

    

    private static final String LABEL_EMAIL = "Email";
    private static final String LABEL_PASSWORD = "Password";

    private static final String BOTAO_LOGIN = "Login";
    private static final String BOTAO_CANCEL = "Cancelar";

    private static final String TITULO_LOGIN = "Login";

    private static final String TITULO_AGENT = "Agent Menu";
    private static final String TITULO_NETWORK = "Network Manager Menu";
    private static final String TITULO_STORE_MANAGER = "Store Manager Menu";


    private static final String CABECALHO_INVALID_LOGIN = "Login Error";
    private static final String CABECALHO_LOGIN_SUCCESS = "Login Success";


    private static final String MESSAGE_ERROR_LOGIN = "Invalid Credentials ";
    private static final String MESSAGE_LOGIN_SUCCESS = "Login successfully";


    private static final String CABECALHO_ERRO_FATAL = "Fatal Error";


    /**
     * The Btn login.
     */
    public Button btnLogin;
    /**
     * The Btn cancel.
     */
    public Button btnCancel;
    /**
     * The Lbl email.
     */
    public Label lblEmail;
    /**
     * The Lbl password.
     */
    public Label lblPassword;

    /**
     * The Txt field email.
     */
    public TextField txtFieldEmail;
    /**
     * The Password field.
     */
    public javafx.scene.control.PasswordField passwordField;

    /**
     * The Controller.
     */
    AnnouncementController controller;
    /**
     * The Auth.
     */
    AuthenticationController auth;

    private Stage MenuStage;
    private Stage MenuStage1;

    private AgentMainUI agentMainUI;
    private NetworkManagerMainUI networkManagerMainUI;
    private StoreManagerMainUI storeManagerMainUI;

    private MainAppUI mainAppUI;




    @Override
    public void initialize(URL url, ResourceBundle rb) {iniciarControlos();}


    /**
     * Limpar gui.
     */
    public void limparGUI() {
        txtFieldEmail.clear();
        passwordField.clear();

    }

    @FXML
    private void cancelAction(ActionEvent event) {

            ((Node) event.getSource()).getScene().getWindow().hide();
            ((Stage) btnCancel.getScene().getWindow()).hide();
    }

    @FXML
    private void loginAction(ActionEvent event) {
        auth = new AuthenticationController();

        String path="";
        auth.doLogin(txtFieldEmail.getText(),passwordField.getText());

        if (auth.isLogin()){
            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_LOGIN,
                    CABECALHO_LOGIN_SUCCESS, MESSAGE_LOGIN_SUCCESS).showAndWait();


            if (auth.getUserRoles().size()==1){

                switch (auth.getUserRoles().get(0).getDescription()){
                    case AuthenticationController.ROLE_AGENT:

                        try{

                            controller=mainAppUI.getAppControler();

                            MenuStage = new Stage();
                            MenuStage.initModality(Modality.APPLICATION_MODAL);
                            MenuStage.setTitle(TITULO_AGENT);
                            MenuStage.setResizable(false);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AgentMain.fxml"));
                            Parent root = loader.load();

                            Scene scene = new Scene(root);
                            MenuStage.setScene(scene);
                            MenuStage.sizeToScene();

                            MenuStage.setOnHiding(new EventHandler<WindowEvent>() {
                                @Override
                                public void handle(WindowEvent event) {
                                    agentMainUI.limparGUI();
                                }
                            });

                            agentMainUI = loader.getController();
                            agentMainUI.associarParentUI(this);

                            MenuStage.show();
                            cancelAction(event);
                            mainAppUI.mnuCloseAction(event);

                        } catch (Exception ex) {

                            AlertaUI.criarAlerta(Alert.AlertType.ERROR,
                                    TITULO_AGENT, CABECALHO_ERRO_FATAL, ex.getLocalizedMessage()).showAndWait();
                            Platform.exit();
                        };
                        break;

                    case AuthenticationController.ROLE_NETWORK_MANAGER:
                        try{

                            controller=mainAppUI.getAppControler();

                            MenuStage = new Stage();
                            MenuStage.initModality(Modality.APPLICATION_MODAL);
                            MenuStage.setTitle(TITULO_NETWORK);
                            MenuStage.setResizable(false);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NetworkManagerMain.fxml"));
                            Parent root = loader.load();

                            Scene scene = new Scene(root);
                            MenuStage.setScene(scene);
                            MenuStage.sizeToScene();

                            MenuStage.setOnHiding(new EventHandler<WindowEvent>() {
                                @Override
                                public void handle(WindowEvent event) {
                                    networkManagerMainUI.limparGUI();
                                }
                            });

                            networkManagerMainUI = loader.getController();
                            networkManagerMainUI.associarParentUI(this);

                            MenuStage.show();
                            cancelAction(event);
                            mainAppUI.mnuCloseAction(event);

                        } catch (Exception ex) {

                            AlertaUI.criarAlerta(Alert.AlertType.ERROR,
                                    TITULO_NETWORK, CABECALHO_ERRO_FATAL, ex.getLocalizedMessage()).showAndWait();
                            Platform.exit();
                        };
                        break;

                    case AuthenticationController.ROLE_STORE_MANAGER:
                        try{

                            controller=mainAppUI.getAppControler();

                            MenuStage = new Stage();
                            MenuStage.initModality(Modality.APPLICATION_MODAL);
                            MenuStage.setTitle(TITULO_STORE_MANAGER);
                            MenuStage.setResizable(false);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StoreManagerMain.fxml"));
                            Parent root = loader.load();

                            Scene scene = new Scene(root);
                            MenuStage.setScene(scene);
                            MenuStage.sizeToScene();

                            MenuStage.setOnHiding(new EventHandler<WindowEvent>() {
                                @Override
                                public void handle(WindowEvent event) {
                                    storeManagerMainUI.limparGUI();
                                }
                            });

                            storeManagerMainUI = loader.getController();
                            storeManagerMainUI.associarParentUI(this);

                            MenuStage.show();
                            cancelAction(event);
                            mainAppUI.mnuCloseAction(event);

                        } catch (Exception ex) {

                            AlertaUI.criarAlerta(Alert.AlertType.ERROR,
                                    TITULO_STORE_MANAGER, CABECALHO_ERRO_FATAL, ex.getLocalizedMessage()).showAndWait();
                            Platform.exit();
                        };
                        break;
                }

            }

        }else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITULO_LOGIN,
                    CABECALHO_INVALID_LOGIN, MESSAGE_ERROR_LOGIN).show();
        }



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
        return auth;
    }


    /**
     * Associar parent ui.
     *
     * @param mainAppUI the main app ui
     */
    public void associarParentUI(MainAppUI mainAppUI) {
        this.mainAppUI = mainAppUI;
    }



    private void iniciarControlos() {


        lblEmail.setText(LABEL_EMAIL);
        lblPassword.setText(LABEL_PASSWORD);
        btnLogin.setText(BOTAO_LOGIN);
        btnCancel.setText(BOTAO_CANCEL);
    }



}
