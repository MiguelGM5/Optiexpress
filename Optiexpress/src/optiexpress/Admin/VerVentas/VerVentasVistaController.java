/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package optiexpress.Admin.VerVentas;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import optiexpress.Admin.EdicionArmazones.DataModelEdicionArma;
import static optiexpress.Admin.EdicionArmazones.EdicionArmaVistaController.contraseña;
import static optiexpress.Admin.EdicionArmazones.EdicionArmaVistaController.url;
import static optiexpress.Admin.EdicionArmazones.EdicionArmaVistaController.usuario;

/**
 * FXML Controller class
 *
 * @author Migue
 */
public class VerVentasVistaController implements Initializable {

    @FXML
    private TableView<DataModelVerVentas> TableViewVentas;
    private ObservableList<DataModelVerVentas> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<DataModelVerVentas, Integer> ColIdVenta;
    @FXML
    private TableColumn<DataModelVerVentas, Integer> ColIdEmpleado;
    @FXML
    private TableColumn<DataModelVerVentas, Date> ColFechaVenta;
    @FXML
    private TableColumn<DataModelVerVentas, Integer> ColIdArmazon;
    @FXML
    private TableColumn<DataModelVerVentas, Integer> ColFormaPago;
    @FXML
    private TableColumn<DataModelVerVentas, Integer> ColMontoTotal;
    @FXML
    private TextField BuscaVenta;
    @FXML
    private Button BuscaBoton;
    
    
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
        ColIdVenta.setCellValueFactory(new PropertyValueFactory<>("Id_venta"));
      //ColIdCliente.setCellValueFactory(new PropertyValueFactory<>("Id_cliente"));
        ColIdEmpleado.setCellValueFactory(new PropertyValueFactory<>("Id_empleado"));
        ColFechaVenta.setCellValueFactory(new PropertyValueFactory<>("Fecha_venta"));
        ColIdArmazon.setCellValueFactory(new PropertyValueFactory<>("Id_armazon"));
        ColFormaPago.setCellValueFactory(new PropertyValueFactory<>("Id_formapago"));
        ColMontoTotal.setCellValueFactory(new PropertyValueFactory<>("Monto_total"));
        
        TableViewVentas.setItems(data);
        loadDataFromDatabase();
    }
    
    private void loadDataFromDatabase() {
         try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ventas"); 
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int Id_venta = rs.getInt("Id_venta");
              //int Id_cliente = rs.getInt("Id_cliente");
                int Id_empleado = rs.getInt("Id_empleado");
                Date Fecha_venta = rs.getDate("Fecha_venta");
                int Id_armazon = rs.getInt("Id_armazon");
                int Id_formapago = rs.getInt("Id_formapago");
                int Monto_total = rs.getInt("Monto_total");
                
                DataModelVerVentas dataModelVentas = new DataModelVerVentas(Id_venta, Id_empleado,Fecha_venta, Id_armazon, Id_formapago, Monto_total);
                data.add(dataModelVentas);
            }
            TableViewVentas.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    


    @FXML
    private void BuscarBoton(ActionEvent event) {
         String textoBusqueda = BuscaVenta.getText().trim();
        // Realiza la búsqueda en la base de datos utilizando el texto de búsqueda
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ventas WHERE Id_venta LIKE ? OR Id_empleado LIKE ? OR Fecha_venta LIKE ?")) {

            stmt.setString(1, "%" + textoBusqueda + "%");
            stmt.setString(2, "%" + textoBusqueda + "%");
            stmt.setString(3, "%" + textoBusqueda + "%");
            

            ResultSet rs = stmt.executeQuery();

            // Limpiar la lista actual antes de agregar los nuevos resultados de la búsqueda
            data.clear();

            boolean resultadosEncontrados = false;

            while (rs.next()) {
                int Id_venta = rs.getInt("Id_venta");
                //int Id_cliente = rs.getInt("Id_cliente");
                int Id_empleado = rs.getInt("Id_empleado");
                Date Fecha_venta = rs.getDate("Fecha_venta");
                int Id_armazon = rs.getInt("Id_armazon");
                int Id_formapago = rs.getInt("Id_formapago");
                int Monto_total = rs.getInt("Monto_total");
                
                DataModelVerVentas dataModelVentas = new DataModelVerVentas(Id_venta, Id_empleado,Fecha_venta, Id_armazon, Id_formapago, Monto_total);
                data.add(dataModelVentas);

                resultadosEncontrados = true;
            }

            if (!resultadosEncontrados) {
                // Mostrar mensaje de error de producto inexistente
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de búsqueda");
                alert.setHeaderText(null);
                alert.setContentText("No se encontraron productos que coincidan con la búsqueda.");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   

    @FXML
    private void Close(ActionEvent event) {
        Stage stage = (Stage) Cerrar.getScene().getWindow();
        stage.close();
    }
}
