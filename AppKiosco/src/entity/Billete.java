/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import enumaration.Denominacion;

/**
 *
 * @author vale-
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
   
}
