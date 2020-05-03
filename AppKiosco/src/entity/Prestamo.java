package entity;

import enumaration.Denominacion;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author Juan Sebastian Barreto Jimenez Juan Camilo Devia Bastos Nicolas
 * Javier Ramirez Beltran Valentina López Suárez 
 * Mayo 04 2020
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
        this.pagoBillete = new HashMap();
        this.librosEnPrestamo = new HashMap();
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
