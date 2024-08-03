package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pt.ipp.isep.dei.esoft.project.domain.linearRegression.LinearRegression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * The type Linear regression app controller.
 */
public class LinearRegressionAppController implements Initializable {

    // Excel file containing the relevant data for the regression analysis
    private XSSFWorkbook CSVFile;

    // Layout variables
    @FXML
    private SplitPane splitPane;

    // Regression type menu
    @FXML
    private Menu regressionTypeMenu;
    @FXML
    private RadioMenuItem simpleRegressionMenuItem;
    @FXML
    private RadioMenuItem multipleRegressionMenuItem;

    // Simple regression menu
    @FXML
    private Menu simpleRegressionMenu;
    @FXML
    private RadioMenuItem areaMenuItem;
    @FXML
    private RadioMenuItem distanceCenterMenuItem;
    @FXML
    private RadioMenuItem bedroomsMenuItem;
    @FXML
    private RadioMenuItem bathroomsMenuItem;
    @FXML
    private RadioMenuItem parkingSpacesMenuItem;

    // Mulltiple regression menu
    @FXML
    private Menu multipleRegressionMenu;
    @FXML
    private MenuItem viewMultipleRegressionMenuItem;

    // File menu
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem chooseCSVFileMenuItem;

    // Left side split pane
    @FXML
    private Label label1;
    @FXML
    private TextField textField;
    @FXML
    private Button okButton;
    @FXML
    private Label label2;
    @FXML
    private Label statistic1, statistic2, statistic3, statistic4, statistic5, statistic6, statistic7, statistic8;
    @FXML
    private TitledPane relevantStatisticsPane;

    // Right side split pane
    @FXML
    private ScatterChart<Double, Double> scatterChart;

    private SimpleRegression simpleRegression;

    private OLSMultipleLinearRegression multipleRegression;


    /**
     * This method is called as soon as the app is opened, it assures that all menu options are disabled except for
     * the file and help. This assures that the user must choose the file before using the app, alternatively he can open
     * the user manual in the help tab for information on how to use the app.
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        disableMenuOptions(true);
        disableSplitPane(true);
        // Enable options and SplitPane components when an Excel file is selected
        chooseCSVFileMenuItem.setOnAction(event -> {
            CSVFile = handleChooseCSVFileMenuItem(event);
            disableMenuOptions(false);
            simpleRegressionMenu.setDisable(true);
            multipleRegressionMenu.setDisable(true);
        });
    }

    /**
     * Disables all the menu options except for the file and help.
     * @param disable true or false according to wether we want to enable or disable the options
     */


    private void disableMenuOptions(boolean disable) {
        regressionTypeMenu.setDisable(disable);
        simpleRegressionMenu.setDisable(disable);
        multipleRegressionMenu.setDisable(disable);
        simpleRegressionMenuItem.setDisable(disable);
        multipleRegressionMenuItem.setDisable(disable);
        areaMenuItem.setDisable(disable);
        distanceCenterMenuItem.setDisable(disable);
        bedroomsMenuItem.setDisable(disable);
        bathroomsMenuItem.setDisable(disable);
        parkingSpacesMenuItem.setDisable(disable);
    }

    /**
     * Disables the split pane options
     * @param disable true or false according to wether we want to enable or disable the options
     */

    private void disableSplitPane(boolean disable) {
        splitPane.setDisable(disable);
        for (int i = 0; i < splitPane.getItems().size(); i++) {
            if (splitPane.getItems().get(i) instanceof ScatterChart) {
                ((ScatterChart<?, ?>) splitPane.getItems().get(i)).setDisable(disable);
            }
        }
    }

    /**
     * This is the method that handles the regression type menu. It only allows one option from the menu to be chosen
     * by the user, and according to that option either the simple regression menu becomes available or the multiple
     * regression menu becomes available.
     *
     * @param event This event is associated in the FXML document to the correct menu item.
     */
    @FXML
    void handleRegressionTypeMenuItem(ActionEvent event) {
        // Reset the selection state for all options
        simpleRegressionMenuItem.setSelected(false);
        multipleRegressionMenuItem.setSelected(false);

        // Set the selected state for the clicked option
        RadioMenuItem selectedOption = (RadioMenuItem) event.getSource();
        selectedOption.setSelected(true);

        if (selectedOption.equals(simpleRegressionMenuItem)) {
            simpleRegressionMenu.setDisable(false);
            multipleRegressionMenu.setDisable(true);
        } else {
            multipleRegressionMenu.setDisable(false);
            simpleRegressionMenu.setDisable(true);
        }
    }

