/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package optiexpress.Optometrista.RegPac;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Migue
 */
public class RegistroPacVistaController implements Initializable {

    @FXML
    private TextField NomPacTxt;
    @FXML
    private TextField EdadTxt;
    @FXML
    private TextField SexTxt;
    @FXML
    private TextField Fnacimientotxt;
    @FXML
    private TextField DomicilioTxt;
    @FXML
    private TextField NumTelTxt;
    @FXML
    private TextField CorreoTxt;
    @FXML
    private Button RegPac;
    @FXML
    private Button Limpiar;
    
    // Varibles de conexion
    public static final String url = "jdbc:mysql://localhost:3306/optiexpress";
    public static final String usuario = "root";
    public static final String contraseña = "washimido15";
    ResultSet rs;
    @FXML
    private Button Cerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RegistrarPaciente(ActionEvent event) {
        System.out.println("Si ingresa Cliente");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement stmt = conn.prepareStatement("insert into clientes(NomCliente, Edad, Sexo, F_nacimiento, Domicilio, Num_tel, Correo) values (?,?,?,?,?,?,?)");
                stmt.setString(1, NomPacTxt.getText());
                stmt.setString(2, EdadTxt.getText());
                stmt.setString(3, SexTxt.getText());
                stmt.setString(4, Fnacimientotxt.getText());
                stmt.setString(5, DomicilioTxt.getText());
                stmt.setString(6, NumTelTxt.getText());
                stmt.setString(7, CorreoTxt.getText());
                int resultado = stmt.executeUpdate();
                
                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ingreso exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Has ingresado tus datos correctamente.");
                    alert.showAndWait();
                    LimpiarCampos();
                }
                conn.close();
                }catch (SQLException e) {
                if (e.getErrorCode() == 1452) {
                // Otro manejo de excepciones o re-lanzamiento
                    System.out.println("Error desconocido: " + e.getMessage());
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("FORMATO O LLENADO ERRONEO EN CAMPOS");
                    alert.showAndWait();
                    }  
                } catch (Exception ex){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("FORMATO O LLENADO ERRONEO EN CAMPOS");
                    alert.showAndWait();
                    } 
                }
    }   

    @FXML
    private void LimpiarCampos() {
        NomPacTxt.setText(null);
        EdadTxt.setText(null);
        SexTxt.setText(null);
        Fnacimientotxt.setText(null);
        DomicilioTxt.setText(null);
        NumTelTxt.setText(null);
        CorreoTxt.setText(null);
        }

    @FXML
    private void Close(ActionEvent event) {
        Stage stage = (Stage) Cerrar.getScene().getWindow();
        stage.close();
    }
}
