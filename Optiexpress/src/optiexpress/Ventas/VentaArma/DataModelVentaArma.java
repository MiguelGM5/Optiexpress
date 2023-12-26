/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package optiexpress.Ventas.VentaArma;

/**
 *
 * @author Migue
 */
public class DataModelVentaArma {
    private int Id_Armazon;
    private String Marca;
    private String Modelo;
    private String Material;
    private int Cantidad;
    private int Precio;

    public DataModelVentaArma(int Id_Armazon, String Marca, String Modelo, String Material, int Cantidad, int Precio) {
        this.Id_Armazon = Id_Armazon;
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.Material = Material;
        this.Cantidad = Cantidad;
        this.Precio = Precio;
    }

    public int getId_Armazon() {
        return Id_Armazon;
    }

    public void setId_Armazon(int Id_Armazon) {
        this.Id_Armazon = Id_Armazon;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String Material) {
        this.Material = Material;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }
    
}
