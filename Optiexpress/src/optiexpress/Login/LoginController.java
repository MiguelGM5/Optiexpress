/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package optiexpress.Login;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import optiexpress.Admin.AdminVistaController;
import optiexpress.Optometrista.OptoVistaController;
import optiexpress.Ventas.VentaArma.VentaArmaVistaController;


/**
 * FXML Controller class
 *
 * @author Migue
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private Button btnIngresa;
    @FXML
    private PasswordField txtPassword;
 // variables de ingreso
    
    private String usuario = null;
    private String contraseniaAdmin = "admin12345";
    private String contraseniaVentas = "venta5";
    private String contraseniaOpto = "0pto";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ingresauser(ActionEvent event) throws IOException {
        String user = txtUsername.getText();
        user.toLowerCase();
        String password = txtPassword.getText();

        if (user.equals("admin") && password.equals(contraseniaAdmin)) {
            System.out.println("Entras a ADMI");
            Parent root = FXMLLoader.load(AdminVistaController.class.getResource("AdminVista.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Administrador");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.NONE);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
            stage.setResizable(false);
            
        } else if (user.equals("ventas") && password.equals(contraseniaVentas)) {
            System.out.println("Entras a Ventas");
            Parent root = FXMLLoader.load(VentaArmaVistaController.class.getResource("VentaArmaVista.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ventas");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.NONE);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
            stage.setResizable(false);
            
        } else if (user.equals("optometrista") && password.equals(contraseniaOpto)) {
            System.out.println("Entras a Optometrista");
            Parent root = FXMLLoader.load(OptoVistaController.class.getResource("OptoVista.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Optometrista");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.NONE);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
            stage.setResizable(false);
       
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setTitle("Error");
            alert.setContentText("USUARIO O CONTRASEÑA INCORRECTOS");
            alert.showAndWait();
            alert.setResizable(false);
        }
    }
}
   


