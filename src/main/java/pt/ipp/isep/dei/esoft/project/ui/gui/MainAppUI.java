package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;

//import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Teste controller.
 */
public class MainAppUI implements Initializable {

    /**
     * The Mnu login.
     */
    public MenuItem mnuLogin;

    @FXML
    private Menu mnuAccount;
    @FXML
    private MenuItem mnuClose;


    private static final String MENU_ACCOUNT = "Account";
    private static final String MENU_LOGIN = "Login";

    private static final String MENU_FICHEIRO_SAIR = "Close";


    private static final String CABECALHO_ERRO_FATAL = "Fatal Error";

    private static final String TITULO_LOGIN = "Login";

    private static final String TITULO_MAIN = "Main";


    private AnnouncementController controller;


    private Stage LoginStage;
    private LoginUserUI loginUserUI;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarControlos();
        controller=new AnnouncementController();

        try {

        } catch (Exception ex) {

            AlertaUI.criarAlerta(Alert.AlertType.ERROR,
                    TITULO_MAIN, CABECALHO_ERRO_FATAL, ex.getLocalizedMessage()).showAndWait();
            Platform.exit();
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
     * Mnu close action.
     *
     * @param event the event
     */
    @FXML
    public void mnuCloseAction(ActionEvent event) {
        Window janela = mnuClose.getParentPopup().getOwnerWindow();
        janela.fireEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    private void mnuLogin(ActionEvent event) {


    try {
        LoginStage = new Stage();
        LoginStage.initModality(Modality.APPLICATION_MODAL);
        LoginStage.setTitle(TITULO_LOGIN);
        LoginStage.setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginUser.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        LoginStage.setScene(scene);
        LoginStage.sizeToScene();

        LoginStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                loginUserUI.limparGUI();
            }
        });

        loginUserUI = loader.getController();
        loginUserUI.associarParentUI(this);

        LoginStage.show();
    } catch (Exception ex) {

        AlertaUI.criarAlerta(Alert.AlertType.ERROR,
                TITULO_LOGIN, CABECALHO_ERRO_FATAL, ex.getLocalizedMessage()).showAndWait();
        Platform.exit();
    }

    }



    private void iniciarControlos() {
        mnuLogin.setText(MENU_LOGIN);
        mnuAccount.setText(MENU_ACCOUNT);
        mnuClose.setText(MENU_FICHEIRO_SAIR);

    }


}



