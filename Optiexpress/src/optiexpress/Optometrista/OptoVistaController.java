/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package optiexpress.Optometrista;

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
import optiexpress.Optometrista.RegGrad.RegistrarGraduacionVistaController;
import optiexpress.Optometrista.RegPac.RegistroPacVistaController;

/**
 * FXML Controller class
 *
 * @author Migue
 */
public class OptoVistaController implements Initializable {

    @FXML
    private Button RegistroGrad;
    @FXML
    private Button RegistroPaciente;
    @FXML
    private Button LogoutOpto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RegGrad(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(RegistrarGraduacionVistaController.class.getResource("RegistrarGraduacionVista.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Registrar Paciente");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.NONE);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();
    }

    @FXML
    private void RegPaciente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(RegistroPacVistaController.class.getResource("RegistroPacVista.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Registrar Paciente");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.NONE);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();
    }

    @FXML
    private void CerrarSesionOpto(ActionEvent event) {
        System.out.println("Cierra Sesion");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesion");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro?");
        alert.showAndWait();
        
        Stage stage = (Stage) LogoutOpto.getScene().getWindow();
        stage.close();
    }   
}
