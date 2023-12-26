/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package optiexpress.Ventas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import optiexpress.Optometrista.RegGrad.RegistrarGraduacionVistaController;
import optiexpress.Ventas.VentaArma.VentaArmaVistaController;

/**
 * FXML Controller class
 *
 * @author Migue
 */
public class VentasVistaController implements Initializable {

    @FXML
    private Button Logout;
    @FXML
    private Button VerArma;
    @FXML
    private Button VentaArma;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
       
    @FXML
    private void VenderArmazon(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(VentaArmaVistaController.class.getResource("VentaArmaVista.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Registrar Paciente");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.NONE);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show(); 
    }
    
    @FXML
    private void Cerrarsesion(ActionEvent event) {
        System.out.println("Cierra Sesion");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesion");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro?");
        
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        Stage stage = (Stage) Logout.getScene().getWindow();
        stage.close();
    }
}
