/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package optiexpress.Ventas.VentaArma;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import optiexpress.Admin.VerVentas.DataModelVerVentas;
import static optiexpress.Admin.VerVentas.VerVentasVistaController.contraseña;
import static optiexpress.Admin.VerVentas.VerVentasVistaController.url;
import static optiexpress.Admin.VerVentas.VerVentasVistaController.usuario;
import optiexpress.Ventas.Ticket.TicketViewController;

/**
 * FXML Controller class
 *
 * @author Migue
 */
public class VentaArmaVistaController implements Initializable {

    @FXML
    private TableView<DataModelVentaArma> ProductosTableview;
    private ObservableList<DataModelVentaArma> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<DataModelVentaArma, Integer> ColIdArma;
    @FXML
    private TableColumn<DataModelVentaArma, String> ColMarca;
    @FXML
    private TableColumn<DataModelVentaArma, String> ColModelo;
    @FXML
    private TableColumn<DataModelVentaArma, String> ColMaterial;
    @FXML
    private TableColumn<DataModelVentaArma, Integer> ColCantidad;
    @FXML
    private TableColumn<DataModelVentaArma, Integer> ColPrecio;
    @FXML
    private TextField BuscaArma;
    @FXML
    private Button BuscaArmaBtn;
    @FXML
    private TableView<Productos> CarritoTableview;
    private ObservableList<Productos> data2 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Productos, String> ColMarcaCarrito;
    @FXML
    private TableColumn<Productos, String> ColModeloCarrito;
    @FXML
    private TableColumn<Productos, Integer> ColPrecioCarrito;
    @FXML
    private TextField PrecioTotaltxt;
    // Varibles de conexion
    public static final String url = "jdbc:mysql://localhost:3306/optiexpress";
    public static final String usuario = "root";
    public static final String contraseña = "washimido15";
    ResultSet rs, rs2;
    @FXML
    private Button GenVenta;
    @FXML
    private Button SeleccionarArmazon;
    @FXML
    private Button Vacia;
    @FXML
    private Button Update;
    private Button Logout;
    @FXML
    private Button Cerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ColIdArma.setCellValueFactory(new PropertyValueFactory<>("Id_armazon"));
        ColMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        ColModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        ColMaterial.setCellValueFactory(new PropertyValueFactory<>("Material"));
        ColCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        ColPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        
        ProductosTableview.setItems(data);
        loadDataFromDatabase();

