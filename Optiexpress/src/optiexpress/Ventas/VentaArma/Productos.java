/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package optiexpress.Ventas.VentaArma;

/**
 *
 * @author Migue
 */
public class Productos {
    private int Id_armazon;
    private String Marca;
    private String Modelo;
    private String Material;
    private int Cantidad;
    private int Precio;

    public Productos(int Id_armazon, String Marca, String Modelo, String Material, int Cantidad, int Precio) {
        this.Id_armazon = Id_armazon;
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.Material = Material;
        this.Cantidad = Cantidad;
        this.Precio = Precio;
    }

    public int getId_armazon() {
        return Id_armazon;
    }

    public void setId_armazon(int Id_armazon) {
        this.Id_armazon = Id_armazon;
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
