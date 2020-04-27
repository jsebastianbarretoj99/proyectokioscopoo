/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import enumaration.Denominacion;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author vale-
 */
public class Prestamo {
    
    private LocalDate fechaHora;
    private int numero;
    private HashMap < Denominacion, Billete > pagoBillete; 
    private HashMap < String, Libro> librosEnPrestamo;

    public Prestamo() {
        this.pagoBillete = new HashMap();
        this.librosEnPrestamo = new HashMap();
    }

    public Prestamo(LocalDate fechaHora, int numero) {
        this.fechaHora = fechaHora;
        this.numero = numero;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDate fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public HashMap<Denominacion, Billete> getPagoBillete() {
        return pagoBillete;
    }

    public void setPagoBillete(HashMap<Denominacion, Billete> pagoBillete) {
        this.pagoBillete = pagoBillete;
    }

    public HashMap<String, Libro> getLibrosEnPrestamo() {
        return librosEnPrestamo;
    }

    public void setLibrosEnPrestamo(HashMap<String, Libro> librosEnPrestamo) {
        this.librosEnPrestamo = librosEnPrestamo;
    }
}
