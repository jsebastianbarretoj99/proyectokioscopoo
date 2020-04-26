/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author vale-
 */
public class PaperBook extends Libro{
    
    private String ubicacion;
    private double precioPapeleria; 

    public PaperBook(String isbn, int unidadesDisponibles, double precioBase, String nombre, int numeroImagenes, int numeroVIdeos) {
        super(isbn, unidadesDisponibles, precioBase, nombre, numeroImagenes, numeroVIdeos);
    }

    public PaperBook(String ubicacion, double precioPapeleria, String isbn, int unidadesDisponibles, double precioBase, String nombre, int numeroImagenes, int numeroVIdeos) {
        super(isbn, unidadesDisponibles, precioBase, nombre, numeroImagenes, numeroVIdeos);
        this.ubicacion = ubicacion;
        this.precioPapeleria = precioPapeleria;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getPrecioPapeleria() {
        return precioPapeleria;
    }

    public void setPrecioPapeleria(double precioPapeleria) {
        this.precioPapeleria = precioPapeleria;
    }

    // punto 4 a IV 1 a 
    @Override
    public double precioTotal() {
        return super.getPrecioBase() + this.precioPapeleria;
    }
    
}