        ColMarcaCarrito.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        ColModeloCarrito.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        ColPrecioCarrito.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        CarritoTableview.setItems(data2);
    }

    private void loadDataFromDatabase() {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM armazones"); 
            
            ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                int Id_armazon = rs.getInt("Id_armazon");
                String Marca = rs.getString("Marca");
                String Modelo = rs.getString("Modelo");
                String Material = rs.getString("Material");
                int Cantidad = rs.getInt("Cantidad");
                int Precio = rs.getInt("Precio");

                DataModelVentaArma dataModelVentaArmazon = new DataModelVentaArma(Id_armazon, Marca, Modelo, Material, Cantidad, Precio);
                data.add(dataModelVentaArmazon);
            }
            ProductosTableview.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BuscarArmazon(ActionEvent event) {
        String textoBusqueda = BuscaArma.getText().trim();
        // Realiza la búsqueda en la base de datos utilizando el texto de búsqueda
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña); 
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM armazones WHERE Id_armazon LIKE ? OR Marca LIKE ? OR Modelo LIKE ? OR Material LIKE ?")) {

            stmt.setString(1, "%" + textoBusqueda + "%");
            stmt.setString(2, "%" + textoBusqueda + "%");
            stmt.setString(3, "%" + textoBusqueda + "%");
            stmt.setString(4, "%" + textoBusqueda + "%");

            ResultSet rs = stmt.executeQuery();
            // Limpiar la lista actual antes de agregar los nuevos resultados de la búsqueda
            data.clear();

            boolean resultadosEncontrados = false;

            while (rs.next()) {
                int Id_armazon = rs.getInt("Id_armazon");
                String Marca = rs.getString("Marca");
                String Modelo = rs.getString("Modelo");
                String Material = rs.getString("Material");
                int Cantidad = rs.getInt("Cantidad");
                int Precio = rs.getInt("Precio");

                DataModelVentaArma dataModelbusca = new DataModelVentaArma(Id_armazon, Marca, Modelo, Material, Cantidad, Precio);
                data.add(dataModelbusca);

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
    private void SeleccionArma(ActionEvent event) {
        try {
            int cantidadProductos = 0;
            DataModelVentaArma DataModelventas = ProductosTableview.getSelectionModel().getSelectedItem();
            int Id_armazon = DataModelventas.getId_Armazon();
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);

            // consulta para saber si hay existencias
            PreparedStatement stmt2 = conn.prepareStatement("select Cantidad from armazones where Id_armazon = ?");
            stmt2.setInt(1, Id_armazon);
            rs2 = stmt2.executeQuery();

            if (rs2.next()) {
                cantidadProductos = Integer.parseInt(rs2.getString("Cantidad"));
            }

            if (cantidadProductos >= 1) {
                // Consulta para agregar - mostrar los datos en el carrito
                PreparedStatement stmt = conn.prepareStatement("select * from armazones where Id_armazon = ?");
                stmt.setInt(1, Id_armazon);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    int IdArmazon = rs.getInt("Id_armazon");
                    String MarcaC = rs.getString("Marca");
                    String ModeloC = rs.getString("Modelo");
                    String MaterialC = rs.getString("Material");
                    int CantidadC = rs.getInt("Cantidad");
                    int PrecioC = rs.getInt("Precio");

                    Productos productoCarrito = new Productos(IdArmazon, MarcaC, ModeloC, MaterialC, CantidadC, PrecioC);
                    data2.add(productoCarrito);

                    System.out.println("Se obtuvieron los datos");

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("");
                    alert.setTitle("Error");
                    alert.setContentText("Error...");
                    alert.showAndWait();
                }
                realizaCompra(event);

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("NO CONTAMOS CON EXISTENCIAS DE ESTE PRODUCTO");
                alert.showAndWait();
            }

        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("");
            alert.setTitle("Error");
            alert.setContentText("NO SELECCIONASTE NINGUN PRODUCTO");
            alert.showAndWait();
        }
    }

    private void realizaCompra(ActionEvent event) {
        int total = 0;
        for (Productos elemento : data2) {
            total += elemento.getPrecio();
        }
        PrecioTotaltxt.setText("$ " + total + "");
    }

    @FXML
    private void GeneraVentas(ActionEvent event) {
        TicketViewController contarid = new TicketViewController();
        int resultado = 0;
        try {
            if (!data2.isEmpty()) {
                Iterator<Productos> iterator = data2.iterator();
                while (iterator.hasNext()) {
                    Productos producto = iterator.next();
                    int Id_armazon = producto.getId_armazon();
                    int Cantidad = producto.getCantidad();
                    Cantidad--;
                    try {
                        Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                        PreparedStatement ps = conn.prepareStatement("update armazones set Cantidad = ? where Id_armazon = ?");
                        ps.setInt(2, Id_armazon);
                        ps.setInt(1, Cantidad);
                        resultado = ps.executeUpdate();
                        conn.close();
                    } catch (Exception ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("");
                        alert.setTitle("Error");
                        alert.setContentText("ERROR DESCONOCIDO " + ex);
                        alert.showAndWait();
                    }
                } // while - for
                if (resultado > 0) {
                    Actualiza(event);
                    // Crear una instancia del controlador del ticket
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/optiexpress/Ventas/Ticket/TicketView.fxml"));
                    Parent root = loader.load();
                    TicketViewController ticketController = loader.getController();
                    // Pasar los datos del carrito al controlador del ticket
                    ticketController.actualizarTicket(data2);
                    // Calcular el total y pasarlo al controlador del ticket
                    int total = 0;
                    for (Productos elemento : data2) {
                        total += elemento.getPrecio();
                    }
                    ticketController.setTotal("$ " + total);

                    // Mostrar la ventana del ticket
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("");
                alert.setTitle("Error");
                alert.setContentText("NO HAY ARMAZONES SELECCIONADOS");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void VaciaCarrito(ActionEvent event) {
        data2.clear();
        realizaCompra(event);
    }

    @FXML
    private void Actualiza(ActionEvent event) {
        data.clear();
        loadDataFromDatabase();
    }

    @FXML
    private void Close(ActionEvent event) {
        System.out.println("Cierra Sesion");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesion");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro?");
        alert.showAndWait();
        
        Stage stage = (Stage) Cerrar.getScene().getWindow();
        stage.close();
    }
}
