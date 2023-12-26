/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package optiexpress.Admin;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import optiexpress.Admin.EdicionArmazones.EdicionArmaVistaController;
import optiexpress.Admin.VerVentas.VerVentasVistaController;

/**
 * FXML Controller class
 *
 * @author Migue
 */
public class AdminVistaController implements Initializable {

    @FXML
    private Button Logout;
    @FXML
    private Button EditaArmazon;
    @FXML
    private Button VisualizaVentas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Cerrarsesion(ActionEvent event) {
        System.out.println("Cierra Sesion");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesion");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro?"); 
        alert.showAndWait();
        
        Stage stage = (Stage) Logout.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void EditarArmazon(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(EdicionArmaVistaController.class.getResource("EdicionArmaVista.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Tabla Armazones");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.NONE);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();
    }

    @FXML
    private void VerVentas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(VerVentasVistaController.class.getResource("VerVentasVista.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ventas");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.NONE);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();
    }
    
}
