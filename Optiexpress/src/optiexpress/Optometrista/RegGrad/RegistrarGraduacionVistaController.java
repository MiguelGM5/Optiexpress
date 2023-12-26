/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package optiexpress.Optometrista.RegGrad;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static optiexpress.Optometrista.RegPac.RegistroPacVistaController.contraseña;
import static optiexpress.Optometrista.RegPac.RegistroPacVistaController.url;
import static optiexpress.Optometrista.RegPac.RegistroPacVistaController.usuario;

/**
 * FXML Controller class
 *
 * @author Migue
 */
public class RegistrarGraduacionVistaController implements Initializable {

    @FXML
    private TextField NomPacTxt;
    @FXML
    private TextField EjeDerLejos;
    @FXML
    private TextField CilDerLejos;
    @FXML
    private TextField EsfDerLejos;
    @FXML
    private TextField PrismDerLejos;
    @FXML
    private TextField EjeDerCerca;
    @FXML
    private TextField CilDerCerca;
    @FXML
    private TextField EsfDerCerca;
    @FXML
    private TextField PrismDerCerca;
    @FXML
    private TextField EjeIzqLejos;
    @FXML
    private TextField CilIzqLejos;
    @FXML
    private TextField EsfIzqLejos;
    @FXML
    private TextField PrismIzqLejos;
    @FXML
    private TextField EjeIzqCerca;
    @FXML
    private TextField CilIzqCerca;
    @FXML
    private TextField EsfIzqCerca;
    @FXML
    private TextField PrismIzqCerca;
    @FXML
    private Button RegGrad;
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
    private void RegistrarGraduacion(ActionEvent event) {
         System.out.println("Si ingresa Cliente");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement stmt = conn.prepareStatement("insert into graduacion(NomCliente, Eje_ojoDer_lejos, Cilindro_ojoDer_lejos, Esfera_ojoDer_lejos, Prisma_ojoDer_lejos, Eje_ojoDer_cerca, Cilindro_ojoDer_cerca, Esfera_ojoDer_cerca, Prisma_ojoDer_cerca, "
                        + "Eje_ojoIzq_lejos, Cilindro_ojoIzq_lejos, Esfera_ojoIzq_lejos, Prisma_ojoIzq_lejos, Eje_ojoIzq_cerca, Cilindro_ojoIzq_cerca, Esfera_ojoIzq_cerca, Prisma_ojoIzq_cerca) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, NomPacTxt.getText());
                stmt.setString(2, EjeDerLejos.getText());
                stmt.setString(3, CilDerLejos.getText());
                stmt.setString(4, EsfDerLejos.getText());
                stmt.setString(5, PrismDerLejos.getText());
                stmt.setString(6, EjeDerCerca.getText());
                stmt.setString(7, CilDerCerca.getText());
                stmt.setString(8, EsfDerCerca.getText());
                stmt.setString(9, PrismDerCerca.getText());
                stmt.setString(10, EjeIzqLejos.getText());
                stmt.setString(11, CilIzqLejos.getText());
                stmt.setString(12, EsfIzqLejos.getText());
                stmt.setString(13, PrismIzqLejos.getText());
                stmt.setString(14, EjeIzqCerca.getText());
                stmt.setString(15, CilIzqCerca.getText());
                stmt.setString(16, EsfIzqCerca.getText());
                stmt.setString(17, PrismIzqCerca.getText());
                
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
        EjeDerLejos.setText(null);
        CilDerLejos.setText(null);
        EsfDerLejos.setText(null);
        PrismDerLejos.setText(null);
        EjeDerCerca.setText(null);
        CilDerCerca.setText(null);
        EsfDerCerca.setText(null);
        PrismDerCerca.setText(null);
        EjeIzqLejos.setText(null);
        CilIzqLejos.setText(null);
        EsfIzqLejos.setText(null);
        PrismIzqLejos.setText(null);
        EjeIzqCerca.setText(null);
        CilIzqCerca.setText(null);
        EsfIzqCerca.setText(null);
        PrismIzqCerca.setText(null);
    }

    @FXML
    private void Close(ActionEvent event) {
        Stage stage = (Stage) Cerrar.getScene().getWindow();
        stage.close();
    }
}
