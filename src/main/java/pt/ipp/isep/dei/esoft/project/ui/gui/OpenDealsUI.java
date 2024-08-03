package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementSold;
import pt.ipp.isep.dei.esoft.project.domain.Apartment;
import pt.ipp.isep.dei.esoft.project.domain.House;
import pt.ipp.isep.dei.esoft.project.domain.Land;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * The type Open deals ui.
 */
public class OpenDealsUI implements Initializable {

    /**
     * The Lbl business.
     */
    @FXML
    public Label lblBusiness;
    /**
     * The Lbl publish.
     */
    @FXML
    public Label lblPublish;
    /**
     * The Lbl request price.
     */
    @FXML
    public Label lblRequestPrice;
    /**
     * The Lbl agencyid.
     */
    @FXML
    public Label lblAgencyid;
    /**
     * The Lbl agent name.
     */
    @FXML
    public Label lblAgentName;

    /**
     * The Lbl agency name.
     */
    @FXML
    public Label lblAgencyName;
    /**
     * The Lbl property type.
     */
    @FXML
    public Label lblPropertyType;
    /**
     * The Lbl agent comission.
     */
    @FXML
    public Label lblAgentComission;
    /**
     * The Btn cancel.
     */
    @FXML
    public Button btnCancel;
    /**
     * The Grid.
     */
    @FXML
    public RowConstraints grid;
    private static final String LABEL_PUBLISH_DATE = "Publish date:";
    private static final String LABEL_REQUESTED_PRICE = "Requested price:";

    private static final String LABEL_BUSINESS_TYPE = "Business type:";
    private static final String LABEL_AGENCY_ID = "Agency id:";
    private static final String LABEL_AGENCY_NAME = "Agency name:";
    private static final String LABEL_AGENT_NAME = "Agent name:";
    private static final String LABEL_AGENT_COMISSION = "Agent comission:";
    private static final String LABEL_PROPERTY_TYPE = "Property type:";

    private static final String LABEL_PROPERTY_LOCATION = "Property location:";
    private static final String LABEL_PROPERTY_AREA = "Property Area:";
    private static final String LABEL_PROPERTY_ROOMS = "Property rooms:";
    private static final String LABEL_SOLD_DATE = "Sold Price:";
    private static final String LABEL_SOLD_PRICE = "Sold Date:";



    private static final String BTN_ACCEPT = "Accept";
    private static final String BTN_REFUSE = "Refuse";
    private static final String BTN_CANCEL = "Cancel";

    private static final String TITULO_SEARCH = "Visit Requests";

    private static final String CABECALHO_VALUES_INVALIDOS = "Invalid Values";


    private static final String MESSAGE_ERROR_SEARCH = "Both inputs must be filled";
    private static final String MESSAGE_ERROR_SEARCH_LOGICAL = "Begin date must be lower than End date";

    private static final String MESSAGE_ERROR_EMPTY_LIST = "There aren´t any Visit Requests for the dates that you select";


    private static final String CABECALHO_ERRO_FATAL = "Erro Fatal";

    private static final String CABECALHO_EMPTY_LIST = "Empty Search";

    private static final String TITLE_SEARCH = "List of visit Requests";

    /**
     * The Lbl property location.
     */
    @FXML
    public Label lblPropertyLocation;
    /**
     * The Lbl property area.
     */
    @FXML
    public Label lblPropertyArea;
    /**
     * The Lbl property rooms.
     */
    @FXML
    public Label lblPropertyRooms;
    /**
     * The Lbl requested value.
     */
    public Label lblRequestedValue;
    /**
     * The Lbl agency id value.
     */
    public Label lblAgencyIdValue;
    /**
     * The Lblbusiness value.
     */
    public Label lblbusinessValue;
    /**
     * The Lbl agent name value.
     */
    public Label lblAgentNameValue;
    /**
     * The Lbl property type value.
     */
    public Label lblPropertyTypeValue;
    /**
     * The Lbl agent comission value.
     */
    public Label lblAgentComissionValue;
    /**
     * The Lbl location value.
     */
    public Label lblLocationValue;
    /**
     * The Lbl area value.
     */
    public Label lblAreaValue;
    /**
     * The Lbl rooms value.
     */
    public Label lblRoomsValue;
    /**
     * The Lbl sold price.
     */
    public Label lblSoldPrice;
    /**
     * The Lbl sold date.
     */
    public Label lblSoldDate;
    /**
     * The Lbl sold price value.
     */
    public Label lblSoldPriceValue;
    /**
     * The Lbl sold date value.
     */
    public Label lblSoldDateValue;
    /**
     * The Lbl publish value.
     */
    public Label lblPublishValue;
    /**
     * The Lbl agency name value.
     */
    public Label lblAgencyNameValue;


