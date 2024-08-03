package pt.ipp.isep.dei.esoft.project.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.domain.Serialization.Serialization;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.ImportFileUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.RegisterEmployeeUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Main.
 */
public class Main  extends Application  {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();

        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("JavaFX application", new RegisterEmployeeUI()));
        options.add(new MenuItem("Console application", new ImportFileUI()));
        int choice = Utils.showAndSelectIndex(options,"Choose an option:")+1;

        try {
            if (choice == 1) {
                launch(args);
            } else if (choice == 2) {
                MainMenuUI menu = new MainMenuUI();
                menu.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Serialization.saveToFile("data.ser");
        }




    }



    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainApp.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root);

        stage.setTitle("Main");
        stage.setScene(scene);

        stage.sizeToScene();
        stage.setResizable(false);

        stage.show();
    }



}
