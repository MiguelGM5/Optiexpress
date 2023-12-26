/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package optiexpress.Admin.EdicionArmazones;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author Migue
 */
public class EdicionArmaVistaController implements Initializable {
    
    @FXML
    private TableView<DataModelEdicionArma> TableViewArma;
    private ObservableList<DataModelEdicionArma> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<DataModelEdicionArma, Integer> ColIdArmazon;
    @FXML
    private TableColumn<DataModelEdicionArma, String> ColMarcaArma;
    @FXML
    private TableColumn<DataModelEdicionArma, String> ColModeloArma;
    @FXML
    private TableColumn<DataModelEdicionArma, String> ColMaterialArma;
    @FXML
    private TableColumn<DataModelEdicionArma, Integer> ColCantidadArma;
    @FXML
    private TableColumn<DataModelEdicionArma, Integer> ColPrecioArma;
    @FXML
    private TextField BuscaArmazon;
    @FXML
    private Button BuscaBoton;
    @FXML
    private Button AgregarBoton;
    @FXML
    private Button Actualizabtn;
    @FXML
    private Button ActArma;
    @FXML
    private TextField Marcatxt;
    @FXML
    private TextField Modelotxt;
    @FXML
    private TextField Materialtxt;
    @FXML
    private TextField Cantidadtxt;
    @FXML
    private TextField Preciotxt;
    @FXML
    private TextField IdArmazontxt;
    @FXML
    private Button EliminarArma;
    @FXML
    private Button Limpiar;
    @FXML
    private Button Cancelarbtn;
    
    
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
        ColIdArmazon.setCellValueFactory(new PropertyValueFactory<>("Id_armazon"));
        ColMarcaArma.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        ColModeloArma.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        ColMaterialArma.setCellValueFactory(new PropertyValueFactory<>("Material"));
        ColCantidadArma.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        ColPrecioArma.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        
        TableViewArma.setItems(data);
        loadDataFromDatabase();
    }   
    
     public void loadDataFromDatabase() {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM armazones"); 
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int Id_armazon = rs.getInt("Id_armazon");
                String Marca = rs.getString("Marca");
                String Modelo = rs.getString("Modelo");
                String Material = rs.getString("Material");
                int Cantidad = rs.getInt("Cantidad");
                int Precio = rs.getInt("Precio");
                
                DataModelEdicionArma dataModelArmazon = new DataModelEdicionArma(Id_armazon, Marca, Modelo, Material, Cantidad, Precio);
                data.add(dataModelArmazon);
            }
            TableViewArma.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BuscarBoton(ActionEvent event) {
        try {
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement stmt = conn.prepareStatement("select * from armazones where Id_armazon = ?");
            stmt.setString(1, BuscaArmazon.getText());
            rs = stmt.executeQuery();

            LimpiaCampos();
            if (rs.next()) {
                IdArmazontxt.setText(String.valueOf(rs.getInt("Id_armazon")));
                Marcatxt.setText(rs.getString("Marca"));
                Modelotxt.setText(String.valueOf(rs.getString("Modelo")));
                Materialtxt.setText(rs.getString("Material"));
                Cantidadtxt.setText(rs.getString("Cantidad"));
                Preciotxt.setText(String.valueOf(rs.getString("Precio")));

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("Armazon no encontrado...");
                alert.showAndWait();
                LimpiaCampos();
            }
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }

    @FXML
    private void AgregaArmazon(ActionEvent event) throws IOException {
        System.out.println("Agrega Armazon");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement stmt = conn.prepareStatement("insert into armazones(Marca, Modelo, Material, Cantidad, Precio) values (?,?,?,?,?)");
                stmt.setString(1, Marcatxt.getText());
                stmt.setString(2, Modelotxt.getText());
                stmt.setString(3, Materialtxt.getText());
                stmt.setString(4, Cantidadtxt.getText());
                stmt.setString(5, Preciotxt.getText());
                
                int resultado = stmt.executeUpdate();
                
                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ingreso exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Has ingresado tus datos correctamente.");
                    alert.showAndWait();
                    LimpiaCampos();
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
    private void Update(ActionEvent event) {
        data.clear();
        loadDataFromDatabase();
    }

    @FXML
    private void ActualizaArmazon(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement ps = conn.prepareStatement("update armazones set Id_armazon = ?, Marca = ?, Modelo = ?, Material = ?, Precio = ?, Cantidad = ? where Id_armazon = ?");
                ps.setInt(7, Integer.parseInt(BuscaArmazon.getText()));
                ps.setInt(1, Integer.parseInt(IdArmazontxt.getText()));
                ps.setString(2, Marcatxt.getText());
                ps.setString(3, Modelotxt.getText());
                ps.setString(4, Materialtxt.getText());
                ps.setInt(5, Integer.parseInt(Preciotxt.getText()));
                ps.setInt(6, Integer.parseInt(Cantidadtxt.getText()));
                

                int resultado = ps.executeUpdate();
                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ingreso exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Has Modificado tus datos correctamente.");
                    alert.showAndWait();
                    LimpiaCampos();
                }

                conn.close();
            } catch (SQLException e) {
                if (e.getErrorCode() == 1062) {
                    // Manejo específico para el error de clave primaria duplicada
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("VIOLACION DE CLAVE PRIMARIA, EL ID YA EXISTE EN LA TABLA.");
                    alert.showAndWait();
                } else {
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
    private void EliminaArmazon(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres continuar?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {

            try {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement ps = conn.prepareStatement("delete from armazones where Id_armazon = ?");
                ps.setInt(1, Integer.parseInt(BuscaArmazon.getText()));
                int resultado = ps.executeUpdate();
                if (resultado > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ingreso exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Se ha borrado el Armazon correctamente.");
                    alert.showAndWait();
                    LimpiaCampos();
                }
                conn.close();
            } catch (SQLException e ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("ERROR CON LA BASE DE DATOS");
                alert.showAndWait();
            } catch (Exception e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("FORMATO O LLENADO ERRONEO EN CAMPOS");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void LimpiaCampos() {
        IdArmazontxt.setText(null);
        Marcatxt.setText(null);
        Modelotxt.setText(null);
        Materialtxt.setText(null);
        Cantidadtxt.setText(null);
        Preciotxt.setText(null);
    }

    @FXML
    private void Cancelar(ActionEvent event) {
        Stage stage = (Stage) Cancelarbtn.getScene().getWindow();
        stage.close();
    }
}
