/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package optiexpress.Ventas.Ticket;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import optiexpress.Ventas.VentaArma.Productos;

/**
 * FXML Controller class
 *
 * @author Migue
 */
public class TicketViewController implements Initializable {

    @FXML
    private Label labelFecha;
    @FXML
    private Label labelNombreUser;
    @FXML
    private TableView<Productos> tableViewTicket;
    @FXML
    private TableColumn<Productos, Integer> IDArmaColumn;
    @FXML
    private TableColumn<Productos, String> MarcaColumn;
    @FXML
    private TableColumn<Productos, String> modeloColumn;
    @FXML
    private TableColumn<Productos, Integer> precioColumn;
    @FXML
    private Label labelPrecio;
    @FXML
    private ChoiceBox<String> FormasPago;
    
    LocalDate fechaActual = LocalDate.now();
    String fecha = fechaActual + "";
   
    private ObservableList<Productos> data2;
   
    public String formaPago;
    int idC = 0;
    int idFormaDePago = 0;
    java.sql.Date fechaDeCompra;
    
    private String [] fp = {"Efectivo","Tarjeta de Debito","Tarjeta de Credito"};
    
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
        IDArmaColumn.setCellValueFactory(new PropertyValueFactory<>("Id_armazon"));
        MarcaColumn.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        modeloColumn.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        
        FormasPago.getItems().addAll(fp); 
        empleadoId(obtenerIdEmpleado());
        //ingresarVenta(idC, formaPago, fechaDeCompra);
    } 
    /*
    public void ingresarVenta(int idC, int Id_cliente, Date fechaDeCompra, int Id_armazon, int Id_formapago){
        try {
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement psInsert = conn.prepareStatement("INSERT INTO ventas (Id_empleado, Id_cliente, Fecha_venta, Id_armazon, Id_formapago) VALUES (?, 201, ?, 102, ?)");
            psInsert.setInt(1, idC);
            psInsert.setInt(2, Id_cliente);
            psInsert.setDate(3, fechaDeCompra);
            psInsert.setInt(4, Id_armazon);
            psInsert.setInt(5, Id_formapago);
            
            psInsert.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }
    */
    public int obtenerIdEmpleado() {
        Random random = new Random();
        int numero = random.nextInt(2) + 401;
        return numero;
    } 
    
    public void empleadoId(int idP) {
   
        try {
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement stmt = conn.prepareStatement("SELECT NomEmpleado FROM empleados where Id_empleado = ?");
            stmt.setInt(1, idP);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                labelNombreUser.setText(rs.getString("NomEmpleado"));
                labelFecha.setText(fecha);
                
                // AGREGA LA VENTA  LA TABLA VENTAS CON SUS RESPECTIVOS DATOS
                idC = idP;
                fechaDeCompra = new java.sql.Date(System.currentTimeMillis());
            } 
            System.out.println(idC + "," + fechaActual);

        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }
    
    public void actualizarTicket(List<Productos> carrito) {
        data2 = FXCollections.observableArrayList(carrito);
        tableViewTicket.setItems(data2); 
    }
    
    public void setTotal(String total) {
        labelPrecio.setText(total);
    }  
}
