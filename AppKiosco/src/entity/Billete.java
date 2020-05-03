package entity;

import enumaration.Denominacion;

/**
 *
 * @author Juan Sebastian Barreto Jimenez Juan Camilo Devia Bastos Nicolas
 * Javier Ramirez Beltran Valentina López Suárez 
 * Mayo 04 2020
 */
public class Billete {
    private int cantidad;
    private Denominacion denominacion;
    

    public Billete() {
    }

    public Billete(int cantidad, Denominacion denominacion) {
        this.cantidad = cantidad;
        this.denominacion = denominacion; 
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Denominacion getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(Denominacion denominacion) {
        this.denominacion = denominacion;
    }

    @Override
    public String toString() {
        return "Billete{" + "cantidad=" + cantidad + ", denominacion=" + denominacion + '}';
    }
    
    
   
}
