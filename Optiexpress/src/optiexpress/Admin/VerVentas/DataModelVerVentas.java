/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package optiexpress.Admin.VerVentas;
import java.sql.Date;
import javafx.collections.ObservableList;
/**
 *
 * @author Migue
 */
public class DataModelVerVentas {
    private int Id_venta;
   // private int Id_cliente;
    private int Id_empleado;
    private Date Fecha_venta;
    private int Id_armazon;
    private int Id_formapago;
    private int Monto_total;

    public DataModelVerVentas(int Id_venta, int Id_empleado, Date Fecha_venta, int Id_armazon, int Id_formapago, int Monto_total) {
        this.Id_venta = Id_venta;
        /*this.Id_cliente = Id_cliente;*/
        this.Id_empleado = Id_empleado;
        this.Fecha_venta = Fecha_venta;
        this.Id_armazon = Id_armazon;
        this.Id_formapago = Id_formapago;
        this.Monto_total = Monto_total;
    }

    public int getId_venta() {
        return Id_venta;
    }

    public void setId_venta(int Id_venta) {
        this.Id_venta = Id_venta;
    }

    public int getId_empleado() {
        return Id_empleado;
    }

    public void setId_empleado(int Id_empleado) {
        this.Id_empleado = Id_empleado;
    }

    public Date getFecha_venta() {
        return Fecha_venta;
    }

    public void setFecha_venta(Date Fecha_venta) {
        this.Fecha_venta = Fecha_venta;
    }

    public int getId_armazon() {
        return Id_armazon;
    }

    public void setId_armazon(int Id_armazon) {
        this.Id_armazon = Id_armazon;
    }

    public int getId_formapago() {
        return Id_formapago;
    }

    public void setId_formapago(int Id_formapago) {
        this.Id_formapago = Id_formapago;
    }

    public int getMonto_total() {
        return Monto_total;
    }

    public void setMonto_total(int Monto_total) {
        this.Monto_total = Monto_total;
    }
    
}
