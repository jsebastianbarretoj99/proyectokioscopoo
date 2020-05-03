package entity;

import java.util.HashMap;

/**
 *
 * @author Juan Sebastian Barreto Jimenez Juan Camilo Devia Bastos Nicolas
 * Javier Ramirez Beltran Valentina López Suárez 
 * Mayo 04 2020
 */
public abstract class Descuento {
    private double porcentaje;

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Descuento(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Descuento() {
    }
    
    public abstract double calcularTotal(double valor, HashMap <Integer,Libro> saga);
    
    
}
