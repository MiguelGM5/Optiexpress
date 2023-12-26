/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package optiexpress.Admin.AgregarArma;

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
import static optiexpress.Optometrista.RegPac.RegistroPacVistaController.contraseña;
import static optiexpress.Optometrista.RegPac.RegistroPacVistaController.url;
import static optiexpress.Optometrista.RegPac.RegistroPacVistaController.usuario;

/**
 * FXML Controller class
 *
 * @author Migue
 */
public class AgregarArmazonVistaController implements Initializable {

    @FXML
    private TextField Marcatxt;
    @FXML
    private TextField Modelotxt;
    @FXML
    private TextField Materialtxt;
    @FXML
    private TextField Preciotxt;
    @FXML
    private TextField Cantidadtxt;
    @FXML
    private Button Agregabtn;
    @FXML
    private Button Limpiarbtn;
    
    // Varibles de conexion
    public static final String url = "jdbc:mysql://localhost:3306/optiexpress";
    public static final String usuario = "root";
    public static final String contraseña = "washimido15";
    ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AgregarArmazon(ActionEvent event) {
        System.out.println("Si ingresa Cliente");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement stmt = conn.prepareStatement("insert into armazones(Marca, Modelo, Material, Precio, Cantidad) values (?,?,?,?,?)");
                stmt.setString(1, Marcatxt.getText());
                stmt.setString(2, Modelotxt.getText());
                stmt.setString(3, Materialtxt.getText());
                stmt.setString(4, Preciotxt.getText());
                stmt.setString(5, Cantidadtxt.getText());
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
        Marcatxt.setText(null);
        Modelotxt.setText(null);
        Materialtxt.setText(null);
        Preciotxt.setText(null);
        Cantidadtxt.setText(null);
    }
    
}
