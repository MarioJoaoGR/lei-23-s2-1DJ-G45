package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * The type Alerta ui.
 */
public class AlertaUI {

    /**
     * Criar alerta alert.
     *
     * @param tipoAlerta the tipo alerta
     * @param titulo     the titulo
     * @param cabecalho  the cabecalho
     * @param mensagem   the mensagem
     * @return the alert
     */
    public static Alert criarAlerta(Alert.AlertType tipoAlerta, String titulo, String cabecalho, String mensagem) {
        Alert alerta = new Alert(tipoAlerta);
        
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(mensagem);
        
        if (tipoAlerta == Alert.AlertType.CONFIRMATION) {
            ((Button) alerta.getDialogPane().lookupButton(ButtonType.OK)).setText("Sim");
            ((Button) alerta.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Não");
        }
        
        return alerta;
    }
}