    /**
     * The Controller.
     */
    AnnouncementController controller;

    private ListDealsUI listDealsUI;

    private AnnouncementSold announcementSold;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarControlos();
        Platform.runLater(() -> {

            controller=listDealsUI.getAppController();

            incializarAnnouncement();
        });


    }


    /**
     * Associar parent ui.
     *
     * @param listDealsUI the list deals ui
     */
    public void associarParentUI(ListDealsUI listDealsUI) {
        this.listDealsUI = listDealsUI;
    }


    /**
     * Limpar gui.
     */
    public void limparGUI() {
        //dateStart.setValue(null);
        //dateEnd.setValue(null);

    }

    @FXML
    private void cancelAction(ActionEvent event) {
            ((Node) event.getSource()).getScene().getWindow().hide();
            ((Stage) btnCancel.getScene().getWindow()).hide();
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
        return listDealsUI.getAuthControler();
    }



    private void iniciarControlos() {
        lblAgencyid.setText(LABEL_AGENCY_ID);
        lblPublish.setText(LABEL_PUBLISH_DATE);
        lblBusiness.setText(LABEL_BUSINESS_TYPE);
        lblRequestPrice.setText(LABEL_REQUESTED_PRICE);
        lblAgencyName.setText(LABEL_AGENCY_NAME);
        lblAgentComission.setText(LABEL_AGENT_COMISSION);
        lblPropertyType.setText(LABEL_PROPERTY_TYPE);
        lblPropertyArea.setText(LABEL_PROPERTY_AREA);
        lblPropertyLocation.setText(LABEL_PROPERTY_LOCATION);
        lblPropertyRooms.setText(LABEL_PROPERTY_ROOMS);
        lblSoldDate.setText(LABEL_SOLD_DATE);
        lblSoldPrice.setText(LABEL_SOLD_PRICE);
        btnCancel.setText(BTN_CANCEL);

    }


    /**
     * Incializar announcement.
     */
    public void incializarAnnouncement(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        lblAgencyIdValue.setText(String.valueOf(announcementSold.getAgencyFromRepository().getId()));
        lblPublishValue.setText(dateFormat.format(announcementSold.getDate()));
        lblbusinessValue.setText(announcementSold.getBusinessType());
        lblRequestedValue.setText(String.valueOf(announcementSold.getPrice()));
        lblAgencyNameValue.setText(announcementSold.getAgency().getName());
        lblAgentComissionValue.setText(announcementSold.getComission().getCommissionValue() + " - " + announcementSold.getComission().getCommissionType());
        lblPropertyTypeValue.setText(announcementSold.getProperty().getPropertyType());
        lblAreaValue.setText(String.valueOf(announcementSold.getProperty().getArea()));
        lblLocationValue.setText(String.valueOf(announcementSold.getProperty().getLocation()));
        if (announcementSold.getProperty() instanceof House){
            House house = (House) announcementSold.getProperty();
            lblRoomsValue.setText(String.valueOf(house.getNumberRooms()));
        }
        if (announcementSold.getProperty() instanceof Apartment){
            Apartment apartment = (Apartment) announcementSold.getProperty();
            lblRoomsValue.setText(String.valueOf(apartment.getNumberRooms()));
        }
        if (announcementSold.getProperty() instanceof Land){
            lblRoomsValue.setText("-");
        }
        lblSoldDateValue.setText(dateFormat.format(announcementSold.getSaleDate()));
        lblSoldPriceValue.setText(String.valueOf(announcementSold.getSalePrice()));
    }


    /**
     * Sets anouncement sold.
     *
     * @param announcementSold the announcement sold
     */
    public void setAnouncementSold(AnnouncementSold announcementSold) {
        this.announcementSold = announcementSold;
    }


}