    /**
     * Handles each menu item in the simple regression menu. According to the option that the user chooses it will
     * update the split pane tab.
     *
     * @param event This event is associated in the FXML document to the correct menu item.
     * @throws IOException the io exception
     */
    @FXML
    void handleSimpleRegressionMenuItem(ActionEvent event) throws IOException {
        areaMenuItem.setSelected(false);
        distanceCenterMenuItem.setSelected(false);
        bedroomsMenuItem.setSelected(false);
        bathroomsMenuItem.setSelected(false);
        parkingSpacesMenuItem.setSelected(false);

        RadioMenuItem selectedOption = (RadioMenuItem) event.getSource();
        selectedOption.setSelected(true);

        if (selectedOption.equals(areaMenuItem)) {
            handleSpecificSimpleRegressionItem("Predict price based on area: ", 0);
        } else if (selectedOption.equals(distanceCenterMenuItem)) {
            handleSpecificSimpleRegressionItem("Predict price based on distance center: ", 1);
        } else if (selectedOption.equals(bedroomsMenuItem)) {
            handleSpecificSimpleRegressionItem("Predict price based on number of bedrooms: ", 2);
        } else if (selectedOption.equals(bathroomsMenuItem)) {
            handleSpecificSimpleRegressionItem("Predict price based on number of bathrooms: ", 3);
        } else if (selectedOption.equals(parkingSpacesMenuItem)) {
            handleSpecificSimpleRegressionItem("Predict price based on number of parking spaces: ", 4);
        }
    }

    /**
     * Handles the simple regression menu item if it's based on area, distance center, bedrooms, bathrooms, parking.
     * It enables the options in the split pane allowing for an update based on the regression that the user chose.
     *
     * @param label1Text  the text for the label 1 this should match the simple regression independent variable
     * @param columnIndex the index of the column in the XSFFWorkbook file table that represnets the independent variable
     *                    see the user manual for more info. To access the manual open the app and go to the help tab, next choose the manual option.
     */

    private void handleSpecificSimpleRegressionItem(String label1Text, int columnIndex) throws IOException {
        disableSplitPane(false);
        label1.setDisable(false);
        label2.setDisable(false);
        textField.setDisable(false);
        okButton.setDisable(false);
        textField.clear();
        label1.setText(label1Text);
        label2.setText("Predicted price: ");
        simpleRegression = new SimpleRegression();
        double[][] regressionData = LinearRegression.getDataSimpleRegression(CSVFile, columnIndex);
        simpleRegression.addData(regressionData);
        fillScatterChartSimpleRegression(regressionData);
        setStatisticsForSimpleRegression(simpleRegression);
        okButton.setOnAction(actionEvent -> {
            double inputedValue = Double.parseDouble(textField.getText());
            double predictedValue = simpleRegression.predict(inputedValue);
            label2.setText(String.valueOf("Predicted price: " + predictedValue));
        });
    }

    /**
     * Sets the relevant statistics in the split pane according to the simple regression that the user chose in the
     * simple regression menu.
     * @param simpleRegression The simple regression that is created according to the user option, it can be a regression
     */

    private void setStatisticsForSimpleRegression(SimpleRegression simpleRegression) {
        statistic1.setText("R²: " + simpleRegression.getRSquare());
        statistic2.setText("Slope: " + simpleRegression.getSlope());
        statistic3.setText("Significance: " + simpleRegression.getSignificance());
        statistic4.setText("Confidence interval slope: " + simpleRegression.getSlopeConfidenceInterval());
        statistic5.setText("Intercept: " + simpleRegression.getIntercept());
        statistic6.setText("Slope error: " + simpleRegression.getSlopeStdErr());
        statistic7.setText("Intercept error: " + simpleRegression.getInterceptStdErr());
        statistic8.setText("Sample size: " + simpleRegression.getN());
    }

    /**
     * Clears the statistics that were set on the split pane. This is needed for when the user alters the simple regression
     * for example from area to number of bedrooms.
     */

