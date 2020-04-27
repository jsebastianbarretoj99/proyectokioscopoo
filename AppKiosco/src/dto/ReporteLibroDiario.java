/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author vale-
 */
public class ReporteLibroDiario {
    private String tipo;
    private int cantidadPrestamo;
    private double preciPrestamo;

    public ReporteLibroDiario() {
    }

    public ReporteLibroDiario(String tipo, int cantidadPrestamo, double preciPrestamo) {
        this.tipo = tipo;
        this.cantidadPrestamo = cantidadPrestamo;
        this.preciPrestamo = preciPrestamo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidadPrestamo() {
        return cantidadPrestamo;
    }

    public void setCantidadPrestamo(int cantidadPrestamo) {
        this.cantidadPrestamo = cantidadPrestamo;
    }

    public double getPreciPrestamo() {
        return preciPrestamo;
    }

    public void setPreciPrestamo(double preciPrestamo) {
        this.preciPrestamo = preciPrestamo;
    }
    
    
}