    private void clearStatistics() {
        statistic1.setText("");
        statistic2.setText("");
        statistic3.setText("");
        statistic4.setText("");
        statistic5.setText("");
        statistic6.setText("");
        statistic7.setText("");
        statistic8.setText("");
    }

    /**
     * Fills the scatter chart in the split pane with the data from the csv document. The data comes in a array of doubles
     * of size n by 2 giving that the first column is the independent variable (x axis) and the second column is the dependent
     * variable (y axis). The number of rows is always equal for both columns of course.
     * @param regressionData The data needed to fill the scatter chart.
     */

    private void fillScatterChartSimpleRegression(double[][] regressionData) {
        scatterChart.getData().clear();
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        for (int i = 0; i < regressionData.length; i++) {
            double xValue = regressionData[i][0];
            double yValue = regressionData[i][1];
            series.getData().add(new XYChart.Data<>(xValue, yValue));
        }
        scatterChart.getData().add(series);
    }

    @FXML
    private void handleViewMultipleRegression(ActionEvent actionEvent) {
        // Disable irrelevant elements
        label1.setDisable(true);
        label2.setDisable(true);
        textField.setDisable(true);
        okButton.setDisable(true);
        scatterChart.getData().clear();
        clearStatistics();
        disableSplitPane(false);

        multipleRegression = new OLSMultipleLinearRegression();
        double[] dependentVariable = LinearRegression.getDependentVariable(CSVFile);
        double[][] independentVariable = LinearRegression.getIndependentVariables(CSVFile);
        multipleRegression.newSampleData(dependentVariable, independentVariable);
        statistic1.setText("Error variance: " + multipleRegression.estimateErrorVariance());
        statistic2.setText("R² adjusted: " + multipleRegression.calculateAdjustedRSquared());
        statistic3.setText("R²: " + multipleRegression.calculateRSquared());
        statistic4.setText("Regressand variance: " + multipleRegression.estimateRegressandVariance());
    }

    /**
     * Handles the file menu item in the file menu. The file chosen must be in the same format as the one available in the moodle also
     * it must be a csv file.
     * @param actionEvent
     * @return
     */


    @FXML
    private XSSFWorkbook handleChooseCSVFileMenuItem(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        Stage stage = (Stage) chooseCSVFileMenuItem.getParentPopup().getOwnerWindow();
        File file = fileChooser.showOpenDialog(stage);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int rowCount = 0;
            String line;

            while ((line = reader.readLine()) != null && rowCount < 200) {
                rowCount++;
            }

            if (rowCount >= 30) {
                return convertCSVToWorkbook(file);
            }

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("The selected file is not valid. Make sure the CSV file has at least 200 rows.");
            alert.showAndWait();
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
        }

        return null;
    }

    /**
     * This converts the csv chosen by the user to a XSSFWorkbook type file this method is used to make the manipulation of the file for
     * the relevant calculations easier.
     * @param csvFile The csv file that was chosen by the user
     * @return The same file but as a XSSFWorkbook object instead of a File object
     * @throws IOException
     */

    private XSSFWorkbook convertCSVToWorkbook(File csvFile) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            int rowNum = 0;

            while ((line = reader.readLine()) != null) {
                if (rowNum > 0) { // Ignore the first row
                    Row row = sheet.createRow(rowNum - 1);

                    String[] cells = line.split(";");

                    org.apache.poi.ss.usermodel.Cell cell8 = row.createCell(0);
                    cell8.setCellValue(Double.parseDouble(cells[7]));

                    org.apache.poi.ss.usermodel.Cell cell10 = row.createCell(1);
                    cell10.setCellValue(Double.parseDouble(cells[9]));

                    org.apache.poi.ss.usermodel.Cell cell11 = row.createCell(2);
                    cell11.setCellValue(Double.parseDouble(cells[10]));

                    org.apache.poi.ss.usermodel.Cell cell12 = row.createCell(3);
                    cell12.setCellValue(Double.parseDouble(cells[11]));

                    org.apache.poi.ss.usermodel.Cell cell13 = row.createCell(4);
                    cell13.setCellValue(Double.parseDouble(cells[12]));

                    org.apache.poi.ss.usermodel.Cell cell20 = row.createCell(5);
                    cell20.setCellValue(Double.parseDouble(cells[19]));
                }

                rowNum++;
            }
        }

        return workbook;
    }
}

